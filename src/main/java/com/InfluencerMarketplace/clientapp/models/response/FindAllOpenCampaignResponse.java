package com.InfluencerMarketplace.clientapp.models.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FindAllOpenCampaignResponse {
    private Long id;
    private String title;
    private String description;
    private String company;
    private String name;
    private Long startIg;
    private Long endIg;
    private Long startTiktok;
    private Long endTiktok;
    private Long startYt;
    private Long endYt;
}
