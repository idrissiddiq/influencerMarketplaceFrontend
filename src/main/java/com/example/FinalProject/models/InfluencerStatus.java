package com.example.FinalProject.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InfluencerStatus {
    private Long id;
    private String name;
    private Influencer influencer;
}
