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
        parameter = "?method=getList&apiKey=&itmId=T3+T4+&objL1=00+11+11110+11140+11170+11200+11215+11230+11260+11290+11305+11320+11350+11380+11410+11440+11470+11500+11530+11545+11560+11590+11620+11650+11680+11710+11740+26+26110+26140+26170+26200+26230+26260+26290+26320+26350+26380+26410+26440+26470+26500+26530+26710+27+27110+27140+27170+27200+27230+27260+27290+27710+28+28110+28140+28170+28177+28185+28200+28237+28245+28260+28710+28720+29+29110+29140+29155+29170+29200+30+30110+30140+30170+30200+30230+31+31110+31140+31170+31200+31710+36+36110+41+41105+41110+41111+41113+41115+41117+41130+41131+41133+41135+41150+41170+41171+41173+41190+41195+41197+41199+41210+41220+41250+41270+41271+41273+41280+41281+41285+41287+41290+41310+41360+41370+41390+41410+41430+41450+41460+41461+41463+41465+41480+41500+41550+41570+41590+41610+41630+41650+41670+41730+41800+41820+41830+42+42105+42110+42130+42150+42170+42190+42210+42230+42720+42730+42750+42760+42770+42780+42790+42800+42810+42820+42830+43+43110+43111+43112+43113+43114+43130+43150+43710+43720+43730+43740+43745+43750+43760+43770+43800+44+44130+44131+44133+44150+44180+44200+44210+44230+44250+44270+44710+44730+44760+44770+44790+44800+44810+44825+44830+45+45110+45111+45113+45130+45140+45180+45190+45210+45710+45720+45730+45740+45750+45770+45790+45800+46+46110+46130+46150+46170+46230+46710+46720+46730+46770+46780+46790+46800+46810+46820+46830+46840+46860+46870+46880+46890+46900+46910+47+47110+47111+47113+47130+47150+47170+47190+47210+47230+47250+47280+47290+47720+47730+47750+47760+47770+47820+47830+47840+47850+47900+47920+47930+47940+48+48120+48121+48123+48125+48127+48129+48170+48220+48240+48250+48270+48310+48330+48720+48730+48740+48820+48840+48850+48860+48870+48880+48890+50+50110+50130+&objL2=ALL&objL3=&objL4=&objL5=&objL6=&objL7=&objL8=&format=json&jsonVD=Y&prdSe=M&startPrdDe=2022&endPrdDe=2022&loadGubun=2&orgId=101&tblId=DT_1B04005N";
        Map<String, Object> result = new HashMap<>();
        Map<String, String> splitParams = realEstateService.splitParameter(parameter);

        List<Map<String, Object>> dataList = new ArrayList<>();
        for (int month = 1; month <= 1; month++) {
            parameter = realEstateService.stringCombination(splitParams, month);

            StringBuilder stringBuilder = commonService.getApiResult(url, parameter, FORMAT, SITE);
            JSONArray jsonList = (JSONArray) commonService.apiJsonParser(stringBuilder);

            for (Object jsonObject : jsonList) {
                Map<String, Object> dataMap = new HashMap<>();
                JSONObject jsonData = (JSONObject) jsonObject;

                String year = (String) jsonData.get("PRD_DE");
                year = year.substring(0, 4);
                String getMonth = (String) jsonData.get("PRD_DE");
                getMonth = getMonth.substring(4, 6);

                String ctyName = AreaNameUtil.areaName((String) jsonData.get("C1"), "");
                String ageName = AgeUtil.getAgeName((String) jsonData.get("C2"));

                dataMap.put("yrDt", year);
                dataMap.put("monDt", getMonth);
                dataMap.put("age", ageName);
                dataMap.put("itmNm", jsonData.get("ITM_NM"));
                dataMap.put("ctyNm", ctyName);
                dataMap.put("dstNm", jsonData.get("C1_NM"));
                dataMap.put("unit", jsonData.get("UNIT_NM"));
                dataMap.put("manCnt", jsonData.get("DT"));
                dataMap.put("womanCnt", jsonData.get("DT"));

                String areaCd = (String) jsonData.get("C1");
//                세종특별자치시 중복 제거
                if (!"36110".equals(areaCd)) {
                    dataList.add(dataMap);
                    //commonService.insertContents(dataMap, PROGRAM_ID + ".insertPopulationAge");
                }
            }
        }

        result.put("data", dataList);
        result.put("success", "성공");
        return result;
    }
}

