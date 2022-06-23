package com.teraenergy.bisolution.realEstate;

import java.util.List;

import javax.annotation.Resource;

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
    public List<?> tradeRealAptList() throws Exception {
        RealEstateDTO realEstateDTO = new RealEstateDTO();

        System.out.println("selectList = {} : " + commonService.selectList(realEstateDTO, PROGRAM_ID + ".getTradeTealAptList"));

        return commonService.selectList(realEstateDTO, PROGRAM_ID + ".getTradeTealAptList");
    }
}
