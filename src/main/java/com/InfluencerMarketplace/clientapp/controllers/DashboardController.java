package com.InfluencerMarketplace.clientapp.controllers;

import java.util.Set;

import com.InfluencerMarketplace.clientapp.services.*;
import com.InfluencerMarketplace.clientapp.utils.GetAuthContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private AllReportService allReportService;
    private NotificationService notificationService;

    @Autowired
    public DashboardController(InfluencerService influencerService, CampaignService campaignService, BrandService brandService,
                               InfluencerTypeService influencerTypeService, CampaignStatusService campaignStatusService,
                               AllReportService allReportService, NotificationService notificationService) {
        this.influencerService = influencerService;
        this.campaignService = campaignService;
        this.brandService = brandService;
        this.influencerTypeService = influencerTypeService;
        this.campaignStatusService = campaignStatusService;
        this.allReportService = allReportService;
        this.notificationService = notificationService;
    }

    @GetMapping
    public String index(Model model) {
        Set<String> roles = GetAuthContext.getAuthorityDetail(GetAuthContext.getAuthorization());
        if (roles.contains("ROLE_ADMIN")) {
            return "Admin/HomeAdmin";
        }
        if (roles.contains("ROLE_INFLUENCER")){
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            model.addAttribute("username", authentication.getName());
            model.addAttribute("campaigns", campaignService.findAllOpenCampaign());
            model.addAttribute("notificationAll", notificationService.findAllNotification());
            model.addAttribute("notifications", notificationService.findNotification());
            model.addAttribute("notificationRead", notificationService.findNotifRead());
            return "Influencer/homeNew";
        }
        model.addAttribute("campaigns", campaignService.findAllOpenCampaign());
        return "Anonym/home";
    }

    @GetMapping("/campaign")
    public String indexCampaign(Model model) {
        Set<String> roles = GetAuthContext.getAuthorityDetail(GetAuthContext.getAuthorization());
        if (roles.contains("ROLE_ADMIN")) {
            return "dashboard_admin";
        }
        if (roles.contains("ROLE_INFLUENCER")){
            model.addAttribute("notificationAll", notificationService.findAllNotification());
            model.addAttribute("notifications", notificationService.findNotification());
            return "Influencer/campaignNew";
        }
        return "Anonym/campaign";
    }

    @GetMapping("/report/{id}")
    public String indexReport(Model model, @PathVariable String id) {
        Set<String> roles = GetAuthContext.getAuthorityDetail(GetAuthContext.getAuthorization());
        if (roles.contains("ROLE_ADMIN")) {
            model.addAttribute("reports", allReportService.findAllReport(id));
            return "Admin/report";
        }
        if (roles.contains("ROLE_INFLUENCER")){
            return "dashbord_influencer";
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("role", authentication.getPrincipal().toString());
        return "error";
    }

    @GetMapping("/contract/{id}")
    public String indexMyDetailContract(Model model) {
        Set<String> roles = GetAuthContext.getAuthorityDetail(GetAuthContext.getAuthorization());
        if (roles.contains("ROLE_ADMIN")) {
            return "dashboard_admin";
        }
        if (roles.contains("ROLE_BRAND")){
            return "Brand/myContract";
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("role", authentication.getPrincipal().toString());
        return "error";
    }

    @GetMapping("/contract")
    public String indexMyContract(Model model) {
        Set<String> roles = GetAuthContext.getAuthorityDetail(GetAuthContext.getAuthorization());
        if (roles.contains("ROLE_ADMIN")) {
            return "dashboard_admin";
        }
        if (roles.contains("ROLE_INFLUENCER")){
            model.addAttribute("notificationAll", notificationService.findAllNotification());
            model.addAttribute("notifications", notificationService.findNotification());
            return "Influencer/contractNew";
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("role", authentication.getPrincipal().toString());
        return "error";
    }

    @GetMapping("/profile")
    public String indexMyProfile(Model model) {
        Set<String> roles = GetAuthContext.getAuthorityDetail(GetAuthContext.getAuthorization());
        if (roles.contains("ROLE_ADMIN")) {
            return "dashboard_admin";
        }
        if (roles.contains("ROLE_INFLUENCER")){
            model.addAttribute("photo", influencerService.getMyProfilePhotoPath());
            model.addAttribute("profiles", influencerService.getMyProfileData());
            model.addAttribute("types", influencerService.getMyType());
            model.addAttribute("listTypes", influencerTypeService.findAll());
            model.addAttribute("notificationAll", notificationService.findAllNotification());
            model.addAttribute("notifications", notificationService.findNotification());
            return "Influencer/profileNew";
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("role", authentication.getPrincipal().toString());
        return "error";
    }


//    @GetMapping("/pdf")
//    public String cobaPdf() {
//        return "PdfGenerator";
//    }

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