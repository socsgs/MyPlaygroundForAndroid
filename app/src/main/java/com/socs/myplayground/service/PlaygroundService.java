package com.socs.myplayground.service;

import com.socs.myplayground.model.User;
import com.socs.myplayground.repository.IPlaygroundRepository;
import com.socs.myplayground.repository.PlaygroundRepository;

/**
 * Created by SocsDrive on 7/12/2014.
 */
public class PlaygroundService implements IPlaygroundService {
    IPlaygroundRepository playgroundRepository;

    public PlaygroundService() {
        this(new PlaygroundRepository());
    }

    public PlaygroundService(IPlaygroundRepository playgroundRepository) {
        this.playgroundRepository = playgroundRepository;
    }

    @Override
    public User getUser(long id) {
        return playgroundRepository.getUser(id);
    }

    @Override
    public User getUser(String emailAddress) {
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
