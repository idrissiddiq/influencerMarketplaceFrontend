package com.InfluencerMarketplace.clientapp.services;

import com.InfluencerMarketplace.clientapp.models.response.*;
import com.InfluencerMarketplace.clientapp.models.Campaign;
import com.InfluencerMarketplace.clientapp.models.request.CreateCampaignRequest;
import com.InfluencerMarketplace.clientapp.models.request.UpdateCampaignRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CampaignService {
    private RestTemplate restTemplate;

    @Value("${server.baseUrl}/campaign")
    private String url;

    @Autowired
    public CampaignService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseListData<Campaign> findAllOnlyOpen(){
        ResponseEntity<ResponseListData<Campaign>> response = restTemplate.exchange(url + "/1", HttpMethod.GET,
                null, new ParameterizedTypeReference<ResponseListData<Campaign>>(){} );
        return response.getBody();
    }

    public ResponseListData<FindAllOpenCampaignResponse> findAllOpenCampaign(){
        ResponseEntity<ResponseListData<FindAllOpenCampaignResponse>> response = restTemplate.exchange(url + "/open", HttpMethod.GET,
                null, new ParameterizedTypeReference<ResponseListData<FindAllOpenCampaignResponse>>(){} );
        return response.getBody();
    }

    public ResponseListData<Campaign> findMyCampaign(){
        ResponseEntity<ResponseListData<Campaign>> response = restTemplate.exchange(url + "/me", HttpMethod.GET,
                null, new ParameterizedTypeReference<ResponseListData<Campaign>>(){} );
        return response.getBody();
    }

    public ResponseListData<FindAllTableCampaignResponse> findAllTableCampaign(){
        ResponseEntity<ResponseListData<FindAllTableCampaignResponse>> response = restTemplate.exchange(url + "/table", HttpMethod.GET,
                null, new ParameterizedTypeReference<ResponseListData<FindAllTableCampaignResponse>>(){} );
        return response.getBody();
    }

    public ResponseData<Campaign> findById(Long id){
        ResponseEntity<ResponseData<Campaign>> response = restTemplate.exchange(url + "/search/" + id.toString(), HttpMethod.GET,
                null, new ParameterizedTypeReference<ResponseData<Campaign>>(){} );
        return response.getBody();
    }

    public ResponseMessage<CreateCampaignRequest> create(CreateCampaignRequest data){
        HttpEntity<CreateCampaignRequest> entity = new HttpEntity(data);
        ResponseEntity<ResponseMessage<CreateCampaignRequest>> response = restTemplate
                .exchange(url + "/create", HttpMethod.POST,
                        entity, new ParameterizedTypeReference<ResponseMessage<CreateCampaignRequest>>(){} );

        return response.getBody();
    }

    public ResponseMessage<UpdateCampaignRequest> update(Long id, UpdateCampaignRequest request) {

        //request.setId(id);
        Campaign campaign = new Campaign();
        campaign.setId(id);
        HttpEntity entity = new HttpEntity(request);
        ResponseEntity<ResponseMessage<UpdateCampaignRequest>> response = restTemplate
                .exchange(url + "/me/" + id, HttpMethod.PUT, new HttpEntity(request), new
                        ParameterizedTypeReference<ResponseMessage<UpdateCampaignRequest>>() {});

        return response.getBody();
    }
}
