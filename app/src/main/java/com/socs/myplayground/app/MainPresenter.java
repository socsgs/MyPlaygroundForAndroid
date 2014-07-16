package com.socs.myplayground.app;

import android.util.Log;

import com.socs.myplayground.model.User;
import com.socs.myplayground.service.IPlaygroundService;
import com.socs.myplayground.service.PlaygroundService;

/**
 * Created by SocsDrive on 7/11/2014.
 */
public class MainPresenter implements IMainPresenter {
    private static final String TAG = "MainPresenter";
    private IMainView mainView;
    private IPlaygroundService playgroundService;

    public MainPresenter(IMainView mainView) {
        this(mainView, new PlaygroundService());
    }

    public MainPresenter(IMainView mainView, IPlaygroundService playgroundService) {
        this.mainView =  mainView;
        this.playgroundService = playgroundService;
    }

    @Override
    public void loginClicked() {
        Log.d(TAG, "login clicked");

        User currentUser = playgroundService.getUser(mainView.getEmailAddress());

        if(currentUser != null) {
            String emailAddress = currentUser.getEmailAddress();
            String password = currentUser.getPassword();

            if(mainView.getEmailAddress().equals(emailAddress) && mainView.getPassword().equals(password)){
                Log.d(TAG, "Email address and the password matched");
                mainView.showLoginSuccess();
            }
            else {
                Log.d(TAG, "Email address and the password does not match");
                mainView.showLoginFailure();
            }
        }
        else {
            Log.d(TAG, "User not found");
            mainView.showUserNotFound();
        }

    }
}
