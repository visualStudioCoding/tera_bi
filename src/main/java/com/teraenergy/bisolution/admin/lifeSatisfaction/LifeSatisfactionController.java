package com.teraenergy.bisolution.admin.lifeSatisfaction;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import com.teraenergy.global.common.utilities.AreaNameUtil;
import com.teraenergy.global.service.CommonService;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
//@RequestMapping("/life")
public class LifeSatisfactionController {
    private static final String PROGRAM_ID = ".LifeSatisfaction";
    private static final String PAGE_ID = "admin";
    private static final String DIRECTORY = "/lifeSatisfaction/LifeSatisfaction";

    @Resource(name = "commonService")
    private CommonService commonService;

    @GetMapping("/admin/lifeSatisfaction/main")
//    public String mainIndex(HttpServletResponse response,Model model) throws IOException {
    public String mainIndex(Model model) throws Exception {
        log.info(PAGE_ID + DIRECTORY + "Main");
        //return PAGE_ID + DIRECTORY + PROGRAM_ID + "Main";
        //response.setContentType("text/html;charset=euc-kr");
        //PrintWriter out = response.getWriter();

        Date today = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        //out.print("year : " + sdf.format(today));
        String year = sdf.format(today);
        model.addAttribute("year", year);

        //1.삶의 만족도
        Map<String,Object> dataMap1 = new HashMap<>();
        dataMap1 = (Map<String, Object>) commonService.selectContents(null, PAGE_ID + PROGRAM_ID + ".selectLifeSatisfaction");
        model.addAttribute("data1", dataMap1);

        //2.결혼
        Map<String,Object> dataMap2 = new HashMap<>();
        dataMap2 = (Map<String, Object>) commonService.selectContents(null, PAGE_ID + PROGRAM_ID + ".selectMarriage");
        model.addAttribute("data2", dataMap2);

        //3.이혼
        Map<String,Object> dataMap3 = new HashMap<>();
        dataMap3 = (Map<String, Object>) commonService.selectContents(null, PAGE_ID + PROGRAM_ID + ".selectDivorce");
        model.addAttribute("data3", dataMap3);

        //4. 고용률
        Map<String,Object> dataMap4 = new HashMap<>();
        dataMap4 = (Map<String, Object>) commonService.selectContents(null, PAGE_ID + PROGRAM_ID + ".selectEmplyrate");
        model.addAttribute("data4", dataMap4);

        //5.실업률
        Map<String,Object> dataMap5 = new HashMap<>();
        dataMap5 = (Map<String, Object>) commonService.selectContents(null, PAGE_ID + PROGRAM_ID + ".selectUnmplrate");
        model.addAttribute("data5", dataMap5);

        //6.전산업생산지수
        Map<String,Object> dataMap6 = new HashMap<>();
        dataMap6 = (Map<String, Object>) commonService.selectContents(null, PAGE_ID + PROGRAM_ID + ".selectAllprindex");
        model.addAttribute("data6", dataMap6);

        //7.해외여행
        Map<String,Object> dataMap7 = new HashMap<>();
        dataMap7 = (Map<String, Object>) commonService.selectContents(null, PAGE_ID + PROGRAM_ID + ".selectOvrsstrip");
        model.addAttribute("data7", dataMap7);

        //8.기업규모별 개인소득 점유율
        Map<String,Object> dataMap8 = new HashMap<>();
        dataMap8 = (Map<String, Object>) commonService.selectContents(null, PAGE_ID + PROGRAM_ID + ".selectPrsnlinshr");
        model.addAttribute("data8", dataMap8);

        return PAGE_ID + DIRECTORY + "Main";
        //return DIRECTORY+ "index2";
    }

    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    @GetMapping("/admin/lifeSatisfaction/LifeSatisfaction")
    public Object LifeSatisfaction_Parse(String url, String parameter) throws Exception {
        log.info(PAGE_ID + DIRECTORY + PROGRAM_ID + "LifeSatisfaction");  //DT_417001_0002 // life_stsfc
        //return PAGE_ID + DIRECTORY + PROGRAM_ID + "Main";
        System.out.println("url : " + url);
         System.out.println("parameter : " + parameter);

        //kosis = json, enara = xml
        String format = "json";
        String site = "kosis";
        StringBuilder stringBuilder = commonService.getApiResult(url, parameter, format, site);
        JSONArray jsonList = (JSONArray) commonService.apiJsonParser(stringBuilder);
        //
        // {"err":"30","errMsg":"데이터가 존재하지 않습니다."}

        Map<String, Object> result = new HashMap<>();
        Map<String, Object> dataMap = new HashMap<>();
        int cnt=1;
        for (Object jsonObject : jsonList) {
            JSONObject jsonData = (JSONObject) jsonObject;

            //년도
            String yrdt = (String) jsonData.get("PRD_DE");
            //yr_dt = yr_dt.substring(0, 4);
            //중분류
            String midgrp = (String) jsonData.get("C1_NM");

            //소분류 - 점수종류 (0~10)
            String smlgrp = (String) jsonData.get("C2_NM");
            if(smlgrp.equals("ⓞ 전혀 만족하지 않는다")) {
                smlgrp = "0";
            } else if(smlgrp.equals("ⓞ 전혀 만족하지 않는다")) {
                smlgrp = "0";
            } else if(smlgrp.equals("①")) {
                smlgrp = "1";
            } else if(smlgrp.equals("②")) {
                smlgrp = "2";
            } else if(smlgrp.equals("③")) {
                smlgrp = "3";
            } else if(smlgrp.equals("④")) {
                smlgrp = "4";
            } else if(smlgrp.equals("⑤ 보통")) {
                smlgrp = "5";
            } else if(smlgrp.equals("⑥")) {
                smlgrp = "6";
            } else if(smlgrp.equals("⑦")) {
                smlgrp = "7";
            } else if(smlgrp.equals("⑧")) {
                smlgrp = "8";
            } else if(smlgrp.equals("⑨")) {
                smlgrp = "9";
            } else if(smlgrp.equals("⑩ 매우 만족한다")) {
                smlgrp = "10";
            }

            //대분류
            String topgrp="";

            //대분류 - 중분류보고 만든다
            if(midgrp != null) {
                if (midgrp.equals("전체")) {
                    topgrp = "전체";
                } else if (midgrp.equals("도시(동부)")) {
                    topgrp = "동읍면부별";
                } else if (midgrp.equals("농어촌(읍면부)")) {
                    topgrp = "동읍면부별";
                } else if (midgrp.equals("남자")) {
                    topgrp = "성별";
                } else if (midgrp.equals("여자")) {
                    topgrp = "성별";
                } else if (midgrp.equals("19~29세") || midgrp.equals("30~39세") || midgrp.equals("40~49세") || midgrp.equals("50~59세") || midgrp.equals("60~69세")|| midgrp.equals("60세 이상")|| midgrp.equals("65세 이상")) {
                    topgrp = "연령별";
                } else if (midgrp.equals("전문관리") || midgrp.equals("사무") || midgrp.equals("서비스판매") || midgrp.equals("농림어업") || midgrp.equals("기능노무") || midgrp.equals("기타")) {
                    topgrp = "직업별";
                } else if (midgrp.equals("100만원 미만") || midgrp.equals("100~200만원 미만") || midgrp.equals("200~300만원 미만") || midgrp.equals("300~400만원 미만") || midgrp.equals("400~500만원 미만") || midgrp.equals("500~600만원 미만") || midgrp.equals("600만원 이상")) {
                    topgrp = "가구소득별";
                } else if (midgrp.equals("초졸 이하") || midgrp.equals("중졸") || midgrp.equals("고졸") || midgrp.equals("대졸 이상")) {
                    topgrp = "교육정도별";
                }
            }

            //점수단위(%)
            String unit = (String) jsonData.get("UNIT_NM");

            //점수
            String val = (String)jsonData.get("DT");

            dataMap.put("yrdt", yrdt);
            dataMap.put("topgrp", topgrp);
            dataMap.put("midgrp", midgrp);
            dataMap.put("smlgrp", smlgrp);
            dataMap.put("unit", unit);
            dataMap.put("val", val);

            if(smlgrp.equals("평균")) {
                continue;
            }

           commonService.insertContents(dataMap, PAGE_ID + PROGRAM_ID + ".insertLifeSatisfaction");//LifeSatisfaction.insertLifeSatisfaction
            cnt++;
        }

        result.put("data", dataMap);
        result.put("size", cnt);
        result.put("success", "성공");

        return result;
    }

    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    @GetMapping("/admin/lifeSatisfaction/Marriage")
    public Object Marriage_Parse(String url, String parameter) throws Exception {
        log.info(PAGE_ID + DIRECTORY + PAGE_ID + PROGRAM_ID + "Marriage");
        //return PAGE_ID + DIRECTORY + PAGE_ID + PROGRAM_ID + "Main";
        System.out.println("url : " + url);
        System.out.println("parameter : " + parameter);

        //kosis = json, enara = xml
        String format = "json";
        String site = "kosis";
        StringBuilder stringBuilder = commonService.getApiResult(url, parameter, format, site);
        JSONArray jsonList = (JSONArray) commonService.apiJsonParser(stringBuilder);
        //
        // {"err":"30","errMsg":"데이터가 존재하지 않습니다."}

        Map<String, Object> result = new HashMap<>();
        Map<String, Object> dataMap = new HashMap<>();
        int cnt=1;
        for (Object jsonObject : jsonList) {
            JSONObject jsonData = (JSONObject) jsonObject;

            //년도
            String prdde = (String) jsonData.get("PRD_DE");
            String yrdt = prdde.substring(0, 4);

            //월
            String monDt = prdde.substring(4, 6);

            //시도코드
            String ctyCode = (String) jsonData.get("C1");

            //시도
            String ctyNm = "";

            //구는 뺀다
            if(ctyCode.length() >= 5 && !"0".equals(ctyCode.substring(4,5))) {
                continue;
            }

            if("33040".equals(ctyCode)) {
                continue;
            }

            ctyNm = AreaNameUtil.areaName((String) jsonData.get("C1"), "other");
/*
            if(ctyCode.equals("00")) {
                ctyNm = "전국";
            } else if("11".equals(ctyCode.substring(0,2))) {
                ctyNm = "서울특별시";
            } else if("21".equals(ctyCode.substring(0,2))) {
                ctyNm = "부산광역시";
            } else if("22".equals(ctyCode.substring(0,2))) {
                ctyNm = "대구광역시";
            } else if("23".equals(ctyCode.substring(0,2))) {
                ctyNm = "인천광역시";
            } else if("24".equals(ctyCode.substring(0,2))) {
                ctyNm = "광주광역시";
            } else if("25".equals(ctyCode.substring(0,2))) {
                ctyNm = "대전광역시";
            } else if("26".equals(ctyCode.substring(0,2))) {
                ctyNm = "울산광역시";
            } else if("29".equals(ctyCode.substring(0,2))) {
                ctyNm = "세종특별자치시";
            } else if("31".equals(ctyCode.substring(0,2))) {
                ctyNm = "경기도";
            } else if("32".equals(ctyCode.substring(0,2))) {
                ctyNm = "강원도";
            } else if("33".equals(ctyCode.substring(0,2))) {
                ctyNm = "충청북도";
            } else if("34".equals(ctyCode.substring(0,2))) {
                ctyNm = "충청남도";
            } else if("35".equals(ctyCode.substring(0,2))) {
                ctyNm = "전라북도";
            } else if("36".equals(ctyCode.substring(0,2))) {
                ctyNm = "전라남도";
            } else if("37".equals(ctyCode.substring(0,2))) {
                ctyNm = "경상북도";
            } else if("38".equals(ctyCode.substring(0,2))) {
                ctyNm = "경상남도";
            } else if("39".equals(ctyCode.substring(0,2))) {
                ctyNm = "제주";
            } else if("90".equals(ctyCode.substring(0,2))) {
                ctyNm = "국외";
            }
*/
            //시도 , 시군구
            String dstNm = (String) jsonData.get("C1_NM");

            //점수단위(건)
            String unit = (String) jsonData.get("UNIT_NM");

            //점수
            String val = (String)jsonData.get("DT");

            dataMap.put("yrdt", yrdt);
            dataMap.put("mondt", monDt);
            dataMap.put("areacd", ctyCode);
            dataMap.put("ctynm", ctyNm);
            dataMap.put("dstnm", dstNm);
            dataMap.put("unit", unit);
            dataMap.put("val", val);



            commonService.insertContents(dataMap, PAGE_ID + PROGRAM_ID + ".insertMarriage");//LifeSatisfaction.insertMarriage
            cnt++;
        }

        result.put("data", dataMap);
        result.put("size", cnt);
        result.put("success", "성공");

        return result;
    }

    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    @GetMapping("/admin/lifeSatisfaction/Divorce")
    public Object Divorce_Parse(String url, String parameter) throws Exception {
        log.info(PAGE_ID + DIRECTORY + PROGRAM_ID + "Divorce");
        System.out.println("url : " + url);
        System.out.println("parameter : " + parameter);

        //kosis = json, enara = xml
        String format = "json";
        String site = "kosis";
        StringBuilder stringBuilder = commonService.getApiResult(url, parameter, format, site);
        JSONArray jsonList = (JSONArray) commonService.apiJsonParser(stringBuilder);
        //
        // {"err":"30","errMsg":"데이터가 존재하지 않습니다."}

        Map<String, Object> result = new HashMap<>();
        Map<String, Object> dataMap = new HashMap<>();
        int cnt=1;
        for (Object jsonObject : jsonList) {
            JSONObject jsonData = (JSONObject) jsonObject;

            //년도
            String prdde = (String) jsonData.get("PRD_DE");
            String yrdt = prdde.substring(0, 4);

            //월
            String monDt = prdde.substring(4, 6);

            //시도코드
            String ctyCode = (String) jsonData.get("C1");

            //시도
            String ctyNm = "";

            //구는 뺀다
            if(ctyCode.length() >= 5 && !"0".equals(ctyCode.substring(4,5))) {
                continue;
            }

            //청주 ? 중복됨
            if("33040".equals(ctyCode)) {
                continue;
            }

            ctyNm = AreaNameUtil.areaName((String) jsonData.get("C1"), "other");

            /*
            if(ctyCode.equals("00")) {
                ctyNm = "전국";
            } else if("11".equals(ctyCode.substring(0,2))) {
                ctyNm = "서울특별시";
            } else if("21".equals(ctyCode.substring(0,2))) {
                ctyNm = "부산광역시";
            } else if("22".equals(ctyCode.substring(0,2))) {
                ctyNm = "대구광역시";
            } else if("23".equals(ctyCode.substring(0,2))) {
                ctyNm = "인천광역시";
            } else if("24".equals(ctyCode.substring(0,2))) {
                ctyNm = "광주광역시";
            } else if("25".equals(ctyCode.substring(0,2))) {
                ctyNm = "대전광역시";
            } else if("26".equals(ctyCode.substring(0,2))) {
                ctyNm = "울산광역시";
            } else if("29".equals(ctyCode.substring(0,2))) {
                ctyNm = "세종특별자치시";
            } else if("31".equals(ctyCode.substring(0,2))) {
                ctyNm = "경기도";
            } else if("32".equals(ctyCode.substring(0,2))) {
                ctyNm = "강원도";
            } else if("33".equals(ctyCode.substring(0,2))) {
                ctyNm = "충청북도";
            } else if("34".equals(ctyCode.substring(0,2))) {
                ctyNm = "충청남도";
            } else if("35".equals(ctyCode.substring(0,2))) {
                ctyNm = "전라북도";
            } else if("36".equals(ctyCode.substring(0,2))) {
                ctyNm = "전라남도";
            } else if("37".equals(ctyCode.substring(0,2))) {
                ctyNm = "경상북도";
            } else if("38".equals(ctyCode.substring(0,2))) {
                ctyNm = "경상남도";
            } else if("39".equals(ctyCode.substring(0,2))) {
                ctyNm = "제주";
            } else if("90".equals(ctyCode.substring(0,2))) {
                ctyNm = "국외";
            }
*/

            //시도 , 시군구
            String dstNm = (String) jsonData.get("C1_NM");

            //점수단위(건)
            String unit = (String) jsonData.get("UNIT_NM");

            //점수
            String val = (String)jsonData.get("DT");

            dataMap.put("yrdt", yrdt);
            dataMap.put("mondt", monDt);
            dataMap.put("areacd", ctyCode);
            dataMap.put("ctynm", ctyNm);
            dataMap.put("dstnm", dstNm);
            dataMap.put("unit", unit);
            dataMap.put("val", val);



            commonService.insertContents(dataMap, PAGE_ID + PROGRAM_ID + ".insertDivorce");//LifeSatisfaction.insertMarriage
            cnt++;
        }

        result.put("data", dataMap);
        result.put("size", cnt);
        result.put("success", "성공");

        return result;
    }

    //4.고용률
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    @GetMapping("/admin/lifeSatisfaction/emplyrate")
    public Object Emplyrate_Parse(String url, String parameter) throws Exception {
        log.info(PAGE_ID + DIRECTORY + PROGRAM_ID + "emplyrate");
        System.out.println("url : " + url);
        System.out.println("parameter : " + parameter);

        //DT_1DA7014S
        //kosis = json, enara = xml
        String format = "json";
        String site = "kosis";
        StringBuilder stringBuilder = commonService.getApiResult(url, parameter, format, site);
        JSONArray jsonList = (JSONArray) commonService.apiJsonParser(stringBuilder);
        //
        // {"err":"30","errMsg":"데이터가 존재하지 않습니다."}

        Map<String, Object> result = new HashMap<>();
        Map<String, Object> dataMap = new HashMap<>();
        int cnt=1;
        for (Object jsonObject : jsonList) {
            JSONObject jsonData = (JSONObject) jsonObject;

            //아이템 - 고용률
            String item = (String) jsonData.get("ITM_NM");
            //시도
            String ctyNm = (String) jsonData.get("C1_NM");
            //남녀
            String grp = (String) jsonData.get("C2_NM");
            //고용률이고 계는 뺀다
            if("고용률".equals(item) && !"계".equals(ctyNm) && !"계".equals(grp)) {
                //년도
                String prdde = (String) jsonData.get("PRD_DE");
                String yrdt = prdde.substring(0, 4);
                //월
                String monDt = prdde.substring(4, 6);
                //점수단위(%)
                String unit = (String) jsonData.get("UNIT_NM");
                //고용률
                String val = (String) jsonData.get("DT");

                dataMap.put("yrdt", yrdt);
                dataMap.put("mondt", monDt);
                dataMap.put("ctynm", ctyNm);
                dataMap.put("grp", grp);
                dataMap.put("unit", unit);
                dataMap.put("val", val);

                commonService.insertContents(dataMap, PAGE_ID + PROGRAM_ID + ".insertEmplyrate");//LifeSatisfaction.insertMarriage
                cnt++;
            }
        }

        result.put("data", dataMap);
        result.put("size", cnt);
        result.put("success", "성공");

        return result;
    }

    //5.실업률
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    @GetMapping("/admin/lifeSatisfaction/unmplrate")
    public Object Unmplrate_Parse(String url, String parameter) throws Exception {
        log.info(PAGE_ID + DIRECTORY + PROGRAM_ID + "unmplrate");
        System.out.println("url : " + url);
        System.out.println("parameter : " + parameter);

        //DT_1DA7104S
        //kosis = json, enara = xml
        String format = "json";
        String site = "kosis";
        StringBuilder stringBuilder = commonService.getApiResult(url, parameter, format, site);
        JSONArray jsonList = (JSONArray) commonService.apiJsonParser(stringBuilder);
        //
        // {"err":"30","errMsg":"데이터가 존재하지 않습니다."}

        Map<String, Object> result = new HashMap<>();
        Map<String, Object> dataMap = new HashMap<>();
        int cnt=1;
        for (Object jsonObject : jsonList) {
            JSONObject jsonData = (JSONObject) jsonObject;

            //아이템 - 실업률
            String item = (String) jsonData.get("ITM_NM");
            //시도
            String ctyNm = (String) jsonData.get("C1_NM");
            //남녀
            String grp = (String) jsonData.get("C2_NM");
            //고용률이고 계는 뺀다
            if("실업률".equals(item) && !"계".equals(ctyNm) && !"계".equals(grp)) {
                //년도
                String prdde = (String) jsonData.get("PRD_DE");
                String yrdt = prdde.substring(0, 4);
                //월
                String monDt = prdde.substring(4, 6);
                //점수단위(%)
                String unit = (String) jsonData.get("UNIT_NM");
                //고용률
                String val = (String) jsonData.get("DT");

                dataMap.put("yrdt", yrdt);
                dataMap.put("mondt", monDt);
                dataMap.put("ctynm", ctyNm);
                dataMap.put("grp", grp);
                dataMap.put("unit", unit);
                dataMap.put("val", val);

                commonService.insertContents(dataMap, PAGE_ID + PROGRAM_ID + ".insertUmplrate");//LifeSatisfaction.insertMarriage
                cnt++;
            }
        }

        result.put("data", dataMap);
        result.put("size", cnt);
        result.put("success", "성공");

        return result;
    }

    //6. 전산업생산지수
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    @GetMapping("/admin/lifeSatisfaction/allprindex")
    public Object Allprindex_Parse(String url, String parameter) throws Exception {
        log.info(PAGE_ID + DIRECTORY + PROGRAM_ID + "allprindex");
        System.out.println("url : " + url);
        System.out.println("parameter : " + parameter);

        //DT_1JH20151
        //kosis = json, enara = xml
        String format = "json";
        String site = "kosis";
        StringBuilder stringBuilder = commonService.getApiResult(url, parameter, format, site);
        JSONArray jsonList = (JSONArray) commonService.apiJsonParser(stringBuilder);
        //
        // {"err":"30","errMsg":"데이터가 존재하지 않습니다."}

        Map<String, Object> result = new HashMap<>();
        Map<String, Object> dataMap = new HashMap<>();
        int cnt=1;
        for (Object jsonObject : jsonList) {
            JSONObject jsonData = (JSONObject) jsonObject;

            //산업
            String type = (String) jsonData.get("C1_NM");
            //년도
            String prdde = (String) jsonData.get("PRD_DE");
            String yrdt = prdde.substring(0, 4);
            //월
            String monDt = prdde.substring(4, 6);
            //점수단위(%)
            String unit = (String) jsonData.get("UNIT_NM");
            //지수
            String val = (String) jsonData.get("DT");

            dataMap.put("yrdt", yrdt);
            dataMap.put("mondt", monDt);
            dataMap.put("type", type);
            dataMap.put("unit", unit);
            dataMap.put("val", val);

            commonService.insertContents(dataMap, PAGE_ID + PROGRAM_ID + ".insertAllprindex");//LifeSatisfaction.insertMarriage
            cnt++;
        }

        result.put("data", dataMap);
        result.put("size", cnt);
        result.put("success", "성공");

        return result;
    }

    //7. 해외여행 경험 및 횟수
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    @GetMapping("/admin/lifeSatisfaction/ovrsstrip")
    public Object Ovrsstrip_Parse(String url, String parameter) throws Exception {
        log.info(PAGE_ID + DIRECTORY + PROGRAM_ID + "ovrsstrip");
        System.out.println("url : " + url);
        System.out.println("parameter : " + parameter);

        //DT_1SSCL060R
        //kosis = json, enara = xml
        String format = "json";
        String site = "kosis";
        StringBuilder stringBuilder = commonService.getApiResult(url, parameter, format, site);
        JSONArray jsonList = (JSONArray) commonService.apiJsonParser(stringBuilder);
        //
        // {"err":"30","errMsg":"데이터가 존재하지 않습니다."}

        Map<String, Object> result = new HashMap<>();
        Map<String, Object> dataMap = new HashMap<>();
        int cnt=1;
        for (Object jsonObject : jsonList) {
            JSONObject jsonData = (JSONObject) jsonObject;

            //년도
            String prdde = (String) jsonData.get("PRD_DE");
            String yrdt = prdde.substring(0, 4);

            //시도
            String ctynm = (String) jsonData.get("C1_NM");

            //대분류
            String topgrp = (String) jsonData.get("C2_NM");

            //소분류
            String midgrp = (String) jsonData.get("ITM_NM");

            //계는 통과
            if("계".equals(midgrp)) {
                continue;
            }

            //점수단위(%,회)
            String unit = (String) jsonData.get("UNIT_NM");

            //횟수
            String val = (String) jsonData.get("DT");

            dataMap.put("yrdt", yrdt);
            dataMap.put("ctynm", ctynm);
            dataMap.put("topgrp", topgrp);
            dataMap.put("midgrp", midgrp);
            dataMap.put("unit", unit);
            dataMap.put("val", val);

            commonService.insertContents(dataMap, PAGE_ID + PROGRAM_ID + ".insertOvrsstrip");//LifeSatisfaction.insertMarriage
            cnt++;
        }

        result.put("data", dataMap);
        result.put("size", cnt);
        result.put("success", "성공");

        return result;
    }

 //8. 기업규모별 개인소득 점유율
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    @GetMapping("/admin/lifeSatisfaction/prsnlnshr")
    public Object Prsnlnshr_Parse(String url, String parameter) throws Exception {
        log.info(PAGE_ID + DIRECTORY + PROGRAM_ID + "prsnlnshr");
        System.out.println("url : " + url);
        System.out.println("parameter : " + parameter);

        //DT_1EP_2001
        //kosis = json, enara = xml
        String format = "json";
        String site = "kosis";
        StringBuilder stringBuilder = commonService.getApiResult(url, parameter, format, site);
        JSONArray jsonList = (JSONArray) commonService.apiJsonParser(stringBuilder);
        //
        // {"err":"30","errMsg":"데이터가 존재하지 않습니다."}

        Map<String, Object> result = new HashMap<>();
        Map<String, Object> dataMap = new HashMap<>();
        int cnt=1;
        for (Object jsonObject : jsonList) {
            JSONObject jsonData = (JSONObject) jsonObject;

            //년도
            String prdde = (String) jsonData.get("PRD_DE");
            String yrdt = prdde.substring(0, 4);

            //기업규모
            String topgrp = (String) jsonData.get("C2_NM");

            //소득구간
            String midgrp = (String) jsonData.get("C1_NM");

            //단위
            String unit = (String) jsonData.get("UNIT_NM");

            //점유율
            String val = (String) jsonData.get("DT");

            dataMap.put("yrdt", yrdt);
            dataMap.put("topgrp", topgrp);
            dataMap.put("midgrp", midgrp);
            dataMap.put("unit", unit);
            dataMap.put("val", val);

            commonService.insertContents(dataMap, PAGE_ID + PROGRAM_ID + ".insertPrsnlinshr");//LifeSatisfaction.insertMarriage
            cnt++;
        }

        result.put("data", dataMap);
        result.put("size", cnt);
        result.put("success", "성공");

        return result;
    }




}