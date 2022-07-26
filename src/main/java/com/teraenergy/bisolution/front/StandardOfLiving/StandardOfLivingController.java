package com.teraenergy.bisolution.front.StandardOfLiving;

import com.teraenergy.global.service.CommonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
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

}
