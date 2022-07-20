package com.teraenergy.bisolution.admin.realestate;


import com.teraenergy.global.common.utilities.AgeUtil;
import com.teraenergy.global.common.utilities.AreaNameUtil;
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
 * 부동산시장동향 api 호출 및 db 적재
 *
 * @author tera
 * @version 1.0.0
 * 작성일 2022-07-19
 **/
@Slf4j
@Controller
@RequestMapping("/admin/realEstate")
public class RealEstateController {

    private static final String PROGRAM_ID = ".RealEstate";
    private static final String PAGE_ID = "admin";
    private static final String DIRECTORY = "/realEstate/RealEstate";
    private static final String FORMAT = "json";
    private static final String SITE = "kosis";
    @Resource(name = "commonService")
    private CommonService commonService;
    @Resource(name = "realEstateService")
    private RealEstateService realEstateService;

    @GetMapping("/main")
    public String realEstateMain() throws Exception {
        log.info(PAGE_ID + DIRECTORY + "Main");
        RealEstateDTO realEstateDTO = new RealEstateDTO();
//        List<RealEstateDTO> list = (List<RealEstateDTO>) commonService.selectList(realEstateDTO,PAGE_ID + PROGRAM_ID + ".getTradeRealAptList");
        return PAGE_ID + DIRECTORY + "Main";
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

                String date = (String) jsonData.get("PRD_DE");
                String year = date.substring(0, 4);
                String getMonth = date.substring(4, 6);

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
                    commonService.insertContents(dataMap, PAGE_ID + PROGRAM_ID + ".insertGenderPopulation");
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

                String date = (String) jsonData.get("PRD_DE");
                String year = date.substring(0, 4);
                String getMonth = date.substring(4, 6);

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
                    commonService.insertContents(dataMap, PAGE_ID + PROGRAM_ID + ".insertAptSalesStatus");
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

                String date = (String) jsonData.get("PRD_DE");
                String year = date.substring(0, 4);
                String getMonth = date.substring(4, 6);

                String ctyName = AreaNameUtil.areaName((String) jsonData.get("C1"), "other");

                dataMap.put("yrDt", year);
                dataMap.put("monDt", getMonth);
                dataMap.put("ctyNm", ctyName);
                dataMap.put("dstNm", jsonData.get("C2_NM"));
                dataMap.put("unit", jsonData.get("UNIT_NM"));
                dataMap.put("val", jsonData.get("DT"));

                commonService.insertContents(dataMap, PAGE_ID + PROGRAM_ID + ".insertUnsoldHouse");
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

                String date = (String) jsonData.get("PRD_DE");
                String year = date.substring(0, 4);
                String getMonth = date.substring(4, 6);

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
                    commonService.insertContents(dataMap, PAGE_ID + PROGRAM_ID + ".insertAgeAptSales");
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
//        url = "https://kosis.kr/openapi/Param/statisticsParameterData.do";
//        parameter = "?method=getList&apiKey=&itmId=T3+T4+&objL1=00+50+50110+50130+&objL2=ALL&objL3=&objL4=&objL5=&objL6=&objL7=&objL8=&format=json&jsonVD=Y&prdSe=M&startPrdDe=2022&endPrdDe=2022&loadGubun=2&orgId=101&tblId=DT_1B04005N";
        Map<String, Object> result = new HashMap<>();
        Map<String, String> splitParams = realEstateService.splitParameter(parameter);

        List<Map<String, Object>> dataList = new ArrayList<>();
        for (int month = 1; month <= 12; month++) {
            parameter = realEstateService.stringCombination(splitParams, month);

            StringBuilder stringBuilder = commonService.getApiResult(url, parameter, FORMAT, SITE);
            JSONArray jsonList = (JSONArray) commonService.apiJsonParser(stringBuilder);

            for (Object jsonObject : jsonList) {
                Map<String, Object> dataMap = new HashMap<>();
                JSONObject jsonData = (JSONObject) jsonObject;

                String date = (String) jsonData.get("PRD_DE");
                String year = date.substring(0, 4);
                String getMonth = date.substring(4, 6);

                String ctyName = AreaNameUtil.areaName((String) jsonData.get("C1"), "");
                String ageName = AgeUtil.getAgeName((String) jsonData.get("C2"));

                dataMap.put("yrDt", year);
                dataMap.put("monDt", getMonth);
//                dataMap.put("age", ageName);
                dataMap.put("age", jsonData.get("C2"));
                dataMap.put("itmNm", jsonData.get("ITM_NM"));
                dataMap.put("ctyNm", ctyName);
                dataMap.put("dstNm", jsonData.get("C1_NM"));
                dataMap.put("unit", jsonData.get("UNIT_NM"));
                dataMap.put("tmpCnt", jsonData.get("DT"));

                String areaCd = (String) jsonData.get("C1");
                dataMap.put("areaCd", areaCd);
//                세종특별자치시 중복 제거
                if (!"36110".equals(areaCd)) {
                    dataList.add(dataMap);
                    commonService.insertContents(dataMap, PAGE_ID + PROGRAM_ID + ".insertPopulationAge");
                }
            }
        }
//        Map<String, Object> tmpMap = new HashMap<>();
//        commonService.insertContents(tmpMap, PAGE_ID + PROGRAM_ID + ".insertManPopulation");
//        commonService.updateContents(tmpMap, PAGE_ID + PROGRAM_ID + ".updateWomanPopulation");

        result.put("data", dataList);
        result.put("success", "성공");
        return result;
    }

    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    @GetMapping("/populationAgeDivision")
    public Object populationAgeDivision() throws Exception {

        Map<String, Object> temp = new HashMap<>();
        commonService.insertContents(temp, PAGE_ID + PROGRAM_ID + ".insertManPopulation");
        commonService.updateContents(temp, PAGE_ID + PROGRAM_ID + ".updateWomanPopulation");
        commonService.deleteContents(temp, PAGE_ID + PROGRAM_ID + ".deletePopulationTmp");

        temp.put("success", "성공");
        return temp;
    }

    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    @GetMapping("/api/grp")
    public Object getGrossRegionalProduct(String url, String parameter) throws Exception {
        Map<String, Object> result = new HashMap<>();
        List<Map<String, Object>> dataList = new ArrayList<>();
        StringBuilder stringBuilder = commonService.getApiResult(url, parameter, FORMAT, SITE);
        JSONArray jsonList = (JSONArray) commonService.apiJsonParser(stringBuilder);

        for (Object jsonObject : jsonList) {
            Map<String, Object> dataMap = new HashMap<>();
            JSONObject jsonData = (JSONObject) jsonObject;

            dataMap.put("yrDt", jsonData.get("PRD_DE"));
            dataMap.put("ctyNm", jsonData.get("C1_NM"));
            dataMap.put("unit", String.valueOf(jsonData.get("UNIT_NM")).substring(0, 3));
            dataMap.put("val", jsonData.get("DT"));

            dataList.add(dataMap);
            commonService.insertContents(dataMap, PAGE_ID + PROGRAM_ID + ".insertGrp");
        }

        result.put("data", dataList);
        result.put("success", "성공");
        return result;
    }
}