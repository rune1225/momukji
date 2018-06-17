package com.momukji.mfront.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.momukji.common.dao.AbstractDAO;
import com.momukji.mfront.domain.MenuReviewVO;
import com.momukji.mfront.domain.MenuVO;

@SuppressWarnings("unchecked")
@Repository("menuDAO")
public class MenuDAO extends AbstractDAO{
    
    /**
	 * 모먹지대표메뉴 리스트
	 * @param paramMap
	 * @return List<MenuVO>
	 */
	public List<MenuVO> selectMomukjiTypicalMenu(HashMap<String, Object> hashmap) throws Exception{
		
        return (List<MenuVO>)selectList("front.menu.selectMomukjiTypicalMenu", hashmap);
    }

	/**
	 * 모먹지전체메뉴 리스트
	 * @param paramMap
	 * @return List<MenuVO>
	 */
	public List<MenuVO> selectMomukjiEntireMenu(HashMap<String, Object> hashmap) throws Exception{
		
        return (List<MenuVO>)selectList("front.menu.selectMomukjiEntireMenu", hashmap);
    }
	
	/**
	 * 모먹지메뉴리뷰저장
	 * @param MenuReviewVO
	 * @return int
	 * @throws Exception
	 */
	public int insertMenuReview(MenuReviewVO menureview) throws Exception{
		return (Integer) insert("front.menu.insertMenuReview", menureview);
	}
	
	/**
	 * 모먹지전체메뉴 리스트
	 * @param paramMap
	 * @return List<MenuVO>
	 */
	public List<MenuReviewVO> selectMenuReview(HashMap<String, Object> hashmap) throws Exception{
		
        return (List<MenuReviewVO>)selectList("front.menu.selectMomukjiMenuReview", hashmap);
    }

	/**
	 * 메뉴저장
	 * @param MenuVO
	 * @return int
	 * @throws Exception
	 */
	public int insertMenu(MenuVO menu) throws Exception{
		// TODO Auto-generated method stub
		return (Integer) insert("front.menu.insertMenu", menu);
	}
	
	/**
	 * 메뉴수정
	 * @param HashMap
	 * @return int
	 * @throws Exception
	 */
	public int updateMenu(HashMap<String, Object> hashmap) throws Exception{
		// TODO Auto-generated method stub
		return (Integer) update("front.menu.modifyMenu", hashmap);
	}

	/**
	 * 메뉴 이미지 경로 저장
	 * @param HashMap
	 * @return int
	 * @throws Exception
	 */
	public int insertMenuImg(HashMap<String, Object> hashmap) {
		// TODO Auto-generated method stub
		return (Integer) insert("front.menu.insertMenuImg", hashmap);
	}
	
	/**
	 * 메뉴 이미지 경로 수정
	 * @param HashMap
	 * @return int
	 * @throws Exception
	 */
	public int updateMenuImg(HashMap<String, Object> hashmap) throws Exception{
		// TODO Auto-generated method stub
		return (Integer) update("front.menu.modifyMenuImg", hashmap);
	}

	/**
	 * 메뉴 카테고리 리스트
	 * @return HashMap
	 * @throws Exception
	 */
	public List<HashMap<String, Object>> selectMenuCategory() {
		// TODO Auto-generated method stub
		return (List<HashMap<String, Object>>)selectList("front.menu.selectMenuCategory", "");
	}

	/**
	 * 메뉴 카테고리 리스트
	 * @param HashMap
	 * @return MenuVO
	 * @throws Exception
	 */
	public MenuVO selectMenu(HashMap<String, Object> hashmap) {
		// TODO Auto-generated method stub
		return (MenuVO)selectByPk("front.menu.selectMenu", hashmap);
	}
	 
}