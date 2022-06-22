package com.teraenergy.common.service;

import java.util.List;
import java.util.Map;

public interface CommonService {
    
    /* 목록조회 */
    List<?> selectList(Object paramVO, String queryId) throws Exception;

    /* 맵 조회 */
	Map<?, ?> selectMap(Object paramVO, String queryId, String mapKey) throws Exception;

	/* 레코드 카운트 조회 */
	int selectCount(Object paramVO, String queryId) throws Exception;

	/* 상세정보 조회 */
	Object selectContents(Object paramVO, String queryId) throws Exception;

	/* 등록 */
	void insertContents(Object paramVO, String queryId) throws Exception;

	/* 등록하고 키 반환 */
	Object insertSelectKey(Object paramVO, String queryId) throws Exception;

	/* 수정 */
	void updateContents(Object paramVO, String queryId) throws Exception;

	/* 삭제 */
	void deleteContents(Object paramVO, String queryId) throws Exception;
}
