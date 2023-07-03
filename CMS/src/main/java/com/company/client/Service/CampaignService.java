package com.company.client.Service;

import com.company.client.Entity.Campaign;
import com.company.client.Error.CampaignNotFoundException;
import com.company.client.Model.CampaignModel;

import java.util.List;

public interface CampaignService {
    Campaign createCampaign(CampaignModel campaignModel);

    List<Campaign> fetchCampaignList();

    Campaign fetchCampaignById(Long campaignId) throws CampaignNotFoundException;

    void deleteCampaignById(Long campaignId);

    Campaign updateCampaign(Long campaignId, CampaignModel campaignModel);
}
