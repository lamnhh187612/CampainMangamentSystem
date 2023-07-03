package com.company.client.Event.Listener;

import com.company.client.Event.RegistrationCompleteEvent;
import com.company.client.Entity.User;
import com.company.client.Service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;

import org.springframework.stereotype.Component;

import java.util.UUID;


@Component
@Slf4j
public class RegistrationCompleteEventListener implements ApplicationListener<RegistrationCompleteEvent> {


    public RegistrationCompleteEventListener() {
        super();
        System.out.println("Registration Complete Event Listener is created!");
    }

    @Autowired
    private UserService userService;





    @Override
    public void onApplicationEvent(RegistrationCompleteEvent event) {
        System.out.println("Context started");
        //Create the Verification Token for the User with Link
        User user=event.getUser();
        String token = UUID.randomUUID().toString();
        userService.saveVerificationTokenForUser(token,user);
        //Send mail to user
        String url=event.getApplicationURL()+ "/verifyRegistration?token="+token;
        //sendVerificationEmail()
        log.info(" Click the link to verify your account : {}",url);
    }
}
