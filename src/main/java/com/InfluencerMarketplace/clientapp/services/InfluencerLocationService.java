package com.InfluencerMarketplace.clientapp.services;

import com.InfluencerMarketplace.clientapp.models.Job;
import com.InfluencerMarketplace.clientapp.models.Location;
import com.InfluencerMarketplace.clientapp.models.response.ResponseData;
import com.InfluencerMarketplace.clientapp.models.response.ResponseListData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class InfluencerLocationService {
    private RestTemplate restTemplate;

    @Value("${server.baseUrl}/location")
    private String url;

    @Autowired
    public InfluencerLocationService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    public ResponseData<Location> getProvince(String name){
        ResponseEntity<ResponseData<Location>> response = restTemplate.exchange(url+"/search/" + name, HttpMethod.GET,
                null, new ParameterizedTypeReference<ResponseData<Location>>(){} );

        return response.getBody();
    }

    public ResponseData<Location> getKab(Long id, String name){
        ResponseEntity<ResponseData<Location>> response = restTemplate.exchange(url+"/search/" + id.toString() + "/" + name, HttpMethod.GET,
                null, new ParameterizedTypeReference<ResponseData<Location>>(){} );

        return response.getBody();
    }

    public ResponseListData<Location> provinsi(){
        ResponseEntity<ResponseListData<Location>> response = restTemplate.exchange(url, HttpMethod.GET,
                null, new ParameterizedTypeReference<ResponseListData<Location>>(){} );
        return response.getBody();
    }
    public ResponseListData<Location> kabupaten(String id){
        ResponseEntity<ResponseListData<Location>> response = restTemplate.exchange(url+ "/city?id=" +id, HttpMethod.GET,
                null, new ParameterizedTypeReference<ResponseListData<Location>>(){} );
        return response.getBody();
    }
    public ResponseListData<Location> kecamatan(){
        ResponseEntity<ResponseListData<Location>> response = restTemplate.exchange(url+"/kecamatan/3402", HttpMethod.GET,
                null, new ParameterizedTypeReference<ResponseListData<Location>>(){} );
        return response.getBody();
    }
    public ResponseListData<Location> kelurahan(){
        ResponseEntity<ResponseListData<Location>> response = restTemplate.exchange(url+"/kelurahan/3402060", HttpMethod.GET,
                null, new ParameterizedTypeReference<ResponseListData<Location>>(){} );
        return response.getBody();
    }
}
