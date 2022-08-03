package com.teraenergy.global.common.utilities;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor(access= AccessLevel.PRIVATE)
public class SearchUtil {

	public static Map<String, String> searchDate(Map<String, Object> paramMap) {

		String searchParam = String.valueOf(paramMap.get("term"));

		Map<String, String> params = new HashMap<>();
		if (searchParam.contains("-")) {
			searchParam = searchParam.replaceAll("[-]", "");
			String[] periods = searchParam.split("  ");

			params.put("searchStartDate", periods[0].substring(0,8));
			params.put("searchEndDate", periods[1].substring(0,8));
		} else {
			params.put("searchPeriod", searchParam);
		}

		return params;
	}

}
