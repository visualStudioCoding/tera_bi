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
import java.util.List;
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

        model.addAttribute("exchangeRate", getExchangeRate());
        model.addAttribute("baseRate", getBaseRate());
        model.addAttribute("gdpGni", getGDPGNP());
        model.addAttribute("inflationRate", getInflationRate());

        return PAGE_ID + DIRECTORY + "Main";
    }

    //    환율 SELECT
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

    //    기준금리 SELECT
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

    //    GDP 및 GNP SELECT
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

    //    경제성장률률 SELECT
    @ResponseBody
    @GetMapping("/api/getEconomicGrowth")
    public Map<String, List<Map<String, Object>>> getEconomicGrowth() throws Exception {

        List<Map<String, Object>> dataList = (List<Map<String, Object>>) commonService.selectList(null, PAGE_ID + PROGRAM_ID + ".selectEnmcGrrt");
//        Map<String, String> EmncGrrt = (Map<String, String>) commonService.selectContents(null, PAGE_ID + PROGRAM_ID + ".selectEnmcGrrtOne");

        Map<String, List<Map<String, Object>>> result = new HashMap<>();

        result.put("emncGrrt", dataList);

        return result;
    }

    //     물가 상승률(소비자, 근원, 생활) SELECT
    @ResponseBody
    @GetMapping("/api/getInflationRate")
    public Map<String, String> getInflationRate() throws Exception {

        Map<String, Float> consume = (Map<String, Float>) commonService.selectContents(null, PAGE_ID + PROGRAM_ID + ".selectCnsmrInrt");
        Map<String, Float> source = (Map<String, Float>) commonService.selectContents(null, PAGE_ID + PROGRAM_ID + ".selectSrcInrt");
        Map<String, Float> living = (Map<String, Float>) commonService.selectContents(null, PAGE_ID + PROGRAM_ID + ".selectLvngInrt");

        Map<String, String> result = new HashMap<>();

        result.put("consume", Float.toString(consume.get("val")));
        result.put("source", Float.toString(source.get("val")));
        result.put("living", Float.toString(living.get("val")));

        return result;
    }

}
