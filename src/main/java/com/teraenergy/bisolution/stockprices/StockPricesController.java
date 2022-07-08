package com.teraenergy.bisolution.stockprices;


import com.teraenergy.global.service.CommonService;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.json.XML;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
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
        JSONParser jsonParser = new JSONParser();
        org.json.JSONArray sss = (org.json.JSONArray) jsonParser.parse(jsonPrettyPrintString);
        System.out.println(jsonParser.parse(jsonPrettyPrintString));
        System.out.println(sss);



        //JSONArray jsonArray = (JSONArray) jsonParser.parse(jsonPrettyPrintString);
        //System.out.println(jsonArray);

        JSONArray jsonList = (JSONArray) commonService.apiJsonParser(stringBuilder);
        Document documentInfo = null;
        documentInfo = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(url);
        documentInfo.getDocumentElement().normalize();

        // 제일 첫번째 태그
        Element root = documentInfo.getDocumentElement();
        //Root: 지표
        System.out.println("Root: " + documentInfo.getDocumentElement().getNodeName());

        NodeList children = root.getElementsByTagName(tagName);
        System.out.println("nodeList: " + children);

        Map<String, Object> dataMap = new HashMap<>();
        for (int idx = 0; idx < children.getLength(); idx++) {
            Node node = children.item(idx);
            NamedNodeMap attributes = node.getAttributes();
            Node attr = attributes.item(idx);
            Element childElement = (Element) node;
            dataMap.put("attr", attr.getNodeValue());

        }



        Map<String, Object> result = new HashMap<>();

        result.put("data", dataMap);
        result.put("success", "성공");
//        log.info(String.valueOf(result));
        return result;
    }
}

