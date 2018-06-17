package com.momukji.mfront.service.restaurant;

import java.util.HashMap;
import java.util.List;

import com.momukji.mfront.domain.RestaurantVO;

public interface RestaurantService {
	
	/**
	 * 식당정보insert
	 * @param RestaurantVO
	 * @return Integer
	 * @throws Exception
	 */
	int insertRestaurant(RestaurantVO res) throws Exception;
	
	/**
	 * 식당정보update
	 * @param RestaurantVO
	 * @return Integer
	 * @throws Exception
	 */
	int updateRestaurant(HashMap<String, Object> hashmap) throws Exception;

	/**
	 * 식당검색
	 * @param String
	 * @return RestaurantVO
	 * @throws Exception
	 */
	List<RestaurantVO> selectRestaurant(HashMap<String, Object> hashmap) throws Exception;
	
	/**
	 * 식당검색list
	 * @param paramMap
	 * @return List
	 * @throws Exception
	 */
	List<RestaurantVO> selectRestaurantList(HashMap<String, Object> hashmap) throws Exception;

	/**
	 * 식당 이미지 경로 등록
	 * @param HashMap
	 * @return Integer
	 * @throws Exception
	 */
	int insertResImg(HashMap<String, Object> hashmap) throws Exception;

	/**
	 * 식당 이미지 경로 수정
	 * @param HashMap
	 * @return Integer
	 * @throws Exception
	 */
	int updateResImg(HashMap<String, Object> hashmap)throws Exception;

	/**
	 * 식당 지역 카운트업
	 * @param HashMap
	 * @return Integer
	 * @throws Exception
	 */
	int updateLocalCategoryCount(HashMap<String, Object> hashmap)throws Exception;

	/**
	 * 식당 방문 카운터 추가
	 * @param HashMap
	 * @return Integer
	 * @throws Exception
	 */
	int insertResVisitCount(HashMap<String, Object> hashmap) throws Exception;

	/**
	 * 식당 방문 카운트업
	 * @param HashMap
	 * @return Integer
	 * @throws Exception
	 */
	int updateResVisitCount(HashMap<String, Object> hashmap) throws Exception;

}