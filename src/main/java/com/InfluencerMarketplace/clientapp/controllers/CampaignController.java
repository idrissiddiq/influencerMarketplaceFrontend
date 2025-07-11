package com.InfluencerMarketplace.clientapp.controllers;

import com.InfluencerMarketplace.clientapp.models.Campaign;
import com.InfluencerMarketplace.clientapp.models.request.CreateCampaignRequest;
import com.InfluencerMarketplace.clientapp.models.request.UpdateCampaignRequest;
import com.InfluencerMarketplace.clientapp.models.response.ResponseData;
import com.InfluencerMarketplace.clientapp.models.response.ResponseListData;
import com.InfluencerMarketplace.clientapp.models.response.ResponseMessage;
import com.InfluencerMarketplace.clientapp.services.CampaignService;
import com.InfluencerMarketplace.clientapp.utils.GetAuthContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/campaign")
public class CampaignController {
    private CampaignService campaignService;

    @Autowired
    public CampaignController(CampaignService campaignService) {
        this.campaignService = campaignService;
    }

    @GetMapping("/getOnlyOpen")
    public @ResponseBody
    ResponseListData<Campaign> findAll(){
        Authentication auth = GetAuthContext.getAuthorization();
        System.out.println("AUTH : " + auth.getPrincipal());
        System.out.println("AUTHORITIES : " + auth.getAuthorities());
        return campaignService.findAllOnlyOpen();
    }

    @GetMapping("/getOnlyMe")
    public @ResponseBody
    ResponseListData<Campaign> findMyCampaign(){
        return campaignService.findMyCampaign();
    }

    @GetMapping("/{id}")
    public @ResponseBody
    ResponseData<Campaign> findById(@PathVariable Long id) {
        return campaignService.findById(id);
    }

    @PostMapping("/create")
    public @ResponseBody ResponseMessage<CreateCampaignRequest> add(@RequestBody CreateCampaignRequest request) {
        System.out.println("CREATED");
        return  campaignService.create(request);
    }

    @PutMapping("/edit/{id}")
    public @ResponseBody ResponseMessage<UpdateCampaignRequest> update(@PathVariable("id") Long id, @RequestBody UpdateCampaignRequest request) {
        System.out.println("UPDATE");
        return campaignService.update(id, request);
    }
}
