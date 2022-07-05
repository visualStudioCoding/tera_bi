package com.teraenergy.global.service.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.teraenergy.global.configuration.ApiKeyConfiguration;
import org.springframework.stereotype.Service;

import com.teraenergy.global.mapper.CommonMapper;
import com.teraenergy.global.service.CommonService;

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
	public StringBuilder getApiResult(String url, String parameter) throws Exception {

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
//		System.out.println("Response code:" + conn.getResponseCode());

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
		return sb;
	}
}
