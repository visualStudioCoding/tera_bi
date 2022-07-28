package com.teraenergy.bisolution.front.stockprices;

import com.teraenergy.global.service.CommonService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Service("stockPricesService")
public class StockPricesService {

    @Resource(name = "commonService")
    private CommonService commonService;

    public Map<String, Object> getOffsetMap(String programId, String pageId, String queryId) throws Exception {

        Map<String, String> recent = new HashMap<>();
        recent.put("recent", "recent");
        Map<String, Object> data = (Map<String, Object>) commonService.selectContents(recent, pageId + programId + queryId);

        Map<String, String> offset = new HashMap<>();
        offset.put("offset", "offset");
        if (queryId.equals(".selectBaseRate")) {
            queryId = ".selectBaseRateCompare";
        } else if(queryId.equals(".selectExchangeRate")) queryId = ".selectExchangeRateCompare";
        Map<String, Object> dataOffset = (Map<String, Object>) commonService.selectContents(offset, pageId + programId + queryId);

        float dataFloat = (Float) data.get("val");
        float dataFloatOffset = (Float) dataOffset.get("val");
        String unit = (String) data.get("unit");

        return getOffset(dataFloat, dataFloatOffset, unit);
    }

    private Map<String, Object> getOffset(float data, float dataOffset, String unit) {

        float subtraction;
        float subRate;

        if (data < dataOffset) {
            subRate = (dataOffset - data) / data * 100;
            subtraction = dataOffset - data;
        } else {
            subRate = (data - dataOffset) / data * 100;
            subtraction = data - dataOffset;
        }

        Map<String, Object> result = new HashMap<>();

        String tmpSubRate = String.format("%.2f", subRate);
        String tmpSubtraction = String.format("%.2f", subtraction);
        result.put("current", Float.toString(data));
        result.put("past", Float.toString(dataOffset));
        result.put("subRate", tmpSubRate);
        result.put("subtraction", tmpSubtraction);
        result.put("unit", unit);

        return result;
    }
}
