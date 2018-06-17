package com.momukji.mfront.service.travel;

import java.util.HashMap;
import java.util.List;

import com.momukji.mfront.domain.RestaurantVO;
import com.momukji.mfront.domain.model.LocalCategory;

public interface TravelService {
	
	/**
	 * 모먹지여행리스트
	 * @param HashMap
	 * @return List<RestaurantVO>
	 * @throws Exception
	 */
	List<RestaurantVO> selectMomukjiTravel(HashMap<String, Object> hashmap) throws Exception;

	/**
	 * 모먹지지역카테고리리스트
	 * @param HashMap
	 * @return List<LocalCategory>
	 * @throws Exception
	 */
	List<LocalCategory> selectLocalCategoryList(HashMap<String, Object> hashmap) throws Exception;


}