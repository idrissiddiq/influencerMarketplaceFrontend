/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.FinalProject.services;

import com.example.FinalProject.models.request.LoginRequest;
import com.example.FinalProject.models.response.LoginResponse;
import com.example.FinalProject.utils.GetAuthContext;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.stream.Collectors;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author ASUS
 */
@Service
public class LoginService {

    private RestTemplate restTemplate;

    @Value("${server.baseUrl}/login/influencer")
    private String url;

    @Value("${server.baseUrl}/login/brand")
    private String urlBrand;

    @Autowired
    public LoginService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public boolean login(LoginRequest request) {
        try {
            ResponseEntity<LoginResponse> response = restTemplate
                    .postForEntity(url, request, LoginResponse.class);
            
            if (response.getStatusCode() == HttpStatus.OK) {
                setAuthorization(request, response.getBody());
                
                return true;
            }
            return false;
            
        } catch (RestClientException e) {
            return false;
        }
        
    }

    public boolean loginBrand(LoginRequest request) {
        try {
            ResponseEntity<LoginResponse> response = restTemplate
                    .postForEntity(urlBrand, request, LoginResponse.class);

            if (response.getStatusCode() == HttpStatus.OK) {
                setAuthorization(request, response.getBody());

                return true;
            }
            return false;

        } catch (RestClientException e) {
            return false;
        }

    }

    private void setAuthorization(LoginRequest req, LoginResponse res) {
        UsernamePasswordAuthenticationToken auth
                = new UsernamePasswordAuthenticationToken(req.getUsername(),
                        createCredential(req), getAuthorities(res));

        GetAuthContext.setAuthorization(auth);
    }

    private Collection<GrantedAuthority> getAuthorities(LoginResponse res) {
        return res.getAuthorities()
                .stream()
                .map(auth -> new SimpleGrantedAuthority(auth))
                .collect(Collectors.toList());
    }

    private String createCredential(LoginRequest request) {
        String auth = request.getUsername() + ":" + request.getPassword();
        byte[] encodedAuth = Base64.encodeBase64(
                auth.getBytes(Charset.forName("US-ASCII")));
        String authHeader = new String(encodedAuth);

        return authHeader;
    }
    
    
}
