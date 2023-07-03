package com.company.client.Service.Impl;

import com.company.client.Entity.Campaign;
import com.company.client.Entity.Email;
import com.company.client.Entity.Phone;
import com.company.client.Error.CampaignNotFoundException;
import com.company.client.Model.CampaignModel;
import com.company.client.Repository.CampaignRepository;
import com.company.client.Repository.EmailRepository;
import com.company.client.Repository.PhoneRepository;
import com.company.client.Service.CampaignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CampaignServiceImpl implements CampaignService {

    @Autowired
    CampaignRepository campaignRepository;

    @Autowired
    EmailRepository emailRepository;

    @Autowired
    PhoneRepository phoneRepository;

    @Override
    public Campaign createCampaign(CampaignModel campaignModel) {
        List<Email> emails = emailRepository.findAll();
        List<Phone> phones = phoneRepository.findAll();
        Campaign campaign= new Campaign();
        campaign.setName(campaignModel.getName());
        campaign.setDescription(campaignModel.getDescription());
        campaign.setEmails(emails);
        campaign.setPhones(phones);
        campaignRepository.save(campaign);
        return campaign;
    }

    @Override
    public List<Campaign> fetchCampaignList() {
        return campaignRepository.findAll();
    }

    @Override
    public Campaign fetchCampaignById(Long campaignId) throws CampaignNotFoundException {
        Optional<Campaign> campaign=campaignRepository.findById(campaignId);
        if(!campaign.isPresent()){
            throw new CampaignNotFoundException("Campaign not available");
        }
        return campaign.get();
    }

    @Override
    public void deleteCampaignById(Long campaignId) {
        campaignRepository.deleteById(campaignId);
    }

    @Override
    public Campaign updateCampaign(Long campaignId, CampaignModel campaignModel) {
        Campaign camDb = campaignRepository.findById(campaignId).get();
        if(Objects.nonNull(campaignModel.getName()) && !"".equalsIgnoreCase(campaignModel.getName())){
            camDb.setName(campaignModel.getName());
        }
        if(Objects.nonNull(campaignModel.getDescription()) && !"".equalsIgnoreCase(campaignModel.getDescription())){
            camDb.setDescription(campaignModel.getDescription());
        }
        return campaignRepository.save(camDb);
    }
}
