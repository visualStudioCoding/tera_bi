package com.teraenergy.bisolution.lifeSatisfaction;

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
public class LifeSatisfaction {
    private static final String PROGRAM_ID = "LifeSatisfaction";
    private static final String DIRECTORY = "lifeSatisfaction/";

    @Resource(name = "commonService")
    private CommonService commonService;

    @GetMapping("/lifeSatisfaction/main")
//    public String mainIndex(HttpServletResponse response,Model model) throws IOException {
    public String mainIndex(Model model) throws IOException {
        log.info(DIRECTORY + PROGRAM_ID + "Main");
        //return DIRECTORY + PROGRAM_ID + "Main";
        //response.setContentType("text/html;charset=euc-kr");
        //PrintWriter out = response.getWriter();

        Date today = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        //out.print("year : " + sdf.format(today));
        String year = sdf.format(today);

        model.addAttribute("year", year);
        return DIRECTORY + PROGRAM_ID + "Main";
        //return DIRECTORY+ "index2";
    }

    @ResponseBody
    @GetMapping("/lifeSatisfaction/LifeSatisfaction")
    public Object LifeSatisfaction_Parse(String url, String parameter) throws Exception {
        log.info(DIRECTORY + PROGRAM_ID + "LifeSatisfaction");
        //return DIRECTORY + PROGRAM_ID + "Main";
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

            dataMap.put("yrDt", yrdt);
            dataMap.put("topgrp", topgrp);
            dataMap.put("midgrp", midgrp);
            dataMap.put("smlgrp", smlgrp);
            dataMap.put("unit", unit);
            dataMap.put("val", val);

            if(smlgrp.equals("평균")) {
                continue;
            }

            commonService.insertContents(dataMap, PROGRAM_ID + ".insertLifeSatisfaction");//LifeSatisfaction.insertLifeSatisfaction
            cnt++;
        }

        result.put("data", dataMap);
        result.put("size", cnt);
        result.put("success", "성공");

        return result;
    }

    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    @GetMapping("/lifeSatisfaction/Marriage")
    public Object Marriage_Parse(String url, String parameter) throws Exception {
        log.info(DIRECTORY + PROGRAM_ID + "Marriage");
        //return DIRECTORY + PROGRAM_ID + "Main";
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



            commonService.insertContents(dataMap, PROGRAM_ID + ".insertMarriage");//LifeSatisfaction.insertMarriage
            cnt++;
        }

        result.put("data", dataMap);
        result.put("size", cnt);
        result.put("success", "성공");

        return result;
    }

    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    @GetMapping("/lifeSatisfaction/Divorce")
    public Object Divorce_Parse(String url, String parameter) throws Exception {
        log.info(DIRECTORY + PROGRAM_ID + "Divorce");
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



            commonService.insertContents(dataMap, PROGRAM_ID + ".insertDivorce");//LifeSatisfaction.insertMarriage
            cnt++;
        }

        result.put("data", dataMap);
        result.put("size", cnt);
        result.put("success", "성공");

        return result;
    }


}