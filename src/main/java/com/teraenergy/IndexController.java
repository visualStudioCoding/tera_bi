package com.teraenergy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class IndexController {

    @GetMapping("/admin")
    public String adminPage(Model model) {
        return "admin/economicGrowth/EconomicGrowthMain";
    }

    @GetMapping(value = {"/", "/index"})
    public String frontPage(Model model) {
        return "index";
    }
}
