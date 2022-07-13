package com.teraenergy.bisolution.economicGrowth;

import com.teraenergy.global.service.CommonService;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
        log.info(DIRECTORY + PROGRAM_ID + "List");
        return DIRECTORY + PROGRAM_ID + "Main";
    }

    //getInflationRate
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    @GetMapping("/api/getMonthlyExchangeRateList")
//    public Object getInflationRate(String url, String parameter) throws Exception {

    public Object getInflationRate() throws Exception {
        String url = "http://www.index.go.kr/openApi/xml_stts.do";
        String parameter = "?userId=&statsCode=106801";

        //kosis = json, enara = xml
        String format = "xml";
        String site = "enara";
        StringBuilder stringBuilder = commonService.getApiResult(url, parameter, format, site);

//        List<Map<String, Object>> xmlList = commonService.apiXmlParser(stringBuilder);

//        System.out.println(xmlList);

        Map<String, Object> result = new HashMap<>();
        result.put("data", stringBuilder);
        result.put("success", "성공");
//        log.info(String.valueOf(result));
        return result;
    }
    @ResponseBody
    @GetMapping("api/getStateDebtList")
    public Object getStateDebtList() throws Exception {
        Map<String, Object> dataMap = new HashMap<>();
        Map<String, Object> result = new HashMap<>();

        List<ArrayList> resultList = (List<ArrayList>) commonService.selectList(dataMap, PROGRAM_ID + ".selectStateDebt");

        result.put("title", "국가채무현황");
        result.put("datas",resultList);
        result.put("result", "success");

        return result;
    }
    // 국가채무현황
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    @GetMapping("/api/getStateDebt")
    public Object getstateDebt(String url, String parameter) throws Exception {
        System.out.println(url);
        System.out.println(parameter);
        String format = "json";
        String site = "kosis";

        StringBuilder stringBuilder = commonService.getApiResult(url, parameter, format, site);

        JSONArray jsonList = (JSONArray) commonService.apiJsonParser(stringBuilder);

        Map<String, Object> result = new HashMap<>();
        Map<String, Object> dataMap = new HashMap<>();

        for(Object jsonObject : jsonList){
            JSONObject jsonData = (JSONObject) jsonObject;
            if("국가채무".equals((String) jsonData.get("C1_NM"))) {
                String year = (String) jsonData.get("PRD_DE");
                String unit = (String) jsonData.get("UNIT_NM");
                String val = (String) jsonData.get("DT");

                dataMap.put("yr_dt", year);
                dataMap.put("unit", unit);
                dataMap.put("val", val);

                commonService.insertContents(dataMap, PROGRAM_ID + ".insertStateDebt");

                break;
            }
        }

        result.put("data", dataMap);
        result.put("success", "성공");


        return result;
    }

    // 경제활동별 GDP 및 GNI
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    @GetMapping("/api/getGdpAndGni")
    public Object getGdpAndGni(String url, String parameter) throws Exception {
        System.out.println(url);
        System.out.println(parameter);
        String format = "json";
        String site = "kosis";

        String year = null;
        String unit = null;
        String val = null;

        StringBuilder stringbuilder = commonService.getApiResult(url, parameter, format, site);
        JSONArray jsonList = (JSONArray) commonService.apiJsonParser(stringbuilder);

        Map<String, Object> result = new HashMap<>();
        Map<String, Object> dataMap = new HashMap<>();

        for(Object jsonObject : jsonList){
            JSONObject jsonData = (JSONObject) jsonObject;
            if("국내총생산(시장가격 GDP)".equals((String) jsonData.get("C1_NM"))){
                year = (String) jsonData.get("PRD_DE");
                unit = (String) jsonData.get("UNIT_NM");
                val = (String) jsonData.get("DT");
            }else if("국내총소득(GDI)".equals((String) jsonData.get("C1_NM"))){
                year = (String) jsonData.get("PRD_DE");
                unit = (String) jsonData.get("UNIT_NM");
                val = (String) jsonData.get("DT");
            }else if("국민총소득(GNI)".equals((String) jsonData.get("C1_NM"))){
                year = (String) jsonData.get("PRD_DE");
                unit = (String) jsonData.get("UNIT_NM");
                val = (String) jsonData.get("DT");
            }
            dataMap.put("yr_dt", year);
            dataMap.put("unit", unit);
            dataMap.put("val", val);

            if("국내총생산(시장가격 GDP)".equals((String) jsonData.get("C1_NM"))){
                commonService.insertContents(dataMap, PROGRAM_ID + ".insertGDP");
            }else if("국내총소득(GDI)".equals((String) jsonData.get("C1_NM"))){
                commonService.insertContents(dataMap, PROGRAM_ID + ".insertGDI");
            }else if("국민총소득(GNI)".equals((String) jsonData.get("C1_NM"))){
                commonService.insertContents(dataMap, PROGRAM_ID + ".insertGNI");
            }

        }
        result.put("data", dataMap);
        result.put("success", "성공");

        return result;
    }
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    @GetMapping("/api/getGrowthRate")
    public Object getGrowthRate(String url, String parameter) throws Exception {
        System.out.println(url);
        System.out.println(parameter);
        String format = "json";
        String site = "kosis";

        StringBuilder stringBuilder = commonService.getApiResult(url, parameter, format, site);
        JSONArray jsonArray = (JSONArray) commonService.apiJsonParser(stringBuilder);

        Map<String, Object> dataMap = new HashMap<>();
        Map<String, Object> result = new HashMap<>();

        for(Object jsonObject : jsonArray){
            JSONObject jsonData = (JSONObject) jsonObject;

            String yrdt = (String) jsonData.get("PRD_DE");
            String city = (String) jsonData.get("C1_NM");
            String unit = (String) jsonData.get("UNIT_NM");
            String value = (String) jsonData.get("DT");

            dataMap.put("yr_dt", yrdt);
            dataMap.put("cty_nm", city);
            dataMap.put("unit", unit);
            dataMap.put("val", value);

            commonService.insertContents(dataMap, PROGRAM_ID + ".insertGrowthRate");
        }

        result.put("data", dataMap);
        result.put("success", "성공");

        return result;
    }
}
