package com.InfluencerMarketplace.clientapp.controllers;

import com.InfluencerMarketplace.clientapp.models.Contract;
import com.InfluencerMarketplace.clientapp.models.request.CreateCampaignRequest;
import com.InfluencerMarketplace.clientapp.models.request.CreateContractRequest;
import com.InfluencerMarketplace.clientapp.models.response.ResponseListData;
import com.InfluencerMarketplace.clientapp.models.response.ResponseMessage;
import com.InfluencerMarketplace.clientapp.services.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/contract")
public class ContractController {
    private ContractService contractService;

    @Autowired
    public ContractController(ContractService contractService) {
        this.contractService = contractService;
    }

    @GetMapping("/{id}/detail")
    public @ResponseBody
    ResponseListData<Contract> findAllMyContractByCampaign(@PathVariable Long id) {
        return contractService.findAllMyContractByCampaign(id);
    }

    @GetMapping("/me")
    public @ResponseBody
    ResponseListData<Contract> findMyContractByInfluencer() {
        return contractService.findMyContractByInfluencer();
    }

    @PostMapping("/create/{id}")
    public @ResponseBody
    ResponseMessage<CreateContractRequest> createContract(@PathVariable Long id, @RequestBody CreateContractRequest request) {
        return  contractService.createContract(request, id);
    }
}
