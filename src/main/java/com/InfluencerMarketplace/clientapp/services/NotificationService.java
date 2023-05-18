package com.InfluencerMarketplace.clientapp.services;

import com.InfluencerMarketplace.clientapp.models.AllReport;
import com.InfluencerMarketplace.clientapp.models.Location;
import com.InfluencerMarketplace.clientapp.models.Notification;
import com.InfluencerMarketplace.clientapp.models.request.ReportContractRequest;
import com.InfluencerMarketplace.clientapp.models.response.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NotificationService {

    private RestTemplate restTemplate;

    @Value("${server.baseUrl}/notification")
    private String url;

    @Autowired
    public NotificationService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseListData<Notification> findNotification(){
        ResponseEntity<ResponseListData<Notification>> response = restTemplate.exchange(url, HttpMethod.GET,
                null, new ParameterizedTypeReference<ResponseListData<Notification>>(){} );
        return response.getBody();
    }

    public ResponseListData<Notification> findAllNotification() {
        ResponseEntity<ResponseListData<Notification>> response = restTemplate.exchange(url + "/expanded", HttpMethod.GET,
                null, new ParameterizedTypeReference<ResponseListData<Notification>>() {
                });
        return response.getBody();
    }
    public NotifReadResponse findNotifRead(){
        ResponseEntity<NotifReadResponse> response = restTemplate.exchange(url + "/read", HttpMethod.GET,
                null, new ParameterizedTypeReference<NotifReadResponse>(){} );
        return response.getBody();
    }
    public NotifUpdateResponse findNotifUpdate(){
        ResponseEntity<NotifUpdateResponse> response = restTemplate.exchange(url + "/update", HttpMethod.POST,
                null, new ParameterizedTypeReference<NotifUpdateResponse>(){} );
        return response.getBody();
    }
}
