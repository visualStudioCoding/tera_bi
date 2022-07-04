package com.teraenergy;

import com.teraenergy.global.configuration.ApiKeyConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    private final ApiKeyConfiguration apiKeyConfiguration;

    public IndexController(ApiKeyConfiguration apiKeyConfiguration) {
        this.apiKeyConfiguration = apiKeyConfiguration;
    }

    @GetMapping("/")
    public String indexPage(Model model) {
        model.addAttribute("hello", "index page!!");
//        System.out.println(apiKeyConfiguration.getKosisKey());
//        System.out.println(apiKeyConfiguration.getEnaraId());
//        System.out.println(apiKeyConfiguration.getEnaraKey());

        return "index";
    }
}
