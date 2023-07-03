package com.company.client.Controller;

import com.company.client.Entity.Phone;
import com.company.client.Model.PhoneModel;
import com.company.client.Service.PhoneService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PhoneController {
    @Autowired
    PhoneService phoneService;

    @PostMapping("/create_phone")
    public String createPhone(@RequestBody PhoneModel phoneModel, final HttpServletRequest request){
        Phone phone = phoneService.createPhone(phoneModel);
        return "new phone added";
    }
}
