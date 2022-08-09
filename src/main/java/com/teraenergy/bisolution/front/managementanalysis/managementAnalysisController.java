package com.teraenergy.bisolution.front.managementanalysis;

import com.teraenergy.global.service.CommonService;
import com.teraenergy.global.service.VolatilityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/front/managementAnalysis")
public class managementAnalysisController {

    private static final String PROGRAM_ID = ".managementAnalysis";
    private static final String PAGE_ID = "front";
    private static final String DIRECTORY = "/managementAnalysis/ManagementAnalysis";

    @Resource(name = "commonService")
    private CommonService commonService;

    @Resource(name = "volatilityService")
    private VolatilityService volatilityService;

    @GetMapping("/main")
    public String managementAnalysisMain(@RequestParam Map<String, Object> paramMap, Model model) throws Exception {
        log.info(PAGE_ID + DIRECTORY + "Main");
        model.addAttribute("menuCode", "005");

        return PAGE_ID + DIRECTORY + "Main";
    }
}
