package com.InfluencerMarketplace.clientapp.models.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EditInfluencerProfileRequest {
    private String fullname;
    private String email;
    private String city;
    private LocalDate birthDate;
    private Long province;
    private String detailAddress;
    private String instagram;
    private String tiktok;
    private String youtube;
    private String facebook;
}
