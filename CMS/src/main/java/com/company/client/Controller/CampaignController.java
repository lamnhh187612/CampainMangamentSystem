package com.company.client.Controller;

import com.company.client.Entity.Campaign;
import com.company.client.Error.CampaignNotFoundException;
import com.company.client.Model.CampaignModel;
import com.company.client.Service.CampaignService;
import com.company.client.Service.EmailService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CampaignController {
    @Autowired
    CampaignService campaignService;

    @Autowired
    EmailService emailService;

    @GetMapping("/campaigns")
    public List<Campaign> fetchCampaign(){
        return campaignService.fetchCampaignList();
    }

    @GetMapping("/campaigns/{id}")
    public Campaign fetchCampaignById(@PathVariable("id") Long campaignId) throws CampaignNotFoundException {
        return campaignService.fetchCampaignById(campaignId);
    }

    @PostMapping("/create_campaign")
    public String createCampaign(@RequestBody CampaignModel campaignModel, final HttpServletRequest request){
        Campaign campaign = campaignService.createCampaign(campaignModel);
        return "new campaign added";
    }

    @DeleteMapping("/campaigns/{id}")
    public String deleteCampaignById(@PathVariable("id") Long campaignId){
        campaignService.deleteCampaignById(campaignId);
        return "Campaign deleted successfully !";
    }

    @PutMapping("/campaigns/{id}")
    public Campaign updateCampaign(@PathVariable("id") Long campaignId, @RequestBody CampaignModel campaignModel){
        return campaignService.updateCampaign(campaignId,campaignModel);
    }


}
