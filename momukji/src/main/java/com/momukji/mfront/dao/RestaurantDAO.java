package com.momukji.mfront.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.momukji.common.dao.AbstractDAO;
import com.momukji.mfront.domain.RestaurantVO;

@SuppressWarnings("unchecked")
@Repository("restaurantDAO")
public class RestaurantDAO extends AbstractDAO{
	
	/**
	 * 식당 검색
	 * @param HashMap
	 * @return List<RestaurantVO>
	 */
	public List<RestaurantVO> selectRestaurant(HashMap<String, Object> hashmap) throws Exception{
        return (List<RestaurantVO>)selectList("front.restaurant.selectRestaurant", hashmap);
    }
	
	/**
	 * 식당 리스트
	 * @param HashMap
	 * @return List<RestaurantVO>
	 */
	public List<RestaurantVO> selectRestaurantList(HashMap<String, Object> hashmap) throws Exception{
        return (List<RestaurantVO>)selectList("front.restaurant.selectRestaurantList", hashmap);
    }

	/**
	 * 식당 등록
	 * @param RestaurantVO
	 * @return int
	 */
	public int insertRestaurant(RestaurantVO res) throws Exception{
		// TODO Auto-generated method stub
		return (Integer) insert("front.restaurant.insertRestaurant", res);
	}
	
	/**
	 * 식당 정보 수정
	 * @param HashMap
	 * @return int
	 */
	public int updateRestaurant(HashMap<String, Object> hashmap) throws Exception{
		// TODO Auto-generated method stub
		return (Integer) update("front.restaurant.modifyRestaurant", hashmap);
	}

	/**
	 * 식당 이미지 경로 등록
	 * @param HashMap
	 * @return int
	 */
	public int insertResImg(HashMap<String, Object> hashmap) throws Exception{
		// TODO Auto-generated method stub
		return (Integer) insert("front.restaurant.insertResImg", hashmap);
	}

	/**
	 * 식당 이미지 경로 수정
	 * @param HashMap
	 * @return int
	 */
	public int updateResImg(HashMap<String, Object> hashmap) throws Exception{
		// TODO Auto-generated method stub
		return (Integer) update("front.restaurant.modifyResImg", hashmap);
	}

	/**
	 * 식당 지역 카운트업
	 * @param HashMap
	 * @return int
	 */
	public int updateLocalCategoryCount(HashMap<String, Object> hashmap) {
		// TODO Auto-generated method stub
		return (Integer) update("front.restaurant.updateLocalCategoryCount", hashmap);
	}

	/**
	 * 식당 방문 카운터 추가
	 * @param HashMap
	 * @return int
	 * @throws Exception
	 */
	public int insertResVisitCount(HashMap<String, Object> hashmap) {
		// TODO Auto-generated method stub
		return (Integer) insert("front.restaurant.insertResVisitCount", hashmap);
	}
	
	/**
	 * 식당 방문 카운팅
	 * @param HashMap
	 * @return int
	 * @throws Exception
	 */
	public int updateResVisitCount(HashMap<String, Object> hashmap) {
		// TODO Auto-generated method stub
		return (Integer) update("front.restaurant.updateResVisitCount", hashmap);
	}
	 
}