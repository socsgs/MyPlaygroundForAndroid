package com.socs.myplayground.ui;

import android.content.Context;

/**
 * Created by SocsDrive on 7/11/2014.
 */
public interface IMainView {
    String getEmailAddress();
    String getPassword();

    void showLoginSuccess();
    void showLoginFailure();
    void showUserNotFound();

    Context getApplicationContext();
}
