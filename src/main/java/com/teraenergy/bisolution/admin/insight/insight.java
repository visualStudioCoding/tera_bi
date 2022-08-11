package com.teraenergy.bisolution.admin.insight;

import com.teraenergy.global.service.CommonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/front/temp")
public class insight {

    private static final String PROGRAM_ID = ".temp";
    private static final String PAGE_ID = "front";
    private static final String DIRECTORY = "/temp/temp";

    @Resource(name = "commonService")
    private CommonService commonService;

    @GetMapping("/main")
    public String tempMain(Model model) throws Exception {
        log.info(PAGE_ID + DIRECTORY + "Main");
        model.addAttribute("menuCode", "001");
        return PAGE_ID + DIRECTORY + "Main";
    }

    //kospi 데이터 가져오기 - tempChart.js 에서 호출
    @ResponseBody
    @GetMapping("/api/getKospi")
    public Map<String, List<Map<String, Object>>> covidKospi() throws Exception {

        List<Map<String, Object>> covidKospiList = (List<Map<String, Object>>) commonService.selectList(null, PAGE_ID + PROGRAM_ID + ".selectKospi");
        Map<String, List<Map<String, Object>>> result = new HashMap<>();
        result.put("covidKospi", covidKospiList);

        //예측 - insight_kospi_index
        Map<Object,Object> map = new HashMap();
        map.put("days",30);
        List<Map<String, Object>> insightKospiList = (List<Map<String, Object>>) commonService.selectList(map, PAGE_ID + PROGRAM_ID + ".selectInsightKospi");
        result.put("insightKospi", insightKospiList);
        return result;
    }

    ///스케쥴 - kospi 예측해서 db입력
    //@Scheduled(cron = "* * 4 * * *")
    //@Scheduled(cron = "0 * * * * *")
    //@Scheduled(cron = "* * 4 * * *")
    @GetMapping("/schedule/Kospi")
    public void insertKospi() throws Exception {
        try {
            //insight_kospi_index 지워준다
            commonService.deleteContents(null, PAGE_ID + PROGRAM_ID + ".deleteInsightKospi");
            //예측 실행 후 DB저장
            //ProcessBuilder pb = new ProcessBuilder("python","C:\\Users\\tera\\IdeaProjects\\tera_bi\\src\\main\\resources\\templates\\admin\\insight\\kospi.py");
            ProcessBuilder pb = new ProcessBuilder("python","C:\\Users\\tera\\IdeaProjects\\tera_bi\\src\\main\\java\\com\\teraenergy\\bisolution\\admin\\insight\\kospi.py");
            //ProcessBuilder pb = new ProcessBuilder("python","/home/terabi/py/kospi.py");
            Process p = pb.start();
            BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream(), "utf-8"));

            String line = "";
            //함수호출이(return)이 아니고 파이썬 함수에서 print된걸 받아오는것이다
            while((line = br.readLine()) != null) {
                System.out.println("dd : " + line);
            }

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
