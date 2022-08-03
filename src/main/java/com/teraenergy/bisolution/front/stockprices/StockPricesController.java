package com.teraenergy.bisolution.front.stockprices;

import com.teraenergy.global.common.utilities.SearchUtil;
import com.teraenergy.global.service.CommonService;
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
 *
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

    @Resource(name = "stockPricesService")
    private StockPricesService stockPricesService;

    @GetMapping("/main")
    public String stockPricesMain(Model model) throws Exception {
        log.info(PAGE_ID + DIRECTORY + "Main");
        model.addAttribute("menuCode", "003");
        return PAGE_ID + DIRECTORY + "Main";
    }

    @ResponseBody
    @GetMapping("/api/getKospiIndex")
    public Map<String, Object> kospiIndex(@RequestParam Map<String, Object> paramMap) throws Exception {
        return stockPricesService.getOffsetMap(paramMap, PROGRAM_ID, PAGE_ID, ".selectKospiIndex");
    }
    @ResponseBody
    @GetMapping("/api/getKosdaqIndex")
    public Map<String, Object> kosdaqIndex(@RequestParam Map<String, Object> paramMap) throws Exception {
        return stockPricesService.getOffsetMap(paramMap, PROGRAM_ID, PAGE_ID, ".selectKosdaqIndex");
    }
    @ResponseBody
    @GetMapping("/api/getOilPrice")
    public Map<String, Object> oilPrice(@RequestParam Map<String, Object> paramMap) throws Exception {
        return stockPricesService.getOffsetMap(paramMap, PROGRAM_ID, PAGE_ID, ".selectOilPrice");
    }@ResponseBody
    @GetMapping("/api/getBaseRate")
    public Map<String, Object> baseRate(@RequestParam Map<String, Object> paramMap) throws Exception {
        return stockPricesService.getOffsetMap(paramMap, ECONOMIC_PROGRAM_ID, PAGE_ID, ".selectBaseRate");
    }
    @ResponseBody
    @GetMapping("/api/getExchangeRate")
    public Map<String, Object> exchangeRate(@RequestParam Map<String, Object> paramMap) throws Exception {
        return stockPricesService.getOffsetMap(paramMap, ECONOMIC_PROGRAM_ID, PAGE_ID, ".selectExchangeRate");
    }

    @ResponseBody
    @GetMapping("/api/getGdpData")
    public List<Object> gdpData(@RequestParam Map<String, Object> paramMap) throws Exception {
        log.info(PAGE_ID + DIRECTORY + "getGdpData");

        Map<String, String> dateParam = SearchUtil.searchDate(paramMap);

        List<Map<String, Object>> gdpMap;

        if(dateParam.size() <= 1) {
            if("0".equals(dateParam.get("searchPeriod"))) dateParam.put("searchPeriod", "5");
            gdpMap = (List<Map<String, Object>>) commonService.selectList(dateParam, PAGE_ID + ECONOMIC_PROGRAM_ID + ".selectWholeGDP");
        } else{
            gdpMap = (List<Map<String, Object>>) commonService.selectList(dateParam, PAGE_ID + ECONOMIC_PROGRAM_ID + ".selectWholeGDPDetail");
        }

        List<Object> result = new ArrayList<>();

        for (Map<String, Object> objectMap : gdpMap) {
            List<Object> gdpList = new ArrayList<>();

            gdpList.add(objectMap.get("yr_dt"));
            gdpList.add(Float.parseFloat(String.valueOf(objectMap.get("nmnl_val"))) * 0.001);
            gdpList.add(Float.parseFloat(String.valueOf(objectMap.get("real_val"))) * 0.001);

            result.add(gdpList);
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
    public Map<String, List<Map<String, Object>>> annualGrowthRate(@RequestParam Map<String, Object> paramMap) throws Exception {

        Map<String, String> dateParam = SearchUtil.searchDate(paramMap);

        List<Map<String, Object>> annualGrowthRateList;

        if(dateParam.size() <= 1) {
            if("0".equals(dateParam.get("searchPeriod"))) dateParam.put("searchPeriod", "5");
            annualGrowthRateList = (List<Map<String, Object>>) commonService.selectList(dateParam, PAGE_ID + ECONOMIC_PROGRAM_ID + ".selectAnnualGrowthRate");
        } else{
            annualGrowthRateList = (List<Map<String, Object>>) commonService.selectList(dateParam, PAGE_ID + ECONOMIC_PROGRAM_ID + ".selectAnnualGrowthRateDetail");
        }

        Map<String, List<Map<String, Object>>> result = new HashMap<>();

        result.put("annualGrowthRate", annualGrowthRateList);

        return result;
    }
    // 연간 경제성장률
    @ResponseBody
    @GetMapping("/api/getBaseRateAndKospi")
    public Map<String, List<Map<String, Object>>> baseRateAndKospi(@RequestParam Map<String, Object> paramMap) throws Exception {

        Map<String, String> dateParam = SearchUtil.searchDate(paramMap);

        List<Map<String, Object>> baseRateAndKospiList;

        if(dateParam.size() <= 1) {
            baseRateAndKospiList = (List<Map<String, Object>>) commonService.selectList(dateParam, PAGE_ID + PROGRAM_ID + ".selectBaseRateAndKospi");
        } else{
            baseRateAndKospiList = (List<Map<String, Object>>) commonService.selectList(dateParam, PAGE_ID + PROGRAM_ID + ".selectBaseRateAndKospiDetail");
        }

        Map<String, List<Map<String, Object>>> result = new LinkedHashMap<>();

        result.put("baseRateAndKospi", baseRateAndKospiList);

        return result;
    }

}
