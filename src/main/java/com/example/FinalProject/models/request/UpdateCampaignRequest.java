package com.example.FinalProject.models.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateCampaignRequest {
    private String title;
    private String description;
    private Long campaignStatus;
}
