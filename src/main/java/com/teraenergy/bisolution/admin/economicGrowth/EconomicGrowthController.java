package com.teraenergy.bisolution.admin.economicGrowth;

import com.teraenergy.global.service.ApiParseService;
import com.teraenergy.global.service.CommonService;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
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
@RequestMapping("/admin/economicGrowth")
public class EconomicGrowthController {
    private static final String PROGRAM_ID = ".EconomicGrowth";
    private static final String PAGE_ID = "admin";

    private static final String DIRECTORY = "/economicGrowth/EconomicGrowth";

    @Resource(name = "commonService")
    private CommonService commonService;

    @Resource(name = "apiParseService")
    private ApiParseService apiParseService;

    @Resource(name = "economicGrowthService")
    private EconomicGrowthService economicGrowthService;

    @GetMapping("/main")
    public String economicGrowthMain(Model model) throws Exception {
        log.info(PAGE_ID + DIRECTORY + "List");
        commonService.selectList(null, PAGE_ID + PROGRAM_ID + ".selectStateDebt");
        model.addAttribute("menuCode", "001");
        return PAGE_ID + DIRECTORY + "Main";
    }

    //기준금리
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    @GetMapping("/api/getBaseRate")
    public Object getBaseRate(String url, String parameter) throws Exception {

        System.out.println(url);
        System.out.println(parameter);

        //kosis = json, enara = xml
        String format = "json";
        String site = "ecos";
        String message = "성공";
        StringBuilder stringBuilder = apiParseService.getApiResult(url, parameter, format, site);

        JSONArray jsonList = apiParseService.ecosApiJsonParser(stringBuilder, "StatisticSearch");
        System.out.println(jsonList);
        Map<String, Object> dataMap = new HashMap<>();
        Map<String, Object> result = new HashMap<>();

        for (Object jsonObject : jsonList) {
            JSONObject jsonData = (JSONObject) jsonObject;

            if ("Fail".equals(jsonData.get("RESULT"))) {
                dataMap.put("err", jsonData.get("CODE"));
                message = (String) jsonData.get("MESSAGE");
            } else {
                String baseMoneyRate = (String) jsonData.get("DATA_VALUE");
                String unit = (String) jsonData.get("UNIT_NAME");
                String date = (String) jsonData.get("TIME");
                String year = date.substring(0, 4);
                String month = date.substring(4, 6);
                String day = date.substring(6, 8);

                dataMap.put("yr_dt", year);
                dataMap.put("mon_dt", month);
                dataMap.put("dy_dt", day);
                dataMap.put("unit", unit);
                dataMap.put("val", baseMoneyRate);
                System.out.println(dataMap);

                commonService.insertContents(dataMap, PAGE_ID + PROGRAM_ID + ".insertBaseRate");
            }

        }

        result.put("data", dataMap);
        result.put("success", message);

        return result;
    }

    //환율
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    @GetMapping("/api/getExchangeRate")
    public Object getExchangeRate(String url, String parameter) throws Exception {

        System.out.println(url);
        System.out.println(parameter);

        String[] typeList = {"0000001", "0000053", "0000002", "0000003"};

        Map<String, Object> result = new HashMap<>();

        for (int i = 0; i < typeList.length; i++) {
            System.out.println(typeList[i]);
            if (i >= 1) {
                parameter = parameter.replace(typeList[i - 1], typeList[i]);
            }
            System.out.println(parameter);

            //kosis = json, enara = xml
            String format = "json";
            String site = "ecos";
            String message = "성공";
            StringBuilder stringBuilder = apiParseService.getApiResult(url, parameter, format, site);

            JSONArray jsonList = apiParseService.ecosApiJsonParser(stringBuilder, "StatisticSearch");
            System.out.println(jsonList);
            Map<String, Object> dataMap = new HashMap<>();

            for (Object jsonObject : jsonList) {
                JSONObject jsonData = (JSONObject) jsonObject;

                if ("Fail".equals(jsonData.get("RESULT"))) {
                    dataMap.put("err", jsonData.get("CODE"));
                    message = (String) jsonData.get("MESSAGE");
                } else {
                    String typeBf = jsonData.get("ITEM_NAME1").toString();
                    String type = null;

                    if (typeBf.contains("매매기준율")) {
                        type = typeBf.replace("(매매기준율)", "");
                    } else {
                        type = typeBf;
                    }
                    String exMoneyRate = (String) jsonData.get("DATA_VALUE");
                    String unit = (String) jsonData.get("UNIT_NAME");
                    String date = (String) jsonData.get("TIME");
                    String year = date.substring(0, 4);
                    String month = date.substring(4, 6);
                    String day = date.substring(6, 8);
                    dataMap.put("yr_dt", year);
                    dataMap.put("mon_dt", month);
                    dataMap.put("dy_dt", day);
                    if (type == "원/달러") {
                        dataMap.put("type", "원/미국달러");
                    } else if (type == "원/일본엔(100엔)") {
                        dataMap.put("type", "원/엔(100엔)");
                    } else {
                        dataMap.put("type", type);
                    }
                    dataMap.put("unit", unit);
                    dataMap.put("val", exMoneyRate);
                    System.out.println(dataMap);

                    commonService.insertContents(dataMap, PAGE_ID + PROGRAM_ID + ".insertExchangeRate");
                }
            }


            result.put("data", dataMap);
            result.put("success", message);
        }
        return result;
    }

    @ResponseBody
    @GetMapping("api/getStateDebtList")
    public Object getStateDebtList() throws Exception {
        Map<String, Object> dataMap = new HashMap<>();
        Map<String, Object> result = new HashMap<>();

        List<ArrayList> resultList = (List<ArrayList>) commonService.selectList(dataMap, PAGE_ID + PROGRAM_ID + ".selectStateDebt");

        result.put("title", "국가채무현황");
        result.put("datas", resultList);
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
        String message = "성공";

        StringBuilder stringBuilder = apiParseService.getApiResult(url, parameter, format, site);

        JSONArray jsonList = (JSONArray) apiParseService.apiJsonParser(stringBuilder);

        Map<String, Object> result = new HashMap<>();
        Map<String, Object> dataMap = new HashMap<>();

        for (Object jsonObject : jsonList) {
            JSONObject jsonData = (JSONObject) jsonObject;
            if ("Fail".equals(jsonData.get("RESULT"))) {
                dataMap.put("err", jsonData.get("err"));
                message = (String) jsonData.get("errMsg");
            } else {
                if ("국가채무".equals((String) jsonData.get("C1_NM"))) {
                    String year = (String) jsonData.get("PRD_DE");
                    String unit = (String) jsonData.get("UNIT_NM");
                    String val = (String) jsonData.get("DT");

                    dataMap.put("yr_dt", year);
                    dataMap.put("unit", unit);
                    dataMap.put("val", val);
                    message = "성공";

                    commonService.insertContents(dataMap, PAGE_ID + PROGRAM_ID + ".insertStateDebt");

                    break;
                }
            }
        }

        result.put("data", dataMap);
        result.put("success", message);


        return result;
    }

    // 경제활동별 GDI 및 GNI
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    @GetMapping("/api/getGdiAndGni")
    public Object getGdpAndGni(String url, String parameter) throws Exception {
        System.out.println(url);
        System.out.println(parameter);
        String format = "json";
        String site = "kosis";
        String message = "성공";

        String year = null;
        String unit = null;
        String val = null;

        StringBuilder stringbuilder = apiParseService.getApiResult(url, parameter, format, site);
        JSONArray jsonList = (JSONArray) apiParseService.apiJsonParser(stringbuilder);

        Map<String, Object> result = new HashMap<>();
        Map<String, Object> dataMap = new HashMap<>();

        for (Object jsonObject : jsonList) {
            JSONObject jsonData = (JSONObject) jsonObject;
            if ("Fail".equals(jsonData.get("RESULT"))) {
                dataMap.put("err", jsonData.get("err"));
                message = (String) jsonData.get("errMsg");
            } else {
                if ("국내총소득(GDI)".equals((String) jsonData.get("C1_NM"))) {
                    year = (String) jsonData.get("PRD_DE");
                    unit = (String) jsonData.get("UNIT_NM");
                    val = (String) jsonData.get("DT");
                } else if ("국민총소득(GNI)".equals((String) jsonData.get("C1_NM"))) {
                    year = (String) jsonData.get("PRD_DE");
                    unit = (String) jsonData.get("UNIT_NM");
                    val = (String) jsonData.get("DT");
                }
                dataMap.put("yr_dt", year);
                dataMap.put("unit", unit);
                dataMap.put("val", val);

                if ("국내총소득(GDI)".equals((String) jsonData.get("C1_NM"))) {
                    commonService.insertContents(dataMap, PAGE_ID + PROGRAM_ID + ".insertGDI");
                } else if ("국민총소득(GNI)".equals((String) jsonData.get("C1_NM"))) {
                    commonService.insertContents(dataMap, PAGE_ID + PROGRAM_ID + ".insertGNI");
                }
            }
        }
        result.put("data", dataMap);
        result.put("success", message);

        return result;
    }

    //  GDP
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    @GetMapping("/api/getGdp")
    public Object getGdp(String url, String parameter) throws Exception {

        System.out.println(parameter);
        System.out.println(url);

        //kosis = json, enara = xml
        String format = "json";
        String site = "ecos";
        String message = "성공";

        String[] tblCd = {"200Y009", "200Y010"};

        Map<String, Object> result = new HashMap<>();

        for (int i = 0; i < tblCd.length; i++) {
            Map<String, Object> dataMap = new HashMap<>();

            if(i == 1) {

                String[] splitParams = parameter.split(tblCd[0]);

                parameter = splitParams[0] + tblCd[1] + splitParams[1];

            }

            StringBuilder stringBuilder = apiParseService.getApiResult(url, parameter, format, site);

            JSONArray jsonList = apiParseService.ecosApiJsonParser(stringBuilder, "StatisticSearch");

            for (Object jsonObject : jsonList) {
                JSONObject jsonData = (JSONObject) jsonObject;

                if ("Fail".equals(jsonData.get("RESULT"))) {
                    dataMap.put("err", jsonData.get("CODE"));
                    message = (String) jsonData.get("MESSAGE");
                } else {
                    String GDP = (String) jsonData.get("DATA_VALUE");
                    String unit = (String) jsonData.get("UNIT_NAME");
                    String yrDt = (String) jsonData.get("TIME");

                    dataMap.put("yr_dt", yrDt);
                    dataMap.put("unit", unit);
                    dataMap.put("val", GDP);
                    System.out.println(dataMap);

                    if(i == 1){
                        commonService.insertContents(dataMap, PAGE_ID + PROGRAM_ID + ".insertNmnlGDP");
                    }else {
                        commonService.insertContents(dataMap, PAGE_ID + PROGRAM_ID + ".insertRealGDP");
                    }
                }
            }
        }

        result.put("success", message);

        return result;
    }

    // 경제성장률
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    @GetMapping("/api/getGrowthRate")
    public Object getGrowthRate(String url, String parameter) throws Exception {
        System.out.println(url);
        System.out.println(parameter);
        String format = "json";
        String site = "kosis";
        String message = "성공";


        StringBuilder stringBuilder = apiParseService.getApiResult(url, parameter, format, site);
        JSONArray jsonArray = (JSONArray) apiParseService.apiJsonParser(stringBuilder);

        Map<String, Object> dataMap = new HashMap<>();
        Map<String, Object> result = new HashMap<>();

        for (Object jsonObject : jsonArray) {
            JSONObject jsonData = (JSONObject) jsonObject;

            if ("Fail".equals(jsonData.get("RESULT"))) {
                dataMap.put("err", jsonData.get("err"));
                message = (String) jsonData.get("errMsg");

            } else {
                String yrdt = (String) jsonData.get("PRD_DE");
                String city = (String) jsonData.get("C1_NM");
                String unit = (String) jsonData.get("UNIT_NM");
                String value = (String) jsonData.get("DT");

                dataMap.put("yr_dt", yrdt);
                dataMap.put("cty_nm", city);
                dataMap.put("unit", unit);
                dataMap.put("val", value);

                commonService.insertContents(dataMap, PAGE_ID + PROGRAM_ID + ".insertGrowthRate");
            }
        }

        result.put("data", dataMap);
        result.put("success", message);

        return result;
    }

    //  국제 경제 성장률
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    @GetMapping("/api/getGrowthRateInternational")
    public Object getGrowthRateInternational(String url, String parameter) throws Exception {

        System.out.println(parameter);
        System.out.println(url);

        //kosis = json, enara = xml
        String format = "json";
        String site = "ecos";
        String message = "성공";

        String[] countryList = {"KOR", "ISR","FRA","CHN","AUS","CAN","JPN","DEU","GBR","USA"};

        Map<String, Object> result = new HashMap<>();

        for (int i = 0; i < countryList.length; i++) {
            Map<String, Object> dataMap = new HashMap<>();

            if (i >= 1) {
                parameter = parameter.replace(countryList[i - 1], countryList[i]);
            }

            StringBuilder stringBuilder = apiParseService.getApiResult(url, parameter, format, site);

            JSONArray jsonList = apiParseService.ecosApiJsonParser(stringBuilder, "StatisticSearch");

            for (Object jsonObject : jsonList) {
                JSONObject jsonData = (JSONObject) jsonObject;

                if ("Fail".equals(jsonData.get("RESULT"))) {
                    dataMap.put("err", jsonData.get("CODE"));
                    message = (String) jsonData.get("MESSAGE");
                } else {
                    String val = (String) jsonData.get("DATA_VALUE");
                    String unit = (String) jsonData.get("UNIT_NAME");
                    String yrDt = (String) jsonData.get("TIME");
                    String countryName = (String) jsonData.get("ITEM_NAME1");

                    dataMap.put("yrDt", yrDt);
                    dataMap.put("unit", unit);
                    dataMap.put("val", val);
                    dataMap.put("cntryNm", countryName);

                    commonService.insertContents(dataMap, PAGE_ID + PROGRAM_ID + ".insertGrowthRateInternational");
                }
            }
        }

        result.put("success", message);

        return result;
    }

    //  국제 물가상승률
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    @GetMapping("/api/getPriceIncreaseInternational")
    public Object getPriceIncreaseInternational(String url, String parameter) throws Exception {

        System.out.println(parameter);
        System.out.println(url);

        //kosis = json, enara = xml
        String format = "json";
        String site = "ecos";
        String message = "성공";

        String[] countryList = {"KR", "IL","FR","CA","JP","DE","GB","US"};

        Map<String, Object> result = new HashMap<>();

        for (int i = 0; i < countryList.length; i++) {
            Map<String, Object> dataMap = new HashMap<>();

            if (i >= 1) {
                parameter = parameter.replace(countryList[i - 1], countryList[i]);
            }

            StringBuilder stringBuilder = apiParseService.getApiResult(url, parameter, format, site);

            JSONArray jsonList = apiParseService.ecosApiJsonParser(stringBuilder, "StatisticSearch");

            for (Object jsonObject : jsonList) {
                JSONObject jsonData = (JSONObject) jsonObject;

                if ("Fail".equals(jsonData.get("RESULT"))) {
                    dataMap.put("err", jsonData.get("CODE"));
                    message = (String) jsonData.get("MESSAGE");
                } else {
                    String val = (String) jsonData.get("DATA_VALUE");
                    String unit = (String) jsonData.get("UNIT_NAME");
                    String period = (String) jsonData.get("TIME");
                    String yrDt = period.substring(0, 4);
                    String monDt = period.substring(4);
                    String countryName = (String) jsonData.get("ITEM_NAME1");

                    dataMap.put("yrDt", yrDt);
                    dataMap.put("monDt", monDt);
                    dataMap.put("unit", unit);
                    dataMap.put("val", val);
                    dataMap.put("cntryNm", countryName);

                    System.out.println(dataMap);

                    commonService.insertContents(dataMap, PAGE_ID + PROGRAM_ID + ".insertPriceIncreaseInternational");
                }
            }
        }

        result.put("success", message);

        return result;
    }

    //    소비자/근원/생활 물가 상승률
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    @GetMapping("/api/getInflationRate")
    public Object getInflationRate(String url, String parameter) throws Exception {
        String format = "json";
        String site = "kosis";
        System.out.println(parameter);

        Map<String, String> splitParams = economicGrowthService.splitParmas(parameter);

        Map<String, Object> dataMap = new HashMap<>();
        Map<String, Object> result = new HashMap<>();

//        for(int year = 1966; year <= 2022; year++){
//            for (int month = 1; month <= 12; month++) {
//                String mon_dt = null;
//                if(year == 2022 && month == 7){
//                    break;
//                }
//                if(month < 10){
//                    mon_dt = "0" + Integer.toString(month);
//                }else{
//                    mon_dt = Integer.toString(month);
//                }
//                parameter = economicGrowthService.combineParams(splitParams, Integer.toString(year), mon_dt);
//                System.out.println(parameter);
//                StringBuilder stringBuilder = apiParseService.getApiResult(url, parameter, format, site);
//                JSONArray jsonArray = (JSONArray) apiParseService.apiJsonParser(stringBuilder);
//
//
//                String years = null;
//                String months = null;
//                String val = null;
//                String unit = null;
//
//                for(Object jsonObject : jsonArray){
//                    JSONObject jsonData = (JSONObject) jsonObject;
//
//                    years = jsonData.get("PRD_DE").toString().substring(0, 4);
//                    months = jsonData.get("PRD_DE").toString().substring(4,6);
//                    val = (String) jsonData.get("DT");
//                    unit = (String) jsonData.get("ITM_NM");
//
//                    dataMap.put("val",val);
//                    dataMap.put("yr_dt",years);
//                    dataMap.put("mon_dt",months);
//                    dataMap.put("unit", unit);
//
//                    if("총지수".equals(jsonData.get("C1_NM"))){
//                        commonService.insertContents(dataMap, PAGE_ID + PROGRAM_ID + ".insertConsumerPriceInflation");
//                    }else if("생활물가지수".equals(jsonData.get("C1_NM"))){
//                        commonService.insertContents(dataMap, PAGE_ID + PROGRAM_ID + ".insertLivingInflationRate");
//                    }else if("농산물및석유류제외지수".equals(jsonData.get("C1_NM"))){
//                        commonService.insertContents(dataMap, PAGE_ID + PROGRAM_ID + ".insertCoreInflationRate");
//                    }
//                }
//
//                result.put("data",dataMap);
//                result.put("success", "성공");
//
//            }
//        }

        StringBuilder stringBuilder = apiParseService.getApiResult(url, parameter, format, site);
        JSONArray jsonArray = (JSONArray) apiParseService.apiJsonParser(stringBuilder);

        String year = null;
        String month = null;
        String val = null;
        String unit = null;
        String message = "성공";

        for (Object jsonObject : jsonArray) {
            JSONObject jsonData = (JSONObject) jsonObject;

            if ("Fail".equals(jsonData.get("RESULT"))) {
                dataMap.put("err", jsonData.get("err"));
                message = (String) jsonData.get("errMsg");

            } else {
                year = jsonData.get("PRD_DE").toString().substring(0, 4);
                month = jsonData.get("PRD_DE").toString().substring(4, 6);
                val = (String) jsonData.get("DT");
                unit = (String) jsonData.get("ITM_NM");

                dataMap.put("val", val);
                dataMap.put("yr_dt", year);
                dataMap.put("mon_dt", month);
                dataMap.put("unit", unit);

                if ("총지수".equals(jsonData.get("C1_NM"))) {
                    commonService.insertContents(dataMap, PAGE_ID + PROGRAM_ID + ".insertConsumerPriceInflation");
                } else if ("생활물가지수".equals(jsonData.get("C1_NM"))) {
                    commonService.insertContents(dataMap, PAGE_ID + PROGRAM_ID + ".insertLivingInflationRate");
                } else if ("농산물및석유류제외지수".equals(jsonData.get("C1_NM"))) {
                    commonService.insertContents(dataMap, PAGE_ID + PROGRAM_ID + ".insertCoreInflationRate");
                }
            }
        }

        result.put("data", dataMap);
        result.put("success", message);

        return result;
    }
}
