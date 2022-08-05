package com.teraenergy.global.service;

import org.json.JSONObject;
import org.json.simple.JSONArray;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ApiParseService {

	StringBuilder getApiResult(String url, String parameter, String format, String site) throws Exception;

	List<?> apiJsonParser(StringBuilder stringBuilder) throws Exception;

	JSONArray ecosApiJsonParser(StringBuilder stringBuilder, String statistic) throws Exception;

	JSONObject apiXmlParser(StringBuilder stringBuilder) throws Exception;
}
