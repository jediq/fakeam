package com.jediq.fakeam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.UUID;

@RestController
public class AuthenticateController {

    @Autowired
    private UserStore users;

    /**
     *

     FAILURE CURL :
     curl --request POST \
     --header "X-OpenAM-Username: demo" \
     --header "X-OpenAM-Password: changeit" \
     --header "Content-Type: application/json" \
     --data "{}" \
     "localhost:8084/json/authenticate"

     SUCCESS CURL :
     curl --request POST \
     --header "X-OpenAM-Username:user1" \
     --header "X-OpenAM-Password:password1" \
     --header "Content-Type: application/json" \
     --data "{}" \
     "localhost:8084/json/authenticate"

     *
     * @param username
     * @param password
     * @return
     */
    @RequestMapping("json/authenticate")
    public ResponseEntity<AuthenticateResponse> authenticate(
            @RequestHeader("X-OpenAM-Username") String username,
            @RequestHeader("X-OpenAM-Password") String password) {

        Optional<User> optionalUser = users.findUser(username);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (user.getPassword().equals(password)) {
                AuthenticateResponse authenticateResponse = new AuthenticateResponse();
                user.setToken(UUID.randomUUID().toString());
                authenticateResponse.setTokenId(optionalUser.get().getToken());
                authenticateResponse.setSuccessUrl("/successfulAuthentication");
                return new ResponseEntity<>(authenticateResponse, HttpStatus.OK);
            }
        }

        AuthenticateResponse failureResponse = new AuthenticateResponse();
        failureResponse.setErrorMessage("Failed login");
        failureResponse.setFailureUrl("/failedAuthentication");
        return new ResponseEntity<>(failureResponse, HttpStatus.FORBIDDEN);
    }


}
