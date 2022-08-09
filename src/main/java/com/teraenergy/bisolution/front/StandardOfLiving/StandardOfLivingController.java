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
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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

    // 임금 대비 물가상승률
    @ResponseBody
    @GetMapping("/api/getIncomePriceRate")
    public Map<String, List> getIncomePriceRate(String parameter) throws Exception {

        Map<String, String> params = new HashMap<>();

        LocalDate now = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy");
        String currentYear = now.format(formatter);

        if (parameter.contains("-")) {
            parameter = parameter.replaceAll("[-]", "");
            String[] periods = parameter.split("  ");

            params.put("searchStartDate", periods[0].substring(0, 6));
            params.put("searchEndDate", periods[1].substring(0, 6));
        } else {
            params.put("searchDate", Integer.toString(Integer.parseInt(currentYear) - Integer.parseInt(parameter)));

            if ("0".equals(parameter)) {
                params.put("searchDate", Integer.toString(Integer.parseInt(currentYear) - 5));
            }
        }

        List<Map<String, Object>> dataList = (List<Map<String, Object>>) commonService.selectList(params, PAGE_ID + PROGRAM_ID + ".selectIncomePriceRate");

        List<Map<String, Object>> minPayRate = new ArrayList<>();
        List<Map<String, Object>> priceRate = new ArrayList<>();
        List<Map<String, Object>> wageRate = new ArrayList<>();

        Map<String, List> result = new HashMap<>();


        for (int i = 0; i < dataList.size(); i++) {
            Map<String, Object> minPayRateData = new HashMap<>();
            Map<String, Object> priceRateData = new HashMap<>();
            Map<String, Object> wageRateData = new HashMap<>();

            if ("물가상승률".equals(dataList.get(i).get("type"))) {
                minPayRateData.put("period", dataList.get(i).get("yr_dt"));
                minPayRateData.put("val", dataList.get(i).get("val"));
                minPayRate.add(minPayRateData);

            } else if ("최저임금상승률".equals(dataList.get(i).get("type"))) {
                priceRateData.put("period", dataList.get(i).get("yr_dt"));
                priceRateData.put("val", dataList.get(i).get("val"));
                priceRate.add(priceRateData);

            } else {
                wageRateData.put("period", dataList.get(i).get("yr_dt"));
                wageRateData.put("val", dataList.get(i).get("val"));
                wageRate.add(wageRateData);
            }

        }

        result.put("minPayRate", minPayRate);
        result.put("priceRate", priceRate);
        result.put("wageRate", wageRate);

        return result;
    }

    //    경제성장률 SELECT
    @ResponseBody
    @GetMapping("/api/getEconomicGrowth")
    public Map<String, List<List>> getEconomicGrowth(String parameter) throws Exception {

        Map<String, String> params = new HashMap<>();

        LocalDate now = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy");
        String currentYear = now.format(formatter);

        List<Map<String, Object>> dataList = new ArrayList<>();

        if (parameter.equals("0")) {
            dataList = (List<Map<String, Object>>) commonService.selectList(params, PAGE_ID + PROGRAM_ID + ".selectEconomicGrowthDefault");
        } else {
            if (parameter.contains("-")) {
                parameter = parameter.replaceAll("[-]", "");
                String[] periods = parameter.split("  ");

                params.put("searchDate", periods[0].substring(0, 4));
            } else {
                params.put("searchDate", Integer.toString(Integer.parseInt(currentYear) - Integer.parseInt(parameter)));
            }

            dataList = (List<Map<String, Object>>) commonService.selectList(params, PAGE_ID + PROGRAM_ID + ".selectEconomicGrowth");
        }

        List<List> datas = new ArrayList<>();
        Map<String, List<List>> result = new HashMap<>();

        for (int i = 0; i < dataList.size(); i++) {
            List<String> data = new ArrayList<>();

            data.add((String) dataList.get(i).get("cty_nm"));
            data.add(String.valueOf(dataList.get(i).get("val")));

            datas.add(data);
        }

        result.put("emncGrrt", datas);

        return result;
    }

    // 우크라이나 전쟁 이후 물가상승률
    @ResponseBody
    @GetMapping("api/getNationalPriceIncrease")
    public Map<String, List> getNationalPriceIncrease() throws Exception {

        List<Map<String, Object>> dataList = (List<Map<String, Object>>) commonService.selectList(null, PAGE_ID + PROGRAM_ID + ".selectPriceIncreaseInternational");

        List<String> country = new ArrayList<>();
        List<String> begin = new ArrayList<>();
        List<String> end = new ArrayList<>();

        for (int i = 0; i < dataList.size(); i++) {
            if ("202111".equals(dataList.get(i).get("period"))) {
                country.add(dataList.get(i).get("cntry_nm").toString());
                begin.add(dataList.get(i).get("val").toString());
            } else {
                end.add(dataList.get(i).get("val").toString());
            }

        }

        Map<String, List> result = new HashMap<>();

        result.put("country", country);
        result.put("begin", begin);
        result.put("end", end);

        return result;
    }

    // 연령별 해외여행 통계
    @ResponseBody
    @GetMapping("api/getOverseaTrip")
    public Map<String, List> getOverseaTrip(String parameter) throws Exception {
        Map<String, String> params = new HashMap<>();

        LocalDate now = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy");
        String currentYear = now.format(formatter);

        if (parameter.contains("-")) {
            parameter = parameter.replaceAll("[-]", "");
            String[] periods = parameter.split("  ");

            params.put("searchStartDate", periods[0].substring(0, 6));
            params.put("searchEndDate", periods[1].substring(0, 6));
        } else {
            params.put("searchDate", Integer.toString(Integer.parseInt(currentYear) - Integer.parseInt(parameter)));

            if ("0".equals(parameter)) {
                params.put("searchDate", Integer.toString(Integer.parseInt(currentYear) - 5));
            }
        }
        List<Map<String, Object>> dataList = (List<Map<String, Object>>) commonService.selectList(params, PAGE_ID + PROGRAM_ID + ".selectOverseaTrip");


        List<String> period = new ArrayList<>();

        List<String> setOne = new ArrayList<>();
        List<String> setTwo = new ArrayList<>();
        List<String> setThree = new ArrayList<>();
        List<String> setFour = new ArrayList<>();
        List<String> setFive = new ArrayList<>();
        List<String> setSix = new ArrayList<>();

        for (int i = 0; i < dataList.size(); i++) {

            if ("0-20세".equals(dataList.get(i).get("ages"))) {
                if (setOne.size() == 0) {
                    setOne.add(dataList.get(i).get("ages").toString());
                }
                setOne.add(dataList.get(i).get("val").toString());
                period.add(dataList.get(i).get("yr_dt").toString());

            } else if ("21-30세".equals(dataList.get(i).get("ages"))) {
                if (setTwo.size() == 0) {
                    setTwo.add(dataList.get(i).get("ages").toString());
                }
                setTwo.add(dataList.get(i).get("val").toString());
            } else if ("31-40세".equals(dataList.get(i).get("ages"))) {
                if (setThree.size() == 0) {
                    setThree.add(dataList.get(i).get("ages").toString());
                }
                setThree.add(dataList.get(i).get("val").toString());
            } else if ("41-50세".equals(dataList.get(i).get("ages"))) {
                if (setFour.size() == 0) {
                    setFour.add(dataList.get(i).get("ages").toString());
                }
                setFour.add(dataList.get(i).get("val").toString());
            } else if ("51-60세".equals(dataList.get(i).get("ages"))) {
                if (setFive.size() == 0) {
                    setFive.add(dataList.get(i).get("ages").toString());
                }
                setFive.add(dataList.get(i).get("val").toString());
            } else if ("61세 이상".equals(dataList.get(i).get("ages"))) {
                if (setSix.size() == 0) {
                    setSix.add(dataList.get(i).get("ages").toString());
                }
                setSix.add(dataList.get(i).get("val").toString());
            }
        }

        Map<String, List> result = new HashMap<>();

        result.put("period", period);
        result.put("twenty", setOne);
        result.put("thirty", setTwo);
        result.put("fourty", setThree);
        result.put("fifty", setFour);
        result.put("sixty", setFive);
        result.put("seventy", setSix);

        return result;
    }
}
