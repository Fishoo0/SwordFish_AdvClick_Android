package acmes.swordfish.advclick.controler.main;

import android.os.Handler;
import android.os.Message;

import com.acmes.simpleandroid.mvc.ISimpleModeCallback;
import com.acmes.simpleandroid.mvc.model.SimpleRequest;
import com.acmes.simpleandroid.mvc.model.SimpleResponse;

import acmes.swordfish.advclick.AdvClickMode;
import acmes.swordfish.advclick.controler.login.IAccountManager;
import acmes.swordfish.advclick.controler.login.SharedPrefAccountManager;
import acmes.swordfish.advclick.mode.bean.BEarn;
import acmes.swordfish.advclick.mode.bean.BSearch;
import acmes.swordfish.advclick.mode.bean.BUser;
import acmes.swordfish.advclick.mode.request.AcmesRequest;
import acmes.swordfish.advclick.mode.request.ClickRequest;
import acmes.swordfish.advclick.mode.request.GetEarnRequest;
import acmes.swordfish.advclick.mode.request.GetUserRequest;
import acmes.swordfish.advclick.mode.request.RequestWithDrawRequest;
import acmes.swordfish.advclick.mode.request.SearchRequest;
import acmes.swordfish.advclick.mode.request.UploadEarnRequest;
import acmes.swordfish.advclick.mode.response.AcmesResponse;

/**
 * Created by fishyu on 2018/1/2.
 */

public class ClickMode extends AdvClickMode {

    private static ClickMode mLoginMode = new ClickMode();
    private IAccountManager mAccountManager = SharedPrefAccountManager.getInstance();

    public static ClickMode getInstance() {
        return mLoginMode;
    }

    private UploadEarnRequest mUploadEarnRequest;

    @Override
    public void onResponse(SimpleRequest request, SimpleResponse response) {
        // update user
        if (response.getData() instanceof BUser) {
            BEarn earn = getUser().mEarn;
            BUser user = (BUser) response.getData();
            user.mEarn = earn;
            mAccountManager.updateUser(user);
        }

        if (response.getData() instanceof BEarn) {
            if (mAccountManager.getCurrentUser() != null) {
                mAccountManager.getCurrentUser().mEarn = (BEarn) response.getData();
            }
        }


        if (request instanceof UploadEarnRequest) {
            mUploadEarnRequest = null;
            if (response.isSuccess()) {
                ((UploadEarnRequest) request).mSearch.mEarnUploaded += ((UploadEarnRequest) request).value;
                // upload the rest
                uploadEarn(((UploadEarnRequest) request).mSearch);
            }
        }
        super.onResponse(request, response);
    }

    @Override
    public void onRequestStart(SimpleRequest request) {
        super.onRequestStart(request);
        if (request instanceof UploadEarnRequest) {
            mUploadEarnRequest = (UploadEarnRequest) request;
        }
    }

    @Override
    protected void onFailure(SimpleRequest request, Throwable exception, ISimpleModeCallback callback) {
        super.onFailure(request, exception, callback);
        if (request instanceof UploadEarnRequest) {
            mUploadEarnRequest = null;
        }
    }

    /**
     * Getting current user.
     *
     * @return
     */
    public BUser getUser() {
        return mAccountManager.getCurrentUser();
    }


    /**
     * 每15秒点击一次搜索的广告，每次点击生成收益并计算进收入和余额中，每次点击的收入为0.02-0.06之间的随机值 其中0.05-0.06显示为高额广告
     *
     * @return
     */
    private class LocalFunctionServer extends Handler {

        public AcmesRequest mRequest;
        public AcmesResponse<BSearch> mResponse;


        public static final long FREQUENCY_SEARCH = 2 * 1000;
        public long mLastSearchTime;

        public static final long FREQUENCY_CLICK = 2 * 1000;
        public long mLastClickTime;

        public static final long FREQUENCY_EARN = 15 * 1000;
        public long mLastEarnTime;


        public LocalFunctionServer() {
            mResponse = new AcmesResponse<>();
            mResponse.code = 0;
            mResponse.data = new BSearch();
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (mRequest instanceof SearchRequest) {
                long current = System.currentTimeMillis();
                if (current - mLastSearchTime >= FREQUENCY_SEARCH) {
                    int delta = (int) (((float) (220.0 - 180.0) * Math.random()) + 180.0);
                    mResponse.getData().mSearchCount += delta;
                    mLastSearchTime = current;
                    onResponse(mRequest, mResponse);
                }
                sendEmptyMessageDelayed(0, 1000);
                return;
            } else if (mRequest instanceof ClickRequest) {
                if (mResponse.getData().mClickCount < mResponse.getData().mSearchCount) {
                    long current = System.currentTimeMillis();
                    if (current - mLastClickTime >= FREQUENCY_CLICK) {
                        int delta = (int) (((float) (10.0 - 5.0) * Math.random()) + 50.0);
                        mResponse.getData().mClickCount += delta;
                        if (mResponse.getData().mClickCount > mResponse.getData().mSearchCount) {
                            mResponse.getData().mClickCount = mResponse.getData().mSearchCount;
                        }
                        mLastClickTime = current;

                        if (current - mLastEarnTime >= FREQUENCY_EARN) {
                            float deltaFloat = (float) (((float) (0.06 - 0.02) * Math.random()) + 0.02);
                            mResponse.getData().mLastEarn = deltaFloat;
                            mResponse.getData().mEarn += deltaFloat;
                            mLastEarnTime = current;

                            uploadEarn(mResponse.getData());
                        }
                        onResponse(mRequest, mResponse);
                    }
                    sendEmptyMessageDelayed(0, 1000);
                    return;
                } else {
                    AcmesResponse failure = new AcmesResponse();
                    failure.code = 100;
                    failure.message = "已达点击上限,请重新开始搜索。";
                    onResponse(mRequest, failure);
                }
            }
            stop();
        }

        public void stop() {
            mRequest = null;
            removeSimpleCallback(null);
        }

        public void start(AcmesRequest request) {
            stop();
            mRequest = request;
            sendEmptyMessage(0);
        }
    }

    private LocalFunctionServer mFunctionServer = new LocalFunctionServer();


    private boolean checkMainFunction(final SimpleRequest request) {

        if (!getUser().isFullFunction()) {
            onRequestStart(request);
            getUIHandler().post(new Runnable() {
                @Override
                public void run() {
                    AcmesResponse response = new AcmesResponse();
                    response.code = 100;
                    response.message = "无权限操作，请联系管理员!";
                    onResponse(request, response);
                }
            });
            return false;
        }

        return true;
    }


    public void startSearch() {
        AcmesRequest request = new SearchRequest();
        if (!checkMainFunction(request)) {
            return;
        }
        mFunctionServer.start(new SearchRequest());
    }

    public void stopSearch() {
        mFunctionServer.stop();
    }


    public void startClick() {
        AcmesRequest request = new ClickRequest();
        if (!checkMainFunction(request)) {
            return;
        }
        mFunctionServer.start(request);
    }

    public void stopClick() {
        mFunctionServer.stop();
    }


    public void refreshUser() {
        GetUserRequest request = new GetUserRequest(mAccountManager.getCurrentUser().mUserId);
        performRequest(request);
    }


    public void uploadEarn(BSearch search) {
        if (mUploadEarnRequest == null) {
            if (search.mEarnUploaded < search.mEarn) {
                UploadEarnRequest request = new UploadEarnRequest(
                        getUser().mUserId,
                        search
                );
                if (!checkMainFunction(request)) {
                    return;
                }
                performRequest(request);
            }
        }
    }

    public void getEarn() {
        GetEarnRequest request = new GetEarnRequest(SharedPrefAccountManager.getInstance().getCurrentUser());
        performRequest(request);
    }

    public void requestWithDraw() {
        RequestWithDrawRequest request =
                new RequestWithDrawRequest(
                        SharedPrefAccountManager.getInstance().getCurrentUser().mUserId);
        performRequest(request);
    }

}
