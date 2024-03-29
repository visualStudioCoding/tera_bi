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
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        model.addAttribute("profitSales", getProfitLossAndSales());


        return PAGE_ID + DIRECTORY + "Main";
    }

    // 손익/매출현황
    @ResponseBody
    @GetMapping("/api/getProfitLossAndSales")
    public Map<String, Object> getProfitLossAndSales() throws Exception {

        List<Map<String, Object>> dataList = (List<Map<String, Object>>) commonService.selectList(null, PAGE_ID + PROGRAM_ID + ".selectProfitLossSales");
        List<Map<String, Object>> compareList = (List<Map<String, Object>>) commonService.selectList(null, PAGE_ID + PROGRAM_ID + ".selectProfitLossSalesCompare");

        Long prftLoss = Long.valueOf(String.valueOf(dataList.get(0).get("prft_loss")));
        Long trgtPrftLoss = Long.valueOf(String.valueOf(dataList.get(0).get("trgt_prft_loss")));
        Long sales = Long.valueOf(String.valueOf(dataList.get(0).get("sales")));
        Long trgtSales = Long.valueOf(String.valueOf(dataList.get(0).get("trgt_sales")));

        Long cmpPrftLoss = Long.valueOf(String.valueOf(compareList.get(0).get("trgt_prft_loss")));
        Long cmpSales = Long.valueOf(String.valueOf(compareList.get(0).get("trgt_sales")));

        double profitLossAcheive = (double) prftLoss / trgtPrftLoss * 100;
        double salesAcheive = (double) sales / trgtSales * 100;

        Map<String, Object> result = new HashMap<>();

        result.put("trgtPrftLoss", trgtPrftLoss / 10000);
        result.put("trgtSales", trgtSales / 10000);
        result.put("profitLossAcheive", Math.round(profitLossAcheive));
        result.put("salesAcheive", Math.round(salesAcheive));
        result.put("comparePrftLoss", cmpPrftLoss / 10000);
        result.put("compareSales", cmpSales / 10000);

        System.out.println(result);

        return result;
    }

    // 사원 현황
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
            if ("dept".equals(type)) {
                dept.put("value", dataMap.get("value"));
                dept.put("name", dataMap.get("name").toString());
                deptList.add(dept);
            } else {
                work.put("value", dataMap.get("value"));
                work.put("name", dataMap.get("name").toString());
                workList.add(work);
            }

        }
        result.put("deptList", deptList);
        result.put("workList", workList);

        return result;
    }

    // 부서별 인원 현황
    @ResponseBody
    @GetMapping("/api/getDepartment")
    public Map<String, List<Map<String, Object>>> departmentList() throws Exception {

        List<Map<String, Object>> departmentList = (List<Map<String, Object>>) commonService.selectEmplyList(null, PAGE_ID + PROGRAM_ID + ".selectDeptList");

        Map<String, List<Map<String, Object>>> result = new HashMap<>();

        result.put("departmentList", departmentList);

        return result;
    }

    // 기술자 보유 현황
    @ResponseBody
    @GetMapping("/api/getTechnical")
    public Map<String, List<Map<String, Object>>> technicalList() throws Exception {

        List<Map<String, Object>> technicalList = (List<Map<String, Object>>) commonService.selectEmplyList(null, PAGE_ID + PROGRAM_ID + ".selectTechnicalList");

        Map<String, List<Map<String, Object>>> result = new HashMap<>();

        result.put("technicalList", technicalList);

        return result;
    }

    //      거래처별 매출 현황 Chart
    @ResponseBody
    @GetMapping("/api/getClientSales")
    public Map<String, Object> getClientSales(String parameter) throws Exception {

        Map<String, String> params = new HashMap<>();

        LocalDate now = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy");
        String currentYear = now.format(formatter);

        if (parameter.equals("0")) {
            params.put("searchDate", "5");
        } else {
            if (parameter.contains("-")) {
                parameter = parameter.replaceAll("[-]", "");
                String[] periods = parameter.split("  ");

                params.put("searchDate", periods[0].substring(0, 4));
            } else {
                params.put("searchDate", Integer.toString(Integer.parseInt(currentYear) - Integer.parseInt(parameter)));
            }
        }
        List<Map<String, Object>> dataList = (List<Map<String, Object>>) commonService.selectList(params, PAGE_ID + PROGRAM_ID + ".selectClientSales");

        List<String> years = new ArrayList<>();

        for (int i = 0; i < dataList.size(); i++) {
            years.add((String) dataList.get(i).get("yr_dt"));
        }
        years = years.stream().distinct().collect(Collectors.toList());

        List<Object> data = new ArrayList<>();
        int cnt = 0;

        for (int i = 0; i < dataList.size(); i++) {
            List<Object> salesData = new ArrayList<>();
            for (int j = 0; j < years.size(); j++) {
                if (j == 0) {
                    salesData.add(dataList.get(cnt).get("cli_nm"));
                }
                if ("-".equals(dataList.get(cnt).get("suply_val"))) {
                    salesData.add(dataList.get(cnt).get("suply_val"));
                } else {
                    salesData.add(Integer.parseInt((String) dataList.get(cnt).get("suply_val")) / 10000);
                }
                cnt++;
            }
            i += 3;
            data.add(salesData);
        }

        Map<String, Object> result = new HashMap<>();

        result.put("salesData", data);
        result.put("years", years);

        return result;
    }

    //      재무제표 현황
    @ResponseBody
    @GetMapping("/api/getCapitalSales")
    public Map<String, Object> getCapitalSales(String parameter) throws Exception {

        Map<String, String> params = new HashMap<>();

        LocalDate now = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy");
        String currentYear = now.format(formatter);

        if ("0".equals(parameter)) {
            params.put("searchDate", Integer.toString(Integer.parseInt(currentYear) - 5));
        } else {
            if (parameter.contains("-")) {
                parameter = parameter.replaceAll("[-]", "");
                String[] periods = parameter.split("  ");

                params.put("searchDate", periods[0].substring(0, 4));
            } else {
                params.put("searchDate", Integer.toString(Integer.parseInt(currentYear) - Integer.parseInt(parameter)));
            }
        }

        List<Map<String, Object>> dataList = (List<Map<String, Object>>) commonService.selectList(params, PAGE_ID + PROGRAM_ID + ".selectCapitalAndSales");

        List<Object> ttlAsset = new ArrayList<>();
        List<Object> capital = new ArrayList<>();
        List<Object> eqtyCptl = new ArrayList<>();
        List<Object> crntLblts = new ArrayList<>();
        List<Object> consulting = new ArrayList<>();
        List<Object> sysdevPart = new ArrayList<>();
        List<Object> smPart = new ArrayList<>();
        List<Object> swPart = new ArrayList<>();
        List<Object> totalSum = new ArrayList<>();
        List<Object> period = new ArrayList<>();

        List<Object> cagrTarget = new ArrayList<>();
        Object unit = dataList.get(0).get("unit");

        for (int i = 0; i < dataList.size(); i++) {
            if (ttlAsset.size() == 0) {
                ttlAsset.add("총자산");
            }
            if (capital.size() == 0) {
                capital.add("자본금");
            }
            if (eqtyCptl.size() == 0) {
                eqtyCptl.add("자기자본");
            }
            if (crntLblts.size() == 0) {
                crntLblts.add("유동부채");
            }
            if (consulting.size() == 0) {
                consulting.add("컨설팅");
            }
            if (sysdevPart.size() == 0) {
                sysdevPart.add("시스템개발");
            }
            if (smPart.size() == 0) {
                smPart.add("유지관리");
            }
            if (swPart.size() == 0) {
                swPart.add("응용S/W");
            }
            if (totalSum.size() == 0) {
                totalSum.add("총매출");
            }

            ttlAsset.add(dataList.get(i).get("ttl_asset"));
            capital.add(dataList.get(i).get("capital"));
            eqtyCptl.add(dataList.get(i).get("eqty_cptl"));
            crntLblts.add(dataList.get(i).get("crnt_lblts"));
            consulting.add(dataList.get(i).get("consulting"));
            sysdevPart.add(dataList.get(i).get("sysdev_part"));
            smPart.add(dataList.get(i).get("sm_part"));
            swPart.add(dataList.get(i).get("sw_part"));
            totalSum.add(dataList.get(i).get("total_sales"));
            cagrTarget.add(dataList.get(i).get("total_sales"));
            period.add(dataList.get(i).get("yr_dt"));
        }

        List<Object> cagr = new ArrayList<>();

        for (int i = 0; i < cagrTarget.size(); i++) {
            if(i == cagrTarget.size() - 1){
                break;
            }
            Float cal = ((BigDecimal) cagrTarget.get(i + 1)).floatValue() /  ((BigDecimal) cagrTarget.get(i)).floatValue() - 1;
            if(cagr.size() == 0){
                cagr.add(0);
            }
            cagr.add(Math.round(cal * 100));
        }

        //
        Map<String, Object> result = new HashMap<>();

        result.put("ttlAsset", ttlAsset);
        result.put("capital", capital);
        result.put("eqtyCptl", eqtyCptl);
        result.put("crntLblts", crntLblts);
        result.put("consulting", consulting);
        result.put("sysdevPart", sysdevPart);
        result.put("smPart", smPart);
        result.put("swPart", swPart);
        result.put("totalSum", totalSum);
        result.put("period", period);
        result.put("unit", unit);
        result.put("cagr", cagr);

        System.out.println(result);

        return result;
    }

    //      근속년수 현황
    @ResponseBody
    @GetMapping("/api/getWorkYears")
    public List<List<Object>> getWorkYears(String parameter) throws Exception {

        Map<String, String> params = new HashMap<>();

        LocalDate now = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy");
        String currentYear = now.format(formatter);

        if ("0".equals(parameter)) {
            params.put("searchDate", Integer.toString(Integer.parseInt(currentYear) - 5));
        } else {
            if (parameter.contains("-")) {
                parameter = parameter.replaceAll("[-]", "");
                String[] periods = parameter.split("  ");

                params.put("searchStartDate", periods[0].substring(0, 6));
                params.put("searchEndDate", periods[1].substring(0, 6));
            } else {
                params.put("searchDate", Integer.toString(Integer.parseInt(currentYear) - Integer.parseInt(parameter)));
            }
        }

        List<Map<String, Object>> dataList = (List<Map<String, Object>>) commonService.selectEmplyList(params, PAGE_ID + PROGRAM_ID + ".selectWorkYears");

        List<List<Object>> graphData = new ArrayList<>();

        for (int i = 0; i < dataList.size(); i++) {
            List<Object> datas = new ArrayList<>();

            if (graphData.size() < 1) {
                datas.add("amount");
                datas.add("period");
                graphData.add(datas);

                datas = new ArrayList<>();

                datas.add(dataList.get(i).get("work_yr"));
                datas.add(dataList.get(i).get("years"));
                graphData.add(datas);
            } else {
                datas.add(dataList.get(i).get("work_yr"));
                datas.add(dataList.get(i).get("years"));
                graphData.add(datas);
            }
        }

        System.out.println(graphData);

        return graphData;
    }
}
