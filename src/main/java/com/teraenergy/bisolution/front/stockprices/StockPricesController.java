package com.teraenergy.bisolution.front.stockprices;

import com.teraenergy.global.service.CommonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

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
    @Resource(name = "commonService")
    private CommonService commonService;

    @GetMapping("/main")
    public String stockPricesMain(Model model) throws Exception {
        log.info(PAGE_ID + DIRECTORY + "Main");

        model.addAttribute("menuCode", "003");
        return PAGE_ID + DIRECTORY + "Main";
    }


}
