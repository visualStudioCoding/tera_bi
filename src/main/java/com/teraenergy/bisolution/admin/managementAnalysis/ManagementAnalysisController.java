package com.teraenergy.bisolution.admin.managementAnalysis;

import com.teraenergy.global.service.CommonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/admin/managementAnalysis")
public class ManagementAnalysisController {

    private static final String PROGRAM_ID = ".ManagementAnalysis";
    private static final String PAGE_ID = "admin";

    private static final String DIRECTORY = "/managementAnalysis/ManagementAnalysis";

    @Resource(name = "commonService")
    private CommonService commonService;
    @GetMapping("/main")
    public String managementAnalysisMain(Model model) throws Exception {
        log.info(PAGE_ID + DIRECTORY + "List");
        model.addAttribute("menuCode", "006");
        model.addAttribute("clients", getClientList());
        return PAGE_ID + DIRECTORY + "Main";
    }

    @GetMapping("/api/getClientList")
    public List<String> getClientList() throws Exception {
        List<Map<String, Object>> dataList = (List<Map<String, Object>>) commonService.selectList(null, PAGE_ID + PROGRAM_ID + ".selectClients");

        List<String> result = new ArrayList<>();

        for (int i = 0; i < dataList.size(); i++) {
            result.add((String) dataList.get(i).get("cli_nm"));
        }

        return result;
    }
}
