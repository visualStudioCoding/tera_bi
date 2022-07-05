package com.teraenergy.bisolution.economicGrowth;

import com.teraenergy.global.service.CommonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/economicGrowth")
public class EconomicGrowthController {
    private static final String PROGRAM_ID = "EconomicGrowth";

    private static final String DIRECTORY = "economicGrowth/";

    @Resource(name = "commonService")
    private CommonService commonService;

    @GetMapping("/main")
    public String economicGrowthMain() throws Exception {
        log.info(DIRECTORY + PROGRAM_ID + "Main");
        return DIRECTORY + PROGRAM_ID + "Main";
    }

    //getInflationRate
    @ResponseBody
    @GetMapping("/api/getInflationRate")
    public Object getInflationRate(String url, String parameter) throws Exception {
        System.out.println(url);
        System.out.println(parameter);
        //kosis = json, enara = xml
        String format = "xml";
        String site = "enara";
        StringBuilder stringBuilder = commonService.getApiResult(url, parameter, format, site);

        Map<String, Object> result = new HashMap<>();
        result.put("data", stringBuilder);
        result.put("success", "성공");
        log.info(String.valueOf(result));
        return result;
    }
}
