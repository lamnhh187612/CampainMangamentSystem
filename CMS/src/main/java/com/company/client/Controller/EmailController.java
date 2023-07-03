package com.company.client.Controller;

import com.company.client.Entity.Email;
import com.company.client.Model.EmailModel;
import com.company.client.Service.EmailService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {
    @Autowired
    EmailService emailService;

    @PostMapping("/create_email")
    public String createEmail(@RequestBody EmailModel emailModel, final HttpServletRequest request){
        Email email = emailService.createEmail(emailModel);
        return "new email added";
    }


}
