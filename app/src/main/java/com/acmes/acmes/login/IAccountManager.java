package com.acmes.acmes.login;

import com.acmes.acmes.mode.bean.BUser;

/**
 * Created by fishyu on 2018/1/2.
 * <p>
 * <p>
 * Manage Account ,like reading/writing
 * <p>
 * Single-Instance is recommended pattern.
 */

public interface IAccountManager {


    /**
     * Getting current user
     *
     * @return
     */
    BUser getCurrentUser();


    /**
     * Store user to system
     *
     * @param user
     */
    void addUser(BUser user);


    /**
     * Remove user from system
     *
     * @param user
     */
    void removeUser(BUser user);


}
