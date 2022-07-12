package com.teraenergy.global.service.impl;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import com.teraenergy.global.configuration.ApiKeyConfiguration;
import com.teraenergy.global.common.utilities.AreaNameUtil;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import com.teraenergy.global.mapper.CommonMapper;
import com.teraenergy.global.service.CommonService;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

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

		}catch (Exception e) {
			e.printStackTrace();
		}
		return stringBuilder;
	}

	public String generateParameter(String parameter, String site) {

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

	public Map<String,Object> apiXmlParser(StringBuilder stringBuilder) throws ParserConfigurationException, IOException, SAXException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//		DocumentBuilder documentBuilder = factory.newDocumentBuilder();
//		Document document = documentBuilder.parse(String.valueOf(stringBuilder));
		InputStream is = new ByteArrayInputStream((stringBuilder.toString()).getBytes());

		DocumentBuilder documentBuilder = factory.newDocumentBuilder();
		Document document = documentBuilder.parse(is);

		Element root = document.getDocumentElement();

		NodeList children = root.getChildNodes();
		NodeList items = root.getElementsByTagName("분류1");

		NodeList inner_title = items.item(0).getChildNodes();

		Node inner_data = items.item(0);
		NodeList inner_list = inner_data.getChildNodes();

		Node inner_dataList = inner_list.item(0);

		System.out.println(items.item(0).getAttributes());

		for(int j = 0 ; j < inner_title.getLength(); j++){
			Element elm = (Element)inner_dataList;
			System.out.println((inner_title.item(j).getNodeName()).toString() + ":" + (elm.getAttribute("주기")).toString());
			System.out.println("값 : " + inner_title.item(j).getTextContent().toString());
		}

//		System.out.println(items.getLength());
//		System.out.println((items.getClass()).toString());

		System.out.println("=====================================================");
		for(int i = 0 ; i < items.getLength(); i++){
			Node item = items.item(i);
			NodeList text = item.getChildNodes();
			String itemValue = text.item(i).getTextContent();
//			System.out.println(item);
//			System.out.println(text.item(i));
//			System.out.println(itemValue);
		}

//		for( int i = 0 ; i < children.getLength(); i++){
//			Node node = children.item(i);
//			if(node.getNodeType() == Node.ELEMENT_NODE){
//				Element elm = (Element)node;
//				System.out.println(elm.getNodeName());
//			}
//		}
//		System.out.println("====================================");
//
//		Element middle = (Element) children;
//		NodeList grand_children = middle.getChildNodes();
//
//		for( int j = 0 ; j < grand_children.getLength(); j++){
//			Node node = grand_children.item(j);
//			if(node.getNodeType() == Node.ELEMENT_NODE){
//				Element elm = (Element)node;
//				System.out.println(elm.getNodeName());
//			}
//		}

		return null;
	}

	@Override
	public String getCtyNm(String areaCd, String other) {
		return AreaNameUtil.areaName(areaCd, other);
	}


}
