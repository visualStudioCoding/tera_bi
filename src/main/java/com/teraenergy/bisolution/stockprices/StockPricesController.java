package com.teraenergy.bisolution.stockprices;


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
        String tagName = "분류1";
        StringBuilder stringBuilder = commonService.getApiResult(url, parameter, format, site);
        url = url + parameter;

        JSONObject xmlJSONObj = XML.toJSONObject(String.valueOf(stringBuilder));
        String jsonPrettyPrintString = xmlJSONObj.toString(4);

        JSONObject object = new JSONObject(jsonPrettyPrintString);

        // 배열을 가져옵니다.
        JSONArray jArray = object.getJSONArray("표");
        System.out.println(object.toString());

        String title = object.getString("표");

        System.out.println(object.getString("표"));

        return null;

    }
}

