package com.teraenergy;

import com.teraenergy.global.configuration.ApiKeyConfiguration;
import com.teraenergy.global.service.CommonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
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

        //kosis = json, enara = xml
        String format = "json";
        String site = "kosis";
        StringBuilder stringBuilder = commonService.getApiResult(url, parameter, format, site);

        Map<String, Object> result = new HashMap<>();
        result.put("data", stringBuilder);
        result.put("success", "성공");
//        log.info(String.valueOf(result));
        return result;
    }
//    @Scheduled(fixedDelay = 3000)
//    public void cronJobSch() throws Exception {
//        getIncomes("https://kosis.kr/openapi/Param/statisticsParameterData.do","?method=getList&apiKey=NzViOGU0M2RkMTIxMjFhZjM5YjI3NjA2YjNjOGE0ZDg=&itmId=T20+T21+T22+&objL1=ALL&objL2=&objL3=&objL4=&objL5=&objL6=&objL7=&objL8=&format=json&jsonVD=Y&prdSe=M&startPrdDe=&endPrdDe=&newEstPrdCnt=1&loadGubun=2&orgId=101&tblId=DT_1B040A3");
//    }
}
