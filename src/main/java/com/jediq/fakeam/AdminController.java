package com.jediq.fakeam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Arrays;

@RestController
public class AdminController {

    @Autowired
    private UserStore users;

    /**
     *

     SUCCESS CURL :
     curl --request POST \
     --header "Content-Type: application/json" \
     --data "[{\"username\":\"user1\", \"password\":\"pass1\"}, {\"username\":\"user2\", \"password\":\"pass2\"}]" \
     "localhost:8084/admin/load"

     *
     */
    @RequestMapping("admin/load")
    public String isTokenValid(
            @RequestBody User [] usersArray) throws IOException {
        users.setUsers(Arrays.asList(usersArray));
        return "";
    }

}
