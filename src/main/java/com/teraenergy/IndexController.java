package com.teraenergy;

import com.teraenergy.global.configuration.ApiKeyConfiguration;
import com.teraenergy.global.service.CommonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Controller
public class IndexController {

    @Resource(name = "commonService")
    private CommonService commonService;

    private final ApiKeyConfiguration apiKeyConfiguration;

    public IndexController(ApiKeyConfiguration apiKeyConfiguration) {
        this.apiKeyConfiguration = apiKeyConfiguration;
    }

    @GetMapping("/")
    public String indexPage(Model model) {
        model.addAttribute("hello", "index page!!");
        System.out.println(apiKeyConfiguration.getKosisKey());
        System.out.println(apiKeyConfiguration.getEnaraId());
        System.out.println(apiKeyConfiguration.getEnaraKey());

        return "index";
    }

    @ResponseBody
    @GetMapping("/getIncome")
    public Object getIncomes(String url, String parameter) throws Exception {

        StringBuilder stringBuilder = commonService.getApiResult(url, parameter);

        Map<String, Object> result = new HashMap<>();
        result.put("data", stringBuilder);
        result.put("success", "성공");
        log.info(String.valueOf(result));
        return result;
    }
}
