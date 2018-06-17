package com.momukji.mfront.service.travel;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.momukji.mfront.dao.TravelDAO;
import com.momukji.mfront.domain.RestaurantVO;
import com.momukji.mfront.domain.model.LocalCategory;

@Service("travelService")
public class TravelServiceImpl implements TravelService{
	
Logger log = Logger.getLogger(this.getClass());
    
    @Resource(name="travelDAO")
    private TravelDAO travelDAO;

	@Override
	public List<RestaurantVO> selectMomukjiTravel(HashMap<String, Object> hashmap) throws Exception {
		
		return travelDAO.selectMomukjiTravel(hashmap);
	}
	
	@Override
	public List<LocalCategory> selectLocalCategoryList(HashMap<String, Object> hashmap) throws Exception {
		
		return travelDAO.selectLocalCategoryList(hashmap);
	}

}