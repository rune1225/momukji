package com.momukji.mfront.service.now;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.momukji.mfront.dao.NowDAO;
import com.momukji.mfront.domain.RestaurantVO;

@Service("nowService")
public class NowServiceImpl implements NowService{
	
Logger log = Logger.getLogger(this.getClass());
    
    @Resource(name="nowDAO")
    private NowDAO nowDAO;

	@Override
	public List<RestaurantVO> selectMomukjiNow(HashMap<String, Object> hashmap) throws Exception {
		
		return nowDAO.selectMomukjiNow(hashmap);
	}

	@Override
	public List<RestaurantVO> selectMomukjiNowMap(HashMap<String, Object> hashmap) throws Exception {
		
		return nowDAO.selectMomukjiNowMap(hashmap);
	}
}