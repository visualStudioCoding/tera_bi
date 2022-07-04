package com.teraenergy.bisolution.economicGrowth;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/economicGrowth")
public class EconomicGrowthController {
    private static final String PROGRAM_ID = "EconomicGrowth";

    private static final String DIRECTORY = "economicGrowth/";

    @GetMapping("/main")
    public String economicGrowthMain() throws Exception {
        log.info(DIRECTORY + PROGRAM_ID + "Main");
        return DIRECTORY + PROGRAM_ID + "Main";
    }
}
