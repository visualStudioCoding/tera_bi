package com.teraenergy.common.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teraenergy.common.mapper.CommonMapper;
import com.teraenergy.common.service.CommonService;

@Service("cmmnService")
public class CommonServiceImpl implements CommonService {

    @Autowired
	private CommonMapper commonMapper;

	@Override
    public List<?> selectList(final Object paramVO, final String queryId) throws Exception {
        return commonMapper.selectList(paramVO, queryId);
    }
    
    @Override
	public Map<?, ?> selectMap(final Object paramVO, final String queryId, final String mapKey) throws Exception {
		return commonMapper.selectMap(paramVO, queryId, mapKey);
	}

	@Override
	public int selectCount(final Object paramVO, final String queryId) throws Exception {
		return commonMapper.selectCount(paramVO, queryId);
	}

	@Override
	public Object selectContents(final Object paramVO, final String queryId) throws Exception {
		return commonMapper.selectContents(paramVO, queryId);
	}

	@Override
	public void insertContents(final Object paramVO, final String queryId) throws Exception {
		commonMapper.insertContents(paramVO, queryId);
	}

	@Override
	public Object insertSelectKey(final Object pParamVO, final String pQueryId) throws Exception {
		return commonMapper.insertSelectKey(pParamVO, pQueryId);
	}

	@Override
	public void updateContents(final Object paramVO, final String queryId) throws Exception {
		commonMapper.updateContents(paramVO, queryId);
	}

	@Override
	public void deleteContents(final Object paramVO, final String queryId) throws Exception {
		commonMapper.deleteContents(paramVO, queryId);
	}
}
