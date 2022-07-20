package com.teraenergy.bisolution.admin.realestate;

import com.teraenergy.global.common.utilities.AreaNameUtil;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service("realEstateService")
public class RealEstateService {

    public Map<String, String> splitParameter(String parameter) {
        Map<String, String> result = new HashMap<>();
        String str = "&endPrdDe=";
        String[] params = parameter.split(str);

        String param1 = params[0];
        String param2 = params[1];
        String paramYear = param2.substring(0, 4);

        result.put("firstParam", param1);
        result.put("str", str);
        result.put("paramYear", paramYear);
        result.put("lastParam", param2.substring(4));

        return result;
    }

    public String stringCombination(Map<String, String> splitParams, int month) {
        return splitParams.get("firstParam") + String.format("%02d", month)
                + splitParams.get("str") + splitParams.get("paramYear") + String.format("%02d", month) + splitParams.get("lastParam");
    }

    public Map<String, Object> jsonArrayToMap(String api, JSONArray jsonList) {
        Map<String, Object> result = new HashMap<>();

        for (Object jsonObject : jsonList) {
            JSONObject jsonData = (JSONObject) jsonObject;

            String date = (String) jsonData.get("PRD_DE");
            String year = date.substring(0, 4);
            String getMonth = date.substring(4, 6);

            if ("gender".equals(api)) {
                String gender = (String) jsonData.get("ITM_NM_ENG");
                gender = gender.contains("Male") ? "남" : "여";
                result.put("gender", gender);
            }
            String ctyName = AreaNameUtil.areaName((String) jsonData.get("C1"), "");
            String areaCd = (String) jsonData.get("C1");

            result.put("yrDt", year);
            result.put("monDt", getMonth);
            result.put("ctyNm", ctyName);
            result.put("areaCd", areaCd);
            result.put("dstNm", jsonData.get("C1_NM"));
            result.put("unit", jsonData.get("UNIT_NM"));
            result.put("val", jsonData.get("DT"));
        }

        return result;
    }
}
