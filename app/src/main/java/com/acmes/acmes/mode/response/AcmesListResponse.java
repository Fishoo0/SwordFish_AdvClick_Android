package com.acmes.acmes.mode.response;

import java.io.Serializable;
import java.util.List;

/**
 * Created by fishyu on 2018/2/28.
 */

public class AcmesListResponse<T extends AcmesListResponse.Data> extends AcmesResponse<T> {

    public static class Data<T extends List> implements Serializable {

        public String current_offset;
        public boolean has_more;
        public int limit;
        public int total_videos;
        public T videos;

        public T getList() {
            return videos;
        }

        /**
         * Getting size of list
         *
         * @return
         */
        public int getListSize() {
            if (getList() == null) {
                return 0;
            }
            return getList().size();
        }

        public boolean hasMore() {
            return has_more;
        }

    }

    public boolean isEmpty() {
        if (getData() == null || getData().getListSize() <= 0) {
            return true;
        }
        return false;
    }

}