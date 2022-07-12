package com.teraenergy.bisolution.lifeSatisfaction;

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
    private static final String PROGRAM_ID = "LifeSatisfaction";
    private static final String DIRECTORY = "lifeSatisfaction/";

    @Resource(name = "commonService")
    private CommonService commonService;

    @Scheduled(cron = "0 0/2 * * * *")   //2분마다
    public void test1() throws Exception {

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


                commonService.insertContents(dataMap, PROGRAM_ID + ".insertTest1");//LifeSatisfaction.insertTest1
                cnt++;
            }
        }  //for
        System.out.println("스케쥴러 test1 종료 - 01초에 실행됨");
    }



    @Scheduled(cron = "01 * * * * *")
    public void test2() {
        System.out.println("test222222222222222222222222222222222222");
    }
}