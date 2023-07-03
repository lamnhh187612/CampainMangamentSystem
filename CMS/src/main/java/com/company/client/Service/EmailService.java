package com.company.client.Service;

import com.company.client.Entity.Email;
import com.company.client.Model.EmailModel;

public interface EmailService {
    Email createEmail(EmailModel emailModel);
}
