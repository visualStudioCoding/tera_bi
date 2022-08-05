package com.teraenergy.bisolution.admin.StandardOfLiving;

import com.teraenergy.global.service.ApiParseService;
import com.teraenergy.global.service.CommonService;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class StandardOfLivingScheduler {

    private static final String PROGRAM_ID = ".StandardOfLiving";
    private static final String PAGE_ID = "admin";

    @Resource(name = "commonService")
    private CommonService commonService;
    @Resource(name = "apiParseService")
    private ApiParseService apiParseService;

    //   1인당 개인소득
    @Scheduled(cron = "0 0 0 18 3 *")
    @Transactional(rollbackFor = Exception.class)
    public void capitaPersonalScheduler() throws Exception {

        String url = "https://kosis.kr/openapi/Param/statisticsParameterData.do";
        String parameter = "method=getList&apiKey=&itmId=T3+&objL1=ALL&objL2=&objL3=&objL4=&objL5=&objL6=&objL7=&objL8=&format=json&jsonVD=Y&prdSe=Y&newEstPrdCnt=1&loadGubun=2&orgId=101&tblId=INH_1C86_04";
        String format = "json";
        String site = "kosis";

        StringBuilder stringBuilder = apiParseService.getApiResult(url, parameter, format, site);

        Map<String, Object> dataMap = new HashMap<>();
        Map<String, String> compareCapitalPersonal = (Map<String, String>) commonService.selectContents(null, PAGE_ID + PROGRAM_ID + ".compareCapitalPersonal");

        JSONArray jsonList = (JSONArray) apiParseService.apiJsonParser(stringBuilder);

        for (Object jsonObject : jsonList) {
            JSONObject jsonData = (JSONObject) jsonObject;

            String yr_dt = (String) jsonData.get("PRD_DE");
            String cty_nm = (String) jsonData.get("C1_NM");
            String unit = (String) jsonData.get("UNIT_NM");
            String val = (String) jsonData.get("DT");

            if (compareCapitalPersonal.get("yr_dt").equals(yr_dt)) {
                break;
            }

            dataMap.put("yr_dt", yr_dt);
            dataMap.put("cty_nm", cty_nm);
            dataMap.put("unit", unit);
            dataMap.put("val", val);

            commonService.insertContents(dataMap, PAGE_ID + PROGRAM_ID + ".insertCapitaPersonal");
        }
    }

    //   1인당 국민 총 소득
    @Scheduled(cron="0 0 16 31 3 *")
    @Transactional(rollbackFor = Exception.class)
    public void grossNationalIncomeScheduler() throws Exception {

        String url = "http://www.index.go.kr/openApi/xml_stts.do";
        String parameter = "?userId=&statsCode=422101";
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

            if (data.get("주기").equals(data_l.get("주기"))) {
                jsonObject.put("unit", gubun[0]);
                jsonObject.put("date", data.get("주기"));
                jsonObject.put("gdiVal", data.get("content"));
                jsonObject.put("gniVal", data_l.get("content"));
            }
            jsonArray.put(jsonObject);
        }
        List<Map<String, Object>> data_list = new ArrayList<>();

        Map<String, String> compareIncomeDistributionIndex = (Map<String, String>) commonService.selectContents(null, PAGE_ID + PROGRAM_ID + ".compareGniCapita");
        org.json.JSONObject compareData = (org.json.JSONObject) jsonArray.get(jsonArray.length() - 1);

        if (compareIncomeDistributionIndex.get("yr_dt").equals(compareData.get("date").toString())) {
            System.out.println("이미 최신화된 데이터 입니다.");
        } else {

            Map<String, Object> dataMap = new HashMap<>();

            String yr_dt = Integer.toString((Integer) compareData.get("date"));
            String gdiVal = Integer.toString((Integer) compareData.get("gdiVal"));
            String gniVal = Integer.toString((Integer) compareData.get("gniVal"));
            String getUnit = (String) compareData.get("unit");

            dataMap.put("yr_dt", yr_dt);
            dataMap.put("unit", getUnit);
            dataMap.put("gdi_val", gdiVal);
            dataMap.put("gni_val", gniVal);
            System.out.println(dataMap);
            data_list.add(dataMap);
            commonService.insertContents(dataMap, PAGE_ID + PROGRAM_ID + ".insertGniCapita");
        }

//        for(Object jsonObject : jsonArray){
//            if(compareIncomeDistributionIndex.get("yr_dt").equals(compareData.get("date").toString())){
//                break;
//            }
//            Map<String, Object> dataMap = new HashMap<>();
//
//            org.json.JSONObject jsonData = (org.json.JSONObject) jsonObject;
//
//            String yr_dt =  Integer.toString((Integer) jsonData.get("date"));
//            String gdiVal =  Integer.toString((Integer) jsonData.get("gdiVal"));
//            String gniVal =  Integer.toString((Integer) jsonData.get("gniVal"));
//            String getUnit = (String) jsonData.get("unit");
//
//            dataMap.put("yr_dt", yr_dt);
//            dataMap.put("unit", getUnit);
//            dataMap.put("gdi_val", gdiVal);
//            dataMap.put("gni_val", gniVal);
//            System.out.println(dataMap);
//            data_list.add(dataMap);
//            commonService.insertContents(dataMap, PAGE_ID + PROGRAM_ID + ".insertGniCapita");
//        }
    }

    //   소득분배지표
    @Scheduled(cron = "0 0 0 16 12 *")
    @Transactional(rollbackFor = Exception.class)

    public void incomeDistributionIndexScheduler() throws Exception {

        String url = "https://kosis.kr/openapi/Param/statisticsParameterData.do";
        String parameter = "method=getList&apiKey=&itmId=T002+&objL1=&objL2=10+&objL3=&objL4=&objL5=&objL6=&objL7=&objL8=&format=json&jsonVD=Y&prdSe=Y&newEstPrdCnt=1&loadGubun=2&orgId=101&tblId=DT_1HDLF05";
        String format = "json";
        String site = "kosis";

        StringBuilder stringBuilder = apiParseService.getApiResult(url, parameter, format, site);

        Map<String, Object> dataMap = new HashMap<>();
        Map<String, String> compareIncomeDistributionIndex = (Map<String, String>) commonService.selectContents(null, PAGE_ID + PROGRAM_ID + ".compareIncomeDistributionIndex");

        JSONArray jsonList = (JSONArray) apiParseService.apiJsonParser(stringBuilder);

        for (Object jsonObject : jsonList) {
            JSONObject jsonData = (JSONObject) jsonObject;

            String yr_dt = (String) jsonData.get("PRD_DE");
            String val = (String) jsonData.get("DT");

            if (compareIncomeDistributionIndex.get("yr_dt").equals(yr_dt)) {
                break;
            }

            dataMap.put("yr_dt", yr_dt);
            dataMap.put("val", val);
            System.out.println(dataMap);
            commonService.insertContents(dataMap, PAGE_ID + PROGRAM_ID + ".insertIncomeDistributionIndex");
        }
    }

    @Scheduled(cron = "0 0 4 * * *")
    @Transactional(rollbackFor = Exception.class)
    public void minPayScheduler() throws Exception {
        String url = "http://www.index.go.kr/openApi/xml_stts.do";
        String parameter = "?userId=&statsCode=149201";
        //kosis = json, enara = xml
        String format = "xml";
        String site = "enara";
        StringBuilder stringBuilder = apiParseService.getApiResult(url, parameter, format, site);

//      통계표
        org.json.JSONObject table = apiParseService.apiXmlParser(stringBuilder);
        String unit = table.getString("단위");
        String[] units = unit.split(",");
        org.json.JSONObject innerTable = table.getJSONObject("표");
        org.json.JSONArray category = innerTable.getJSONArray("항목");

        org.json.JSONObject jsonObject = category.getJSONObject(0);
        org.json.JSONArray columns = jsonObject.getJSONArray("열");

        Map<String, Object> dataMap = new HashMap<>();
        org.json.JSONObject jsonData = (org.json.JSONObject) columns.get(columns.length() - 1);
        String date = Integer.toString((Integer) jsonData.get("주기"));

        @SuppressWarnings("unchecked")
        Map<String, String> maxDate = (Map<String, String>) commonService.selectContents(null, PAGE_ID + PROGRAM_ID + ".minPayMaxDate");
        String maxYear = maxDate.get("yrDt");

        boolean dupleCheck = date.equals(maxYear);

        if (dupleCheck) {
            log.info("이미 등록된 최저임금 자료입니다.");
        } else {
            dataMap.put("yrDt", date);
            dataMap.put("unit", units[0]);
            dataMap.put("val", String.valueOf(jsonData.get("content")));

            commonService.insertContents(dataMap, PAGE_ID + PROGRAM_ID + ".insertMinPay");
        }
    }
}
