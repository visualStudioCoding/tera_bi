package com.teraenergy.bisolution.realEstate;


import com.teraenergy.global.configuration.ApiKeyConfiguration;
import com.teraenergy.global.service.CommonService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class RealEstateController {

    private static final String PROGRAM_ID = "RealEstate";

    private static final String DIRECTORY = "realEstate/";

    @Resource(name = "commonService")
    private CommonService commonService;

    private final ApiKeyConfiguration apiKeyConfiguration;

    public RealEstateController(ApiKeyConfiguration apiKeyConfiguration) {
        this.apiKeyConfiguration = apiKeyConfiguration;
    }

    @GetMapping("/all-trade-real-apt")
    public String getAllTradeRealApt() throws Exception {
        System.out.println(DIRECTORY + PROGRAM_ID + "List");
        return DIRECTORY + PROGRAM_ID + "Main";
    }

    @ResponseBody
    @GetMapping("/api/all-trade-real-apt")
    public Object getApiAllTradeRealApt(RealEstateDTO realEstateDTO) throws Exception {

        Map<String, Object> result = new HashMap<>();

        @SuppressWarnings("unchecked") List<RealEstateDTO> data = (List<RealEstateDTO>) commonService.selectList(realEstateDTO, PROGRAM_ID + ".getTradeRealAptList");

        result.put("data", data);
        result.put("success", true);

        return result;
    }

}

