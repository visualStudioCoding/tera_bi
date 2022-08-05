package com.teraenergy.bisolution.front.realestate;

import com.teraenergy.global.service.CommonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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

/**
 * 부동산시장동향 front
 *
 * @author tera
 * @version 1.0.0
 * 작성일 2022-07-20
 **/
@Slf4j
@Controller
@RequestMapping("/front/realEstate")
public class RealEstateController {

    private static final String PROGRAM_ID = ".RealEstate";
    private static final String PAGE_ID = "front";
    private static final String DIRECTORY = "/realEstate/RealEstate";
    @Resource(name = "commonService")
    private CommonService commonService;

    @GetMapping("/main")
    public String realEstateMain(ModelMap model) throws Exception {
        log.info(PAGE_ID + DIRECTORY + "Main");

        model.addAttribute("menuCode", "004");
        return PAGE_ID + DIRECTORY + "Main";
    }

    @ResponseBody
    @GetMapping("/api/aptSalesStatus")
    public Map<String, List> getAptSalesStatus(String parameter) throws Exception {

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

        List<Map<String, Object>> dataList = (List<Map<String, Object>>) commonService.selectList(params, PAGE_ID + PROGRAM_ID + ".selectAptSalesStatus");
        List<String> region = new ArrayList<>();
        List<String> data = new ArrayList<>();

        Map<String, List> result = new HashMap<>();


        for (int i = 0; i < dataList.size(); i++) {
            region.add((String) dataList.get(i).get("cty_nm"));
            data.add(dataList.get(i).get("val").toString());
        }

        result.put("region", region);
        result.put("datas", data);

        return result;
    }

    @ResponseBody
    @GetMapping("/api/ageAptSales")
    public Map<String, List> getAgeAptSales() throws Exception {

        List<Map<String, Object>> dataList = (List<Map<String, Object>>) commonService.selectList(null, PAGE_ID + PROGRAM_ID + ".selectAgeAptSales");
        List<String> ages = new ArrayList<>();
        List<String> data = new ArrayList<>();

        Map<String, List> result = new HashMap<>();


        for (int i = 0; i < dataList.size(); i++) {
            ages.add((String) dataList.get(i).get("age"));
            data.add(dataList.get(i).get("val").toString());
        }

        result.put("ages", ages);
        result.put("datas", data);

        return result;
    }

    @ResponseBody
    @GetMapping("/api/builtYear")
    public Map<String, List> getBuiltYear(String parameter) throws Exception {

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

        List<Map<String, Object>> dataList = (List<Map<String, Object>>) commonService.selectList(params, PAGE_ID + PROGRAM_ID + ".selectBuiltYear");
        List<String> years = new ArrayList<>();
        List<String> data = new ArrayList<>();

        Map<String, List> result = new HashMap<>();


        for (int i = 0; i < dataList.size(); i++) {
            years.add((String) dataList.get(i).get("term"));
            data.add(dataList.get(i).get("val").toString());
        }

        result.put("years", years);
        result.put("datas", data);

        return result;
    }

    @ResponseBody
    @GetMapping("/api/regionPopulation")
    public Map<String, List> getRegionPopulation(String parameter) throws Exception {

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

        List<Map<String, Object>> dataList = (List<Map<String, Object>>) commonService.selectList(params, PAGE_ID + PROGRAM_ID + ".selectRegionPopulation");
        List<String> region = new ArrayList<>();
        List<String> data = new ArrayList<>();

        Map<String, List> result = new HashMap<>();


        for (int i = 0; i < dataList.size(); i++) {
            region.add((String) dataList.get(i).get("cty_nm"));
            data.add(dataList.get(i).get("val").toString());
        }

        result.put("region", region);
        result.put("datas", data);

        return result;
    }

    @ResponseBody
    @GetMapping("/api/unsoldAndCnsmr")
    public Map<String, List> getBaseRate(String parameter) throws Exception {

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

            if("0".equals(parameter)){
                params.put("searchDate", Integer.toString(Integer.parseInt(currentYear) - 5));
            }
        }

        List<Map<String, Object>> dataList = (List<Map<String, Object>>) commonService.selectList(params, PAGE_ID + PROGRAM_ID + ".selectUnsoldAndCnsmr");

        List<Map<String, Object>> unsoldData = new ArrayList<>();
        List<Map<String, Object>> cnsmrData = new ArrayList<>();

        Map<String, List> result = new HashMap<>();


        for (int i = 0; i < dataList.size(); i++) {
            Map<String, Object> unsold = new HashMap<>();
            Map<String, Object> cnsmr = new HashMap<>();

            if("unsold".equals(dataList.get(i).get("type"))){

                unsold.put("period", dataList.get(i).get("period"));
                unsold.put("val", dataList.get(i).get("val"));

                unsoldData.add(unsold);
            } else{
                cnsmr.put("period", dataList.get(i).get("period"));
                cnsmr.put("val", dataList.get(i).get("val"));

                cnsmrData.add(cnsmr);
            }

        }

        result.put("unsold", unsoldData);
        result.put("cnsmr", cnsmrData);

        return result;
    }

    @ResponseBody
    @GetMapping("/api/populationByGender")
    public List<Map<String, String>> getPopulationByGender(String parameter) throws Exception {

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

        List<Map<String, Object>> dataList = (List<Map<String, Object>>) commonService.selectList(params, PAGE_ID + PROGRAM_ID + ".selectGenderPopulation");

        List<Map<String, String>> result = new ArrayList<>();


        for (int i = 0; i < dataList.size(); i++) {
            Map<String, String> data = new HashMap<>();

            data.put("name", dataList.get(i).get("gender").toString() + "성");
            data.put("value", dataList.get(i).get("val").toString());


            result.add(data);
        }


        System.out.println(result);

        return result;
    }
}
