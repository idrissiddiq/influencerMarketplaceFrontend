package com.example.FinalProject.controllers;

import java.io.IOException;
import java.util.Set;

import com.example.FinalProject.models.Influencer;
import com.example.FinalProject.models.response.ResponseListData;
import com.example.FinalProject.utils.GetAuthContext;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import java.io.File;

@EnableAutoConfiguration
@ComponentScan
@Controller
//@RequestMapping
public class DashboardController {
    @GetMapping
    public String index() {
        Set<String> roles = GetAuthContext.getAuthorityDetail(GetAuthContext.getAuthorization());
        if (roles.contains("ROLE_ADMIN")) {
            return "dashboard_admin";
        }
        if (roles.contains("ROLE_BRAND")){
            return "dashboard/brand";
        }
        return "dashboard/home";
    }

    @GetMapping("/campaign")
    public String indexCampaign() {
//        Set<String> roles = GetAuthContext.getAuthorityDetail(GetAuthContext.getAuthorization());
        return "dashboard/homeCampaign";
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