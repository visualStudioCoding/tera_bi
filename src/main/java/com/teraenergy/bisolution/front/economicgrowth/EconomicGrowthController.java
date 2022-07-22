package com.teraenergy.bisolution.front.economicgrowth;

import com.teraenergy.global.service.CommonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/front/economicGrowth")
public class EconomicGrowthController {
    private static final String PROGRAM_ID = ".EconomicGrowth";
    private static final String PAGE_ID = "front";

    private static final String DIRECTORY = "/economicGrowth/EconomicGrowth";

    @Resource(name = "commonService")
    private CommonService commonService;

    @GetMapping("/main")
    public String economicGrowthMain(Model model) throws Exception {
        log.info(PAGE_ID + DIRECTORY + "Main");

        model.addAttribute("exchangeRate",getExchangeRate());
        model.addAttribute("baseRate",getBaseRate());
        model.addAttribute("gdpGni",getGDPGNP());

        return PAGE_ID + DIRECTORY + "Main";
    }

    @ResponseBody
    @GetMapping("/api/getExchangeRate")
        public Map<String, String> getExchangeRate() throws Exception {

        Map<String, Float> dataMap = (Map<String, Float>) commonService.selectContents(null, PAGE_ID + PROGRAM_ID + ".selectExchangeRate");
        Map<String, Float> dataMapCmp = (Map<String, Float>) commonService.selectContents(null, PAGE_ID + PROGRAM_ID + ".selectExchangeRateCompare");
        Map<String, String> result = new HashMap<>();

        float data = dataMap.get("val");
        float dataCmp = dataMapCmp.get("val");
        Float cmpResult = null;

        if(data < dataCmp){
            cmpResult = (dataCmp - data) / data * 100;
        }else{
            cmpResult = (data - dataCmp) / data * 100;
        }
        String tmpResult = String.format("%.2f", cmpResult);

        result.put("current",Float.toString(data));
        result.put("past",Float.toString(dataCmp));
        result.put("differ", tmpResult);


        return result;
        }

    @ResponseBody
    @GetMapping("/api/getBaseRate")
    public Map<String, String> getBaseRate() throws Exception {

        Map<String, Float> dataMap = (Map<String, Float>) commonService.selectContents(null, PAGE_ID + PROGRAM_ID + ".selectBaseRate");
        Map<String, Float> dataMapCmp = (Map<String, Float>) commonService.selectContents(null, PAGE_ID + PROGRAM_ID + ".selectBaseRateCompare");
        Float data = dataMap.get("val");
        Float dataCmp = dataMapCmp.get("val");
        String cmpResult = null;

        if(data < dataCmp){
            cmpResult = Float.toString((dataCmp - data) / data * 100);
        }else{
            cmpResult = Float.toString((data - dataCmp) / data * 100);
        }

        Map<String, String> result = new HashMap<>();

        result.put("current",Float.toString(data));
        result.put("past",Float.toString(dataCmp));
        result.put("differ", cmpResult);


        return result;
    }

    @ResponseBody
    @GetMapping("/api/getGDPGNP")
    public Map<String, String> getGDPGNP() throws Exception {

        Map<String, Float> dataMapGDP = (Map<String, Float>) commonService.selectContents(null, PAGE_ID + PROGRAM_ID + ".selectGDP");
        Map<String, Float> dataMapGNI = (Map<String, Float>) commonService.selectContents(null, PAGE_ID + PROGRAM_ID + ".selectGNI");
        String data = Float.toString(dataMapGDP.get("val"));
        data = data.replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",");

        String dataCmp = Float.toString(dataMapGNI.get("val"));
        dataCmp = dataCmp.replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",");

        Map<String, String> result = new HashMap<>();

        result.put("GDP",data);
        result.put("GNI",dataCmp);


        return result;
    }
}
