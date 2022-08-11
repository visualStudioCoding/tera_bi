package com.teraenergy.bisolution.front.managementanalysis;

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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/front/managementAnalysis")
public class ManagementAnalysisController {

    private static final String PROGRAM_ID = ".ManagementAnalysis";
    private static final String PAGE_ID = "front";
    private static final String DIRECTORY = "/managementAnalysis/ManagementAnalysis";

    @Resource(name = "commonService")
    private CommonService commonService;

    @Resource(name = "volatilityService")
    private VolatilityService volatilityService;

    @GetMapping("/main")
    public String managementAnalysisMain(@RequestParam Map<String, Object> paramMap, Model model) throws Exception {
        log.info(PAGE_ID + DIRECTORY + "Main");
        model.addAttribute("menuCode", "005");

        Map<String, Float> emplyCnt = (Map<String, Float>) commonService.selectEmplyContents(null, PAGE_ID + PROGRAM_ID + ".selectEmployeeCount");
        model.addAttribute("emplyCnt", emplyCnt);

        return PAGE_ID + DIRECTORY + "Main";
    }

    @ResponseBody
    @GetMapping("/api/getEmployeeStatus")
    public Map<String, List<Object>> employeeStatus(@RequestParam Map<String, Object> paramMap, Model model) throws Exception {

        List<Map<String, Object>> dataList = (List<Map<String, Object>>) commonService.selectEmplyList(null, PAGE_ID + PROGRAM_ID + ".selectEmployeeStatusList");

        Map<String, List<Object>> result = new HashMap<>();
        List<Object> deptList = new ArrayList<>();
        List<Object> workList = new ArrayList<>();
        for (Map<String, Object> dataMap : dataList) {
            Map<String, Object> dept = new HashMap<>();
            Map<String, Object> work = new HashMap<>();
            String type = (String) dataMap.get("type");
            if("dept".equals(type)){
                dept.put("value", dataMap.get("value"));
                dept.put("name", dataMap.get("name").toString());
                deptList.add(dept);
            } else {
                work.put("value", dataMap.get("value"));
                work.put("name", dataMap.get("name").toString());
                workList.add(work);
            }

        }
        result.put("deptList",deptList);
        result.put("workList",workList);

        return result;
    }

    @ResponseBody
    @GetMapping("/api/getDepartment")
    public Map<String, List<Map<String, Object>>> departmentList() throws Exception {

        List<Map<String, Object>> departmentList = (List<Map<String, Object>>) commonService.selectEmplyList(null, PAGE_ID + PROGRAM_ID + ".selectDeptList");

        Map<String, List<Map<String, Object>>> result = new HashMap<>();

        result.put("departmentList", departmentList);

        return result;
    }
    @ResponseBody
    @GetMapping("/api/getTechnical")
    public Map<String, List<Map<String, Object>>> technicalList() throws Exception {

        List<Map<String, Object>> technicalList = (List<Map<String, Object>>) commonService.selectEmplyList(null, PAGE_ID + PROGRAM_ID + ".selectTechnicalList");

        Map<String, List<Map<String, Object>>> result = new HashMap<>();

        result.put("technicalList", technicalList);

        return result;
    }

}
