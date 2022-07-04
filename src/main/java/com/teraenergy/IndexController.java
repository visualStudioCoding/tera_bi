package com.teraenergy;

import com.teraenergy.global.configuration.ApiKeyConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.List;

@Controller
public class IndexController {

    private final ApiKeyConfiguration apiKeyConfiguration;

    public IndexController(ApiKeyConfiguration apiKeyConfiguration) {
        this.apiKeyConfiguration = apiKeyConfiguration;
    }

    @GetMapping("/")
    public String indexPage(Model model) {
        model.addAttribute("hello", "index page!!");
        System.out.println(apiKeyConfiguration.getKosisKey());
        System.out.println(apiKeyConfiguration.getEnaraId());
        System.out.println(apiKeyConfiguration.getEnaraKey());

        return "index";
    }

    @RequestMapping("/getIncome")
    public StringBuilder getIncomes(String url, String parameter) throws IOException {

        // api 키 추가
        String[] params = parameter.split("apiKey=");
        String param1 = params[0];
        String param2 = params[1];
        parameter = params[0] + "apiKey=" + apiKeyConfiguration.getKosisKey() + param2;
        System.out.println(parameter);

        // url생성
        StringBuilder loadUrl = new StringBuilder(url);
        loadUrl.append(parameter);
        System.out.println(loadUrl);

        // 생성된 url로 api 연결
        URL apiUrl = new URL(loadUrl.toString());
        HttpURLConnection conn = (HttpURLConnection) apiUrl.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code:" + conn.getResponseCode());

        // 서버로부터 데이터 읽어오기
        BufferedReader rd;

        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300){
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        }else{
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }

        StringBuilder sb = new StringBuilder();
        String line;

        // 결과값을 읽을 수 있는 동안 반복
        while((line = rd.readLine()) != null){
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
        System.out.println(sb.toString());

        return sb;
    }
}
