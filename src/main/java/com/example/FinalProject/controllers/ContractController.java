package com.example.FinalProject.controllers;

import com.example.FinalProject.models.Campaign;
import com.example.FinalProject.models.Contract;
import com.example.FinalProject.models.response.ResponseData;
import com.example.FinalProject.models.response.ResponseListData;
import com.example.FinalProject.services.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
}
