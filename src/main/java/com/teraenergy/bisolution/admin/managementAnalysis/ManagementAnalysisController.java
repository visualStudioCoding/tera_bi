package com.teraenergy.bisolution.admin.managementAnalysis;

import com.teraenergy.global.service.CommonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.support.HttpRequestHandlerServlet;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.net.http.HttpRequest;
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


    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    @GetMapping("/api/insertCapital")
        public Map<String, Object> insertCapital(@RequestParam Map<String, Object> parameter) throws Exception {
        System.out.println(parameter);

        commonService.insertContents(parameter, PAGE_ID + PROGRAM_ID + ".insertCapital");


        Map<String, Object> result = new HashMap<>();

        result.put("data", "자산 데이터 입력 성공!");
        return result;
        }


    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    @GetMapping("/api/insertSales")
    public Map<String, Object> insertSales(@RequestParam Map<String, Object> parameter) throws Exception {
        System.out.println(parameter);

        commonService.insertContents(parameter, PAGE_ID + PROGRAM_ID + ".insertSales");


        Map<String, Object> result = new HashMap<>();

        result.put("data", "매출 데이터 등록 성공!");
        return result;
    }

    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    @GetMapping("/api/insertProfitLossAndSales")
    public Map<String, Object> insertProfitLossAndSales(@RequestParam Map<String, Object> parameter) throws Exception {
        System.out.println(parameter);

        commonService.insertContents(parameter, PAGE_ID + PROGRAM_ID + ".insertProfitLossSalesStatus");


        Map<String, Object> result = new HashMap<>();

        result.put("data", "손익 및 매출 등록 성공!");
        return result;
    }

    @Transactional(rollbackFor =  Exception.class)
    @ResponseBody
    @PostMapping("/api/insertNewClients")
//    public String insertNewClients(@RequestParam Map<String,Object> parameter){
    public Map<String, Object> insertNewClients(@RequestParam MultiValueMap<String,Object> parameter) throws Exception {

        for (int i = 0; i < parameter.get("yrDt").size(); i++) {
            Map<String, Object> params = new HashMap<>();

            params.put("yrDt", parameter.get("yrDt").get(i));
            params.put("cliNm", parameter.get("clientsNm").get(i));
            params.put("cnt", parameter.get("tradeCnt").get(i));
            params.put("suplyVal", parameter.get("sales").get(i));
            params.put("taxAmt", Double.parseDouble(String.valueOf(parameter.get("sales").get(i)).replace(",","")) * 0.1);

            System.out.println(params);
            commonService.insertContents(params, PAGE_ID + PROGRAM_ID + ".insertClientsRecord");

        }

        Map<String, Object> result = new HashMap<>();

        result.put("data", "신규 거래처 등록 성공!");

        return result;
    }

    @Transactional(rollbackFor =  Exception.class)
    @ResponseBody
    @PostMapping("/api/updateClientsSales")
    public Map<String, Object> updateClientsSales(@RequestParam MultiValueMap<String,Object> parameter) throws Exception {

        for (int i = 0; i < parameter.get("yrDt").size(); i++) {
            Map<String, Object> compareParam = new HashMap<>();
            compareParam.put("yrDt", parameter.get("yrDt").get(i));
            compareParam.put("cliNm", parameter.get("clientsNm").get(i));
            List<Map<String, Object>> existYn = (List<Map<String, Object>>) commonService.selectList(compareParam, PAGE_ID + PROGRAM_ID + ".findClientsRecord");
            if(existYn == null){
                Map<String, Object> params = new HashMap<>();

                params.put("yrDt", parameter.get("yrDt").get(i));
                params.put("cliNm", parameter.get("clientsNm").get(i));
                params.put("cnt", parameter.get("tradeCnt").get(i));
                params.put("suplyVal", parameter.get("sales").get(i));
                params.put("taxAmt", Double.parseDouble(String.valueOf(parameter.get("sales").get(i)).replace(",", "")) * 0.1);

                System.out.println(params);
                commonService.insertContents(params, PAGE_ID + PROGRAM_ID + ".insertClientsRecord");

            }else {
                Map<String, Object> params = new HashMap<>();

                params.put("yrDt", parameter.get("yrDt").get(i));
                params.put("cliNm", parameter.get("clientsNm").get(i));
                params.put("cnt", parameter.get("tradeCnt").get(i));
                params.put("suplyVal", parameter.get("sales").get(i));
                params.put("taxAmt", Double.parseDouble(String.valueOf(parameter.get("sales").get(i)).replace(",", "")) * 0.1);

                System.out.println(params);
                commonService.insertContents(params, PAGE_ID + PROGRAM_ID + ".updateClientsRecord");
            }

        }


        Map<String, Object> result = new HashMap<>();

        result.put("data", "거래처 매출 등록 성공!");

        return result;
    }


}
