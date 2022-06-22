package com.teraenergy.common.mapper;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.mybatis.spring.SqlSessionTemplate;

@Repository("commonMapper")
public class CommonMapper {

    @Resource(name = "sqlMapClientCommon")
    private SqlSessionTemplate template;
    
    private static final String PACKAGE_NAME = "teraenergy.";

    /* 목록 조회 */
    public List<?> selectList(final Object paramVO, final String queryId) throws Exception {
        String mQueryId = "";
        if (queryId != null && (queryId.equals("") || queryId.indexOf(".") < 0)) {
            mQueryId = queryId + ".selectList";
        } else {
            mQueryId = queryId;
        }
        return template.selectList(PACKAGE_NAME + mQueryId, paramVO);
    }
    
    /* 맵 조회 */
	public Map<?, ?> selectMap(final Object paramVO, final String queryId, final String mapKey) throws Exception {
		String mQueryId = "";
		if (queryId != null && (queryId.equals("") || queryId.indexOf(".") < 0)) {
			mQueryId = queryId + ".selectMap";
		} else {
			mQueryId = queryId;
		}
		return template.selectMap(PACKAGE_NAME + mQueryId, paramVO, mapKey);
	}

	/* 레코드 카운트 조회 */
	public int selectCount(final Object paramVO, final String queryId) throws Exception {
		String mQueryId = "";
		if (queryId != null && (queryId.equals("") || queryId.indexOf(".") < 0)) {
			mQueryId = queryId + ".selectCount";
		} else {
			mQueryId = queryId;
		}
		return (Integer) template.selectOne(PACKAGE_NAME + mQueryId, paramVO);
	}

	/* 상세 조회 */
	public Object selectContents(final Object paramVO, final String queryId) throws Exception {
		String mQueryId = "";
		if (queryId != null && (queryId.equals("") || queryId.indexOf(".") < 0)) {
			mQueryId = queryId + ".selectContents";
		} else {
			mQueryId = queryId;
		}
		return template.selectOne(PACKAGE_NAME + mQueryId, paramVO);
	}

	/* 등록 */
	public void insertContents(final Object paramVO, final String queryId) throws Exception {
		String mQueryId = "";
		if (queryId != null && (queryId.equals("") || queryId.indexOf(".") < 0)) {
			mQueryId = queryId + ".insertContents";
		} else {
			mQueryId = queryId;
		}
		template.insert(PACKAGE_NAME + mQueryId, paramVO);
	}

	/* 수정 */
	public void updateContents(final Object paramVO, final String queryId) throws Exception {
		String mQueryId = "";
		if (queryId != null && (queryId.equals("") || queryId.indexOf(".") < 0)) {
			mQueryId = queryId + ".updateContents";
		} else {
			mQueryId = queryId;
		}
		template.update(PACKAGE_NAME + mQueryId, paramVO);
	}

	/* 삭제 */
	public void deleteContents(final Object paramVO, final String queryId) throws Exception {
		String mQueryId = "";
		if (queryId != null && (queryId.equals("") || queryId.indexOf(".") < 0)) {
			mQueryId = queryId + ".deleteContents";
		} else {
			mQueryId = queryId;
		}
		template.delete(PACKAGE_NAME + mQueryId, paramVO);
	}

	/* 정보를 입력하고 입력한 키 반환 */
	public Object insertSelectKey(final Object paramVO, final String queryId) throws Exception {
		String mQueryId = "";
		if (queryId != null && (queryId.equals("") || queryId.indexOf(".") < 0)) {
			mQueryId = queryId + ".insertSelectKey";
		} else {
			mQueryId = queryId;
		}
		template.insert(PACKAGE_NAME + mQueryId, paramVO);
		return paramVO;
	}

	/**
	 * Execute Batch 만들기. ( 배열필요)
	 *
	 * @param queryId - 수정 처리 SQL mapping 쿼리 ID
	 * @param parameterObject - 수정 처리 SQL mapping 입력 데이터(key 조건 및 변경 데이터)를 세팅한
	 *        파라메터 객체(보통 VO 또는 Map)
	 * @return DBMS가 지원하는 경우 update 적용 결과 count
	 */
}
