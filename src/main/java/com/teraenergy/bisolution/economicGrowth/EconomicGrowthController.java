package com.teraenergy.bisolution.economicGrowth;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/economicGrowth")
public class EconomicGrowthController {
    private static final String PROGRAM_ID = "EconomicGrowth";

    private static final String DIRECTORY = "economicGrowth/";

    @GetMapping("/main")
    public String economicGrowthMain() throws Exception {
        System.out.println(DIRECTORY + PROGRAM_ID + "Main");
        return DIRECTORY + "main";
    }
}
