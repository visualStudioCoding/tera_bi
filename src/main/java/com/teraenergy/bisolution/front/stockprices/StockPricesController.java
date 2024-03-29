package com.teraenergy.bisolution.front.stockprices;

import com.teraenergy.global.common.utilities.SearchUtil;
import com.teraenergy.global.service.CommonService;
import com.teraenergy.global.service.VolatilityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.*;

/**
 * 종합주가지수
 *
 * @author tera
 * @version 1.0.0
 * 작성일 2022-07-26
 **/
@Slf4j
@Controller
@RequestMapping("/front/stockPrices")
public class StockPricesController {
    private static final String PROGRAM_ID = ".StockPrices";
    private static final String PAGE_ID = "front";
    private static final String DIRECTORY = "/stockPrices/StockPrices";

    private static final String ECONOMIC_PROGRAM_ID = ".EconomicGrowth";

    @Resource(name = "commonService")
    private CommonService commonService;

    @Resource(name = "volatilityService")
    private VolatilityService volatilityService;

    @GetMapping("/main")
    public String stockPricesMain(@RequestParam Map<String, Object> paramMap, Model model) throws Exception {
        log.info(PAGE_ID + DIRECTORY + "Main");
        model.addAttribute("menuCode", "003");

        //if(StringUtils.isEmpty((String) paramMap.get("searchPeriod"))) paramMap.put("term", "5");

        Map<String, String> dateParam = SearchUtil.searchDate(paramMap);
        //if("0".equals(dateParam.get("searchPeriod"))) dateParam.put("searchPeriod", "5");
        model.addAttribute("kospiIndex", volatilityService.getOffsetMap(dateParam, PROGRAM_ID, PAGE_ID, ".selectKospiIndex"));
        model.addAttribute("kosdaqIndex", volatilityService.getOffsetMap(dateParam, PROGRAM_ID, PAGE_ID, ".selectKosdaqIndex"));
        model.addAttribute("oilPrice", volatilityService.getOffsetMap(dateParam, PROGRAM_ID, PAGE_ID, ".selectOilPrice"));
        model.addAttribute("baseRate", volatilityService.getOffsetMap(dateParam, ECONOMIC_PROGRAM_ID, PAGE_ID, ".selectBaseRate"));
        model.addAttribute("exchangeRate", volatilityService.getOffsetMap(dateParam, ECONOMIC_PROGRAM_ID, PAGE_ID, ".selectExchangeRate"));

//        commonService.selectEmplyList(null, PAGE_ID + PROGRAM_ID + ".selectEmplyList");
        return PAGE_ID + DIRECTORY + "Main";
    }

    /*@ResponseBody
    @GetMapping("/api/getKospiIndex")
    public Map<String, Object> kospiIndex(@RequestParam Map<String, Object> paramMap) throws Exception {
        Map<String, String> dateParam = SearchUtil.searchDate(paramMap);
        return stockPricesService.getOffsetMap(dateParam, PROGRAM_ID, PAGE_ID, ".selectKospiIndex");
    }
    @ResponseBody
    @GetMapping("/api/getKosdaqIndex")
    public Map<String, Object> kosdaqIndex(@RequestParam Map<String, Object> paramMap) throws Exception {
        Map<String, String> dateParam = SearchUtil.searchDate(paramMap);
        return stockPricesService.getOffsetMap(dateParam, PROGRAM_ID, PAGE_ID, ".selectKosdaqIndex");
    }
    @ResponseBody
    @GetMapping("/api/getOilPrice")
    public Map<String, Object> oilPrice(@RequestParam Map<String, Object> paramMap) throws Exception {
        Map<String, String> dateParam = SearchUtil.searchDate(paramMap);
        return stockPricesService.getOffsetMap(dateParam, PROGRAM_ID, PAGE_ID, ".selectOilPrice");
    }@ResponseBody
    @GetMapping("/api/getBaseRate")
    public Map<String, Object> baseRate(@RequestParam Map<String, Object> paramMap) throws Exception {
        Map<String, String> dateParam = SearchUtil.searchDate(paramMap);
        return stockPricesService.getOffsetMap(dateParam, ECONOMIC_PROGRAM_ID, PAGE_ID, ".selectBaseRate");
    }
    @ResponseBody
    @GetMapping("/api/getExchangeRate")
    public Map<String, Object> exchangeRate(@RequestParam Map<String, Object> paramMap) throws Exception {
        Map<String, String> dateParam = SearchUtil.searchDate(paramMap);
        return stockPricesService.getOffsetMap(dateParam, ECONOMIC_PROGRAM_ID, PAGE_ID, ".selectExchangeRate");
    }*/

    // GDP
    @ResponseBody
    @GetMapping("/api/getGdpData")
    public List<Object> gdpData(@RequestParam Map<String, Object> paramMap) throws Exception {
        log.info(PAGE_ID + DIRECTORY + "getGdpData");

        Map<String, String> dateParam = SearchUtil.searchDate(paramMap);

        List<Map<String, Object>> gdpMap;

        if (dateParam.size() <= 1) {
            if ("0".equals(dateParam.get("searchPeriod"))) dateParam.put("searchPeriod", "5");
            gdpMap = (List<Map<String, Object>>) commonService.selectList(dateParam, PAGE_ID + ECONOMIC_PROGRAM_ID + ".selectWholeGDP");
        } else {
            gdpMap = (List<Map<String, Object>>) commonService.selectList(dateParam, PAGE_ID + ECONOMIC_PROGRAM_ID + ".selectWholeGDPDetail");
        }

        List<Object> result = new ArrayList<>();

        if (gdpMap.size() == 0) {
            result.add("Fail");
        } else {
            for (Map<String, Object> objectMap : gdpMap) {
                List<Object> gdpList = new ArrayList<>();

                gdpList.add(objectMap.get("yr_dt"));
                gdpList.add(Float.parseFloat(String.valueOf(objectMap.get("nmnl_val"))) * 0.001);
                gdpList.add(Float.parseFloat(String.valueOf(objectMap.get("real_val"))) * 0.001);

                result.add(gdpList);
            }
        }

        return result;
    }


    //   코로나 시기 kospi
    @ResponseBody
    @GetMapping("/api/getCovidKospi")
    public Map<String, List<Map<String, Object>>> covidKospi() throws Exception {

        List<Map<String, Object>> covidKospiList = (List<Map<String, Object>>) commonService.selectList(null, PAGE_ID + PROGRAM_ID + ".selectCovidKospi");

        Map<String, List<Map<String, Object>>> result = new HashMap<>();

        result.put("covidKospi", covidKospiList);

        return result;
    }

    // 연간 경제성장률
    @ResponseBody
    @GetMapping("/api/getAnnualGrowthRate")
    public Map<String, Object> annualGrowthRate(@RequestParam Map<String, Object> paramMap) throws Exception {

        Map<String, String> dateParam = SearchUtil.searchDate(paramMap);

        List<Map<String, Object>> annualGrowthRateList;

        if (dateParam.size() <= 1) {
            if ("0".equals(dateParam.get("searchPeriod"))) dateParam.put("searchPeriod", "5");
            annualGrowthRateList = (List<Map<String, Object>>) commonService.selectList(dateParam, PAGE_ID + ECONOMIC_PROGRAM_ID + ".selectAnnualGrowthRate");
        } else {
            annualGrowthRateList = (List<Map<String, Object>>) commonService.selectList(dateParam, PAGE_ID + ECONOMIC_PROGRAM_ID + ".selectAnnualGrowthRateDetail");
        }

        Map<String, Object> result = new HashMap<>();

        if (annualGrowthRateList.size() == 0) {
            result.put("annualGrowthRate", annualGrowthRateList);
            result.put("result", "Fail");
        } else {
            result.put("annualGrowthRate", annualGrowthRateList);
            result.put("result", "Success");
        }

        return result;
    }

    // 기준금리 & kospi
    @ResponseBody
    @GetMapping("/api/getBaseRateAndKospi")
    public Map<String, Object> baseRateAndKospi(@RequestParam Map<String, Object> paramMap) throws Exception {

        Map<String, String> dateParam = SearchUtil.searchDate(paramMap);

        List<Map<String, Object>> baseRateAndKospiList;

        if (dateParam.size() <= 1) {
            baseRateAndKospiList = (List<Map<String, Object>>) commonService.selectList(dateParam, PAGE_ID + PROGRAM_ID + ".selectBaseRateAndKospi");
        } else {
            baseRateAndKospiList = (List<Map<String, Object>>) commonService.selectList(dateParam, PAGE_ID + PROGRAM_ID + ".selectBaseRateAndKospiDetail");
        }

        Map<String, Object> result = new LinkedHashMap<>();

        if (baseRateAndKospiList.size() == 0) {
            result.put("baseRateAndKospi", baseRateAndKospiList);
            result.put("result", "Fail");
        } else {
            result.put("baseRateAndKospi", baseRateAndKospiList);
            result.put("result", "Success");
        }

        return result;
    }

}
