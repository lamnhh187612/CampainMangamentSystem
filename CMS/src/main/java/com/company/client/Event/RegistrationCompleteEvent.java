package com.company.client.Event;

import com.company.client.Entity.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
public class RegistrationCompleteEvent extends ApplicationEvent {

    private User user;
    private String applicationURL;
    public RegistrationCompleteEvent(User user,String applicationURL) {
        super(user);
        this.user=user;
        this.applicationURL=applicationURL;
    }
}
