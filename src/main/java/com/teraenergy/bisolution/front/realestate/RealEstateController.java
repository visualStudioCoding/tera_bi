package com.teraenergy.bisolution.front.realestate;

import com.teraenergy.global.service.CommonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.sql.Array;
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

    // 행정구역별 매매 거래
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

            params.put("searchStartDate", periods[0].substring(0, 6));
            params.put("searchEndDate", periods[1].substring(0, 6));
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

    // 연령대별 매매거래
    @ResponseBody
    @GetMapping("/api/ageAptSales")
    public Map<String, List> getAgeAptSales(String parameter) throws Exception {

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
        }


        List<Map<String, Object>> dataList = (List<Map<String, Object>>) commonService.selectList(params, PAGE_ID + PROGRAM_ID + ".selectAgeAptSales");
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

    // 건축년수별 매매거래
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

            params.put("searchStartDate", periods[0].substring(0, 6));
            params.put("searchEndDate", periods[1].substring(0, 6));
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

    // 지역별 인구 수
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

            params.put("searchStartDate", periods[0].substring(0, 6));
            params.put("searchEndDate", periods[1].substring(0, 6));
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

    // 소비자물가별 미분양주택
    @ResponseBody
    @GetMapping("/api/unsoldAndCnsmr")
    public Map<String, List> getUnsoldAndCnsmr(String parameter) throws Exception {

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

        List<Map<String, Object>> dataList = (List<Map<String, Object>>) commonService.selectList(params, PAGE_ID + PROGRAM_ID + ".selectUnsoldAndCnsmr");

        List<Map<String, Object>> unsoldData = new ArrayList<>();
        List<Map<String, Object>> cnsmrData = new ArrayList<>();

        Map<String, List> result = new HashMap<>();


        for (int i = 0; i < dataList.size(); i++) {
            Map<String, Object> unsold = new HashMap<>();
            Map<String, Object> cnsmr = new HashMap<>();

            if ("unsold".equals(dataList.get(i).get("type"))) {

                unsold.put("period", dataList.get(i).get("period"));
                unsold.put("val", dataList.get(i).get("val"));

                unsoldData.add(unsold);
            } else {
                cnsmr.put("period", dataList.get(i).get("period"));
                cnsmr.put("val", dataList.get(i).get("val"));

                cnsmrData.add(cnsmr);
            }

        }

        result.put("unsold", unsoldData);
        result.put("cnsmr", cnsmrData);

        return result;
    }

    // 성별 부동산 소유자 비율
    @ResponseBody
    @GetMapping("/api/ownerByGender")
    public List<Map<String, String>> getOwnerByGender(String parameter) throws Exception {

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
        }

        Map<String, Object> dataList = new HashMap<>();

        if ("0".equals(parameter)) {
            dataList = (Map<String, Object>) commonService.selectContents(params, PAGE_ID + PROGRAM_ID + ".selectOwnerByGender");
        } else {
            dataList = (Map<String, Object>) commonService.selectContents(params, PAGE_ID + PROGRAM_ID + ".selectOwnerByGenderPeriod");
        }

        List<Map<String, String>> result = new ArrayList<>();

        Map<String, String> manData = new HashMap<>();
        Map<String, String> wmnData = new HashMap<>();

        manData.put("name", "남성");
        manData.put("value", dataList.get("man_cnt").toString());
        wmnData.put("name", "여성");
        wmnData.put("value", dataList.get("wmn_cnt").toString());

        result.add(manData);
        result.add(wmnData);


        System.out.println(result);

        return result;
    }


    // 연령대 별 부동산 소유자 비율
    @ResponseBody
    @GetMapping("/api/ownerByAge")
    public List<Map<String, String>> getOwnerByAge(String parameter) throws Exception {

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
        }

        List<Map<String, Object>> dataList = new ArrayList<>();

        if ("0".equals(parameter)) {
            dataList = (List<Map<String, Object>>) commonService.selectList(params, PAGE_ID + PROGRAM_ID + ".selectOwnerByAge");
        } else {
            dataList = (List<Map<String, Object>>) commonService.selectList(params, PAGE_ID + PROGRAM_ID + ".selectOwnerByAgePeriod");
        }

        List<Map<String, String>> result = new ArrayList<>();

        for (int i = 0; i < dataList.size(); i++) {
            Map<String, String> data = new HashMap<>();

            data.put("value", dataList.get(i).get("val").toString());
            data.put("name", dataList.get(i).get("age").toString());

            result.add(data);
        }

        System.out.println(result);

        return result;
    }
}
