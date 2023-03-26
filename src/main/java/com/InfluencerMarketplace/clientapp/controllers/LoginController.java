package com.InfluencerMarketplace.clientapp.controllers;

import com.InfluencerMarketplace.clientapp.services.InfluencerLocationService;
import com.InfluencerMarketplace.clientapp.services.InfluencerTypeService;
import com.InfluencerMarketplace.clientapp.services.LoginService;
import com.InfluencerMarketplace.clientapp.models.request.LoginRequest;
import com.InfluencerMarketplace.clientapp.utils.GetAuthContext;
import javax.validation.Valid;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {
  
    private LoginService loginService;
    private InfluencerTypeService influencerTypeService;
    private InfluencerLocationService influencerLocation;

    public LoginController(LoginService loginService, InfluencerTypeService influencerTypeService, InfluencerLocationService influencerLocation) {
        this.loginService = loginService;
        this.influencerTypeService = influencerTypeService;
        this.influencerLocation = influencerLocation;
    }

    @GetMapping("/login")
    public String login(LoginRequest loginRequest, Model model) {
        Authentication auth = GetAuthContext.getAuthorization();
         System.out.println("AUTH : " + auth.getPrincipal());
         System.out.println("AUTHORITIES : " + auth.getAuthorities());
        if(auth == null || auth instanceof AnonymousAuthenticationToken){
            model.addAttribute("listTypes", influencerTypeService.findAll());
            model.addAttribute("listProv", influencerLocation.provinsi());
            return "login";
        }
        return "redirect:/";
    }

    @PostMapping("/login")
    public String login(@Valid LoginRequest request, BindingResult result) {
       System.out.println(request);
        if (result.hasErrors()) {
            return "loginNew";
        }
        if(!loginService.login(request)){
            return "redirect:/login?error=true";
        }
        return "redirect:/";
    }

    @PostMapping("/login/brand")
    public String loginBrand(@Valid LoginRequest request, BindingResult result) {
        System.out.println(request);
        if (result.hasErrors()) {
            return "loginBrand";
        }
        if(!loginService.loginBrand(request)){
            return "redirect:/login?error=true";
        }
        return "redirect:/";
    }
    
     @PostMapping("/logout")
    public String logout() {
        GetAuthContext.setAuthorization(null);
        return "redirect:/login?logout=true";
    }

}
