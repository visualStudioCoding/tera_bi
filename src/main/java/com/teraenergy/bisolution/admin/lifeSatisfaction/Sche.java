package com.teraenergy.bisolution.admin.lifeSatisfaction;

import com.teraenergy.global.service.CommonService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Component
public class Sche {
    private static final String PROGRAM_ID = ".LifeSatisfaction";
    private static final String PAGE_ID = "admin";
    private static final String DIRECTORY = "/lifeSatisfaction/";

    @Resource(name = "commonService")
    private CommonService commonService;

    //@Scheduled(cron = "0 0/2 * * * *")   //2분마다
    //@Scheduled(cron = "0 0 4 * * *")   //4시마다
    public void test1() throws Exception {

        System.out.println("스케줄러 test1 시작 - 01초에 실행됨");
        System.out.println("스케줄러 test1 시작 - 01초에 실행됨");
        System.out.println("스케줄러 test1 시작 - 01초에 실행됨");
        System.out.println("스케줄러 test1 시작 - 01초에 실행됨");
        System.out.println("스케줄러 test1 시작 - 01초에 실행됨");

        String url = "https://kosis.kr/openapi/Param/statisticsParameterData.do";
        String[] parameter = new String[12];
        parameter[0] = "?method=getList&apiKey=&itmId=T3+&objL1=ALL&objL2=&objL3=&objL4=&objL5=&objL6=&objL7=&objL8=&format=json&jsonVD=Y&prdSe=M&startPrdDe=202101&endPrdDe=202101&newEstPrdCnt=&loadGubun=2&orgId=101&tblId=DT_1B83A35";
        parameter[1] = "?method=getList&apiKey=&itmId=T3+&objL1=ALL&objL2=&objL3=&objL4=&objL5=&objL6=&objL7=&objL8=&format=json&jsonVD=Y&prdSe=M&startPrdDe=202102&endPrdDe=202102&newEstPrdCnt=&loadGubun=2&orgId=101&tblId=DT_1B83A35";
        parameter[2] = "?method=getList&apiKey=&itmId=T3+&objL1=ALL&objL2=&objL3=&objL4=&objL5=&objL6=&objL7=&objL8=&format=json&jsonVD=Y&prdSe=M&startPrdDe=202103&endPrdDe=202103&newEstPrdCnt=&loadGubun=2&orgId=101&tblId=DT_1B83A35";
        parameter[3] = "?method=getList&apiKey=&itmId=T3+&objL1=ALL&objL2=&objL3=&objL4=&objL5=&objL6=&objL7=&objL8=&format=json&jsonVD=Y&prdSe=M&startPrdDe=202104&endPrdDe=202104&newEstPrdCnt=&loadGubun=2&orgId=101&tblId=DT_1B83A35";
        parameter[4] = "?method=getList&apiKey=&itmId=T3+&objL1=ALL&objL2=&objL3=&objL4=&objL5=&objL6=&objL7=&objL8=&format=json&jsonVD=Y&prdSe=M&startPrdDe=202105&endPrdDe=202105&newEstPrdCnt=&loadGubun=2&orgId=101&tblId=DT_1B83A35";
        parameter[5] = "?method=getList&apiKey=&itmId=T3+&objL1=ALL&objL2=&objL3=&objL4=&objL5=&objL6=&objL7=&objL8=&format=json&jsonVD=Y&prdSe=M&startPrdDe=202106&endPrdDe=202106&newEstPrdCnt=&loadGubun=2&orgId=101&tblId=DT_1B83A35";
        parameter[6] = "?method=getList&apiKey=&itmId=T3+&objL1=ALL&objL2=&objL3=&objL4=&objL5=&objL6=&objL7=&objL8=&format=json&jsonVD=Y&prdSe=M&startPrdDe=202107&endPrdDe=202107&newEstPrdCnt=&loadGubun=2&orgId=101&tblId=DT_1B83A35";
        parameter[7] = "?method=getList&apiKey=&itmId=T3+&objL1=ALL&objL2=&objL3=&objL4=&objL5=&objL6=&objL7=&objL8=&format=json&jsonVD=Y&prdSe=M&startPrdDe=202108&endPrdDe=202108&newEstPrdCnt=&loadGubun=2&orgId=101&tblId=DT_1B83A35";
        parameter[8] = "?method=getList&apiKey=&itmId=T3+&objL1=ALL&objL2=&objL3=&objL4=&objL5=&objL6=&objL7=&objL8=&format=json&jsonVD=Y&prdSe=M&startPrdDe=202109&endPrdDe=202109&newEstPrdCnt=&loadGubun=2&orgId=101&tblId=DT_1B83A35";
        parameter[9] = "?method=getList&apiKey=&itmId=T3+&objL1=ALL&objL2=&objL3=&objL4=&objL5=&objL6=&objL7=&objL8=&format=json&jsonVD=Y&prdSe=M&startPrdDe=202110&endPrdDe=202110&newEstPrdCnt=&loadGubun=2&orgId=101&tblId=DT_1B83A35";
        parameter[10] = "?method=getList&apiKey=&itmId=T3+&objL1=ALL&objL2=&objL3=&objL4=&objL5=&objL6=&objL7=&objL8=&format=json&jsonVD=Y&prdSe=M&startPrdDe=202111&endPrdDe=202111&newEstPrdCnt=&loadGubun=2&orgId=101&tblId=DT_1B83A35";
        parameter[11] = "?method=getList&apiKey=&itmId=T3+&objL1=ALL&objL2=&objL3=&objL4=&objL5=&objL6=&objL7=&objL8=&format=json&jsonVD=Y&prdSe=M&startPrdDe=202112&endPrdDe=202112&newEstPrdCnt=&loadGubun=2&orgId=101&tblId=DT_1B83A35";

        String format = "json";
        String site = "kosis";

        for(int i=0;i<parameter.length;i++) {
            StringBuilder stringBuilder = commonService.getApiResult(url, parameter[i], format, site);
            JSONArray jsonList = (JSONArray) commonService.apiJsonParser(stringBuilder);
            //
            // {"err":"30","errMsg":"데이터가 존재하지 않습니다."}

            Map<String, Object> result = new HashMap<>();
            Map<String, Object> dataMap = new HashMap<>();
            int cnt = 1;
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
                if (ctyCode.length() >= 5 && !"0".equals(ctyCode.substring(4, 5))) {
                    continue;
                }

                //청주 ? 중복됨
                if ("33040".equals(ctyCode)) {
                    continue;
                }

                if (ctyCode.equals("00")) {
                    ctyNm = "전국";
                } else if ("11".equals(ctyCode.substring(0, 2))) {
                    ctyNm = "서울특별시";
                } else if ("21".equals(ctyCode.substring(0, 2))) {
                    ctyNm = "부산광역시";
                } else if ("22".equals(ctyCode.substring(0, 2))) {
                    ctyNm = "대구광역시";
                } else if ("23".equals(ctyCode.substring(0, 2))) {
                    ctyNm = "인천광역시";
                } else if ("24".equals(ctyCode.substring(0, 2))) {
                    ctyNm = "광주광역시";
                } else if ("25".equals(ctyCode.substring(0, 2))) {
                    ctyNm = "대전광역시";
                } else if ("26".equals(ctyCode.substring(0, 2))) {
                    ctyNm = "울산광역시";
                } else if ("29".equals(ctyCode.substring(0, 2))) {
                    ctyNm = "세종특별자치시";
                } else if ("31".equals(ctyCode.substring(0, 2))) {
                    ctyNm = "경기도";
                } else if ("32".equals(ctyCode.substring(0, 2))) {
                    ctyNm = "강원도";
                } else if ("33".equals(ctyCode.substring(0, 2))) {
                    ctyNm = "충청북도";
                } else if ("34".equals(ctyCode.substring(0, 2))) {
                    ctyNm = "충청남도";
                } else if ("35".equals(ctyCode.substring(0, 2))) {
                    ctyNm = "전라북도";
                } else if ("36".equals(ctyCode.substring(0, 2))) {
                    ctyNm = "전라남도";
                } else if ("37".equals(ctyCode.substring(0, 2))) {
                    ctyNm = "경상북도";
                } else if ("38".equals(ctyCode.substring(0, 2))) {
                    ctyNm = "경상남도";
                } else if ("39".equals(ctyCode.substring(0, 2))) {
                    ctyNm = "제주";
                } else if ("90".equals(ctyCode.substring(0, 2))) {
                    ctyNm = "국외";
                }

                //시도 , 시군구
                String dstNm = (String) jsonData.get("C1_NM");

                //점수단위(건)
                String unit = (String) jsonData.get("UNIT_NM");

                //점수
                String val = (String) jsonData.get("DT");

                dataMap.put("yrdt", yrdt);
                dataMap.put("mondt", monDt);
                dataMap.put("areacd", ctyCode);
                dataMap.put("ctynm", ctyNm);
                dataMap.put("dstnm", dstNm);
                dataMap.put("unit", unit);
                dataMap.put("val", val);


                commonService.insertContents(dataMap, PAGE_ID + PROGRAM_ID + ".insertTest1");//LifeSatisfaction.insertTest1
                cnt++;
            }
        }  //for
        System.out.println("스케쥴러 test1 종료 - 01초에 실행됨");
    }


    //1.삶에 대한 만족도
    //@Scheduled(cron = "0 * * * * *")
    @Scheduled(cron = "* * 4 * * *")
    public void LifeSatisfaction_Schedule() throws Exception {
        String url = "https://kosis.kr/openapi/Param/statisticsParameterData.do";
        String parameter = "?method=getList&apiKey=&itmId=T1+&objL1=ALL&objL2=ALL&objL3=&objL4=&objL5=&objL6=&objL7=&objL8=&format=json&jsonVD=Y&prdSe=Y&newEstPrdCnt=1&loadGubun=2&orgId=417&tblId=DT_417001_0002";
        //https://kosis.kr/openapi/Param/statisticsParameterData.do?method=getList&apiKey=&itmId=T1+&objL1=ALL&objL2=ALL&objL3=&objL4=&objL5=&objL6=&objL7=&objL8=&format=json&jsonVD=Y&prdSe=Y&newEstPrdCnt=1&loadGubun=2&orgId=417&tblId=DT_417001_0002
        //DT_417001_0002

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
        }  //for

    }

    //2.월별혼인
//    @Scheduled(cron = "0 * * * * *")
    @Scheduled(cron = "* * 4 * * *")
    public void Marriage_Schedule() throws Exception {
        String url = "https://kosis.kr/openapi/Param/statisticsParameterData.do";
        String parameter = "?method=getList&apiKey=&itmId=T3+&objL1=ALL&objL2=&objL3=&objL4=&objL5=&objL6=&objL7=&objL8=&format=json&jsonVD=Y&prdSe=M&newEstPrdCnt=1&loadGubun=2&orgId=101&tblId=DT_1B83A35";
        //DT_1B83A35
        //https://kosis.kr/openapi/Param/statisticsParameterData.do?method=getList&apiKey=MDE5NGY4NzM1YzIxMDJmY2FlNTJkMTg0NThiZDJmMjQ=&itmId=T3+&objL1=ALL&objL2=&objL3=&objL4=&objL5=&objL6=&objL7=&objL8=&format=json&jsonVD=Y&prdSe=M&newEstPrdCnt=1&loadGubun=2&orgId=101&tblId=DT_1B83A35

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

            commonService.insertContents(dataMap, PAGE_ID + PROGRAM_ID + ".insertMarriage");//LifeSatisfaction.insertMarriage
            cnt++;
        }

    }

    //3.월별이혼
    //@Scheduled(cron = "0 * * * * *")
    @Scheduled(cron = "* * 4 * * *")
    public void  Divorce_Schedule() throws Exception {
        String url = "https://kosis.kr/openapi/Param/statisticsParameterData.do";
        String parameter = "?method=getList&apiKey=&itmId=T4+&objL1=ALL&objL2=&objL3=&objL4=&objL5=&objL6=&objL7=&objL8=&format=json&jsonVD=Y&prdSe=M&newEstPrdCnt=1&loadGubun=2&orgId=101&tblId=DT_1B85033";
        //DT_1B85033
        //?method=getList&apiKey=MDE5NGY4NzM1YzIxMDJmY2FlNTJkMTg0NThiZDJmMjQ=&itmId=T4+&objL1=ALL&objL2=&objL3=&objL4=&objL5=&objL6=&objL7=&objL8=&format=json&jsonVD=Y&prdSe=M&newEstPrdCnt=1&loadGubun=2&orgId=101&tblId=DT_1B85033

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



            commonService.insertContents(dataMap, PAGE_ID + PROGRAM_ID + ".insertDivorce");//LifeSatisfaction.insertMarriage
            cnt++;
        }

    }

    //4.월별 고용률
    @Scheduled(cron = "* * 4 * * *")
    public void Emplyrate_Schedule() throws Exception {
        String url = "https://kosis.kr/openapi/Param/statisticsParameterData.do";
        String parameter = "?method=getList&apiKey=&itmId=T90+&objL1=ALL&objL2=ALL&objL3=&objL4=&objL5=&objL6=&objL7=&objL8=&format=json&jsonVD=Y&prdSe=M&newEstPrdCnt=1&loadGubun=2&orgId=101&tblId=DT_1DA7014S";
        //DT_1DA7014S
        //https://kosis.kr/openapi/Param/statisticsParameterData.do?method=getList&apiKey=MDE5NGY4NzM1YzIxMDJmY2FlNTJkMTg0NThiZDJmMjQ=&itmId=T90+&objL1=ALL&objL2=ALL&objL3=&objL4=&objL5=&objL6=&objL7=&objL8=&format=json&jsonVD=Y&prdSe=M&newEstPrdCnt=1&loadGubun=2&orgId=101&tblId=DT_1DA7014S

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

    }

    //5.행정구역(시도)/성별 실업률
    @Scheduled(cron = "* * 4 * * *")
    public void unmplyrate_Schedule() throws Exception {
        String url = "https://kosis.kr/openapi/Param/statisticsParameterData.do";
        //String parameter = "?method=getList&apiKey=&itmId=T90+&objL1=ALL&objL2=ALL&objL3=&objL4=&objL5=&objL6=&objL7=&objL8=&format=json&jsonVD=Y&prdSe=M&newEstPrdCnt=1&loadGubun=2&orgId=101&tblId=DT_1DA7014S";
        String parameter = "?method=getList&apiKey=&itmId=T80+&objL1=ALL&objL2=ALL&objL3=&objL4=&objL5=&objL6=&objL7=&objL8=&format=json&jsonVD=Y&prdSe=M&newEstPrdCnt=1&loadGubun=2&orgId=101&tblId=DT_1DA7104S";
        //DT_1DA7104S
        //https://kosis.kr/openapi/Param/statisticsParameterData.do?method=getList&apiKey=MDE5NGY4NzM1YzIxMDJmY2FlNTJkMTg0NThiZDJmMjQ=&itmId=T90+&objL1=ALL&objL2=ALL&objL3=&objL4=&objL5=&objL6=&objL7=&objL8=&format=json&jsonVD=Y&prdSe=M&newEstPrdCnt=1&loadGubun=2&orgId=101&tblId=DT_1DA7014S

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
    }

    //6.전산업생산지수
    @Scheduled(cron = "* * 4 * * *")
    public void allprindex_Schedule() throws Exception {
        String url = "https://kosis.kr/openapi/Param/statisticsParameterData.do";
        //String parameter = "?method=getList&apiKey=&itmId=T90+&objL1=ALL&objL2=ALL&objL3=&objL4=&objL5=&objL6=&objL7=&objL8=&format=json&jsonVD=Y&prdSe=M&newEstPrdCnt=1&loadGubun=2&orgId=101&tblId=DT_1DA7014S";
        String parameter = "?method=getList&apiKey=&itmId=T1+&objL1=ALL&objL2=&objL3=&objL4=&objL5=&objL6=&objL7=&objL8=&format=json&jsonVD=Y&prdSe=M&newEstPrdCnt=1&loadGubun=2&orgId=101&tblId=DT_1JH20151";
        //DT_1JH20151

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
    }

    //7.해외여행
    //@Scheduled(cron = "0 * * * * *")
    @Scheduled(cron = "* * 4 * * *")
    public void ovrsstrip_Schedule() throws Exception {
        String url = "https://kosis.kr/openapi/Param/statisticsParameterData.do";
        //String parameter = "?method=getList&apiKey=&itmId=T90+&objL1=ALL&objL2=ALL&objL3=&objL4=&objL5=&objL6=&objL7=&objL8=&format=json&jsonVD=Y&prdSe=M&newEstPrdCnt=1&loadGubun=2&orgId=101&tblId=DT_1DA7014S";
        //https://kosis.kr/openapi/Param/statisticsParameterData.do
        // ?method=getList&apiKey=MDE5NGY4NzM1YzIxMDJmY2FlNTJkMTg0NThiZDJmMjQ=
        // &itmId=T00+T10+T11+T12+T13+T14+T15+T16+T17+T18+&objL1=ALL&objL2=ALL&objL3=&objL4=&objL5=&objL6=&objL7=&objL8=&format=json&jsonVD=Y&prdSe=Y&newEstPrdCnt=1&loadGubun=2&orgId=101&tblId=DT_1SSCL060R
        String parameter = "?method=getList&apiKey=&itmId=T00+T10+T11+T12+T13+T14+T15+T16+T17+T18+&objL1=ALL&objL2=ALL&objL3=&objL4=&objL5=&objL6=&objL7=&objL8=&format=json&jsonVD=Y&prdSe=Y&newEstPrdCnt=1&loadGubun=2&orgId=101&tblId=DT_1SSCL060R";
        //DT_1JH20151

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

    }

    //8.기업규모별 개인소득 점유율
    //@Scheduled(cron = "0 * * * * *")
    @Scheduled(cron = "* * 4 * * *")
    public void prsnlinshr_Schedule() throws Exception {
        String url = "https://kosis.kr/openapi/Param/statisticsParameterData.do";
//        https://kosis.kr/openapi/Param/statisticsParameterData.do?method=getList&apiKey=MDE5NGY4NzM1YzIxMDJmY2FlNTJkMTg0NThiZDJmMjQ=
//        &itmId=T001+&objL1=ALL&objL2=ALL&objL3=&objL4=&objL5=&objL6=&objL7=&objL8=&format=json&jsonVD=Y&prdSe=Y&startPrdDe=2020&endPrdDe=2020&loadGubun=2&orgId=101&tblId=DT_1EP_2001
        String parameter = "?method=getList&apiKey=&itmId=T001+&objL1=ALL&objL2=ALL&objL3=&objL4=&objL5=&objL6=&objL7=&objL8=&format=json&jsonVD=Y&prdSe=Y&newEstPrdCnt=1&loadGubun=2&orgId=101&tblId=DT_1EP_2001";
        //DT_1JH20151

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
    }


    //9.삶의  만족도
    //@Scheduled(cron = "0 * * * * *")
    @Scheduled(cron = "* * 4 * * *")
    public void LifeSatisfaction2_Schedule() throws Exception {
        String url = "https://kosis.kr/openapi/Param/statisticsParameterData.do";
        String parameter = "?method=getList&apiKey=&itmId=QQQ+&objL1=ALL&objL2=ALL&objL3=&objL4=&objL5=&objL6=&objL7=&objL8=&format=json&jsonVD=Y&prdSe=Y&newEstPrdCnt=1&loadGubun=2&orgId=402&tblId=DT_ES2017_037";

        //https://kosis.kr/openapi/Param/statisticsParameterData.do?method=getList&apiKey=MDE5NGY4NzM1YzIxMDJmY2FlNTJkMTg0NThiZDJmMjQ=
        //&itmId=QQQ+&objL1=ALL&objL2=ALL&objL3=&objL4=&objL5=&objL6=&objL7=&objL8=&format=json&jsonVD=Y&prdSe=Y&startPrdDe=2021&endPrdDe=2021&loadGubun=2&orgId=402&tblId=DT_ES2017_037
        //DT_ES2017_037

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
            if(smlgrp.equals("ⓞ 삶의 만족도 낮음")) {
                smlgrp = "0";
            } else if(smlgrp.equals("①")) {
                smlgrp = "1";
            } else if(smlgrp.equals("②")) {
                smlgrp = "2";
            } else if(smlgrp.equals("③")) {
                smlgrp = "3";
            } else if(smlgrp.equals("④")) {
                smlgrp = "4";
            } else if(smlgrp.equals("⑤ 중간")) {
                smlgrp = "5";
            } else if(smlgrp.equals("⑥")) {
                smlgrp = "6";
            } else if(smlgrp.equals("⑦")) {
                smlgrp = "7";
            } else if(smlgrp.equals("⑧")) {
                smlgrp = "8";
            } else if(smlgrp.equals("⑨")) {
                smlgrp = "9";
            } else if(smlgrp.equals("⑩ 삶의 만족도 높음")) {
                smlgrp = "10";
            }

            //대분류
            String topgrp="";

            //소계는 통과
            if("소계".equals(midgrp) || "전체".equals(midgrp) || "남자".equals(midgrp) || "여자".equals(midgrp) || "상".equals(midgrp) || "중".equals(midgrp) || "하".equals(midgrp)) {
                continue;
            }

            //대분류 - 중분류보고 만든다
            if(midgrp != null) {
                if (midgrp.equals("초등학교") || midgrp.equals("중학교") || midgrp.equals("고등학교")) {
                    topgrp = "학교급";
                } else if (midgrp.equals("일반계고") || midgrp.equals("특성화계고")) {
                    topgrp = "고교유형";
                } else if (midgrp.equals("대도시") || midgrp.equals("중소도시") || midgrp.equals("읍면지역")) {
                    topgrp = "지역규모";
                } else if (midgrp.equals("양부모가정") || midgrp.equals("한부모가정") || midgrp.equals("조손가정") || midgrp.equals("기타")) {
                    topgrp = "가족유형";
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
/*
            if(smlgrp.equals("평균")) {
                continue;
            }
*/
            commonService.insertContents(dataMap, PAGE_ID + PROGRAM_ID + ".insertLifeSatisfaction2");//LifeSatisfaction.insertLifeSatisfaction
            cnt++;
        }

    }

}