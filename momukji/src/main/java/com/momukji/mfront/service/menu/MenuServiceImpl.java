package com.momukji.mfront.service.menu;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.momukji.mfront.dao.MenuDAO;
import com.momukji.mfront.domain.MenuReviewVO;
import com.momukji.mfront.domain.MenuVO;

@Service("menuService")
public class MenuServiceImpl implements MenuService{
	
Logger log = Logger.getLogger(this.getClass());
    
    @Resource(name="menuDAO")
    private MenuDAO menuDAO;

	@Override
	public List<MenuVO> selectMomukjiTypicalMenu(HashMap<String, Object> hashmap) throws Exception {
		
		return menuDAO.selectMomukjiTypicalMenu(hashmap);
	}

	@Override
	public List<MenuVO> selectMomukjiEntireMenu(HashMap<String, Object> hashmap) throws Exception {
		
		return menuDAO.selectMomukjiEntireMenu(hashmap);
	}
	
	@Override
	public	int insertMenuReview(MenuReviewVO menureview) throws Exception {
		
		return menuDAO.insertMenuReview(menureview);
	}
	
	@Override
	public List<MenuReviewVO> selectMenuReview(HashMap<String, Object> hashmap) throws Exception {
		
		return menuDAO.selectMenuReview(hashmap);
	}

	@Override
	public MenuVO selectMenu(HashMap<String, Object> hashmap) throws Exception {
		// TODO Auto-generated method stub
		return menuDAO.selectMenu(hashmap);
	}
	
	@Override
	public int insertMenu(MenuVO menu) throws Exception {
		// TODO Auto-generated method stub
		return menuDAO.insertMenu(menu);
	}
	
	@Override
	public int updateMenu(HashMap<String, Object> formdata) throws Exception {
		// TODO Auto-generated method stub
		return menuDAO.updateMenu(formdata);
	}

	@Override
	public int insertMenuImg(HashMap<String, Object> hashmap) throws Exception {
		// TODO Auto-generated method stub
		return menuDAO.insertMenuImg(hashmap);
	}
	
	@Override
	public int updateMenuImg(HashMap<String, Object> hashmap) throws Exception {
		// TODO Auto-generated method stub
		return menuDAO.updateMenuImg(hashmap);
	}

	@Override
	public List<HashMap<String, Object>> selectMenuCategory() throws Exception {
		// TODO Auto-generated method stub
		return menuDAO.selectMenuCategory();
	}

}