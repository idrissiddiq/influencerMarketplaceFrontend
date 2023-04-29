package com.InfluencerMarketplace.clientapp.services;

import com.InfluencerMarketplace.clientapp.models.request.RegisterInfluencerRequest;
import com.InfluencerMarketplace.clientapp.models.request.ReportContractRequest;
import com.InfluencerMarketplace.clientapp.models.response.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ReportService {

    private RestTemplate restTemplate;

    @Value("${server.baseUrl}/report")
    private String url;

    @Autowired
    public ReportService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseMessage<ReportContractRequest> reportContract(ReportContractRequest request) {
        HttpEntity<ReportContractRequest> entity = new HttpEntity<>(request);
        return restTemplate
                .exchange(url + "/createReport", HttpMethod.POST, entity, new ParameterizedTypeReference<ResponseMessage<ReportContractRequest>>() {
                }).getBody();
    }
}
