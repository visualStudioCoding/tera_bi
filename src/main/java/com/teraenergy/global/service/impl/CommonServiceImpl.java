package com.teraenergy.global.service.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import com.teraenergy.global.configuration.ApiKeyConfiguration;
import com.teraenergy.global.configuration.AreaNameConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import com.teraenergy.global.mapper.CommonMapper;
import com.teraenergy.global.service.CommonService;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@Slf4j
@Service("commonService")
public class CommonServiceImpl implements CommonService {

	private final ApiKeyConfiguration apiKeyConfiguration;

	public CommonServiceImpl(ApiKeyConfiguration apiKeyConfiguration) {
		this.apiKeyConfiguration = apiKeyConfiguration;
	}

    @Resource(name="commonMapper")
	private CommonMapper commonMapper;

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
	public Object insertSelectKey(final Object pparamDTO, final String pQueryId) throws Exception {
		return commonMapper.insertSelectKey(pparamDTO, pQueryId);
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

		parameter = generateUrl(parameter, site);
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

			if("json".equals(format)) {

			}
			if("xml".equals(format)) {
				DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
				Document document = documentBuilder.parse(url);

				// root tag
				document.getDocumentElement().normalize();

				// 파싱할 tag
				NodeList nList = document.getElementsByTagName("지표");
				//HIT Tag 정보들을  검색
				Node firstNode = document.getElementsByTagName("지표").item(0);
				NodeList childNodeList = firstNode.getChildNodes();
				Map<String, Object> nodeMapData = getNodeList(childNodeList);
				System.out.println(nodeMapData.toString());
			}

			bufferedReader.close();
			httpURLConnection.disconnect();

		}catch (Exception e) {
			e.printStackTrace();
		}
		return stringBuilder;
	}

	public String generateUrl(String parameter, String site) {

		String enaraKey = apiKeyConfiguration.getEnaraKey();
		String enaraId = apiKeyConfiguration.getEnaraId();

		enaraKey = enaraId + "&idntfcId=" + enaraKey;

		String apiKey = apiKeyConfiguration.getKosisKey();

		String str = "apiKey=";

		if("enara".equals(site)) {
			apiKey = enaraKey;
			str = "userId=";
		}

		// api 키 추가
		String[] params = parameter.split(str);
		String param1 = params[0];
		String param2 = params[1];
		parameter = param1 + str + apiKey + param2;

		return parameter;
	}

	public static Map<String,Object> getNodeList(NodeList nodeList){
		Map<String, Object> dataMap = new HashMap<>();
		for(int i = 0; i < nodeList.getLength(); i++){
			String tagName = nodeList.item(i).getNodeName();
			if(!"#text".equals(tagName)){
				System.out.println("tagName = " + tagName);
				if(nodeList.item(i).getChildNodes().getLength()>1){
					dataMap.put(tagName,getNodeList(nodeList.item(i).getChildNodes()));
				}else{
					dataMap.put(tagName, nodeList.item(i).getTextContent());
				}
			}
		}
		return dataMap;
	}

	public JSONArray apiJsonParser(StringBuilder stringBuilder) throws ParseException {
		JSONParser jsonParser = new JSONParser();
		return (JSONArray) jsonParser.parse(String.valueOf(stringBuilder));
	}

	public Map<String,Object> apiXmlParser(StringBuilder stringBuilder) {

		Map<String, Object> result = new HashMap<>();


		return result;
	}

	@Override
	public String getCtyNm(String areaCd) {
		return AreaNameConfiguration.areaName(areaCd);
	}


}
