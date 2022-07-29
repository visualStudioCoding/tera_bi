package com.teraenergy.bisolution.front.realestate;

import com.teraenergy.global.service.CommonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
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
    public Map<String, List> getAptSalesStatus() throws Exception {

        List<Map<String, Object>> dataList = (List<Map<String, Object>>) commonService.selectList(null, PAGE_ID + PROGRAM_ID + ".selectAptSalesStatus");
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
    public Map<String, List> getBuiltYear() throws Exception {

        List<Map<String, Object>> dataList = (List<Map<String, Object>>) commonService.selectList(null, PAGE_ID + PROGRAM_ID + ".selectBuiltYear");
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
    public Map<String, List> getRegionPopulation() throws Exception {

        List<Map<String, Object>> dataList = (List<Map<String, Object>>) commonService.selectList(null, PAGE_ID + PROGRAM_ID + ".selectRegionPopulation");
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
}
