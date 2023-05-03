package com.InfluencerMarketplace.clientapp.services;

import com.InfluencerMarketplace.clientapp.models.AllReport;
import com.InfluencerMarketplace.clientapp.models.Contract;
import com.InfluencerMarketplace.clientapp.models.request.ApproveContractRequest;
import com.InfluencerMarketplace.clientapp.models.request.ReportContractRequest;
import com.InfluencerMarketplace.clientapp.models.response.ResponseListData;
import com.InfluencerMarketplace.clientapp.models.response.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AllReportService {
    private RestTemplate restTemplate;

    @Value("${server.baseUrl}/report")
    private String url;

    @Autowired
    public AllReportService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseListData<AllReport> findAllReport(String id) {
        ResponseEntity<ResponseListData<AllReport>> response = restTemplate.exchange(url + "/findAllReport/" + id, HttpMethod.GET,
                null, new ParameterizedTypeReference<ResponseListData<AllReport>>() {
                });
        return response.getBody();
    }
}