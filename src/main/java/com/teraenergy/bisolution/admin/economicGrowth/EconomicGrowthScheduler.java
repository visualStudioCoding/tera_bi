package com.teraenergy.bisolution.admin.economicGrowth;

import com.teraenergy.global.service.CommonService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Component
public class EconomicGrowthScheduler {

    private static final String PROGRAM_ID = ".EconomicGrowth";
    private static final String PAGE_ID = "admin";

    @Resource(name = "commonService")
    private CommonService commonService;

// cron 실행 주기
// *  |  *  |  *  |  *  |  *  |  *
// 초    분     시    일    월    요일

    //기준금리 및 환율
    @Scheduled(cron = "* 30 8 * * *")
    @Transactional(rollbackFor = Exception.class)
    public void monthlyExchangeRateScheduler() throws Exception {
        String url = "https://ecos.bok.or.kr/api/";
        String parameter = "KeyStatisticList/apiKey/json/kr/1/100/";

        String format = "json";
        String site = "ecos";
        StringBuilder stringBuilder = commonService.getApiResult(url, parameter, format, site);

        JSONArray jsonList = commonService.ecosApiJsonParser(stringBuilder);
        Map<String, Object> dataMap = new HashMap<>();
        
         Map<String, String> compareBase = (Map<String, String>) commonService.selectContents(null,PAGE_ID + PROGRAM_ID + ".compareBaseRate");
         Map<String, String> compareExchange = (Map<String, String>) commonService.selectContents(null,PAGE_ID + PROGRAM_ID + ".compareExchangeRate");

        for(Object jsonObject : jsonList){
            JSONObject jsonData = (JSONObject) jsonObject;
            String date = (String) jsonData.get("CYCLE");
            String year = "";
            String month = "";
            String day = "";
            if(date.length() > 6) {
                year = date.substring(0, 4);
                month = date.substring(4, 6);
                day = date.substring(6, 8);
            }

            String maxYear = compareBase.get("yr_dt");
            String maxMonth = compareBase.get("mon_dt");
            String maxDay = compareBase.get("dy_dt");

            boolean baseDupleCheck = (year + month + day).equals(maxYear + maxMonth + maxDay);

            String exMaxYear = compareExchange.get("yr_dt");
            String exMaxMonth = compareExchange.get("mon_dt");
            String exMaxDay = compareExchange.get("dy_dt");
            boolean exDupleCheck = (year + month + day).equals(exMaxYear + exMaxMonth + exMaxDay);


            if("시장금리".equals(jsonData.get("CLASS_NAME")) && jsonData.get("KEYSTAT_NAME").toString().contains("기준금리") && !baseDupleCheck) {
                    String baseMoneyRate = (String) jsonData.get("DATA_VALUE");
                    String unit = (String) jsonData.get("UNIT_NAME");
                    dataMap.put("yr_dt", year);
                    dataMap.put("mon_dt", month);
                    dataMap.put("dy_dt", day);
                    dataMap.put("unit", unit);
                    dataMap.put("val", baseMoneyRate);
                    System.out.println(dataMap);

                    commonService.insertContents(dataMap, PAGE_ID + PROGRAM_ID + ".insertBaseRate");
            } else if("환율".equals(jsonData.get("CLASS_NAME")) && !exDupleCheck) {
                    String[] type = jsonData.get("KEYSTAT_NAME").toString().split(" ");
                    String det_type = type[0];
                    String exMoneyRate = (String) jsonData.get("DATA_VALUE");
                    String unit = (String) jsonData.get("UNIT_NAME");
                    dataMap.put("yr_dt", year);
                    dataMap.put("mon_dt", month);
                    dataMap.put("dy_dt", day);
                    dataMap.put("type", det_type);
                    dataMap.put("unit", unit);
                    dataMap.put("val", exMoneyRate);
                    System.out.println(dataMap);

                    commonService.insertContents(dataMap, PAGE_ID + PROGRAM_ID + ".insertExchangeRate");
            }
        }
    }

//    국가채무현황
    @Scheduled(cron="* * * 23 9 *")
    @Transactional(rollbackFor = Exception.class)
    public void stateDebtScheduler() throws Exception {

        String url = "https://kosis.kr/openapi/Param/statisticsParameterData.do";
        String parameter = "?method=getList&apiKey=&itmId=T01+&objL1=01+&objL2=&objL3=&objL4=&objL5=&objL6=&objL7=&objL8=&format=json&jsonVD=Y&prdSe=Y&newEstPrdCnt=1&loadGubun=2&orgId=102&tblId=DT_102N_A001";
        String format = "json";
        String site = "kosis";

        StringBuilder stringBuilder = commonService.getApiResult(url, parameter, format, site);

        JSONArray jsonList = (JSONArray) commonService.apiJsonParser(stringBuilder);

        Map<String, Object> dataMap = new HashMap<>();
        Map<String, String> compareDebt = (Map<String, String>) commonService.selectContents(null,PAGE_ID + PROGRAM_ID + ".compareStateDebt");

        for(Object jsonObject : jsonList){
            JSONObject jsonData = (JSONObject) jsonObject;
            if("국가채무".equals((String) jsonData.get("C1_NM"))) {
                String year = (String) jsonData.get("PRD_DE");
                String unit = (String) jsonData.get("UNIT_NM");
                String val = (String) jsonData.get("DT");

                if(compareDebt.get("yr_dt").equals(year)){
                    System.out.println("중복된 데이터입니다.");
                    break;
                }

                dataMap.put("yr_dt", year);
                dataMap.put("unit", unit);
                dataMap.put("val", val);

                commonService.insertContents(dataMap, PAGE_ID + PROGRAM_ID + ".insertStateDebt");

                break;
            }
        }
    }

    // 경제활동별 GDP 및 GNI
    @Scheduled(cron="* * * 30 6 *")
    @Transactional(rollbackFor = Exception.class)
    public void gdpAndGniScheduler() throws Exception {
        String url = "https://kosis.kr/openapi/Param/statisticsParameterData.do";
        String parameter = "?method=getList&apiKey=&itmId=13103134593999+&objL1=13102134593ACC_ITEM.1400+13102134593ACC_ITEM.1600+13102134593ACC_ITEM.1800+&objL2=&objL3=&objL4=&objL5=&objL6=&objL7=&objL8=&format=json&jsonVD=Y&prdSe=Y&newEstPrdCnt=1&loadGubun=2&orgId=301&tblId=DT_200Y006";
        String format = "json";
        String site = "kosis";

        String year = null;
        String unit = null;
        String val = null;

        StringBuilder stringbuilder = commonService.getApiResult(url, parameter, format, site);
        JSONArray jsonList = (JSONArray) commonService.apiJsonParser(stringbuilder);

        Map<String, Object> dataMap = new HashMap<>();
        Map<String, String> compareGDP = (Map<String, String>) commonService.selectContents(null,PAGE_ID + PROGRAM_ID + ".compareGDP");
        Map<String, String> compareGDI = (Map<String, String>) commonService.selectContents(null,PAGE_ID + PROGRAM_ID + ".compareGDI");
        Map<String, String> compareGNI = (Map<String, String>) commonService.selectContents(null,PAGE_ID + PROGRAM_ID + ".compareGNI");

        for(Object jsonObject : jsonList){
            JSONObject jsonData = (JSONObject) jsonObject;
            if("국내총생산(시장가격 GDP)".equals((String) jsonData.get("C1_NM"))){
                year = (String) jsonData.get("PRD_DE");
                unit = (String) jsonData.get("UNIT_NM");
                val = (String) jsonData.get("DT");

                if(compareGDP.get("yr_dt").equals(year)){
                    System.out.println("중복된 데이터입니다.");
                    continue;
                }

            }else if("국내총소득(GDI)".equals((String) jsonData.get("C1_NM"))){
                year = (String) jsonData.get("PRD_DE");
                unit = (String) jsonData.get("UNIT_NM");
                val = (String) jsonData.get("DT");

                if(compareGDI.get("yr_dt").equals(year)){
                    System.out.println("중복된 데이터입니다.");
                    continue;
                }

            }else if("국민총소득(GNI)".equals((String) jsonData.get("C1_NM"))){
                year = (String) jsonData.get("PRD_DE");
                unit = (String) jsonData.get("UNIT_NM");
                val = (String) jsonData.get("DT");

                if(compareGNI.get("yr_dt").equals(year)){
                    System.out.println("중복된 데이터입니다.");
                    continue;
                }
            }
            dataMap.put("yr_dt", year);
            dataMap.put("unit", unit);
            dataMap.put("val", val);

            if("국내총생산(시장가격 GDP)".equals((String) jsonData.get("C1_NM"))){
                commonService.insertContents(dataMap, PAGE_ID + PROGRAM_ID + ".insertGDP");
            }else if("국내총소득(GDI)".equals((String) jsonData.get("C1_NM"))){
                commonService.insertContents(dataMap, PAGE_ID + PROGRAM_ID + ".insertGDI");
            }else if("국민총소득(GNI)".equals((String) jsonData.get("C1_NM"))){
                commonService.insertContents(dataMap, PAGE_ID + PROGRAM_ID + ".insertGNI");
            }

        }
    }

    // 경제성장률
    @Scheduled(cron="* * * 28 12 *")
    @Transactional(rollbackFor = Exception.class)
    public void growthRateScheduler() throws Exception {
        String url = "https://kosis.kr/openapi/Param/statisticsParameterData.do";
        String parameter = "?method=getList&apiKey=&itmId=T10+&objL1=ALL&objL2=&objL3=&objL4=&objL5=&objL6=&objL7=&objL8=&format=json&jsonVD=Y&prdSe=Y&newEstPrdCnt=1&loadGubun=2&orgId=101&tblId=DT_1YL20571";
        String format = "json";
        String site = "kosis";

        StringBuilder stringBuilder = commonService.getApiResult(url, parameter, format, site);
        JSONArray jsonArray = (JSONArray) commonService.apiJsonParser(stringBuilder);

        Map<String, Object> dataMap = new HashMap<>();
        Map<String, String> compareGrowth = (Map<String, String>) commonService.selectContents(null,PAGE_ID + PROGRAM_ID + "compareGrowthRate");

        for(Object jsonObject : jsonArray){
            JSONObject jsonData = (JSONObject) jsonObject;

            String yrdt = (String) jsonData.get("PRD_DE");
            String city = (String) jsonData.get("C1_NM");
            String unit = (String) jsonData.get("UNIT_NM");
            String value = (String) jsonData.get("DT");

            if(compareGrowth.get("yr_dt").equals(yrdt)){
                System.out.println("중복된 데이터입니다.");
                break;
            }

            dataMap.put("yr_dt", yrdt);
            dataMap.put("cty_nm", city);
            dataMap.put("unit", unit);
            dataMap.put("val", value);

            commonService.insertContents(dataMap, PAGE_ID + PROGRAM_ID + ".insertGrowthRate");
        }
    }

    // 소비자/근원/생활 물가 상승률
    @Scheduled(cron="* * * 5 * *")
    @Transactional(rollbackFor = Exception.class)
    public void inflationRateScheduler() throws Exception {
        String url = "https://kosis.kr/openapi/Param/statisticsParameterData.do";
        String parameter = "?method=getList&apiKey=&itmId=T03+&objL1=0+1+3+&objL2=&objL3=&objL4=&objL5=&objL6=&objL7=&objL8=&format=json&jsonVD=Y&prdSe=M&newEstPrdCnt=1&loadGubun=2&orgId=101&tblId=DT_1J20042";
        String format = "json";
        String site = "kosis";

        Map<String, Object> dataMap = new HashMap<>();
        Map<String, String> comparePriceInflation = (Map<String, String>) commonService.selectContents(null,PAGE_ID + PROGRAM_ID + ".comparePriceInflation");
        Map<String, String> compareLivingInflation = (Map<String, String>) commonService.selectContents(null,PAGE_ID + PROGRAM_ID + ".compareLivingInflation");
        Map<String, String> compareCoreInflation = (Map<String, String>) commonService.selectContents(null,PAGE_ID + PROGRAM_ID + ".compareCoreInflation");

        StringBuilder stringBuilder = commonService.getApiResult(url, parameter, format, site);
        JSONArray jsonArray = (JSONArray) commonService.apiJsonParser(stringBuilder);

        String year = null;
        String month = null;
        String val = null;
        String unit = null;

        String maxPriceInflation_y = comparePriceInflation.get("yr_dt");
        String maxLivingInflation_y = compareLivingInflation.get("yr_dt");
        String maxCoreInflation_y = compareCoreInflation.get("yr_dt");

        String maxPriceInflation_m = comparePriceInflation.get("mon_dt");
        String maxLivingInflation_m = compareLivingInflation.get("mon_dt");
        String maxCoreInflation_m = compareCoreInflation.get("mon_dt");

        for(Object jsonObject : jsonArray){
            JSONObject jsonData = (JSONObject) jsonObject;

            year = jsonData.get("PRD_DE").toString().substring(0, 4);
            month = jsonData.get("PRD_DE").toString().substring(4,6);
            val = (String) jsonData.get("DT");
            unit = (String) jsonData.get("ITM_NM");

            if(maxPriceInflation_y.equals(year) &&  maxPriceInflation_m.equals(month) || maxLivingInflation_y.equals(year) && maxLivingInflation_m.equals(month) || maxCoreInflation_y.equals(year) && maxCoreInflation_m.equals(month)){
                System.out.println("중복된 데이터입니다.");
                break;
            }

            dataMap.put("val",val);
            dataMap.put("yr_dt",year);
            dataMap.put("mon_dt",month);
            dataMap.put("unit", unit);

            if("총지수".equals(jsonData.get("C1_NM"))){
                commonService.insertContents(dataMap, PAGE_ID + PROGRAM_ID + ".insertConsumerPriceInflation");
            }else if("생활물가지수".equals(jsonData.get("C1_NM"))){
                commonService.insertContents(dataMap, PAGE_ID + PROGRAM_ID + ".insertLivingInflationRate");
            }else if("농산물및석유류제외지수".equals(jsonData.get("C1_NM"))){
                commonService.insertContents(dataMap, PAGE_ID + PROGRAM_ID + ".insertCoreInflationRate");
            }
        }
    }
}
