package com.jediq.fakeam;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserStore {

    private List<User> users = new ArrayList<>();

    public void populateUsers() {
        addUser("user1", "password1");
    }

    private void addUser(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        users.add(user);
    }

    public Optional<User> findUser(String username) {
        return users.stream().filter(u -> username.equals(u.getUsername())).findAny();
    }

    public Optional<User> findUserByToken(String token) {
        return users.stream().filter(u -> token.equals(u.getToken())).findAny();
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
