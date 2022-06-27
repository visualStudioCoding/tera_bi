package com.teraenergy.bisolution.realEstate;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.teraenergy.global.service.CommonService;

@Controller
public class RealEstateController {

    @Resource(name="commonService")
    private CommonService commonService;
    
    private static final String PROGRAM_ID = "RealEstate";
    
    @GetMapping("/all-trade-real-apt")
    public String getAllTradeRealApt() throws Exception {

        return PROGRAM_ID + "/list.html";
    }

    @ResponseBody
    @GetMapping("/api/all-trade-real-apt")
    public Object getApiAllTradeRealApt(RealEstateDTO realEstateDTO) throws Exception {

        Map<String,Object> result = new HashMap<>();
        
        List<RealEstateDTO> data = (List<RealEstateDTO>) commonService.selectList(realEstateDTO, PROGRAM_ID + ".getTradeRealAptList");

		result.put("data", data);
        result.put("sucess", true);

        return result;
    }
}
