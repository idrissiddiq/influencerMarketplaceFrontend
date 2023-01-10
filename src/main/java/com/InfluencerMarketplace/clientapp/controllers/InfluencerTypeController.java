package com.InfluencerMarketplace.clientapp.controllers;

import com.InfluencerMarketplace.clientapp.models.Influencer;
import com.InfluencerMarketplace.clientapp.models.InfluencerType;
import com.InfluencerMarketplace.clientapp.models.response.ResponseListData;
import com.InfluencerMarketplace.clientapp.services.InfluencerTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/influencerType")
public class InfluencerTypeController {
    private InfluencerTypeService influencerTypeService;

    @Autowired
    public InfluencerTypeController(InfluencerTypeService influencerTypeService) {
        this.influencerTypeService = influencerTypeService;
    }

    @GetMapping("/getAll")
    public @ResponseBody
    ResponseListData<InfluencerType> findAll(){
        return influencerTypeService.findAll();
    }
}
