package com.InfluencerMarketplace.clientapp.controllers;

import com.InfluencerMarketplace.clientapp.models.request.RegisterInfluencerRequest;
import com.InfluencerMarketplace.clientapp.models.request.ReportContractRequest;
import com.InfluencerMarketplace.clientapp.models.response.ResponseMessage;
import com.InfluencerMarketplace.clientapp.services.RegisterService;
import com.InfluencerMarketplace.clientapp.services.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/report")
public class ReportController {

    private ReportService reportContract;

    @Autowired
    public ReportController(ReportService reportService) {
        this.reportContract = reportService;
    }


    @PostMapping("/createReport")
    public @ResponseBody
    ResponseMessage<ReportContractRequest> reportContract(@RequestBody ReportContractRequest request) {
        return reportContract.reportContract(request);
    }
}
