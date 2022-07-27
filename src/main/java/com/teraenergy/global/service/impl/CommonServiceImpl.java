package com.teraenergy.global.service.impl;

import com.teraenergy.global.configuration.ApiKeyConfiguration;
import com.teraenergy.global.mapper.CommonMapper;
import com.teraenergy.global.service.CommonService;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.json.XML;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

@Slf4j
@Service("commonService")
public class CommonServiceImpl implements CommonService {

	private final ApiKeyConfiguration apiKeyConfiguration;
    @Resource(name="commonMapper")
	private CommonMapper commonMapper;

	public CommonServiceImpl(ApiKeyConfiguration apiKeyConfiguration) {
		this.apiKeyConfiguration = apiKeyConfiguration;
	}

	@Override
    public List<?> selectList(final Object paramDTO, final String queryId) throws Exception {
        return commonMapper.selectList(paramDTO, queryId);
    }

    @Override
    public Map<?, ?> selectMap(final Object paramDTO, final String queryId, final String mapKey) throws Exception {
        return commonMapper.selectMap(paramDTO, queryId, mapKey);
    }

    @Override
    public int selectCount(final Object paramDTO, final String queryId) throws Exception {
        return commonMapper.selectCount(paramDTO, queryId);
    }

    @Override
    public Object selectContents(final Object paramDTO, final String queryId) throws Exception {
        return commonMapper.selectContents(paramDTO, queryId);
    }

    @Override
    public int insertContents(final Object paramDTO, final String queryId) throws Exception {
        return commonMapper.insertContents(paramDTO, queryId);
    }

    @Override
    public Object insertSelectKey(final Object paramDTO, final String queryId) throws Exception {
        return commonMapper.insertSelectKey(paramDTO, queryId);
    }

    @Override
    public int updateContents(final Object paramDTO, final String queryId) throws Exception {
        return commonMapper.updateContents(paramDTO, queryId);
    }

    @Override
    public int deleteContents(final Object paramDTO, final String queryId) throws Exception {
        return commonMapper.deleteContents(paramDTO, queryId);
    }

    @Override
    public StringBuilder getApiResult(String url, String parameter, String format, String site) throws Exception {

        parameter = generateParameter(parameter, site);
        StringBuilder stringBuilder = new StringBuilder();

        log.info(parameter);

        try {
            // url생성
            StringBuilder loadUrl = new StringBuilder(url);
            loadUrl.append(parameter);
            log.info(String.valueOf(loadUrl));

            // 생성된 url로 api 연결
            URL apiUrl = new URL(loadUrl.toString());
            HttpURLConnection httpURLConnection = (HttpURLConnection) apiUrl.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setRequestProperty("Content-type", "application/json");
//		log.info("Response code:" + conn.getResponseCode());

            // 서버로부터 데이터 읽어오기
            BufferedReader bufferedReader;

            if (httpURLConnection.getResponseCode() >= 200 && httpURLConnection.getResponseCode() <= 300) {
                bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream(), "UTF-8"));
            } else {
                bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getErrorStream(), "UTF-8"));
            }
            String line;

            // 결과값을 읽을 수 있는 동안 반복
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }

            bufferedReader.close();
            httpURLConnection.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return stringBuilder;
    }

    public String generateParameter(String parameter, String site) {

        String enaraKey = apiKeyConfiguration.getEnaraKey();
        String enaraId = apiKeyConfiguration.getEnaraId();

        String ecosKey = apiKeyConfiguration.getEcosKey();

        enaraKey = enaraId + "&idntfcId=" + enaraKey;

        String apiKey = apiKeyConfiguration.getKosisKey();

        String str = "apiKey=";

        if ("kosis".equals(site)) {
            String[] params = parameter.split(str);
            String param1 = params[0];
            String param2 = params[1];
            parameter = param1 + str + apiKey + param2;
        }

        if ("enara".equals(site)) {
            apiKey = enaraKey;
            str = "userId=";

            String[] params = parameter.split(str);
            String param1 = params[0];
            String param2 = params[1];
            parameter = param1 + str + apiKey + param2;
        }
        if ("ecos".equals(site)) {
            apiKey = ecosKey;
            str = "apiKey";

            String[] params = parameter.split(str);
            String param1 = params[0];
            String param2 = params[1];
            parameter = param1 + apiKey + param2;
        }

        // api 키 추가
//		String[] params = parameter.split(str);
//		String param1 = params[0];
//		String param2 = params[1];
//		parameter = param1 + str + apiKey + param2;

        return parameter;
    }

	public JSONArray apiJsonParser(StringBuilder stringBuilder) throws ParseException {
		JSONParser jsonParser = new JSONParser();
		try{
            return (JSONArray) jsonParser.parse(String.valueOf(stringBuilder));
        }catch(Exception e){
            JSONArray sendData = new JSONArray();
            org.json.simple.JSONObject dataContent = (org.json.simple.JSONObject) jsonParser.parse(String.valueOf(stringBuilder));
            dataContent.put("RESULT", "Fail");

            sendData.add(dataContent);
            System.out.println(sendData);
            return sendData;
        }
	}
	public JSONArray ecosApiJsonParser(StringBuilder stringBuilder, String statistic) throws ParseException {
		JSONParser jsonParser = new JSONParser();
		org.json.simple.JSONObject jsonObject = (org.json.simple.JSONObject) jsonParser.parse(String.valueOf(stringBuilder));
        try {
            org.json.simple.JSONObject root = (org.json.simple.JSONObject) jsonObject.get(statistic);
            return (JSONArray) root.get("row");
        }catch(Exception e){
            org.json.simple.JSONObject root = (org.json.simple.JSONObject) jsonObject.get("RESULT");
            root.put("RESULT", "Fail");
            JSONArray sendData = new JSONArray();
            sendData.add(root);
//            root.put("row", sendData);
            return sendData;
        }
	}

    public org.json.JSONObject apiXmlParser(StringBuilder stringBuilder) {
        JSONObject jsonObject = XML.toJSONObject(String.valueOf(stringBuilder));
        JSONObject root = jsonObject.getJSONObject("지표");
        return root.getJSONObject("통계표");
    }

}
