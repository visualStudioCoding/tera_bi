package com.teraenergy.bisolution.front.realestate;

import com.teraenergy.global.model.CommonDTO;
import com.teraenergy.global.service.CommonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 부동산시장동향 front
 *
 * @author tera
 * @version 1.0.0
 * 작성일 2022-07-20
 **/
@Slf4j
@Controller
@RequestMapping("/front/realEstate")
public class RealEstateController {

    private static final String PROGRAM_ID = ".RealEstate";
    private static final String PAGE_ID = "front";
    private static final String DIRECTORY = "/realEstate/RealEstate";
    @Resource(name = "commonService")
    private CommonService commonService;

    @GetMapping("/main")
    public String realEstateMain(ModelMap model) throws Exception {
        log.info(PAGE_ID + DIRECTORY + "Main");

        CommonDTO life = new CommonDTO();
        life = (CommonDTO) commonService.selectContents(life, PAGE_ID + PROGRAM_ID + ".selectLifeSatisfaction");
        model.addAttribute("life", life);

        //2.결혼
        Map<String,Object> dataMap2 = new HashMap<>();
        dataMap2 = (Map<String, Object>) commonService.selectContents(null, PAGE_ID + PROGRAM_ID + ".selectMarriage");
        model.addAttribute("data2", dataMap2);

        //3.이혼
        Map<String,Object> dataMap3 = new HashMap<>();
        dataMap3 = (Map<String, Object>) commonService.selectContents(null, PAGE_ID + PROGRAM_ID + ".selectDivorce");
        model.addAttribute("data3", dataMap3);

        //4. 고용률
        Map<String,Object> dataMap4 = new HashMap<>();
        dataMap4 = (Map<String, Object>) commonService.selectContents(null, PAGE_ID + PROGRAM_ID + ".selectEmplyrate");
        model.addAttribute("data4", dataMap4);

        return PAGE_ID + DIRECTORY + "Main";
    }

}
