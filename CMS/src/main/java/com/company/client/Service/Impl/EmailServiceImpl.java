package com.company.client.Service.Impl;

import com.company.client.Entity.Email;
import com.company.client.Model.EmailModel;
import com.company.client.Repository.EmailRepository;
import com.company.client.Service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {
    @Autowired
    EmailRepository emailRepository;
    @Override
    public Email createEmail(EmailModel emailModel) {
        Email email=new Email();
        email.setName(emailModel.getName());
        email.setEmail(emailModel.getEmail());
        return email;
    }
}
