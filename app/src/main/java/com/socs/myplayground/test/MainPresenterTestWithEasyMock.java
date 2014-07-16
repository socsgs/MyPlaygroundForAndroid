package com.socs.myplayground.test;

import android.test.AndroidTestCase;

import com.socs.myplayground.app.IMainPresenter;
import com.socs.myplayground.app.IMainView;
import com.socs.myplayground.app.MainPresenter;
import com.socs.myplayground.model.User;
import com.socs.myplayground.service.IPlaygroundService;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;

/**
 * Created by SocsDrive on 7/13/2014.
 */
public class MainPresenterTestWithEasyMock extends AndroidTestCase {
    private User testUser;
    private IPlaygroundService service;
    private IMainView view;
    private IMainPresenter presenter;

    @Override
    public void setUp() throws Exception {
        super.setUp();

        testUser = new User();
        testUser.setId(1);
        testUser.setEmailAddress("test@test.com");
        testUser.setPassword("test");

        service = createMock(IPlaygroundService.class);
        expect(service.getUser(1)).andReturn(testUser);
        replay(service);

        view = createMock(IMainView.class);
        presenter = new MainPresenter(view, service);
    }

    public void testLoginSuccess() {
        //Arrange
        expect(view.getEmailAddress()).andReturn(testUser.getEmailAddress());
        expect(view.getPassword()).andReturn(testUser.getPassword());
        view.showLoginSuccess();
        replay(view);

        //Act
        presenter.loginClicked();

        //Assert
        verify(view);
    }

    public void testLoginFailure() {
        //Arrange
        expect(view.getEmailAddress()).andReturn("test@test.com");
        expect(view.getPassword()).andReturn("wrong");
        view.showLoginFailure();
        replay(view);

        //Act
        presenter.loginClicked();

        //Assert
        verify(view);
    }
}
