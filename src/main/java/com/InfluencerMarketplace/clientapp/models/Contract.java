package com.InfluencerMarketplace.clientapp.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Contract {
    private Long id;
    private String startDate;
    private String endDate;
    private Long budget;
    private ContractStatus contractStatus;
    private Campaign campaign;
    private Influencer influencer;
    private Brand brand;
}
