package com.teraenergy.bisolution.front.StandardOfLiving;

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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/front/standardOfLiving")
public class StandardOfLivingController {

    private static final String PROGRAM_ID = ".StandardOfLiving";
    private static final String PAGE_ID = "front";
    private static final String DIRECTORY = "/standardOfLiving/StandardOfLiving";

    @Resource(name = "commonService")
    private CommonService commonService;

    @Resource(name = "volatilityService")
    private VolatilityService volatilityService;

    @GetMapping("/main")
    public String standardOfLivingMain(@RequestParam Map<String, Object> paramMap, Model model) throws Exception {
        log.info(PAGE_ID + DIRECTORY + "Main");
        model.addAttribute("menuCode", "002");

        Map<String, String> dateParam = SearchUtil.searchDate(paramMap);
        model.addAttribute("minPay", volatilityService.getOffsetMap(dateParam, PROGRAM_ID, PAGE_ID, ".selectMinPayVolatility"));
        model.addAttribute("consumerInflation", volatilityService.getOffsetMap(dateParam, PROGRAM_ID, PAGE_ID, ".selectConsumerInflationVolatility"));
        model.addAttribute("economicGrowth", volatilityService.getOffsetMap(dateParam, PROGRAM_ID, PAGE_ID, ".selectEconomicGrowthVolatility"));
        model.addAttribute("gni", volatilityService.getOffsetMap(dateParam, PROGRAM_ID, PAGE_ID, ".selectGniVolatility"));
        return PAGE_ID + DIRECTORY + "Main";
    }

    // 연대별 물가상승률
    @ResponseBody
    @GetMapping("/api/getInflationYear")
    public Map<String, List<Map<String, Object>>> inflationYear() throws Exception {

        List<Map<String, Object>> inflationYearList = (List<Map<String, Object>>) commonService.selectList(null, PAGE_ID + PROGRAM_ID + ".selectInflationYear");

        Map<String, List<Map<String, Object>>> result = new HashMap<>();

        result.put("inflationYearList", inflationYearList);

        return result;
    }

    // 대통령별 물가상승률
    @ResponseBody
    @GetMapping("/api/getInflationPresident")
    public Map<String, List<Map<String, Object>>> inflationPresident() throws Exception {

        List<Map<String, Object>> inflationPresidentList = (List<Map<String, Object>>) commonService.selectList(null, PAGE_ID + PROGRAM_ID + ".selectInflationPresident");

        Map<String, List<Map<String, Object>>> result = new HashMap<>();

        result.put("inflationPresidentList", inflationPresidentList);

        return result;
    }

    // 지니계수
    @ResponseBody
    @GetMapping("/api/getGiniCoefficient")
    public Map<String, List<Map<String, Object>>> giniCoefficient() throws Exception {

        List<Map<String, Object>> giniCoefficientList = (List<Map<String, Object>>) commonService.selectList(null, PAGE_ID + PROGRAM_ID + ".selectGiniCoefficientList");

        Map<String, List<Map<String, Object>>> result = new HashMap<>();

        result.put("giniCoefficientList", giniCoefficientList);

        return result;
    }


}
