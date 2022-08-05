package com.teraenergy.bisolution.admin.StandardOfLiving;

import com.teraenergy.global.service.ApiParseService;
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
/**
 *
 * 생활수준지표 api 호출 및 db 적재
 *
 * @author tera
 * @version 1.0.0
 * 작성일 2022-07-19
**/
@Slf4j
@Controller
@RequestMapping("/admin/standardOfLiving")
public class StandardOfLivingController {

    private static final String PROGRAM_ID = ".StandardOfLiving";
    private static final String PAGE_ID = "admin";
    private static final String DIRECTORY = "/standardOfLiving/StandardOfLiving";

    @Resource(name = "commonService")
    private CommonService commonService;
    @Resource(name = "apiParseService")
    private ApiParseService apiParseService;

    @GetMapping("/main")
        public String standardLivingMain() {
        log.info(PAGE_ID + DIRECTORY + "main");
        return PAGE_ID + DIRECTORY + "Main";
    }
    // 1인당 개인소득
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    @GetMapping("/api/getCapitaPersonal")
    public Object getCapitaPersonal(String url, String parameter) throws Exception {
        System.out.println(url);
        System.out.println(parameter);

        String format = "json";
        String site = "kosis";
        String message = "성공";

        StringBuilder stringBuilder = apiParseService.getApiResult(url, parameter, format, site);

        Map<String, Object> dataMap = new HashMap<>();
        Map<String, Object> result = new HashMap<>();

        JSONArray jsonList = (JSONArray) apiParseService.apiJsonParser(stringBuilder);

        for(Object jsonObject : jsonList) {
            JSONObject jsonData = (JSONObject) jsonObject;

            if ("Fail".equals(jsonData.get("RESULT"))) {
                dataMap.put("err", jsonData.get("CODE"));
                message = (String) jsonData.get("MESSAGE");
            } else {

                String yr_dt = (String) jsonData.get("PRD_DE");
                String cty_nm = (String) jsonData.get("C1_NM");
                String unit = (String) jsonData.get("UNIT_NM");
                String val = (String) jsonData.get("DT");

                dataMap.put("yr_dt", yr_dt);
                dataMap.put("cty_nm", cty_nm);
                dataMap.put("unit", unit);
                dataMap.put("val", val);

                commonService.insertContents(dataMap, PAGE_ID + PROGRAM_ID + ".insertCapitaPersonal");
            }
        }

        result.put("data", dataMap);
        result.put("success", message);

        return result;
    }

//   1인당 국민 총 소득
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    @GetMapping("/api/getGrossNationalIncome")
    public Object getGrossNationalIncome(String url, String parameter) throws Exception {

        System.out.println(url);
        System.out.println(parameter);

        String format = "xml";
        String site = "enara";

        StringBuilder stringBuilder = apiParseService.getApiResult(url, parameter, format, site);

//      통계표
        org.json.JSONObject table = apiParseService.apiXmlParser(stringBuilder);
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
            commonService.insertContents(dataMap, PAGE_ID + PROGRAM_ID + ".insertGniCapita");
        }
        Map<String, Object> result = new HashMap<>();
        result.put("data", data_list);
        result.put("success", "성공");

        return result;
    }

//   소득분배지표
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    @GetMapping("/api/getIncomeDistributionIndex")
    public Object getIncomeDistributionIndex(String url, String parameter) throws Exception {

        System.out.println(url);
        System.out.println(parameter);

        String format = "json";
        String site = "kosis";
        String message = "성공";

        StringBuilder stringBuilder = apiParseService.getApiResult(url, parameter, format, site);

        Map<String, Object> dataMap = new HashMap<>();
        Map<String, Object> result = new HashMap<>();

        JSONArray jsonList = (JSONArray) apiParseService.apiJsonParser(stringBuilder);

        for(Object jsonObject : jsonList) {
            JSONObject jsonData = (JSONObject) jsonObject;
            if ("Fail".equals(jsonData.get("RESULT"))) {
                dataMap.put("err", jsonData.get("CODE"));
                message = (String) jsonData.get("MESSAGE");
            } else {

                String yr_dt = (String) jsonData.get("PRD_DE");
                String val = (String) jsonData.get("DT");

                dataMap.put("yr_dt", yr_dt);
                dataMap.put("val", val);
                System.out.println(dataMap);
                commonService.insertContents(dataMap, PAGE_ID + PROGRAM_ID + ".insertIncomeDistributionIndex");
            }
        }
        result.put("data", dataMap);
        result.put("success", message);

        return result;
    }

    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    @GetMapping("/api/minPay")
    public Object getMinPay(String url, String parameter) throws Exception {
        //kosis = json, enara = xml
        String format = "xml";
        String site = "enara";
        StringBuilder stringBuilder = apiParseService.getApiResult(url, parameter, format, site);

        Map<String, Object> result = new HashMap<>();
        try{
//          통계표
            org.json.JSONObject table = apiParseService.apiXmlParser(stringBuilder);
            String unit = table.getString("단위");
            String[] units = unit.split(",");
            org.json.JSONObject innerTable = table.getJSONObject("표");
            org.json.JSONArray category = innerTable.getJSONArray("항목");

            List<Map<String, Object>> dataList = new ArrayList<>();
            org.json.JSONObject jsonObject = category.getJSONObject(0);
            org.json.JSONArray columns = jsonObject.getJSONArray("열");
            for (Object data : columns) {
                Map<String, Object> dataMap = new HashMap<>();
                org.json.JSONObject jsonData = (org.json.JSONObject) data;
                String date = Integer.toString((Integer) jsonData.get("주기"));

                dataMap.put("yrDt", date);
                dataMap.put("unit", units[0]);
                dataMap.put("val", String.valueOf(jsonData.get("content")));

                dataList.add(dataMap);
                commonService.insertContents(dataMap, PAGE_ID + PROGRAM_ID + ".insertMinPay");
            }
            result.put("data", dataList);
            result.put("success", "성공");
        }catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }



}
