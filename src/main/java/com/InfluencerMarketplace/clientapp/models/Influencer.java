/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.InfluencerMarketplace.clientapp.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

/**
 *
 * @author ASUS
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Influencer {
    private Long id;
    private String fullname;
    private String email;
    private String birthDate;
    private String detailAddress;
    private String city;
    private String province;
    private String rate;
    private String er;
    private Job job;
    private User user;
    private String instagram;
    private String tiktok;
    private String youtube;
    private String facebook;
    private Set<InfluencerType> influenceTypes;
}
