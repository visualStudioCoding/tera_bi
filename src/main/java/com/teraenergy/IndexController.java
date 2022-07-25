package com.teraenergy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class IndexController {

    @GetMapping("/admin")
    public String adminPage() {
        return "admin/economicGrowth/EconomicGrowthMain";
    }

    @GetMapping(value = {"/", "/index"})
    public String frontPage() {
        return "redirect:/front/economicGrowth/main";
    }
}
