package com.InfluencerMarketplace.clientapp.models.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterBrandRequest {
    private Long id;

    private String fullname;

    private String email;
    private String username;
}
