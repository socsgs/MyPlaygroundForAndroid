package com.socs.myplayground.ui;

import android.util.Log;

import com.socs.myplayground.model.User;
import com.socs.myplayground.service.IPlaygroundService;
import com.socs.myplayground.service.PlaygroundService;

/**
 * Created by SocsDrive on 7/11/2014.
 */
public class MainPresenter implements IMainPresenter {
    private static final String TAG = "MainPresenter";
    private IMainView view;
    private IPlaygroundService playgroundService;

    public MainPresenter(IMainView view) {
        this(view, new PlaygroundService(view.getApplicationContext()));
    }

    public MainPresenter(IMainView view, IPlaygroundService playgroundService) {
        this.view = view;
        this.playgroundService = playgroundService;
    }

    @Override
    public void loginClicked() {
        Log.d(TAG, "login clicked");
        String emailAddressFromView = view.getEmailAddress();
        String passwordFromView = view.getPassword();

        User currentUser = playgroundService.getUserByEmailAddress(emailAddressFromView);

        if(currentUser != null) {
            String emailAddressFromService = currentUser.getEmailAddress();
            String passwordFromService = currentUser.getPassword();

            if(emailAddressFromView.equals(emailAddressFromService) && passwordFromView.equals(passwordFromService)){
                Log.d(TAG, "Email address and the password matched");
                view.showLoginSuccess();
            }
            else {
                Log.d(TAG, "Email address and the password does not match");
                view.showLoginFailure();
            }
        }
        else {
            Log.d(TAG, "User not found");
            view.showUserNotFound();
        }

    }
}
