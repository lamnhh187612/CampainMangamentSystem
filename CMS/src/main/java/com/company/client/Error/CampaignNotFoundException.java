package com.company.client.Error;

public class CampaignNotFoundException extends Exception{
    public CampaignNotFoundException(String campaign_not_available) {
        super(campaign_not_available);
    }
}
