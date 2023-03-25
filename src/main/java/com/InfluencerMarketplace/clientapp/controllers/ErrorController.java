/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.InfluencerMarketplace.clientapp.controllers;

import com.InfluencerMarketplace.clientapp.utils.GetAuthContext;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Set;

/**
 *
 * @author ASUS
 */
@EnableAutoConfiguration
@ComponentScan
@Controller
public class ErrorController {
    @GetMapping("/error")
    public String error(Model model) {
        Set<String> roles = GetAuthContext.getAuthorityDetail(GetAuthContext.getAuthorization());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("role", authentication.getPrincipal().toString());
        if (roles.contains("ROLE_INFLUENCER")){
            return "error";
        } else{
            return "error";
        }

    }

}
