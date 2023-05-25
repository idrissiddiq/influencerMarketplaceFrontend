package com.InfluencerMarketplace.clientapp.controllers;

import com.InfluencerMarketplace.clientapp.models.Influencer;
import com.InfluencerMarketplace.clientapp.models.Location;
import com.InfluencerMarketplace.clientapp.models.response.ResponseData;
import com.InfluencerMarketplace.clientapp.models.response.ResponseListData;
import com.InfluencerMarketplace.clientapp.services.InfluencerLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/location")
public class InfluencerLocationController {
    private InfluencerLocationService influencerLocationService;

    @Autowired
    public InfluencerLocationController(InfluencerLocationService influencerLocationService) {
        this.influencerLocationService = influencerLocationService;
    }

    @GetMapping("/search/{name}")
    public @ResponseBody
    ResponseData<Location> getProvince(@PathVariable String name){
        return influencerLocationService.getProvince(name);
    }

    @GetMapping("/search/{id}/{name}")
    public @ResponseBody
    ResponseData<Location> getKab(@PathVariable Long id, String name){
        return influencerLocationService.getKab(id, name);
    }

    @GetMapping("/{id}")
    public @ResponseBody
    ResponseListData<Location> kecamatan(@PathVariable String id){
        return influencerLocationService.kabupaten(id);
    }
}
