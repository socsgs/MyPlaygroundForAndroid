package com.socs.myplayground.test;

import android.test.InstrumentationTestCase;

import com.socs.myplayground.app.IMainPresenter;
import com.socs.myplayground.app.IMainView;
import com.socs.myplayground.app.MainPresenter;
import com.socs.myplayground.model.User;
import com.socs.myplayground.repository.IPlaygroundRepository;
import com.socs.myplayground.service.IPlaygroundService;
import com.socs.myplayground.service.PlaygroundService;

/**
 * Created by SocsDrive on 7/12/2014.
 */
public class MainPresenterTest extends InstrumentationTestCase {
    private final static String testUserEmailAddress = "test@test.com";
    private final static String testUserPassword = "test123";

    private IPlaygroundService playgroundService;
    private IMainView view;
    private IMainPresenter presenter;

    public MainPresenterTest() {
        playgroundService = new PlaygroundService(new IPlaygroundRepository() {
            @Override
            public User getUser(long id) {
                User expectedUser = new User();
                expectedUser.setEmailAddress(testUserEmailAddress);
                expectedUser.setPassword(testUserPassword);

                return expectedUser;
            }

            @Override
            public User getUser(String emailAddress) {
                return null;
            }

            @Override
            public void createUser(User user) {

            }

            @Override
            public void updateUser(User user) {

            }

            @Override
            public void deleteUser(User user) {

            }
        });
    }

    public void testLoginSuccess() throws Exception {
        final boolean showLoginSuccessExpected = true;
        final boolean showLoginFailureExpected = false;

        view = new IMainView() {
            @Override
            public String getEmailAddress() {
                return testUserEmailAddress;
            }

            @Override
            public String getPassword() {
                return testUserPassword;
            }

            @Override
            public void showLoginSuccess() {
                assertTrue(showLoginSuccessExpected);
            }

            @Override
            public void showLoginFailure() {
                assertTrue(showLoginFailureExpected);
            }

            @Override
            public void showUserNotFound() {

            }
        };
        presenter = new MainPresenter(view, playgroundService);

        presenter.loginClicked();
    }

    public void testLoginFailure() throws Exception {
        final boolean showLoginSuccessExpected = false;
        final boolean showLoginFailureExpected = true;

        view = new IMainView() {
            @Override
            public String getEmailAddress() {
                return "test";
            }

            @Override
            public String getPassword() {
                return "test";
            }

            @Override
            public void showLoginSuccess() {
                assertTrue(showLoginSuccessExpected);
            }

            @Override
            public void showLoginFailure() {
                assertTrue(showLoginFailureExpected);
            }

            @Override
            public void showUserNotFound() {

            }
        };
        presenter = new MainPresenter(view, playgroundService);

        presenter.loginClicked();
    }
}
