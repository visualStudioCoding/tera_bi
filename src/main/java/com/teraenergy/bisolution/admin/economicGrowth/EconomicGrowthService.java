package com.teraenergy.bisolution.admin.economicGrowth;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service("economicGrowthService")
public class EconomicGrowthService {

    public Map<String, String> splitParmas(String parameter){
        Map<String, String> param = new HashMap<>();

        String devideOne = "startPrdDe=";
        String devideTwo = "endPrdDe=";

        String[] unitOne = parameter.split(devideOne);
        String[] unitTwo = parameter.split(devideTwo);

        param.put("startPeriod", unitOne[0]);
        param.put("endPeriod", unitTwo[1]);

        return param;
    }

    public String combineParams(Map<String, String> parameter, String year, String month){
        String period = year + month;
        String param = parameter.get("startPeriod") + "startPrdDe=" + period + "&" + "endPrdDe=" + period + parameter.get("endPeriod");

        return param;
    }
}
