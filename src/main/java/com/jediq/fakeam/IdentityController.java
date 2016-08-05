package com.jediq.fakeam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.StringReader;
import java.util.Optional;
import java.util.Properties;

@RestController
public class IdentityController {

    @Autowired
    private UserStore users;

    /**
     *
     *

     FAILURE CURL :
     curl --request POST \
     --header "Content-Type: application/json" \
     --data "tokenid=INVALID" \
     "localhost:8084/openam/identity/isTokenValid"

     SUCCESS CURL:
     Substitute INVALID for valid token

     *
     */
    @RequestMapping("openam/identity/isTokenValid")
    public String isTokenValid(
        @RequestBody String requestBody) throws IOException {
        String tokenid = extractPropertyFromBody(requestBody, "tokenid");

        Optional<User> optionalUser = users.findUserByToken(tokenid);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            return "boolean=true";
        }

        return "boolean=false";
    }

    private String extractPropertyFromBody(String requestBody, String key) throws IOException {
        final Properties p = new Properties();
        p.load(new StringReader(requestBody));
        return p.getProperty(key);
    }


}
