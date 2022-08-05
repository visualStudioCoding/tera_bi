package com.teraenergy.global.service.impl;

import com.teraenergy.global.mapper.CommonMapper;
import com.teraenergy.global.service.VolatilityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service("volatilityService")
public class VolatilityServiceImpl implements VolatilityService {

	@Resource(name="commonMapper")
	private CommonMapper commonMapper;

	public Map<String, Object> getOffsetMap(Map<String, String> paramMap, String programId, String pageId, String queryId) throws Exception {

		Map<String, Object> recent = new HashMap<>();
		recent.put("recent", "recent");

		@SuppressWarnings("unchecked")
		Map<String, Object> data = (Map<String, Object>) commonMapper.selectContents(recent, pageId + programId + queryId);

		Map<String, Object> offset = new HashMap<>();
		offset.put("offset", "offset");
		if (queryId.equals(".selectBaseRate")) {
			queryId = ".selectBaseRateCompare";
		} else if(queryId.equals(".selectExchangeRate")) queryId = ".selectExchangeRateCompare";
		@SuppressWarnings("unchecked")
		Map<String, Object> dataOffset = (Map<String, Object>) commonMapper.selectContents(offset, pageId + programId + queryId);

		float dataFloat = (Float) data.get("val");
		float dataFloatOffset = (Float) dataOffset.get("val");
		String unit = (String) data.get("unit");
		String baseDate = (String) data.get("baseDate");

		return getOffset(dataFloat, dataFloatOffset, unit, baseDate);
	}

	private Map<String, Object> getOffset(float data, float dataOffset, String unit, String baseDate) {

		float subtraction;
		float subRate;

		if (data < dataOffset) {
			subRate = (dataOffset - data) / data * 100;
			subtraction = dataOffset - data;
		} else {
			subRate = (data - dataOffset) / data * 100;
			subtraction = data - dataOffset;
		}

		Map<String, Object> result = new HashMap<>();

		String tmpSubRate = String.format("%.2f", subRate);
		String tmpSubtraction = String.format("%.2f", subtraction);
		result.put("current", NumberFormat.getInstance().format(data));
		result.put("past", NumberFormat.getInstance().format(dataOffset));
		result.put("subRate", tmpSubRate);
		result.put("subtraction", tmpSubtraction);
		result.put("unit", unit);
		result.put("baseDate", baseDate);

		return result;
	}
}
