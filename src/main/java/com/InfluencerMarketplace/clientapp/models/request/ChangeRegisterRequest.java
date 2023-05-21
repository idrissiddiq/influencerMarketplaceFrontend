package com.InfluencerMarketplace.clientapp.models.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChangeRegisterRequest {

    private String oldPassword;
    private String newPassword;
}
