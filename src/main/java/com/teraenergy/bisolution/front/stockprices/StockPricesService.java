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

        Map<String, Object> data = (Map<String, Object>) commonService.selectContents(null, pageId + programId + queryId);

        String offset = "Offset";

        if(programId.equals(".EconomicGrowth")) offset = "Compare";
        Map<String, Object> dataOffset = (Map<String, Object>) commonService.selectContents(null, pageId + programId + queryId + offset);

        Float dataFloat = (Float) data.get("val");
        Float dataFloatOffset = (Float) dataOffset.get("val");

        return getOffset(dataFloat, dataFloatOffset);
    }
    private Map<String, Object> getOffset(Float data, Float dataOffset) {

        Float subtraction = null;
        Float subRate = null;

        if(data < dataOffset){
            subRate = (dataOffset - data) / data * 100;
            subtraction = dataOffset - data;
        }else{
            subRate = (data - dataOffset) / data * 100;
            subtraction = data - dataOffset;
        }

        Map<String, Object> result = new HashMap<>();

        String tmpSubRate = String.format("%.2f", subRate);
        String tmpSubtraction = String.format("%.2f", subtraction);
        result.put("current",Float.toString(data));
        result.put("past",Float.toString(dataOffset));
        result.put("subRate", tmpSubRate);
        result.put("subtraction", tmpSubtraction);

        return result;
    }

}
