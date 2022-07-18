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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    @GetMapping("/api/getGrossNationalIncome")
    public Object getGrossNationalIncome(String url, String parameter) throws Exception {

        System.out.println(url);
        System.out.println(parameter);

        String format = "xml";
        String site = "enara";

        StringBuilder stringBuilder = commonService.getApiResult(url, parameter, format, site);

//      통계표
        org.json.JSONObject table = commonService.apiXmlParser(stringBuilder);
        String unit = table.getString("단위");
        String[] gubun = unit.split(",");
        org.json.JSONObject inner_table = table.getJSONObject("표");
        org.json.JSONArray category = inner_table.getJSONArray("항목");


        org.json.JSONObject info = category.getJSONObject(0);
        org.json.JSONObject info_l = category.getJSONObject(2);

        org.json.JSONArray columns = info.getJSONArray("열");
        org.json.JSONArray columns_l = info_l.getJSONArray("열");
        org.json.JSONArray jsonArray = new org.json.JSONArray();
        for (int j = 0; j < columns.length(); j++) {
            org.json.JSONObject jsonObject = new org.json.JSONObject();
            org.json.JSONObject data = columns.getJSONObject(j);
            org.json.JSONObject data_l = columns_l.getJSONObject(j);

            if(data.get("주기").equals(data_l.get("주기"))) {
                jsonObject.put("unit",gubun[0]);
                jsonObject.put("date", data.get("주기"));
                jsonObject.put("gdiVal", data.get("content"));
                jsonObject.put("gniVal", data_l.get("content"));
            }
            jsonArray.put(jsonObject);
        }
        List<Map<String, Object>> data_list = new ArrayList<>();

        for(Object jsonObject : jsonArray){
            Map<String, Object> dataMap = new HashMap<>();

            org.json.JSONObject jsonData = (org.json.JSONObject) jsonObject;

            String yr_dt =  Integer.toString((Integer) jsonData.get("date"));
            String gdiVal =  Integer.toString((Integer) jsonData.get("gdiVal"));
            String gniVal =  Integer.toString((Integer) jsonData.get("gniVal"));
            String getUnit = (String) jsonData.get("unit");

            dataMap.put("yr_dt", yr_dt);
            dataMap.put("unit", getUnit);
            dataMap.put("gdi_val", gdiVal);
            dataMap.put("gni_val", gniVal);
            System.out.println(dataMap);
            data_list.add(dataMap);
            commonService.insertContents(dataMap, PROGRAM_ID + ".insertGniCapita");
        }
        Map<String, Object> result = new HashMap<>();
        result.put("data", data_list);
        result.put("success", "성공");

        return result;
    }

    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    @GetMapping("/api/getIncomeDistributionIndex")
    public Object getIncomeDistributionIndex(String url, String parameter) throws Exception {

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
            String val = (String) jsonData.get("DT");

            dataMap.put("yr_dt", yr_dt);
            dataMap.put("val", val);
            System.out.println(dataMap);
            commonService.insertContents(dataMap, PROGRAM_ID + ".insertIncomeDistributionIndex");
        }
        result.put("data", dataMap);
        result.put("success", "성공");

        return result;
    }

}
