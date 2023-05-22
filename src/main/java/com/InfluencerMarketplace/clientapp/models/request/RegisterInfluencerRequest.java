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
public class RegisterInfluencerRequest {
    private Long id;
    private String fullname;
    private String email;
    private Long province;
    private String city;
    private String detailAddress;
    private LocalDate birthDate;
    private String username;
    private String instagram;
    private String youtube;
    private String tiktok;
    private String facebook;
}
