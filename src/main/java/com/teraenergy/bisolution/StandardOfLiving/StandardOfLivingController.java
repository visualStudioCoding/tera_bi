package com.teraenergy.bisolution.StandardOfLiving;

import com.teraenergy.global.service.CommonService;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/standardOfLiving")
public class StandardOfLivingController {

    private static final String PROGRAM_ID = "StandardOfLiving";

    private static final String DIRECTORY = "standardOfLiving/";

    @Resource(name = "commonService")
    private CommonService commonService;

    @GetMapping("/main")
        public String standardLivingMain() {
        log.info(DIRECTORY + PROGRAM_ID + "main");
        return DIRECTORY + PROGRAM_ID + "Main";
    }

    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    @GetMapping("/api/getCapitaPersonal")
    public Object getCapitaPersonal(String url, String parameter) throws Exception {
        System.out.println(url);
        System.out.println(parameter);

        String format = "json";
        String site = "kosis";

        StringBuilder stringBuilder = commonService.getApiResult(url, parameter, format, site);

        Map<String, Object> dataMap = new HashMap<>();
        Map<String, Object> result = new HashMap<>();

        JSONArray jsonList = (JSONArray) commonService.apiJsonParser(stringBuilder);

        for(Object jsonObject : jsonList){
            JSONObject jsonData = (JSONObject) jsonObject;

            String yr_dt = (String) jsonData.get("PRD_DE");
            String cty_nm = (String) jsonData.get("C1_NM");
            String unit = (String) jsonData.get("UNIT_NM");
            String val = (String) jsonData.get("DT");

            dataMap.put("yr_dt", yr_dt);
            dataMap.put("cty_nm", cty_nm);
            dataMap.put("unit", unit);
            dataMap.put("val", val);

            commonService.insertContents(dataMap, PROGRAM_ID + ".insertCapitaPersonal");
        }

        result.put("data", dataMap);
        result.put("success", "성공");

        return result;
    }

}
