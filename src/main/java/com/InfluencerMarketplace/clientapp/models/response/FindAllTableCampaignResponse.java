package com.InfluencerMarketplace.clientapp.models.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FindAllTableCampaignResponse {

    private Long id;
    private String title;
    private String description;
    private String company;
    private String name;
    private Long budget;
    private Long quota;
    private Long filled;
    private String dos;
    private String dont;

}
