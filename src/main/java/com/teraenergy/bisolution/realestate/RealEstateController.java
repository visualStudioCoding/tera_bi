package com.teraenergy.bisolution.realestate;


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
@RequestMapping("/realEstate")
public class RealEstateController {

    private static final String PROGRAM_ID = "RealEstate";

    private static final String DIRECTORY = "realEstate/";

    @Resource(name = "commonService")
    private CommonService commonService;

    @GetMapping("/main")
    public String realEstateMain() throws Exception {
        log.info(DIRECTORY + PROGRAM_ID + "List");
        return DIRECTORY + PROGRAM_ID + "Main";
    }

    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    @GetMapping("/api/genderPopulation")
    public Object getGenderPopulation(String url, String parameter) throws Exception {

        //kosis = json, enara = xml
        String format = "json";
        String site = "kosis";
        StringBuilder stringBuilder = commonService.getApiResult(url, parameter, format, site);
        JSONArray jsonList = (JSONArray) commonService.apiJsonParser(stringBuilder);

        Map<String, Object> result = new HashMap<>();
        Map<String, Object> dataMap = new HashMap<>();
        for (Object jsonObject : jsonList) {
            JSONObject jsonData = (JSONObject) jsonObject;

            String year = (String) jsonData.get("PRD_DE");
            year = year.substring(0, 4);
            String month = (String) jsonData.get("PRD_DE");
            month = month.substring(4, 6);

            String gender = (String) jsonData.get("ITM_NM_ENG");
            gender = gender.contains("Male") ? "남" : "여";
            String ctyName = commonService.getCtyNm((String) jsonData.get("C1"));
            String areaCd = (String) jsonData.get("C1");

            dataMap.put("yrDt", year);
            dataMap.put("monDt", month);
            dataMap.put("gender", gender);
            dataMap.put("ctyNm", ctyName);
            dataMap.put("areaCd", areaCd);
            dataMap.put("dstNm", jsonData.get("C1_NM"));
            dataMap.put("unit", jsonData.get("UNIT_NM"));
            dataMap.put("val", jsonData.get("DT"));

            //세종특별자치시 중복 제외
            if(!"36110".equals(areaCd)) {
                commonService.insertContents(dataMap, PROGRAM_ID + ".insertGenderPopulation");
            }

        }

        result.put("data", dataMap);
        result.put("success", "성공");
//        log.info(String.valueOf(result));
        return result;
    }

    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    @GetMapping("/api/aptSalesStatus")
    public Object getAptSalesStatus(String url, String parameter) throws Exception {

        //kosis = json, enara = xml
        String format = "json";
        String site = "kosis";
        StringBuilder stringBuilder = commonService.getApiResult(url, parameter, format, site);
        JSONArray jsonList = (JSONArray) commonService.apiJsonParser(stringBuilder);

        Map<String, Object> result = new HashMap<>();
        Map<String, Object> dataMap = new HashMap<>();
        for (Object jsonObject : jsonList) {
            JSONObject jsonData = (JSONObject) jsonObject;

            String year = (String) jsonData.get("PRD_DE");
            year = year.substring(0, 4);
            String month = (String) jsonData.get("PRD_DE");
            month = month.substring(4, 6);

            String ctyName = commonService.getCtyNm((String) jsonData.get("C1"));

            dataMap.put("yrDt", year);
            dataMap.put("monDt", month);
            dataMap.put("ctyNm", ctyName);
            dataMap.put("dstNm", jsonData.get("C1_NM"));
            dataMap.put("unit", jsonData.get("UNIT_NM"));
            dataMap.put("val", jsonData.get("DT"));

            String areaCd = (String) jsonData.get("C1");
            //세종특별자치시 중복 제외
            if(!"13102114448A.00090001".equals(areaCd)) {
                commonService.insertContents(dataMap, PROGRAM_ID + ".insertAptSalesStatus");
            }
        }

        result.put("data", dataMap);
        result.put("success", "성공");
//        log.info(String.valueOf(result));
        return result;
    }
}

