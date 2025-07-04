/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.InfluencerMarketplace.clientapp.models.response;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Sendy
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseListData<E> {
    private List<E> data;
}
