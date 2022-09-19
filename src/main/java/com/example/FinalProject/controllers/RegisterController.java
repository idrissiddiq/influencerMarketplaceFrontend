package com.example.FinalProject.controllers;

import com.example.FinalProject.models.request.ForgotPasswordRequest;
import com.example.FinalProject.models.request.RegisterBrandRequest;
import com.example.FinalProject.models.request.RegisterInfluencerRequest;
import com.example.FinalProject.models.response.ResponseMessage;
import com.example.FinalProject.services.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/register")
public class RegisterController {
    private RegisterService registerService;

    @Autowired
    public RegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }

    @PutMapping("/influencer/forgot")
    public @ResponseBody
    ResponseMessage<ForgotPasswordRequest> forgotPassword(@RequestBody ForgotPasswordRequest request) {
        System.out.println("UPDATE");
        return registerService.forgotPassword(request);
    }

    @PutMapping("/brand/forgot")
    public @ResponseBody
    ResponseMessage<ForgotPasswordRequest> forgotPasswordBrand(@RequestBody ForgotPasswordRequest request) {
        System.out.println("UPDATE");
        return registerService.forgotPasswordBrand(request);
    }

    @PostMapping("/influencer")
    public @ResponseBody
    ResponseMessage<RegisterInfluencerRequest> registerInfluncer(@RequestBody RegisterInfluencerRequest request) {
        return registerService.registerInflunecer(request);
    }

    @PostMapping("/brand")
    public @ResponseBody
    ResponseMessage<RegisterBrandRequest> registerBrand(@RequestBody RegisterBrandRequest request) {
        return registerService.registerBrand(request);
    }
}
