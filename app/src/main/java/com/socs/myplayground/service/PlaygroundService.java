package com.socs.myplayground.service;

import android.content.Context;

import com.socs.myplayground.model.User;
import com.socs.myplayground.repository.IPlaygroundRepository;
import com.socs.myplayground.repository.PlaygroundRepositoryWithJsonData;
import com.socs.myplayground.repository.PlaygroundRepositoryWithSQLiteDB;

/**
 * Created by SocsDrive on 7/12/2014.
 */
public class PlaygroundService implements IPlaygroundService {
    IPlaygroundRepository playgroundRepository;

    public PlaygroundService(Context context) {
        this(new PlaygroundRepositoryWithSQLiteDB(context));
    }

    public PlaygroundService(IPlaygroundRepository playgroundRepository) {
        this.playgroundRepository = playgroundRepository;
    }

    @Override
    public User getUser(long id) {
        return playgroundRepository.getUser(id);
    }

    @Override
    public User getUserByEmailAddress(String emailAddress) {
        return playgroundRepository.getUser(emailAddress);
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
}
