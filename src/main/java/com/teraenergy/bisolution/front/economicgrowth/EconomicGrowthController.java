package com.teraenergy.bisolution.front.economicgrowth;

import com.teraenergy.global.service.CommonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Slf4j
@Controller
@RequestMapping("/front/economicGrowth")
public class EconomicGrowthController {
    private static final String PROGRAM_ID = ".EconomicGrowth";
    private static final String PAGE_ID = "front";

    private static final String DIRECTORY = "/economicGrowth/EconomicGrowth";

    @Resource(name = "commonService")
    private CommonService commonService;

    @GetMapping("/main")
    public String economicGrowthMain() throws Exception {
        log.info(PAGE_ID + DIRECTORY + "Main");
        return PAGE_ID + DIRECTORY + "Main";
    }
}
