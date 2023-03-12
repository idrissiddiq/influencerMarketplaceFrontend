package com.InfluencerMarketplace.clientapp.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Campaign {
    private Long id;
    private String title;
    private String description;
    private String budget;
    private CampaignStatus campaignStatus;
    private Brand brand;
    private Influencer influencer;
}

