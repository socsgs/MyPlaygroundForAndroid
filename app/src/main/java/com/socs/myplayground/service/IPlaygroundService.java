package com.socs.myplayground.service;

import com.socs.myplayground.model.User;

/**
 * Created by SocsDrive on 7/12/2014.
 */
public interface IPlaygroundService {
    User getUser(long id);
    User getUser(String emailAddress);
    void createUser(User user);
    void updateUser(User user);
    void deleteUser(User user);
}
