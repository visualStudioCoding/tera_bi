package com.teraenergy.bisolution.admin.stockprices;

import com.teraenergy.global.service.CommonService;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class StockPricesScheduler {
    private static final String PROGRAM_ID = ".StockPrices";
    private static final String PAGE_ID = "admin";
    private static final String FORMAT = "xml";
    private static final String SITE = "enara";
    private static final String ENARA_URL = "http://www.index.go.kr/openApi/xml_stts.do";
    @Resource(name = "commonService")
    private CommonService commonService;

    //      초   |  분  |  시  |  일  |  월   | 요일 | 연도
//     0~59 | 0~59 | 0~23 | 1~31 | 1~12 | 0~6 | 생략가능

    @Transactional(rollbackFor = Exception.class)
    @Scheduled(cron = "00 00 4 * * *")
    public void compositeIndexScheduler() throws Exception {
        String url = "https://ecos.bok.or.kr/api/";
        String parameter = "KeyStatisticList/apiKey/json/kr/66/67/";
        String format = "json";
        String site = "ecos";
        StringBuilder stringBuilder = commonService.getApiResult(url, parameter, format, site);
        JSONArray jsonList = commonService.ecosApiJsonParser(stringBuilder, "KeyStatisticList");

        Map<String, Object> dataMap = new HashMap<>();

        @SuppressWarnings("unchecked")
        Map<String, String> kospiMaxDate = (Map<String, String>) commonService.selectContents(null, PAGE_ID + PROGRAM_ID + ".kospiMaxDate");
        @SuppressWarnings("unchecked")
        Map<String, String> kosdaqMaxDate = (Map<String, String>) commonService.selectContents(null, PAGE_ID + PROGRAM_ID + ".kosdaqMaxDate");

        for (Object jsonObject : jsonList) {
            JSONObject jsonData = (JSONObject) jsonObject;

            String dataValue = (String) jsonData.get("DATA_VALUE");
            String date = (String) jsonData.get("CYCLE");
            String year = date.substring(0, 4);
            String month = date.substring(4, 6);
            String day = date.substring(6, 8);

            String kospiMaxYear = kospiMaxDate.get("yrDt");
            String kospiMaxMonth = kospiMaxDate.get("monDt");
            String kospiMaxDay = kospiMaxDate.get("dyDt");

            boolean kospiDupleCheck = (year + month + day).equals(kospiMaxYear + kospiMaxMonth + kospiMaxDay);

            String kosdaqMaxYear = kosdaqMaxDate.get("yrDt");
            String kosdaqMaxMonth = kosdaqMaxDate.get("monDt");
            String kosdaqMaxDay = kosdaqMaxDate.get("dyDt");
            boolean kosdaqDupleCheck = (year + month + day).equals(kosdaqMaxYear + kosdaqMaxMonth + kosdaqMaxDay);

            dataMap.put("yrDt", year);
            dataMap.put("monDt", month);
            dataMap.put("dyDt", day);
            dataMap.put("unit", "P");
            dataMap.put("val", dataValue);

            String division = (String) jsonData.get("KEYSTAT_NAME");

            if ("코스피지수".equals(division)) {
                if (kospiDupleCheck) {
                    log.info("이미 등록된 코스피지수 자료입니다.");
                } else {
                    commonService.insertContents(dataMap, PAGE_ID + PROGRAM_ID + ".insertKospi");
                }
            } else if ("코스닥지수".equals(division)) {
                if (kosdaqDupleCheck) {
                    log.info("이미 등록된 코스닥지수 자료입니다.");
                } else {
                    commonService.insertContents(dataMap, PAGE_ID + PROGRAM_ID + ".insertKosdaq");
                }
            }
        }
    }

    //      초   |  분  |  시  |  일  |  월   | 요일 | 연도
//     0~59 | 0~59 | 0~23 | 1~31 | 1~12 | 0~6 | 생략가능

    @Transactional(rollbackFor = Exception.class)
    @Scheduled(cron = "00 00 4 * * *")
    public void oilPriceSchedule() throws Exception {
        String url = "https://ecos.bok.or.kr/api/";
        String parameter = "KeyStatisticList/apiKey/json/kr/23/23/";
        String format = "json";
        String site = "ecos";
        StringBuilder stringBuilder = commonService.getApiResult(url, parameter, format, site);
        JSONArray jsonList = commonService.ecosApiJsonParser(stringBuilder, "KeyStatisticList");

        Map<String, Object> dataMap = new HashMap<>();

        @SuppressWarnings("unchecked")
        Map<String, String> oilMaxDate = (Map<String, String>) commonService.selectContents(null, PAGE_ID + PROGRAM_ID + ".oilMaxDate");

        for (Object jsonObject : jsonList) {
            JSONObject jsonData = (JSONObject) jsonObject;

            String dataValue = (String) jsonData.get("DATA_VALUE");
            String date = (String) jsonData.get("CYCLE");
            String year = date.substring(0, 4);
            String month = date.substring(4, 6);

            String oilMaxYear = oilMaxDate.get("yrDt");
            String oilMaxMonth = oilMaxDate.get("monDt");

            boolean oilDupleCheck = (year + month).equals(oilMaxYear + oilMaxMonth);

            dataMap.put("yrDt", year);
            dataMap.put("monDt", month);
            dataMap.put("unit", "달러/배럴");
            dataMap.put("val", dataValue);

            if (oilDupleCheck) {
                log.info("이미 등록된 유가 자료입니다.");
            } else {
                commonService.insertContents(dataMap, PAGE_ID + PROGRAM_ID + ".insertOilPrice");
            }
        }
    }

    //      초   |  분  |  시  |  일  |  월   | 요일 | 연도
//     0~59 | 0~59 | 0~23 | 1~31 | 1~12 | 0~6 | 생략가능

    /*@Transactional(rollbackFor = Exception.class)
    @Scheduled(cron = "00 00 4 * * *")
    public void getCompositeIndex() throws Exception {
        String parameter = "?userId=&statsCode=100803";

        StringBuilder stringBuilder = commonService.getApiResult(ENARA_URL, parameter, FORMAT, SITE);

        @SuppressWarnings("unchecked")
        Map<String, String> maxDate = (Map<String, String>) commonService.selectContents(null, PAGE_ID + PROGRAM_ID + ".stockPricesMaxDate");
//      통계표
        org.json.JSONObject table = commonService.apiXmlParser(stringBuilder);
        String unit = table.getString("단위");
        org.json.JSONArray innerTable = table.getJSONArray("표");
        org.json.JSONObject monthTable = innerTable.getJSONObject(1);
        org.json.JSONObject category = monthTable.getJSONObject("항목");
        org.json.JSONArray classification = category.getJSONArray("분류1");

        for (int idx = 0; idx < classification.length(); idx++) {
            org.json.JSONObject jsonObject = classification.getJSONObject(idx);
            org.json.JSONArray columns = jsonObject.getJSONArray("열");
            Map<String, Object> dataMap = new HashMap<>();
            org.json.JSONObject jsonData = (org.json.JSONObject) columns.get(columns.length() - 1);
            String date = Integer.toString((Integer) jsonData.get("주기"));
            String year = date.substring(0, 4);
            String month = date.substring(4, 6);

            String kospiMaxYear = maxDate.get("kospiMaxYear");
            String kospiMaxMonth = maxDate.get("kospiMaxMonth");
            String kosdaqMaxYear = maxDate.get("kosdaqMaxYear");
            String kosdaqMaxMonth = maxDate.get("kosdaqMaxMonth");

            boolean kospiDupleCheck = (year + month).equals(kospiMaxYear + kospiMaxMonth);
            boolean kosdaqDupleCheck = (year + month).equals(kosdaqMaxYear + kosdaqMaxMonth);

            dataMap.put("yrDt", year);
            dataMap.put("monDt", month);
            dataMap.put("unit", unit);
            dataMap.put("val", String.valueOf(jsonData.get("content")));
            String index = (String) jsonObject.get("이름");

            if ("코스피지수".equals(index)) {
                if (kospiDupleCheck) {
                    log.info("이미 등록된 코스피지수 자료입니다.");
                } else {
                    commonService.insertContents(dataMap, PAGE_ID + PROGRAM_ID + ".insertKospi");
                }
            } else {
                if (kosdaqDupleCheck) {
                    log.info("이미 등록된 코스닥지수 자료입니다.");
                } else {
                    commonService.insertContents(dataMap, PAGE_ID + PROGRAM_ID + ".insertKosdaq");
                }
            }
        }
    }*/

    //      초   |  분  |  시  |  일  |  월   | 요일 | 연도
//     0~59 | 0~59 | 0~23 | 1~31 | 1~12 | 0~6 | 생략가능

    @Transactional(rollbackFor = Exception.class)
    @Scheduled(cron = "00 00 4 * * *")
    public void getBirthDeath() throws Exception {
        String parameter = "?userId=&statsCode=101101";
        StringBuilder stringBuilder = commonService.getApiResult(ENARA_URL, parameter, FORMAT, SITE);

        @SuppressWarnings("unchecked")
        Map<String, String> maxDate = (Map<String, String>) commonService.selectContents(null, PAGE_ID + PROGRAM_ID + ".stockPricesMaxDate");

//      통계표
        org.json.JSONObject table = commonService.apiXmlParser(stringBuilder);
        String unit = table.getString("단위");
        String[] units = unit.split(",");
        org.json.JSONObject innerTable = table.getJSONObject("표");
        org.json.JSONArray categoryGroup = innerTable.getJSONArray("항목그룹");

        for (int idx = 0; idx < categoryGroup.length(); idx++) {
            org.json.JSONObject innerCategoryGroup = categoryGroup.getJSONObject(idx);
            org.json.JSONArray category = innerCategoryGroup.getJSONArray("항목");
            for (int i = 0; i < category.length(); i++) {
                org.json.JSONObject jsonObject = category.getJSONObject(i);

                String name = (String) jsonObject.get("이름");

                if ("출생아수".equals(name) || "사망자수".equals(name)) {
                    org.json.JSONArray columns = jsonObject.getJSONArray("열");
                    Map<String, Object> dataMap = new HashMap<>();
                    org.json.JSONObject jsonData = (org.json.JSONObject) columns.get(columns.length() - 1);
                    String date = Integer.toString((Integer) jsonData.get("주기"));

                    String birthMaxYear = maxDate.get("birthMaxYear");
                    String deathMaxYear = maxDate.get("deathMaxYear");

                    boolean birthDupleCheck = date.equals(birthMaxYear);
                    boolean deathDupleCheck = date.equals(deathMaxYear);

                    dataMap.put("yrDt", date);
                    dataMap.put("unit", units[0]); //명
                    dataMap.put("val", String.valueOf(jsonData.get("content")));

                    if ("출생아수".equals(name)) {
                        if (birthDupleCheck) {
                            log.info("이미 등록된 출생아수 자료입니다.");
                        } else {
                            commonService.insertContents(dataMap, PAGE_ID + PROGRAM_ID + ".insertBirth");
                        }
                    } else {
                        if (deathDupleCheck) {
                            log.info("이미 등록된 사망자수 자료입니다.");
                        } else {
                            commonService.insertContents(dataMap, PAGE_ID + PROGRAM_ID + ".insertDeath");
                        }
                    }
                }
            }
            if ("기대수명".equals(innerCategoryGroup.get("이름"))) {
                org.json.JSONArray jsonArray = new org.json.JSONArray();
                org.json.JSONObject manObject = category.getJSONObject(1);
                org.json.JSONObject womanObject = category.getJSONObject(2);
                org.json.JSONArray manColumns = manObject.getJSONArray("열");
                org.json.JSONArray womanColumns = womanObject.getJSONArray("열");
                for (int j = 0; j < manColumns.length(); j++) {
                    org.json.JSONObject tmpObject = new org.json.JSONObject();
                    org.json.JSONObject manData = manColumns.getJSONObject(j);
                    org.json.JSONObject womanData = womanColumns.getJSONObject(j);
                    if (manData.get("주기").equals(womanData.get("주기"))) {
                        String date = Integer.toString((Integer) manData.get("주기"));
                        tmpObject.put("yrDt", date);
                        tmpObject.put("unit", units[2].trim()); //년
                        tmpObject.put("manVal", String.valueOf(manData.get("content")));
                        tmpObject.put("womanVal", String.valueOf(womanData.get("content")));
                    }
                    jsonArray.put(tmpObject);
                }
                Map<String, Object> dataMap = new HashMap<>();
                org.json.JSONObject jsonData = (org.json.JSONObject) jsonArray.get(jsonArray.length() - 1);

                String lifeExMaxYear = maxDate.get("lifeExMaxYear");

                boolean lifeExDupleCheck = jsonData.get("yrDt").equals(lifeExMaxYear);
                if (lifeExDupleCheck) {
                    log.info("이미 등록된 기대수명 자료입니다.");
                } else {
                    dataMap.put("yrDt", jsonData.get("yrDt"));
                    dataMap.put("unit", jsonData.get("unit"));
                    dataMap.put("manVal", jsonData.get("manVal"));
                    dataMap.put("womanVal", jsonData.get("womanVal"));
                    commonService.insertContents(dataMap, PAGE_ID + PROGRAM_ID + ".insertLifeExpectancy");
                }
            }
        }
    }
}
