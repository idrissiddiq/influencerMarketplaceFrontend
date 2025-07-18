package com.InfluencerMarketplace.clientapp.controllers;

import java.util.Set;

import com.InfluencerMarketplace.clientapp.services.*;
import com.InfluencerMarketplace.clientapp.utils.GetAuthContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@EnableAutoConfiguration
@ComponentScan
@Controller
//@RequestMapping
public class DashboardController {
    private InfluencerService influencerService;
    private CampaignService campaignService;
    private BrandService brandService;
    private InfluencerTypeService influencerTypeService;
    private CampaignStatusService campaignStatusService;

    @Autowired
    public DashboardController(InfluencerService influencerService, CampaignService campaignService, BrandService brandService,
                               InfluencerTypeService influencerTypeService, CampaignStatusService campaignStatusService) {
        this.influencerService = influencerService;
        this.campaignService = campaignService;
        this.brandService = brandService;
        this.influencerTypeService = influencerTypeService;
        this.campaignStatusService = campaignStatusService;
    }

    @GetMapping
    public String index(Model model) {
        Set<String> roles = GetAuthContext.getAuthorityDetail(GetAuthContext.getAuthorization());
        if (roles.contains("ROLE_ADMIN")) {
            return "dashboard_admin";
        }
        if (roles.contains("ROLE_BRAND")){
            model.addAttribute("influencers", influencerService.findAllInfluencer());
            return "Brand/homeNew";
        }
        if (roles.contains("ROLE_INFLUENCER")){
            model.addAttribute("influencers", influencerService.findAllInfluencer());
            return "Influencer/index";
        }
        model.addAttribute("influencers", influencerService.findAllInfluencer());
        model.addAttribute("ages", influencerService.findAge());
//        model.addAttribute("photo", influencerService.getProfilePhotoById(id));
        return "Anonym/home";
    }

    @GetMapping("/campaign")
    public String indexCampaign(Model model) {
        Set<String> roles = GetAuthContext.getAuthorityDetail(GetAuthContext.getAuthorization());
        if (roles.contains("ROLE_ADMIN")) {
            return "dashboard_admin";
        }
        if (roles.contains("ROLE_BRAND")){
            model.addAttribute("campaigns", campaignService.findAllOpenCampaign());
            return "Brand/campaignNew";
        }
        if (roles.contains("ROLE_INFLUENCER")){
            model.addAttribute("campaigns", campaignService.findAllOpenCampaign());
            return "Influencer/campaignNew";
        }
        model.addAttribute("campaigns", campaignService.findAllOpenCampaign());
        return "Anonym/campaign";
    }

    @GetMapping("/campaign/me")
    public String indexMyCampaign(Model model) {
        Set<String> roles = GetAuthContext.getAuthorityDetail(GetAuthContext.getAuthorization());
        if (roles.contains("ROLE_ADMIN")) {
            return "dashboard_admin";
        }
        if (roles.contains("ROLE_BRAND")){
            model.addAttribute("status", campaignStatusService.findAll());
            return "Brand/myCampaignNew";
        }
        return "Anonym/listAllCampaign";
    }

    @GetMapping("/contract/{id}")
    public String indexMyDetailContract() {
        Set<String> roles = GetAuthContext.getAuthorityDetail(GetAuthContext.getAuthorization());
        if (roles.contains("ROLE_ADMIN")) {
            return "dashboard_admin";
        }
        if (roles.contains("ROLE_BRAND")){
            return "Brand/myContract";
        }
        return "Anonym/listAllCampaign";
    }

    @GetMapping("/contract")
    public String indexMyContract() {
        Set<String> roles = GetAuthContext.getAuthorityDetail(GetAuthContext.getAuthorization());
        if (roles.contains("ROLE_ADMIN")) {
            return "dashboard_admin";
        }
        if (roles.contains("ROLE_BRAND")){
            return "error";
        }
        if (roles.contains("ROLE_INFLUENCER")){
            return "Influencer/myContract";
        }
        return "Anonym/listAllCampaign";
    }

    @GetMapping("/profile")
    public String indexMyProfile(Model model) {
        Set<String> roles = GetAuthContext.getAuthorityDetail(GetAuthContext.getAuthorization());
        if (roles.contains("ROLE_ADMIN")) {
            return "dashboard_admin";
        }
        if (roles.contains("ROLE_BRAND")){
            model.addAttribute("photo", brandService.getMyProfilePhotoPath());
            model.addAttribute("profiles", brandService.getMyProfileData());
            return "Brand/myProfile";
        }
        if (roles.contains("ROLE_INFLUENCER")){
            model.addAttribute("photo", influencerService.getMyProfilePhotoPath());
            model.addAttribute("profiles", influencerService.getMyProfileData());
            model.addAttribute("types", influencerService.getMyType());
            model.addAttribute("listTypes", influencerTypeService.findAll());
            return "Influencer/myProfile";
        }
        return "Anonym/listAllCampaign";
    }

    @GetMapping("/pdf")
    public String cobaPdf() {
        return "PdfGenerator";
    }

//    @RequestMapping(value = "/image/{image_id}", produces = MediaType.IMAGE_PNG_VALUE)
//    public ResponseEntity<byte[]> getImage(@PathVariable("image_id") Long imageId) throws IOException {
//
//        byte[] imageContent = System.IO.File.ReadAllBytes(HttpContext.Current.Server.MapPath(path));
//                , HttpMethod.GET,null, new ParameterizedTypeReference<ResponseListData<>>(){} );
//        final HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.IMAGE_PNG);
//        return new ResponseEntity<byte[]>(imageContent, headers, HttpStatus.OK);
//    }


}