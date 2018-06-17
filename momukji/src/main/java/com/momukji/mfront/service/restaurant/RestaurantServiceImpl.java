package com.momukji.mfront.service.restaurant;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.momukji.mfront.dao.RestaurantDAO;
import com.momukji.mfront.domain.RestaurantVO;

@Service("restaurantService")
public class RestaurantServiceImpl implements RestaurantService{
	
Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="restaurantDAO")
    private RestaurantDAO restaurantDAO;
	
	public int insertRestaurant(RestaurantVO res) throws Exception {
		// TODO Auto-generated method stub
		return restaurantDAO.insertRestaurant(res);
	}
	
	public int updateRestaurant(HashMap<String, Object> hashmap) throws Exception {
		// TODO Auto-generated method stub
		return restaurantDAO.updateRestaurant(hashmap);
	}
	
	public List<RestaurantVO> selectRestaurant(HashMap<String, Object> hashmap) throws Exception{
		// TODO Auto-generated method stub
		return restaurantDAO.selectRestaurant(hashmap);
	}

	public List<RestaurantVO> selectRestaurantList(HashMap<String, Object> hashmap) throws Exception {
		// TODO Auto-generated method stub
		return restaurantDAO.selectRestaurantList(hashmap);
	}

	public int insertResImg(HashMap<String, Object> hashmap) throws Exception {
		// TODO Auto-generated method stub
		return restaurantDAO.insertResImg(hashmap);
	}

	public int updateResImg(HashMap<String, Object> hashmap) throws Exception {
		// TODO Auto-generated method stub
		return restaurantDAO.updateResImg(hashmap);
	}

	public int updateLocalCategoryCount(HashMap<String, Object> hashmap) throws Exception {
		// TODO Auto-generated method stub
		return restaurantDAO.updateLocalCategoryCount(hashmap);
	}

	public int insertResVisitCount(HashMap<String, Object> hashmap) throws Exception {
		// TODO Auto-generated method stub
		return restaurantDAO.insertResVisitCount(hashmap);
	}

	public int updateResVisitCount(HashMap<String, Object> hashmap) throws Exception {
		// TODO Auto-generated method stub
		return restaurantDAO.updateResVisitCount(hashmap);
	}

}
