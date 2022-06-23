package com.teraenergy.global.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teraenergy.global.mapper.CommonMapper;
import com.teraenergy.global.service.CommonService;

@Service("commonService")
public class CommonServiceImpl implements CommonService {

    @Autowired
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
	public void insertContents(final Object paramDTO, final String queryId) throws Exception {
		commonMapper.insertContents(paramDTO, queryId);
	}

	@Override
	public Object insertSelectKey(final Object pparamDTO, final String pQueryId) throws Exception {
		return commonMapper.insertSelectKey(pparamDTO, pQueryId);
	}

	@Override
	public void updateContents(final Object paramDTO, final String queryId) throws Exception {
		commonMapper.updateContents(paramDTO, queryId);
	}

	@Override
	public void deleteContents(final Object paramDTO, final String queryId) throws Exception {
		commonMapper.deleteContents(paramDTO, queryId);
	}
}
