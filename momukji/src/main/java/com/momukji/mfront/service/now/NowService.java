package com.momukji.mfront.service.now;

import java.util.HashMap;
import java.util.List;

import com.momukji.mfront.domain.RestaurantVO;

public interface NowService {
	
	/**
	 * 모먹지나우리스트
	 * @param HashMap
	 * @return List<RestaurantVO>
	 * @throws Exception
	 */
	List<RestaurantVO> selectMomukjiNow(HashMap<String, Object> hashmap) throws Exception;

	/**
	 * 모먹지나우지도보기
	 * @param HashMap
	 * @return RestaurantVO
	 * @throws Exception
	 */
	List<RestaurantVO> selectMomukjiNowMap(HashMap<String, Object> hashmap) throws Exception;

}