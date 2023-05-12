package com.InfluencerMarketplace.clientapp.controllers;

import com.InfluencerMarketplace.clientapp.models.Campaign;
import com.InfluencerMarketplace.clientapp.models.Notification;
import com.InfluencerMarketplace.clientapp.models.request.LoginRequest;
import com.InfluencerMarketplace.clientapp.models.request.RegisterInfluencerRequest;
import com.InfluencerMarketplace.clientapp.models.request.UpdateCampaignRequest;
import com.InfluencerMarketplace.clientapp.models.response.NotifUpdateResponse;
import com.InfluencerMarketplace.clientapp.models.response.ResponseListData;
import com.InfluencerMarketplace.clientapp.models.response.ResponseMessage;
import com.InfluencerMarketplace.clientapp.services.InfluencerLocationService;
import com.InfluencerMarketplace.clientapp.services.InfluencerTypeService;
import com.InfluencerMarketplace.clientapp.services.LoginService;
import com.InfluencerMarketplace.clientapp.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/notification")
public class NotificationController {

    private NotificationService notificationService;

    @Autowired
    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping("/update")
    public NotifUpdateResponse findUpdate(){
        return notificationService.findNotifUpdate();
    }
}
