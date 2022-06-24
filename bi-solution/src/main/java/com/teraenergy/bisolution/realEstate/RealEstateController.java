package com.teraenergy.bisolution.realEstate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.json.JSONArray;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teraenergy.global.service.CommonService;

import groovy.util.logging.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/realEstate")
public class RealEstateController {

    @Resource(name="commonService")
    private CommonService commonService;
    
    private static final String PROGRAM_ID = "RealEstate";

    @GetMapping("/tradeRealAptList")
    public Object tradeRealAptList(RealEstateDTO realEstateDTO) throws Exception {

        List<RealEstateDTO> data = (List<RealEstateDTO>) commonService.selectList(realEstateDTO, PROGRAM_ID + ".getTradeTealAptList");
        System.out.println(data);
        Map<String,Object> result = new HashMap<>();
		result.put("data", data);
        result.put("sucess", true);

        return result;
    }
}
