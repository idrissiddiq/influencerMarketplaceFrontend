/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.InfluencerMarketplace.clientapp.config;

import com.InfluencerMarketplace.clientapp.utils.GetAuthContext;

import java.io.IOException;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

/**
 *
 * @author ASUS
 */
public class RestTemplateRequestHeaderInterceptor implements ClientHttpRequestInterceptor{

    @Override
    public ClientHttpResponse intercept(HttpRequest request, 
            byte[] body, 
            ClientHttpRequestExecution execution) 
            throws IOException {
       if (!request.getURI().getPath().equals("/api/login/influencer")
               && !request.getURI().getPath().equals("/api/login/brand")
               && !request.getURI().getPath().equals("/api/location")
               && !request.getURI().getPath().equals("/api/location/city")
               && !request.getURI().getPath().equals("/api/location/kecamatan/3402")
               && !request.getURI().getPath().equals("/api/location/kelurahan/3402060")
               && !request.getURI().getPath().equals("/api/register/influencer/forgot")
               && !request.getURI().getPath().equals("/api/register/brand/forgot")
               && !request.getURI().getPath().equals("/api/register/influencer")
               && !request.getURI().getPath().equals("/api/register/brand")
               && !request.getURI().getPath().equals("/api/influencer/findAllSortByRate")
               && !request.getURI().getPath().equals("/api/influencer/findAgeSortByRate")
               && !request.getURI().getPath().equals("/api/influencer/findAllInfluencer")
               && !request.getURI().getPath().equals("/api/campaign/1")
               && !request.getURI().getPath().equals("/api/campaign/open")
               && !request.getURI().getPath().equals("/api/influencerType")
               && !request.getURI().getPath().equals("/api/influencer/profile/photo/**")) {
            request.getHeaders().add("Authorization", "Basic " + GetAuthContext
                    .getAuthorization()
                    .getCredentials().toString());
        }

        ClientHttpResponse response = execution.execute(request, body);

        return response;
    }
}
