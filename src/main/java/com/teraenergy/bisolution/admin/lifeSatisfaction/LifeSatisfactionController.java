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

        //1.?????? ?????????
        Map<String,Object> dataMap1 = new HashMap<>();
        dataMap1 = (Map<String, Object>) commonService.selectContents(null, PAGE_ID + PROGRAM_ID + ".selectLifeSatisfaction");
        model.addAttribute("data1", dataMap1);

        //2.??????
        Map<String,Object> dataMap2 = new HashMap<>();
        dataMap2 = (Map<String, Object>) commonService.selectContents(null, PAGE_ID + PROGRAM_ID + ".selectMarriage");
        model.addAttribute("data2", dataMap2);

        //3.??????
        Map<String,Object> dataMap3 = new HashMap<>();
        dataMap3 = (Map<String, Object>) commonService.selectContents(null, PAGE_ID + PROGRAM_ID + ".selectDivorce");
        model.addAttribute("data3", dataMap3);

        //4. ?????????
        Map<String,Object> dataMap4 = new HashMap<>();
        dataMap4 = (Map<String, Object>) commonService.selectContents(null, PAGE_ID + PROGRAM_ID + ".selectEmplyrate");
        model.addAttribute("data4", dataMap4);

        //5.?????????
        Map<String,Object> dataMap5 = new HashMap<>();
        dataMap5 = (Map<String, Object>) commonService.selectContents(null, PAGE_ID + PROGRAM_ID + ".selectUnmplrate");
        model.addAttribute("data5", dataMap5);

        //6.?????????????????????
        Map<String,Object> dataMap6 = new HashMap<>();
        dataMap6 = (Map<String, Object>) commonService.selectContents(null, PAGE_ID + PROGRAM_ID + ".selectAllprindex");
        model.addAttribute("data6", dataMap6);

        //7.????????????
        Map<String,Object> dataMap7 = new HashMap<>();
        dataMap7 = (Map<String, Object>) commonService.selectContents(null, PAGE_ID + PROGRAM_ID + ".selectOvrsstrip");
        model.addAttribute("data7", dataMap7);

        //8.??????????????? ???????????? ?????????
        Map<String,Object> dataMap8 = new HashMap<>();
        dataMap8 = (Map<String, Object>) commonService.selectContents(null, PAGE_ID + PROGRAM_ID + ".selectPrsnlinshr");
        model.addAttribute("data8", dataMap8);

        //9.?????? ?????????
        Map<String,Object> dataMap9 = new HashMap<>();
        dataMap9 = (Map<String, Object>) commonService.selectContents(null, PAGE_ID + PROGRAM_ID + ".selectLifeSatisfaction2");
        model.addAttribute("data9", dataMap9);

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
        // {"err":"30","errMsg":"???????????? ???????????? ????????????."}

        Map<String, Object> result = new HashMap<>();
        Map<String, Object> dataMap = new HashMap<>();
        int cnt=1;
        for (Object jsonObject : jsonList) {
            JSONObject jsonData = (JSONObject) jsonObject;

            //??????
            String yrdt = (String) jsonData.get("PRD_DE");
            //yr_dt = yr_dt.substring(0, 4);
            //?????????
            String midgrp = (String) jsonData.get("C1_NM");

            //????????? - ???????????? (0~10)
            String smlgrp = (String) jsonData.get("C2_NM");
            if(smlgrp.equals("??? ?????? ???????????? ?????????")) {
                smlgrp = "0";
            } else if(smlgrp.equals("??? ?????? ???????????? ?????????")) {
                smlgrp = "0";
            } else if(smlgrp.equals("???")) {
                smlgrp = "1";
            } else if(smlgrp.equals("???")) {
                smlgrp = "2";
            } else if(smlgrp.equals("???")) {
                smlgrp = "3";
            } else if(smlgrp.equals("???")) {
                smlgrp = "4";
            } else if(smlgrp.equals("??? ??????")) {
                smlgrp = "5";
            } else if(smlgrp.equals("???")) {
                smlgrp = "6";
            } else if(smlgrp.equals("???")) {
                smlgrp = "7";
            } else if(smlgrp.equals("???")) {
                smlgrp = "8";
            } else if(smlgrp.equals("???")) {
                smlgrp = "9";
            } else if(smlgrp.equals("??? ?????? ????????????")) {
                smlgrp = "10";
            }

            //?????????
            String topgrp="";

            //????????? - ??????????????? ?????????
            if(midgrp != null) {
                if (midgrp.equals("??????")) {
                    topgrp = "??????";
                } else if (midgrp.equals("??????(??????)")) {
                    topgrp = "???????????????";
                } else if (midgrp.equals("?????????(?????????)")) {
                    topgrp = "???????????????";
                } else if (midgrp.equals("??????")) {
                    topgrp = "??????";
                } else if (midgrp.equals("??????")) {
                    topgrp = "??????";
                } else if (midgrp.equals("19~29???") || midgrp.equals("30~39???") || midgrp.equals("40~49???") || midgrp.equals("50~59???") || midgrp.equals("60~69???")|| midgrp.equals("60??? ??????")|| midgrp.equals("65??? ??????")) {
                    topgrp = "?????????";
                } else if (midgrp.equals("????????????") || midgrp.equals("??????") || midgrp.equals("???????????????") || midgrp.equals("????????????") || midgrp.equals("????????????") || midgrp.equals("??????")) {
                    topgrp = "?????????";
                } else if (midgrp.equals("100?????? ??????") || midgrp.equals("100~200?????? ??????") || midgrp.equals("200~300?????? ??????") || midgrp.equals("300~400?????? ??????") || midgrp.equals("400~500?????? ??????") || midgrp.equals("500~600?????? ??????") || midgrp.equals("600?????? ??????")) {
                    topgrp = "???????????????";
                } else if (midgrp.equals("?????? ??????") || midgrp.equals("??????") || midgrp.equals("??????") || midgrp.equals("?????? ??????")) {
                    topgrp = "???????????????";
                }
            }

            //????????????(%)
            String unit = (String) jsonData.get("UNIT_NM");

            //??????
            String val = (String)jsonData.get("DT");

            dataMap.put("yrdt", yrdt);
            dataMap.put("topgrp", topgrp);
            dataMap.put("midgrp", midgrp);
            dataMap.put("smlgrp", smlgrp);
            dataMap.put("unit", unit);
            dataMap.put("val", val);

            if(smlgrp.equals("??????")) {
                continue;
            }

            commonService.insertContents(dataMap, PAGE_ID + PROGRAM_ID + ".insertLifeSatisfaction");//LifeSatisfaction.insertLifeSatisfaction
            cnt++;
        }

        result.put("data", dataMap);
        result.put("size", cnt);
        result.put("success", "??????");

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
        // {"err":"30","errMsg":"???????????? ???????????? ????????????."}

        Map<String, Object> result = new HashMap<>();
        Map<String, Object> dataMap = new HashMap<>();
        int cnt=1;
        for (Object jsonObject : jsonList) {
            JSONObject jsonData = (JSONObject) jsonObject;

            //??????
            String prdde = (String) jsonData.get("PRD_DE");
            String yrdt = prdde.substring(0, 4);

            //???
            String monDt = prdde.substring(4, 6);

            //????????????
            String ctyCode = (String) jsonData.get("C1");

            //??????
            String ctyNm = "";

            //?????? ??????
            if(ctyCode.length() >= 5 && !"0".equals(ctyCode.substring(4,5))) {
                continue;
            }

            if("33040".equals(ctyCode)) {
                continue;
            }

            ctyNm = AreaNameUtil.areaName((String) jsonData.get("C1"), "other");
/*
            if(ctyCode.equals("00")) {
                ctyNm = "??????";
            } else if("11".equals(ctyCode.substring(0,2))) {
                ctyNm = "???????????????";
            } else if("21".equals(ctyCode.substring(0,2))) {
                ctyNm = "???????????????";
            } else if("22".equals(ctyCode.substring(0,2))) {
                ctyNm = "???????????????";
            } else if("23".equals(ctyCode.substring(0,2))) {
                ctyNm = "???????????????";
            } else if("24".equals(ctyCode.substring(0,2))) {
                ctyNm = "???????????????";
            } else if("25".equals(ctyCode.substring(0,2))) {
                ctyNm = "???????????????";
            } else if("26".equals(ctyCode.substring(0,2))) {
                ctyNm = "???????????????";
            } else if("29".equals(ctyCode.substring(0,2))) {
                ctyNm = "?????????????????????";
            } else if("31".equals(ctyCode.substring(0,2))) {
                ctyNm = "?????????";
            } else if("32".equals(ctyCode.substring(0,2))) {
                ctyNm = "?????????";
            } else if("33".equals(ctyCode.substring(0,2))) {
                ctyNm = "????????????";
            } else if("34".equals(ctyCode.substring(0,2))) {
                ctyNm = "????????????";
            } else if("35".equals(ctyCode.substring(0,2))) {
                ctyNm = "????????????";
            } else if("36".equals(ctyCode.substring(0,2))) {
                ctyNm = "????????????";
            } else if("37".equals(ctyCode.substring(0,2))) {
                ctyNm = "????????????";
            } else if("38".equals(ctyCode.substring(0,2))) {
                ctyNm = "????????????";
            } else if("39".equals(ctyCode.substring(0,2))) {
                ctyNm = "??????";
            } else if("90".equals(ctyCode.substring(0,2))) {
                ctyNm = "??????";
            }
*/
            //?????? , ?????????
            String dstNm = (String) jsonData.get("C1_NM");

            //????????????(???)
            String unit = (String) jsonData.get("UNIT_NM");

            //??????
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
        result.put("success", "??????");

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
        // {"err":"30","errMsg":"???????????? ???????????? ????????????."}

        Map<String, Object> result = new HashMap<>();
        Map<String, Object> dataMap = new HashMap<>();
        int cnt=1;
        for (Object jsonObject : jsonList) {
            JSONObject jsonData = (JSONObject) jsonObject;

            //??????
            String prdde = (String) jsonData.get("PRD_DE");
            String yrdt = prdde.substring(0, 4);

            //???
            String monDt = prdde.substring(4, 6);

            //????????????
            String ctyCode = (String) jsonData.get("C1");

            //??????
            String ctyNm = "";

            //?????? ??????
            if(ctyCode.length() >= 5 && !"0".equals(ctyCode.substring(4,5))) {
                continue;
            }

            //?????? ? ?????????
            if("33040".equals(ctyCode)) {
                continue;
            }

            ctyNm = AreaNameUtil.areaName((String) jsonData.get("C1"), "other");

            /*
            if(ctyCode.equals("00")) {
                ctyNm = "??????";
            } else if("11".equals(ctyCode.substring(0,2))) {
                ctyNm = "???????????????";
            } else if("21".equals(ctyCode.substring(0,2))) {
                ctyNm = "???????????????";
            } else if("22".equals(ctyCode.substring(0,2))) {
                ctyNm = "???????????????";
            } else if("23".equals(ctyCode.substring(0,2))) {
                ctyNm = "???????????????";
            } else if("24".equals(ctyCode.substring(0,2))) {
                ctyNm = "???????????????";
            } else if("25".equals(ctyCode.substring(0,2))) {
                ctyNm = "???????????????";
            } else if("26".equals(ctyCode.substring(0,2))) {
                ctyNm = "???????????????";
            } else if("29".equals(ctyCode.substring(0,2))) {
                ctyNm = "?????????????????????";
            } else if("31".equals(ctyCode.substring(0,2))) {
                ctyNm = "?????????";
            } else if("32".equals(ctyCode.substring(0,2))) {
                ctyNm = "?????????";
            } else if("33".equals(ctyCode.substring(0,2))) {
                ctyNm = "????????????";
            } else if("34".equals(ctyCode.substring(0,2))) {
                ctyNm = "????????????";
            } else if("35".equals(ctyCode.substring(0,2))) {
                ctyNm = "????????????";
            } else if("36".equals(ctyCode.substring(0,2))) {
                ctyNm = "????????????";
            } else if("37".equals(ctyCode.substring(0,2))) {
                ctyNm = "????????????";
            } else if("38".equals(ctyCode.substring(0,2))) {
                ctyNm = "????????????";
            } else if("39".equals(ctyCode.substring(0,2))) {
                ctyNm = "??????";
            } else if("90".equals(ctyCode.substring(0,2))) {
                ctyNm = "??????";
            }
*/

            //?????? , ?????????
            String dstNm = (String) jsonData.get("C1_NM");

            //????????????(???)
            String unit = (String) jsonData.get("UNIT_NM");

            //??????
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
        result.put("success", "??????");

        return result;
    }

    //4.?????????
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
        // {"err":"30","errMsg":"???????????? ???????????? ????????????."}

        Map<String, Object> result = new HashMap<>();
        Map<String, Object> dataMap = new HashMap<>();
        int cnt=1;
        for (Object jsonObject : jsonList) {
            JSONObject jsonData = (JSONObject) jsonObject;

            //????????? - ?????????
            String item = (String) jsonData.get("ITM_NM");
            //??????
            String ctyNm = (String) jsonData.get("C1_NM");
            //??????
            String grp = (String) jsonData.get("C2_NM");
            //??????????????? ?????? ??????
            if("?????????".equals(item) && !"???".equals(ctyNm) && !"???".equals(grp)) {
                //??????
                String prdde = (String) jsonData.get("PRD_DE");
                String yrdt = prdde.substring(0, 4);
                //???
                String monDt = prdde.substring(4, 6);
                //????????????(%)
                String unit = (String) jsonData.get("UNIT_NM");
                //?????????
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
        result.put("success", "??????");

        return result;
    }

    //5.?????????
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
        // {"err":"30","errMsg":"???????????? ???????????? ????????????."}

        Map<String, Object> result = new HashMap<>();
        Map<String, Object> dataMap = new HashMap<>();
        int cnt=1;
        for (Object jsonObject : jsonList) {
            JSONObject jsonData = (JSONObject) jsonObject;

            //????????? - ?????????
            String item = (String) jsonData.get("ITM_NM");
            //??????
            String ctyNm = (String) jsonData.get("C1_NM");
            //??????
            String grp = (String) jsonData.get("C2_NM");
            //??????????????? ?????? ??????
            if("?????????".equals(item) && !"???".equals(ctyNm) && !"???".equals(grp)) {
                //??????
                String prdde = (String) jsonData.get("PRD_DE");
                String yrdt = prdde.substring(0, 4);
                //???
                String monDt = prdde.substring(4, 6);
                //????????????(%)
                String unit = (String) jsonData.get("UNIT_NM");
                //?????????
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
        result.put("success", "??????");

        return result;
    }

    //6. ?????????????????????
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
        // {"err":"30","errMsg":"???????????? ???????????? ????????????."}

        Map<String, Object> result = new HashMap<>();
        Map<String, Object> dataMap = new HashMap<>();
        int cnt=1;
        for (Object jsonObject : jsonList) {
            JSONObject jsonData = (JSONObject) jsonObject;

            //??????
            String type = (String) jsonData.get("C1_NM");
            //??????
            String prdde = (String) jsonData.get("PRD_DE");
            String yrdt = prdde.substring(0, 4);
            //???
            String monDt = prdde.substring(4, 6);
            //????????????(%)
            String unit = (String) jsonData.get("UNIT_NM");
            //??????
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
        result.put("success", "??????");

        return result;
    }

    //7. ???????????? ?????? ??? ??????
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
        // {"err":"30","errMsg":"???????????? ???????????? ????????????."}

        Map<String, Object> result = new HashMap<>();
        Map<String, Object> dataMap = new HashMap<>();
        int cnt=1;
        for (Object jsonObject : jsonList) {
            JSONObject jsonData = (JSONObject) jsonObject;

            //??????
            String prdde = (String) jsonData.get("PRD_DE");
            String yrdt = prdde.substring(0, 4);

            //??????
            String ctynm = (String) jsonData.get("C1_NM");

            //?????????
            String topgrp = (String) jsonData.get("C2_NM");

            //?????????
            String midgrp = (String) jsonData.get("ITM_NM");

            //?????? ??????
            if("???".equals(midgrp)) {
                continue;
            }

            //????????????(%,???)
            String unit = (String) jsonData.get("UNIT_NM");

            //??????
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
        result.put("success", "??????");

        return result;
    }

    //8. ??????????????? ???????????? ?????????
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
        // {"err":"30","errMsg":"???????????? ???????????? ????????????."}

        Map<String, Object> result = new HashMap<>();
        Map<String, Object> dataMap = new HashMap<>();
        int cnt=1;
        for (Object jsonObject : jsonList) {
            JSONObject jsonData = (JSONObject) jsonObject;

            //??????
            String prdde = (String) jsonData.get("PRD_DE");
            String yrdt = prdde.substring(0, 4);

            //????????????
            String topgrp = (String) jsonData.get("C2_NM");

            //????????????
            String midgrp = (String) jsonData.get("C1_NM");

            //??????
            String unit = (String) jsonData.get("UNIT_NM");

            //?????????
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
        result.put("success", "??????");

        return result;
    }

    //9.?????? ?????????
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    @GetMapping("/admin/lifeSatisfaction/LifeSatisfaction2")
    public Object LifeSatisfaction2_Parse(String url, String parameter) throws Exception {
        log.info(PAGE_ID + DIRECTORY + PROGRAM_ID + "LifeSatisfaction");  //DT_417001_0002 // life_stsfc
        //return PAGE_ID + DIRECTORY + PROGRAM_ID + "Main";
        System.out.println("url : " + url);
        System.out.println("parameter : " + parameter);
        //DT_ES2017_037
//https://kosis.kr/statHtml/statHtml.do?orgId=402&tblId=DT_ES2017_037&vw_cd=MT_ZTITLE&list_id=402_siew6548_2017_50_20&seqNo=&lang_mode=ko&language=kor&obj_var_id=&itm_id=&conn_path=MT_ZTITLE

        //kosis = json, enara = xml
        String format = "json";
        String site = "kosis";
        StringBuilder stringBuilder = commonService.getApiResult(url, parameter, format, site);
        JSONArray jsonList = (JSONArray) commonService.apiJsonParser(stringBuilder);
        //
        // {"err":"30","errMsg":"???????????? ???????????? ????????????."}

        Map<String, Object> result = new HashMap<>();
        Map<String, Object> dataMap = new HashMap<>();
        int cnt=1;
        for (Object jsonObject : jsonList) {
            JSONObject jsonData = (JSONObject) jsonObject;

            //??????
            String yrdt = (String) jsonData.get("PRD_DE");
            //yr_dt = yr_dt.substring(0, 4);
            //?????????
            String midgrp = (String) jsonData.get("C1_NM");

            //????????? - ???????????? (0~10)
            String smlgrp = (String) jsonData.get("C2_NM");
            if(smlgrp.equals("??? ?????? ????????? ??????")) {
                smlgrp = "0";
            } else if(smlgrp.equals("???")) {
                smlgrp = "1";
            } else if(smlgrp.equals("???")) {
                smlgrp = "2";
            } else if(smlgrp.equals("???")) {
                smlgrp = "3";
            } else if(smlgrp.equals("???")) {
                smlgrp = "4";
            } else if(smlgrp.equals("??? ??????")) {
                smlgrp = "5";
            } else if(smlgrp.equals("???")) {
                smlgrp = "6";
            } else if(smlgrp.equals("???")) {
                smlgrp = "7";
            } else if(smlgrp.equals("???")) {
                smlgrp = "8";
            } else if(smlgrp.equals("???")) {
                smlgrp = "9";
            } else if(smlgrp.equals("??? ?????? ????????? ??????")) {
                smlgrp = "10";
            }

            //?????????
            String topgrp="";

            //????????? ??????
            if("??????".equals(midgrp) || "??????".equals(midgrp) || "??????".equals(midgrp) || "??????".equals(midgrp) || "???".equals(midgrp) || "???".equals(midgrp) || "???".equals(midgrp)) {
                continue;
            }

            //????????? - ??????????????? ?????????
            if(midgrp != null) {
                if (midgrp.equals("????????????") || midgrp.equals("?????????") || midgrp.equals("????????????")) {
                    topgrp = "?????????";
                } else if (midgrp.equals("????????????") || midgrp.equals("???????????????")) {
                    topgrp = "????????????";
                } else if (midgrp.equals("?????????") || midgrp.equals("????????????") || midgrp.equals("????????????")) {
                    topgrp = "????????????";
                } else if (midgrp.equals("???????????????") || midgrp.equals("???????????????") || midgrp.equals("????????????") || midgrp.equals("??????")) {
                    topgrp = "????????????";
                }
            }

            //????????????(%)
            String unit = (String) jsonData.get("UNIT_NM");

            //??????
            String val = (String)jsonData.get("DT");

            dataMap.put("yrdt", yrdt);
            dataMap.put("topgrp", topgrp);
            dataMap.put("midgrp", midgrp);
            dataMap.put("smlgrp", smlgrp);
            dataMap.put("unit", unit);
            dataMap.put("val", val);
/*
            if(smlgrp.equals("??????")) {
                continue;
            }
*/
            commonService.insertContents(dataMap, PAGE_ID + PROGRAM_ID + ".insertLifeSatisfaction2");//LifeSatisfaction.insertLifeSatisfaction
            cnt++;
        }

        result.put("data", dataMap);
        result.put("size", cnt);
        result.put("success", "??????");

        return result;
    }
}