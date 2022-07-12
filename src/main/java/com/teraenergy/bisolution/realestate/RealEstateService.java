package com.teraenergy.bisolution.realestate;

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
        String paramYear = param2.substring(0,4);

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
}
