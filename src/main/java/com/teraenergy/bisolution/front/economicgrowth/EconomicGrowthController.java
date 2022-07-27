package com.teraenergy.bisolution.front.economicgrowth;

import com.teraenergy.global.service.CommonService;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
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

        model.addAttribute("menuCode", "001");

        return PAGE_ID + DIRECTORY + "Main";
    }

    //    환율 SELECT
    @ResponseBody
    @GetMapping("/api/getExchangeRate")
        public Map<String, String> getExchangeRate() throws Exception {

        Map<String, Float> dataMap = (Map<String, Float>) commonService.selectContents(null, PAGE_ID + PROGRAM_ID + ".selectExchangeRate");
        Map<String, Float> dataMapCmp = (Map<String, Float>) commonService.selectContents(null, PAGE_ID + PROGRAM_ID + ".selectExchangeRateCompare");
        Map<String, String> result = new HashMap<>();

        Float data = dataMap.get("val");
        Float dataCmp = dataMapCmp.get("val");
        Float subtraction = null;
        Float cmpResult = null;

        if(data < dataCmp){
            cmpResult = (dataCmp - data) / data * 100;
            subtraction = dataCmp - data;
        }else{
            cmpResult = (data - dataCmp) / data * 100;
            subtraction = data - dataCmp;
        }

        String tmpResult = String.format("%.2f", cmpResult);
        String tmpSubtraction = String.format("%.2f", subtraction);

        result.put("current",Float.toString(data));
        result.put("past",Float.toString(dataCmp));
        result.put("differ", tmpResult);
        result.put("subtraction", tmpSubtraction);


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

    //     물가 상승률(소비자, 근원, 생활) Chart
    @ResponseBody
    @GetMapping("/api/getInflationRatePeriod")
    public JSONArray getInflationRatePeriod() throws Exception {

        List<Map<String, Object>> consume = (List<Map<String, Object>>) commonService.selectList(null, PAGE_ID + PROGRAM_ID + ".selectCnsmrInrtPeriod");
        List<Map<String, Object>> source = (List<Map<String, Object>>) commonService.selectList(null, PAGE_ID + PROGRAM_ID + ".selectSrcInrtPeriod");
        List<Map<String, Object>> living = (List<Map<String, Object>>) commonService.selectList(null, PAGE_ID + PROGRAM_ID + ".selectLvngInrtPeriod");

//        List<Map<String, Object>> totalItm = new ArrayList<>();
//
//        for (int i = 0; i < consume.size(); i++) {
//            totalItm.add(consume.get(i));
//            totalItm.add(source.get(i));
//            totalItm.add(living.get(i));
//        }

        JSONArray totalItm = new JSONArray();

        int count = 0;

        for (int i = 0; i < consume.size() * 3; i++) {
            JSONArray consumeList = new JSONArray();
            JSONArray sourceList = new JSONArray();
            JSONArray livingList = new JSONArray();

            consumeList.add(consume.get(count).get("val"));
            consumeList.add(consume.get(count).get("category"));
            consumeList.add(consume.get(count).get("period"));

            sourceList.add(source.get(count).get("val"));
            sourceList.add(source.get(count).get("category"));
            sourceList.add(source.get(count).get("period"));

            livingList.add(living.get(count).get("val"));
            livingList.add(living.get(count).get("category"));
            livingList.add(living.get(count).get("period"));

            totalItm.add(i, consumeList);
            totalItm.add(i + 1, sourceList);
            totalItm.add(i + 2, livingList);

            i+=2;
            count++;
        }

//        Map<String, List<Map<String, Object>>> result = new HashMap<>();
//
//        result.put("data", totalItm);

        return totalItm;
    }

    //      코로나 시기 경제 성장률 Chart
    @ResponseBody
    @GetMapping("/api/getCovidEconomicGrowth")
    public Map<String, List<Map<String, Object>>> getCovidEconomicGrowth() throws Exception {

        List<Map<String, Object>> covidGrowth = (List<Map<String, Object>>) commonService.selectList(null, PAGE_ID + PROGRAM_ID + ".selectCovidGrowth");

        Map<String, Object> tmpData = new HashMap<>();
        tmpData.put("val", 4);
        tmpData.put("yr_dt", 2021);

        covidGrowth.add(tmpData);

        Map<String, List<Map<String, Object>>> result = new HashMap<>();

        result.put("covidGrowth", covidGrowth);

        return result;
    }

    // 1인당 국민 총 소득 및 국가 채무 현황 Chart
    @ResponseBody
    @GetMapping("/api/getStateDebt")
    public Map<String, List<Map<String, Object>>> getStateDebt(String parameter) throws Exception {
        Map<String, String> dataMap = new HashMap<>();

        if(parameter.contains("-")){
            parameter = parameter.replaceAll("[-]", "");
            String[] periods = parameter.split("  ");
            dataMap.put("searchStartDate", periods[0]);
            dataMap.put("searchEndDate", periods[1]);
        }else{
            dataMap.put("searchPeriod", parameter);
        }

        List<Map<String, Object>> gni = new ArrayList<>();
        List<Map<String, Object>> debt = new ArrayList<>();

        if(dataMap.size() <= 1) {
            gni = (List<Map<String, Object>>) commonService.selectList(dataMap, PAGE_ID + ".StandardOfLiving" + ".selectGrossNationalIncome");
            debt = (List<Map<String, Object>>) commonService.selectList(dataMap, PAGE_ID + PROGRAM_ID + ".selectStateDebt");
        }else{
            gni = (List<Map<String, Object>>) commonService.selectList(dataMap, PAGE_ID + ".StandardOfLiving" + ".selectGrossNationalIncomeDetail");
            debt = (List<Map<String, Object>>) commonService.selectList(dataMap, PAGE_ID + PROGRAM_ID + ".selectStateDebtDetail");
        }

        Map<String, List<Map<String, Object>>> result = new HashMap<>();

        result.put("gni", gni);
        result.put("debt", debt);

        return result;
    }
}
