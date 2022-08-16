package com.teraenergy.bisolution.front.economicgrowth;

import com.teraenergy.global.service.CommonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
    public String economicGrowthMain(String parameter, Model model) throws Exception {
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

        String period = String.valueOf(dataMap.get("baseDate"));
        Float data = dataMap.get("val");
        Float dataCmp = dataMapCmp.get("val");
        Float subtraction = null;
        Float cmpResult = null;

        if (data < dataCmp) {
            cmpResult = (dataCmp - data) / data * 100;
            subtraction = dataCmp - data;
        } else {
            cmpResult = (data - dataCmp) / data * 100;
            subtraction = data - dataCmp;
        }

        String tmpResult = String.format("%.2f", cmpResult);
        String tmpSubtraction = String.format("%.2f", subtraction);

        result.put("dataDate", period);
        result.put("current", Float.toString(data));
        result.put("past", Float.toString(dataCmp));
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
        Float cmpResult = null;
        Float substitude = null;
        String dataDate = String.valueOf(dataMap.get("baseDate"));

        if (data < dataCmp) {
            cmpResult = (dataCmp - data) / data * 100;
            substitude = dataCmp - data;
        } else {
            cmpResult = (data - dataCmp) / data * 100;
            substitude = data - dataCmp;
        }

        Map<String, String> result = new HashMap<>();

        result.put("date", dataDate);
        result.put("current", Float.toString(data));
        result.put("past", Float.toString(dataCmp));
        result.put("subRate", String.format("%.2f",cmpResult));
        result.put("differ", String.format("%.1f",substitude));



        return result;
    }

    //    GDP 및 GNP SELECT
    @ResponseBody
    @GetMapping("/api/getGDPGNP")
    public Map<String, Map<String, Object>> getGDPGNP() throws Exception {

        Map<String, Object> dataMapGDP = (Map<String, Object>) commonService.selectContents(null, PAGE_ID + PROGRAM_ID + ".selectGDP");
        Map<String, Object> dataMapGNI = (Map<String, Object>) commonService.selectContents(null, PAGE_ID + PROGRAM_ID + ".selectGNI");

        Map<String, Object> GDP = new HashMap<>();
        Map<String, Object> GNI = new HashMap<>();


        String dataGDP = Float.toString((Float) dataMapGDP.get("real_val"));
        String gdpDate = (String) dataMapGDP.get("yr_dt");
        dataGDP = dataGDP.replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",");
        GDP.put("date", gdpDate);
        GDP.put("val", dataGDP);
        GDP.put("unit", dataMapGDP.get("unit"));

        String dataGNI = Float.toString((Float) dataMapGNI.get("val"));
        String gniDate = (String) dataMapGNI.get("yr_dt");
        dataGNI = dataGNI.replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",");
        GNI.put("date", gniDate);
        GNI.put("val", dataGNI);
        GNI.put("unit", dataMapGNI.get("unit"));

        Map<String, Map<String, Object>> result = new HashMap<>();

        result.put("GDP", GDP);
        result.put("GNI", GNI);


        return result;
    }

    //    경제성장률 SELECT
    @ResponseBody
    @GetMapping("/api/getEconomicGrowth")
    public Map<String, Object> getEconomicGrowth(String parameter) throws Exception {

        Map<String, String> params = new HashMap<>();

        LocalDate now = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy");
        String currentYear = now.format(formatter);

        List<Map<String, Object>> dataList = new ArrayList<>();
        Object wholeRegion = new Object();

        if(parameter.equals("0")) {
            dataList = (List<Map<String, Object>>) commonService.selectList(params, PAGE_ID + PROGRAM_ID + ".selectEconomicGrowthDefault");
//            for (int i = 0; i < dataList.size(); i++) {
//                if("전국".equals(dataList.get(i).get("cty_nm"))){
//                    wholeRegion = dataList.get(i).get("val");
//                }
//            }
        }else{
            if (parameter.contains("-")) {
                parameter = parameter.replaceAll("[-]", "");
                String[] periods = parameter.split("  ");

                params.put("searchDate", periods[0].substring(0, 4));
            } else {
                params.put("searchDate", Integer.toString(Integer.parseInt(currentYear) - Integer.parseInt(parameter)));
            }

            dataList = (List<Map<String, Object>>) commonService.selectList(params, PAGE_ID + PROGRAM_ID + ".selectEconomicGrowthPeriod");
        }

//        Map<String, String> EmncGrrt = (Map<String, String>) commonService.selectContents(null, PAGE_ID + PROGRAM_ID + ".selectEnmcGrrtOne");

        Map<String, Object> result = new HashMap<>();

        if(dataList.size() == 0) {
            result.put("emncGrrt", dataList);
//            result.put("wholeRegion", wholeRegion);
            result.put("result", "Fail");
        }else{
            result.put("emncGrrt", dataList);
//            result.put("wholeRegion", wholeRegion);
            result.put("result", "Success");
        }
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
    public List<Object> getInflationRatePeriod(String parameter) throws Exception {

        Map<String, String> params = new HashMap<>();

        LocalDate now = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy");
        String currentYear = now.format(formatter);

        if (parameter.contains("-")) {
            parameter = parameter.replaceAll("[-]", "");
            String[] periods = parameter.split("  ");

            params.put("searchStartDate", periods[0].substring(0,6));
            params.put("searchEndDate", periods[1].substring(0,6));
        } else {
            params.put("searchDate", Integer.toString(Integer.parseInt(currentYear) - Integer.parseInt(parameter)));
        }

        List<Map<String, Object>> consume = new ArrayList<>();
        List<Map<String, Object>> source = new ArrayList<>();
        List<Map<String, Object>> living = new ArrayList<>();

        if(params.size() <= 1) {
            consume = (List<Map<String, Object>>) commonService.selectList(params, PAGE_ID + PROGRAM_ID + ".selectCnsmrInrtPeriod");
            source = (List<Map<String, Object>>) commonService.selectList(params, PAGE_ID + PROGRAM_ID + ".selectSrcInrtPeriod");
            living = (List<Map<String, Object>>) commonService.selectList(params, PAGE_ID + PROGRAM_ID + ".selectLvngInrtPeriod");
        } else{
            consume = (List<Map<String, Object>>) commonService.selectList(params, PAGE_ID + PROGRAM_ID + ".selectCnsmrInrtDetail");
            source = (List<Map<String, Object>>) commonService.selectList(params, PAGE_ID + PROGRAM_ID + ".selectSrcInrtDetail");
            living = (List<Map<String, Object>>) commonService.selectList(params, PAGE_ID + PROGRAM_ID + ".selectLvngInrtDetail");
        }

//        List<Map<String, Object>> totalItm = new ArrayList<>();
//
//        for (int i = 0; i < consume.size(); i++) {
//            totalItm.add(consume.get(i));
//            totalItm.add(source.get(i));
//            totalItm.add(living.get(i));
//        }

        List<Object> totalItm = new ArrayList<>();

        int count = 0;

        for (int i = 0; i < consume.size() * 3; i++) {
            List<Object> consumeList = new ArrayList<>();
            List<Object> sourceList = new ArrayList<>();
            List<Object> livingList = new ArrayList<>();

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

            i += 2;
            count++;
        }
        List<String> guid = new ArrayList<>();

//        System.out.println(consume);
//        System.out.println(source);
//        System.out.println(living);

        if(consume.size() == 0 || source.size() == 0 || living.size() == 0) {
            guid.add("Fail");
        }else{
            guid.add("inflPer");
            guid.add("Category");
            guid.add("Year");
        }

        totalItm.add(0, guid);

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
        tmpData.put("val", 3.6);
        tmpData.put("yr_dt", 2021);

        covidGrowth.add(tmpData);

        Map<String, List<Map<String, Object>>> result = new HashMap<>();

        result.put("covidGrowth", covidGrowth);

        return result;
    }

    // 1인당 국민 총 소득 및 국가 채무 현황 Chart
    @ResponseBody
    @GetMapping("/api/getStateDebt")
    public Map<String, Object> getStateDebt(String parameter) throws Exception {
        Map<String, String> dataMap = new HashMap<>();

        if(parameter.equals("0")) {
            dataMap.put("searchPeriod", "3");
        }else{
            if (parameter.contains("-")) {
                parameter = parameter.replaceAll("[-]", "");
                String[] periods = parameter.split("  ");
                dataMap.put("searchStartDate", periods[0]);
                dataMap.put("searchEndDate", periods[1]);
            } else {
                dataMap.put("searchPeriod", parameter);
            }
        }

        List<Map<String, Object>> gdp = new ArrayList<>();
        List<Map<String, Object>> debt = new ArrayList<>();

        if (dataMap.size() <= 1) {
            gdp = (List<Map<String, Object>>) commonService.selectList(dataMap, PAGE_ID + PROGRAM_ID + ".selectWholeGDP");
            debt = (List<Map<String, Object>>) commonService.selectList(dataMap, PAGE_ID + PROGRAM_ID + ".selectStateDebt");
        } else {
            gdp = (List<Map<String, Object>>) commonService.selectList(dataMap, PAGE_ID + PROGRAM_ID + ".selectWholeGDPDetail");
            debt = (List<Map<String, Object>>) commonService.selectList(dataMap, PAGE_ID + PROGRAM_ID + ".selectStateDebtDetail");
        }

        Map<String, Object> result = new HashMap<>();

        if(gdp.size() == 0 || debt.size() == 0) {
            result.put("gdp", gdp);
            result.put("debt", debt);
            result.put("result", "Fail");
        } else {
            result.put("gdp", gdp);
            result.put("debt", debt);
            result.put("result", "Success");
        }

        System.out.println(result);

        return result;
    }
}
