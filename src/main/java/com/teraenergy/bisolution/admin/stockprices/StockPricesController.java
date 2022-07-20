package com.teraenergy.bisolution.admin.stockprices;


import com.teraenergy.global.service.CommonService;
import lombok.extern.slf4j.Slf4j;
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
 * 종합주가지수 api 호출 및 db 적재
 *
 * @author tera
 * @version 1.0.0
 * 작성일 2022-07-19
**/
@Slf4j
@Controller
@RequestMapping("/admin/stockPrices")
public class StockPricesController {

    private static final String PROGRAM_ID = ".StockPrices";
    private static final String PAGE_ID = "admin";
    private static final String DIRECTORY = "/stockPrices/StockPrices";

    @Resource(name = "commonService")
    private CommonService commonService;

    @GetMapping("/main")
    public String stockPricesMain() throws Exception {
        log.info(PAGE_ID + DIRECTORY + "Main");
        return PAGE_ID + DIRECTORY + "Main";
    }

    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    @GetMapping("/api/compositeIndex")
    public Object getCompositeIndex(String url, String parameter) throws Exception {
        //kosis = json, enara = xml
        String format = "xml";
        String site = "enara";
        StringBuilder stringBuilder = commonService.getApiResult(url, parameter, format, site);

        Map<String, Object> result = new HashMap<>();
//      통계표
        org.json.JSONObject table = commonService.apiXmlParser(stringBuilder);
        String unit = table.getString("단위");
        org.json.JSONArray innerTable = table.getJSONArray("표");
        org.json.JSONObject monthTable = innerTable.getJSONObject(1);
        org.json.JSONObject category = monthTable.getJSONObject("항목");
        org.json.JSONArray classification = category.getJSONArray("분류1");

        List<Map<String, Object>> dataList = new ArrayList<>();
        for (int idx = 0; idx < classification.length(); idx++) {
            org.json.JSONObject jsonObject = classification.getJSONObject(idx);
            org.json.JSONArray columns = jsonObject.getJSONArray("열");
            for (Object data : columns) {
                Map<String, Object> dataMap = new HashMap<>();
                org.json.JSONObject jsonData = (org.json.JSONObject) data;
                System.out.println(jsonData);
                String date = Integer.toString((Integer) jsonData.get("주기"));
                String year = date.substring(0, 4);
                String month = date.substring(4, 6);

                dataMap.put("yrDt", year);
                dataMap.put("monDt", month);
                dataMap.put("unit", unit);
                dataMap.put("val", String.valueOf(jsonData.get("content")));
                String index = (String) jsonObject.get("이름");

                dataList.add(dataMap);
                if("코스피지수".equals(index)){
                    commonService.insertContents(dataMap, PAGE_ID + PROGRAM_ID + ".insertKospi");
                } else {
                    commonService.insertContents(dataMap, PAGE_ID + PROGRAM_ID + ".insertKosdaq");
                }
            }
        }
        result.put("data", dataList);
        result.put("success", "성공");

        return result;
    }

    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    @GetMapping("/api/birthDeath")
    public Object getBirthDeath(String url, String parameter) throws Exception {
        //kosis = json, enara = xml
        String format = "xml";
        String site = "enara";
        StringBuilder stringBuilder = commonService.getApiResult(url, parameter, format, site);

        Map<String, Object> result = new HashMap<>();
//      통계표
        org.json.JSONObject table = commonService.apiXmlParser(stringBuilder);
        String unit = table.getString("단위");
        String[] units = unit.split(",");
        org.json.JSONObject innerTable = table.getJSONObject("표");
        org.json.JSONArray categoryGroup = innerTable.getJSONArray("항목그룹");

        List<Map<String, Object>> dataList = new ArrayList<>();
        for (int idx = 0; idx < categoryGroup.length(); idx++) {
            org.json.JSONObject innerCategoryGroup = categoryGroup.getJSONObject(idx);
            org.json.JSONArray category = innerCategoryGroup.getJSONArray("항목");
            for (int i = 0; i < category.length(); i++) {
                org.json.JSONObject jsonObject = category.getJSONObject(i);

                String name = (String) jsonObject.get("이름");

                if("출생아수".equals(name) || "사망자수".equals(name)) {
                    org.json.JSONArray columns = jsonObject.getJSONArray("열");
                    for (Object data : columns) {
                        Map<String, Object> dataMap = new HashMap<>();
                        org.json.JSONObject jsonData = (org.json.JSONObject) data;
                        String date = Integer.toString((Integer) jsonData.get("주기"));

                        dataMap.put("yrDt", date);
                        dataMap.put("unit", units[0]); //명
                        dataMap.put("val", String.valueOf(jsonData.get("content")));

                        dataList.add(dataMap);
                        if("출생아수".equals(name)){
                            commonService.insertContents(dataMap, PAGE_ID + PROGRAM_ID + ".insertBirth");
                        } else {
                            commonService.insertContents(dataMap, PAGE_ID + PROGRAM_ID + ".insertDeath");
                        }
                    }
                }
            }
            if("기대수명".equals(innerCategoryGroup.get("이름"))){
                org.json.JSONArray jsonArray = new org.json.JSONArray();
                org.json.JSONObject manObject = category.getJSONObject(1);
                org.json.JSONObject womanObject = category.getJSONObject(2);
                org.json.JSONArray manColumns = manObject.getJSONArray("열");
                org.json.JSONArray womanColumns = womanObject.getJSONArray("열");
                for (int j = 0; j < manColumns.length(); j++) {
                    org.json.JSONObject tmpObject = new org.json.JSONObject();
                    org.json.JSONObject manData = manColumns.getJSONObject(j);
                    org.json.JSONObject womanData = womanColumns.getJSONObject(j);
                    if(manData.get("주기").equals(womanData.get("주기"))){
                        String date = Integer.toString((Integer) manData.get("주기"));
                        tmpObject.put("yrDt", date);
                        tmpObject.put("unit", units[2].trim()); //년
                        tmpObject.put("manVal", String.valueOf(manData.get("content")));
                        tmpObject.put("womanVal", String.valueOf(womanData.get("content")));
                    }
                    jsonArray.put(tmpObject);
                }
                for (Object data : jsonArray) {
                    Map<String, Object> dataMap = new HashMap<>();
                    org.json.JSONObject jsonData = (org.json.JSONObject) data;
                    dataMap.put("yrDt", jsonData.get("yrDt"));
                    dataMap.put("unit", jsonData.get("unit"));
                    dataMap.put("manVal", jsonData.get("manVal"));
                    dataMap.put("womanVal", jsonData.get("womanVal"));
                    dataList.add(dataMap);
                    commonService.insertContents(dataMap, PAGE_ID + PROGRAM_ID + ".insertLifeExpectancy");
                }
            }
        }
        result.put("data", dataList);
        result.put("success", "성공");
        return result;
    }
}

