package com.company.client.Service.Impl;

import com.company.client.Entity.Phone;
import com.company.client.Model.PhoneModel;
import com.company.client.Repository.PhoneRepository;
import com.company.client.Service.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PhoneServiceImpl implements PhoneService {
    @Autowired
    PhoneRepository phoneRepository;

    @Override
    public Phone createPhone(PhoneModel phoneModel) {
        Phone phone=new Phone();
        phone.setName(phoneModel.getName());
        phone.setPhone(phone.getPhone());
        return phone;
    }
}
