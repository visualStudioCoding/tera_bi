package com.teraenergy.bisolution.stockprices;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.teraenergy.global.service.CommonService;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.w3c.dom.*;

import javax.annotation.Resource;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/stockPrices")
public class StockPricesController {

    private static final String PROGRAM_ID = "StockPrices";

    private static final String DIRECTORY = "stockPrices/";

    @Resource(name = "commonService")
    private CommonService commonService;

    @GetMapping("/main")
    public String stockPricesMain() throws Exception {
        log.info(DIRECTORY + PROGRAM_ID + "List");
        return DIRECTORY + PROGRAM_ID + "Main";
    }

    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    @GetMapping("/api/compositeIndex")
    public Object getCompositeIndex(String url, String parameter) throws Exception {

        //kosis = json, enara = xml
        String format = "xml";
        String site = "enara";
        StringBuilder stringBuilder = commonService.getApiResult(url, parameter, format, site);
        try{
            org.json.JSONArray table = commonService.apiXmlParser(stringBuilder);

            org.json.JSONObject obj = table.getJSONObject(1); // 표 주기="월"

            org.json.JSONArray category = obj.getJSONArray("항목");

            org.json.JSONObject cate = category.getJSONObject(0);

            org.json.JSONArray classification = cate.getJSONArray("분류1"); // 찾을 key

            for (int j = 0; j < classification.length(); j++) {
                org.json.JSONObject objClassification = classification.getJSONObject(j);
                System.out.println(objClassification.get("이름"));
                org.json.JSONArray rows = objClassification.getJSONArray("열");
                for (int y = 0; y < rows.length(); y++) {
                    org.json.JSONObject row = rows.getJSONObject(y);
                    System.out.println(row.get("주기"));
                    System.out.println(row.get("content"));
                }
            }

        }catch (Exception e) {
            e.printStackTrace();
        }

        return null;

    }
}

