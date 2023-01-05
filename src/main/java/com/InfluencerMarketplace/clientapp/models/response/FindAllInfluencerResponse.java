package com.InfluencerMarketplace.clientapp.models.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FindAllInfluencerResponse {
    private String fullname;
    private String city;
    private Long rate;
    private Long er;
    private Long finalrate;
    private String username;
    private Long age;
    private String instagram;
    private String tiktok;
    private String youtube;
}
