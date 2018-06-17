package com.momukji.mfront.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.momukji.common.dao.AbstractDAO;
import com.momukji.mfront.domain.RestaurantVO;

@SuppressWarnings("unchecked")
@Repository("nowDAO")
public class NowDAO extends AbstractDAO{
    
    /**
	 * 모먹지나우 리스트
	 * @param paramMap
	 * @return List<RestaurantVO>
	 */
	public List<RestaurantVO> selectMomukjiNow(HashMap<String, Object> hashmap) throws Exception{
		
        return (List<RestaurantVO>)selectList("front.momukjinow.selectMomukjiNow", hashmap);
    }

	/**
	 * 모먹지나우 지도보기
	 * @param paramMap
	 * @return List<RestaurantVO>
	 */
	public List<RestaurantVO> selectMomukjiNowMap(HashMap<String, Object> hashmap) throws Exception{
		
        return (List<RestaurantVO>)selectList("front.momukjinow.selectMomukjiNowMap", hashmap);
    }
	 
}