/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.FinalProject.config;

import com.example.FinalProject.utils.GetAuthContext;
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
        System.out.println(request.getURI().getPath());
       if (!request.getURI().getPath().equals("/api/login/influencer")
               && !request.getURI().getPath().equals("/api/login/brand")
               && !request.getURI().getPath().equals("/api/register/influencer/forgot")
               && !request.getURI().getPath().equals("/api/register/brand/forgot")
               && !request.getURI().getPath().equals("/api/register/influencer")
               && !request.getURI().getPath().equals("/api/register/brand")) {
            request.getHeaders().add("Authorization", "Basic " + GetAuthContext
                    .getAuthorization()
                    .getCredentials().toString());
        }

        ClientHttpResponse response = execution.execute(request, body);

        return response;
    }
}
