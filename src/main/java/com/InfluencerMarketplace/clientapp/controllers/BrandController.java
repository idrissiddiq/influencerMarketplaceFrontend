package com.InfluencerMarketplace.clientapp.controllers;

import com.InfluencerMarketplace.clientapp.models.Brand;
import com.InfluencerMarketplace.clientapp.models.request.EditBrandProfileRequest;
import com.InfluencerMarketplace.clientapp.models.response.ResponseData;
import com.InfluencerMarketplace.clientapp.models.response.ResponseMessage;
import com.InfluencerMarketplace.clientapp.services.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/brand")
public class BrandController {
    private BrandService brandService;

    @Autowired
    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping("/profile")
    public @ResponseBody
    ResponseData<Brand> getMyProfile(){
        return brandService.getMyProfileData();
    }

    @PutMapping("/profile")
    public @ResponseBody
    ResponseMessage<EditBrandProfileRequest> editMyProfile(@RequestBody EditBrandProfileRequest request) {
        return brandService.editMyProfile(request);
    }
}
