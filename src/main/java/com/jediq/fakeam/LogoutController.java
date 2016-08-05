package com.jediq.fakeam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class LogoutController {

    @Autowired
    private UserStore users;

    /**
     *
     *

     SUCCESS CURL :
     curl --request POST \
     --header "iplanetDirectoryPro:invalidtoken" \
     --header "Content-Type: application/json" \
     --data "{}" \
     "localhost:8084/json/sessions?_action=logout"

     *
     * @param action
     * @param token
     * @return
     */
    @RequestMapping("json/sessions")
    public ResponseEntity<LogoutResponse> logout(
            @RequestParam("_action") String action,
            @RequestHeader("iplanetDirectoryPro") String token) {

        if (action.equals("logout")) {
            Optional<User> optionalUser = users.findUserByToken(token);

            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                user.setToken(null);

                LogoutResponse logoutResponse = new LogoutResponse();
                logoutResponse.setResult("Successfully logged out");
                return new ResponseEntity<>(logoutResponse, HttpStatus.OK);
            }
            LogoutResponse logoutResponse = new LogoutResponse();
            logoutResponse.setResult("Not a valid session token");
            return new ResponseEntity<>(logoutResponse, HttpStatus.FORBIDDEN);

        }

        LogoutResponse logoutResponse = new LogoutResponse();
        logoutResponse.setResult("Action is not logout");
        return new ResponseEntity<>(logoutResponse, HttpStatus.METHOD_NOT_ALLOWED);
    }


}
