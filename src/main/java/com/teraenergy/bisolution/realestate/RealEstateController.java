package com.teraenergy.bisolution.realestate;


import com.teraenergy.global.common.utilities.AgeUtil;
import com.teraenergy.global.common.utilities.AreaNameUtil;
import com.teraenergy.global.service.CommonService;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/realEstate")
public class RealEstateController {

    private static final String PROGRAM_ID = "RealEstate";

    private static final String DIRECTORY = "realEstate/";

    @Resource(name = "commonService")
    private CommonService commonService;

    @Resource(name = "realEstateService")
    private RealEstateService realEstateService;

    private static final String FORMAT = "json";
    private static final String SITE = "kosis";

    @GetMapping("/main")
    public String realEstateMain() throws Exception {
        log.info(DIRECTORY + PROGRAM_ID + "List");
        RealEstateDTO realEstateDTO = new RealEstateDTO();
//        List<RealEstateDTO> list = (List<RealEstateDTO>) commonService.selectList(realEstateDTO,PROGRAM_ID + ".getTradeRealAptList");
        return DIRECTORY + PROGRAM_ID + "Main";
    }

    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    @GetMapping("/api/genderPopulation")
    public Object getGenderPopulation(String url, String parameter) throws Exception {

        Map<String, Object> result = new HashMap<>();
        Map<String, Object> dataMap = new HashMap<>();

        Map<String, String> splitParams = realEstateService.splitParameter(parameter);

        for (int month = 1; month <= 12; month++) {

            parameter = realEstateService.stringCombination(splitParams, month);

            StringBuilder stringBuilder = commonService.getApiResult(url, parameter, FORMAT, SITE);
            JSONArray jsonList = (JSONArray) commonService.apiJsonParser(stringBuilder);


            for (Object jsonObject : jsonList) {
                JSONObject jsonData = (JSONObject) jsonObject;

                String year = (String) jsonData.get("PRD_DE");
                year = year.substring(0, 4);
                String getMonth = (String) jsonData.get("PRD_DE");
                getMonth = getMonth.substring(4, 6);

                String gender = (String) jsonData.get("ITM_NM_ENG");
                gender = gender.contains("Male") ? "남" : "여";
                String ctyName = AreaNameUtil.areaName((String) jsonData.get("C1"), "");
                String areaCd = (String) jsonData.get("C1");

                dataMap.put("yrDt", year);
                dataMap.put("monDt", getMonth);
                dataMap.put("gender", gender);
                dataMap.put("ctyNm", ctyName);
                dataMap.put("areaCd", areaCd);
                dataMap.put("dstNm", jsonData.get("C1_NM"));
                dataMap.put("unit", jsonData.get("UNIT_NM"));
                dataMap.put("val", jsonData.get("DT"));

                //세종특별자치시 중복 제외
                if (!"36110".equals(areaCd)) {
                    commonService.insertContents(dataMap, PROGRAM_ID + ".insertGenderPopulation");
                }

            }
        }

        result.put("data", dataMap);
        result.put("success", "성공");
        return result;
    }

    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    @GetMapping("/api/aptSalesStatus")
    public Object getAptSalesStatus(String url, String parameter) throws Exception {

        Map<String, Object> result = new HashMap<>();
        Map<String, Object> dataMap = new HashMap<>();

        Map<String, String> splitParams = realEstateService.splitParameter(parameter);

        for (int month = 1; month <= 12; month++) {

            parameter = realEstateService.stringCombination(splitParams, month);

            StringBuilder stringBuilder = commonService.getApiResult(url, parameter, FORMAT, SITE);
            JSONArray jsonList = (JSONArray) commonService.apiJsonParser(stringBuilder);

            for (Object jsonObject : jsonList) {
                JSONObject jsonData = (JSONObject) jsonObject;

                String year = (String) jsonData.get("PRD_DE");
                year = year.substring(0, 4);
                String getMonth = (String) jsonData.get("PRD_DE");
                getMonth = getMonth.substring(4, 6);

                String ctyName = AreaNameUtil.areaName((String) jsonData.get("C1"), "");

                dataMap.put("yrDt", year);
                dataMap.put("monDt", getMonth);
                dataMap.put("ctyNm", ctyName);
                dataMap.put("dstNm", jsonData.get("C1_NM"));
                dataMap.put("unit", jsonData.get("UNIT_NM"));
                dataMap.put("val", jsonData.get("DT"));

                String areaCd = (String) jsonData.get("C1");
                //세종특별자치시 중복 제외
                if (!"13102114448A.00090001".equals(areaCd)) {
                    commonService.insertContents(dataMap, PROGRAM_ID + ".insertAptSalesStatus");
                }
            }
        }

        result.put("data", dataMap);
        result.put("success", "성공");
        return result;
    }

    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    @GetMapping("/api/unsoldHouse")
    public Object getUnsoldHouse(String url, String parameter) throws Exception {

        Map<String, Object> result = new HashMap<>();
        Map<String, Object> dataMap = new HashMap<>();

        Map<String, String> splitParams = realEstateService.splitParameter(parameter);

        for (int month = 1; month <= 12; month++) {

            parameter = realEstateService.stringCombination(splitParams, month);

            StringBuilder stringBuilder = commonService.getApiResult(url, parameter, FORMAT, SITE);
            JSONArray jsonList = (JSONArray) commonService.apiJsonParser(stringBuilder);

            for (Object jsonObject : jsonList) {
                JSONObject jsonData = (JSONObject) jsonObject;

                String year = (String) jsonData.get("PRD_DE");
                year = year.substring(0, 4);
                String getMonth = (String) jsonData.get("PRD_DE");
                getMonth = getMonth.substring(4, 6);

                String ctyName = AreaNameUtil.areaName((String) jsonData.get("C1"), "other");

                dataMap.put("yrDt", year);
                dataMap.put("monDt", getMonth);
                dataMap.put("ctyNm", ctyName);
                dataMap.put("dstNm", jsonData.get("C2_NM"));
                dataMap.put("unit", jsonData.get("UNIT_NM"));
                dataMap.put("val", jsonData.get("DT"));

                commonService.insertContents(dataMap, PROGRAM_ID + ".insertUnsoldHouse");
            }
        }

        result.put("data", dataMap);
        result.put("success", "성공");
        return result;
    }

    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    @GetMapping("/api/ageAptSales")
    public Object getAgeAptSales(String url, String parameter) throws Exception {

        Map<String, Object> result = new HashMap<>();
        Map<String, Object> dataMap = new HashMap<>();

        Map<String, String> splitParams = realEstateService.splitParameter(parameter);

        for (int month = 1; month <= 12; month++) {

            parameter = realEstateService.stringCombination(splitParams, month);

            StringBuilder stringBuilder = commonService.getApiResult(url, parameter, FORMAT, SITE);
            JSONArray jsonList = (JSONArray) commonService.apiJsonParser(stringBuilder);

            for (Object jsonObject : jsonList) {
                JSONObject jsonData = (JSONObject) jsonObject;

                String year = (String) jsonData.get("PRD_DE");
                year = year.substring(0, 4);
                String getMonth = (String) jsonData.get("PRD_DE");
                getMonth = getMonth.substring(4, 6);

                String ctyName = AreaNameUtil.areaName((String) jsonData.get("C1"), "");

                dataMap.put("yrDt", year);
                dataMap.put("monDt", getMonth);
                dataMap.put("age", jsonData.get("C2_NM"));
                dataMap.put("ctyNm", ctyName);
                dataMap.put("dstNm", jsonData.get("C1_NM"));
                dataMap.put("unit", jsonData.get("UNIT_NM"));
                dataMap.put("val", jsonData.get("DT"));

                String areaCd = (String) jsonData.get("C1");
//                세종특별자치시 중복 제거
                if (!"13102130735A.00090001".equals(areaCd)) {
                    commonService.insertContents(dataMap, PROGRAM_ID + ".insertAgeAptSales");
                }
            }
        }

        result.put("data", dataMap);
        result.put("success", "성공");
        return result;
    }

    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    @GetMapping("/api/populationAge")
    public Object getPopulationAge(String url, String parameter) throws Exception {
        url = "https://kosis.kr/openapi/Param/statisticsParameterData.do";
        parameter = "?method=getList&apiKey=&itmId=T3+T4+&objL1=00+50+50110+50130+&objL2=ALL&objL3=&objL4=&objL5=&objL6=&objL7=&objL8=&format=json&jsonVD=Y&prdSe=M&startPrdDe=2022&endPrdDe=2022&loadGubun=2&orgId=101&tblId=DT_1B04005N";
        Map<String, Object> result = new HashMap<>();
        Map<String, String> splitParams = realEstateService.splitParameter(parameter);
        List<Map<String, Object>> popList = new ArrayList<>();

        String[] ages = {"계","10대","20대","30대","40대","50대","60대","70대","80대","90대","100세이상"};
        Map<String, Object> population = new HashMap<>();

        Map<String, Object> region = new HashMap<>();



        List<Map<String, Object>> dataList = new ArrayList<>();
        List<Map<String, Object>> dataList_1 = new ArrayList<>();
        for (int month = 1; month <= 1; month++) {
            parameter = realEstateService.stringCombination(splitParams, month);

            StringBuilder stringBuilder = commonService.getApiResult(url, parameter, FORMAT, SITE);
            JSONArray jsonList = (JSONArray) commonService.apiJsonParser(stringBuilder);

            for (Object jsonObject : jsonList) {
                Map<String, Object> dataMap = new HashMap<>();
                Map<String, Object> dataMap_1 = new HashMap<>();
                JSONObject jsonData = (JSONObject) jsonObject;

                String year = (String) jsonData.get("PRD_DE");
                year = year.substring(0, 4);
                String getMonth = (String) jsonData.get("PRD_DE");
                getMonth = getMonth.substring(4, 6);

                String ctyName = AreaNameUtil.areaName((String) jsonData.get("C1"), "");
                String ageName = AgeUtil.getAgeName((String) jsonData.get("C2"));

                if(jsonData.get("ITM_NM").toString().contains("여자")) {
                    dataMap.put("yrDt", year);
                    dataMap.put("monDt", getMonth);
                    dataMap.put("age", ageName);
                    dataMap.put("itmNm", jsonData.get("ITM_NM"));
                    dataMap.put("ctyNm", ctyName);
                    dataMap.put("dstNm", jsonData.get("C1_NM"));
                    dataMap.put("womanCnt", jsonData.get("DT"));
                    dataList.add(dataMap);
                } else{
                    dataMap_1.put("unit", jsonData.get("UNIT_NM"));
                    dataMap_1.put("yrDt", year);
                    dataMap_1.put("monDt", getMonth);
                    dataMap_1.put("age", ageName);
                    dataMap_1.put("itmNm", jsonData.get("ITM_NM"));
                    dataMap_1.put("ctyNm", ctyName);
                    dataMap_1.put("dstNm", jsonData.get("C1_NM"));
                    dataMap_1.put("unit", jsonData.get("UNIT_NM"));
                    dataMap_1.put("manCnt", jsonData.get("DT"));
                    dataList_1.add(dataMap_1);
                }


                String areaCd = (String) jsonData.get("C1");
//                세종특별자치시 중복 제거
//                if (!"36110".equals(areaCd)) {
//                    dataList.add(dataMap);
//                    //commonService.insertContents(dataMap, PROGRAM_ID + ".insertPopulationAge");
//                }
            }
            for (int i = 0; i < ages.length; i++) {
                int years = 0;
                Map<String, Object> dataMap = new HashMap<>();

                for (int j = 0; j < dataList.size(); j++) {
                    if(i >= 1) {
                        if (ages[i].equals(dataList.get(j).get("age")) && dataList.get(j).get("ctyNm").equals(dataList.get(j + 1).get("ctyNm")) && dataList.get(j).get("dstNm").equals(dataList.get(j + 1).get("dstNm"))) {
                            years += Integer.parseInt((String) dataList.get(j).get("womanCnt"));
                            dataMap.put("yrDt", dataList.get(j).get("yrDt"));
                            dataMap.put("monDt", dataList.get(j).get("monDt"));
                            dataMap.put("age", dataList.get(j).get("age"));
                            dataMap.put("itmNm", dataList.get(j).get("itmNm"));
                            dataMap.put("ctyNm", dataList.get(j).get("ctyNm"));
                            dataMap.put("dstNm", dataList.get(j).get("dstNm"));
                            dataMap.put("womanCnt", years);
                        }
                    }
                    popList.add(dataMap);
                }
            }

            for (int i = 0; i < dataList_1.size(); i++) {

            }
        }

        result.put("data", popList);
        result.put("success", "성공");
        return result;
    }
}

