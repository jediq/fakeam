package com.jediq.fakeam;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.List;

@SpringBootApplication
public class FakeAmApplication {

    private static final Logger LOG = LoggerFactory.getLogger(FakeAmApplication.class);

    private static String usersFile;

    @Autowired
    private UserStore userStore;

	public static void main(String[] args) {
        if (args.length == 1) {
            usersFile = args[0];
        }
		SpringApplication.run(FakeAmApplication.class, args);
	}

    @PostConstruct
    public void postConstruct() throws IOException {
        if (usersFile != null) {
            ObjectMapper objectMapper = new ObjectMapper();
            TypeReference<List<User>> typeRef = new TypeReference<List<User>>() { };
            List <User> users = objectMapper.readValue(new File(usersFile), typeRef);
            userStore.setUsers(users);
        }
        userStore.populateUsers();
    }

}
