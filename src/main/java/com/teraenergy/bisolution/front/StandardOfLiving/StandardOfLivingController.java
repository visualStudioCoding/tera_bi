package com.teraenergy.bisolution.front.StandardOfLiving;

import com.teraenergy.global.service.CommonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @GetMapping("/main")
    public String standardOfLivingMain(Model model) throws Exception {
        log.info(PAGE_ID + DIRECTORY + "Main");

        return PAGE_ID + DIRECTORY + "Main";
    }

//    @ResponseBody
//    @GetMapping("/api/getGrossNationalIncome")
//    public Map<String, List<Map<String, Object>>> getGrossNationalIncome() throws Exception {
//        List<Map<String, Object>> gni = (List<Map<String, Object>>) commonService.selectList(null, PAGE_ID + PROGRAM_ID + ".selectGdiGni");
//        List<Map<String, Object>> debt = (List<Map<String, Object>>) commonService.selectList(null, PAGE_ID + PROGRAM_ID + ".selectStateDebt");
//
//        Map<String, List<Map<String, Object>>> result = new HashMap<>();
//
//        result.put("gni", gni);
//        result.put("debt", debt);
//
//        return result;
//    }

}
