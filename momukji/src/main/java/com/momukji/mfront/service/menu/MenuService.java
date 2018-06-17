package com.momukji.mfront.service.menu;

import java.util.HashMap;
import java.util.List;

import com.momukji.mfront.domain.MenuReviewVO;
import com.momukji.mfront.domain.MenuVO;

public interface MenuService {
	
	/**
	 * 모먹지대표메뉴리스트
	 * @param HashMap
	 * @return List<RestaurantVO>
	 * @throws Exception
	 */
	List<MenuVO> selectMomukjiTypicalMenu(HashMap<String, Object> hashmap) throws Exception;

	/**
	 * 모먹지전체메뉴리스트
	 * @param HashMap
	 * @return List<RestaurantVO>
	 * @throws Exception
	 */
	List<MenuVO> selectMomukjiEntireMenu(HashMap<String, Object> hashmap) throws Exception;

	/**
	 * 모먹지메뉴리뷰저장
	 * @param MenuReviewVO
	 * @return int
	 * @throws Exception
	 */
	int insertMenuReview(MenuReviewVO menureview) throws Exception;

	/**
	 * 모먹지메뉴리뷰리스트
	 * @param HashMap
	 * @return List<MenuReviewVO>
	 * @throws Exception
	 */
	List<MenuReviewVO> selectMenuReview(HashMap<String, Object> hashmap) throws Exception;

	/**
	 * 메뉴 정보
	 * @param HashMap
	 * @return MenuVO
	 * @throws Exception
	 */
	MenuVO selectMenu(HashMap<String, Object> hashmap) throws Exception;
	
	/**
	 * 메뉴 저장
	 * @param MenuVO
	 * @return int
	 * @throws Exception
	 */
	int insertMenu(MenuVO menu) throws Exception;
	
	/**
	 * 메뉴 수정
	 * @param HashMap
	 * @return int
	 * @throws Exception
	 */
	int updateMenu(HashMap<String, Object> formdata) throws Exception;

	/**
	 * 메뉴 이미지 경로 저장
	 * @param HashMap
	 * @return int
	 * @throws Exception
	 */
	int insertMenuImg(HashMap<String, Object> hashmap) throws Exception;
	
	/**
	 * 메뉴 카테고리 리스트
	 * @return HashMap
	 * @throws Exception
	 */
	List<HashMap<String, Object>> selectMenuCategory() throws Exception;

	/**
	 * 메뉴 이미지 경로 수정
	 * @param HashMap
	 * @return int
	 * @throws Exception
	 */
	int updateMenuImg(HashMap<String, Object> hashmap) throws Exception;
}