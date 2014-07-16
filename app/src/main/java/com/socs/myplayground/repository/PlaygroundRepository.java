package com.socs.myplayground.repository;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.socs.myplayground.model.User;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by SocsDrive on 7/12/2014.
 */
public class PlaygroundRepository implements IPlaygroundRepository {
    private final static String TAG = "PlaygroundRepository";
    @Override
    public User getUser(long id) {
        List<User> allUsers = getAllUser();

        for (User user : allUsers) {
            if(user.getId() == id) {
                return user;
            }
        }

        return null;
    }

    @Override
    public User getUser(String emailAddress) {
        List<User> allUsers = getAllUser();

        for (User user : allUsers) {
            if(user.getEmailAddress().equals(emailAddress)) {
                return user;
            }
        }

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

    private List<User> getAllUser() {
        String allUsersJsonString  = "[{\"id\":1,\"name\":\"Test, User 1\",\"emailAddress\":\"test1@test.com\",\"password\":\"test123\"},{\"id\":2,\"name\":\"Test, User 2\",\"emailAddress\":\"test2@test.com\",\"password\":\"test123\"},{\"id\":3,\"name\":\"Test, User 3\",\"emailAddress\":\"test3@test.com\",\"password\":\"test123\"},{\"id\":4,\"name\":\"Test, User 4\",\"emailAddress\":\"test4@test.com\",\"password\":\"test123\"},{\"id\":5,\"name\":\"Test, User 5\",\"emailAddress\":\"test5@test.com\",\"password\":\"test123\"}]";
        Gson gson = new Gson();
        Type userListType = new TypeToken<List<User>>(){}.getType();
        List<User> allUsers = gson.fromJson(allUsersJsonString, userListType);

        return allUsers;

    }
}
