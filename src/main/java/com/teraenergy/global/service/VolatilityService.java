package com.teraenergy.global.service;

import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public interface VolatilityService {

    Map<String, Object> getOffsetMap(Map<String, String> paramMap, String programId, String pageId, String queryId) throws Exception;

}
