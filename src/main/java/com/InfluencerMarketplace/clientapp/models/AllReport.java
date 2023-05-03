package com.InfluencerMarketplace.clientapp.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AllReport {
    private Long id;
    private String link;
    private Long commment;
    private Long like;
    private Long share;
    private Long save;
    private Long impression;
    private Long reach;
    private Long view;
    private String createBy;
    private String createDate;
    private Brand brand;


}
