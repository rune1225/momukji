package com.momukji.mfront.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.momukji.common.dao.AbstractDAO;
import com.momukji.mfront.domain.RestaurantVO;
import com.momukji.mfront.domain.model.LocalCategory;

@SuppressWarnings("unchecked")
@Repository("travelDAO")
public class TravelDAO extends AbstractDAO{
    
    /**
	 * 모먹지여행 리스트
	 * @param HashMap
	 * @return List<RestaurantVO>
	 */
	public List<RestaurantVO> selectMomukjiTravel(HashMap<String, Object> hashmap) throws Exception{
		// TODO Auto-generated method stub
        return (List<RestaurantVO>)selectList("front.travel.selectMomukjiTravel", hashmap);
    }

	/**
	 * 모먹지지역카테고리리스트
	 * @param HashMap
	 * @return List<LocalCategory>
	 * @throws Exception
	 */
	public List<LocalCategory> selectLocalCategoryList(HashMap<String, Object> hashmap) {
		// TODO Auto-generated method stub
		return (List<LocalCategory>)selectList("front.travel.selectLocalCategoryList", hashmap);
	}

}