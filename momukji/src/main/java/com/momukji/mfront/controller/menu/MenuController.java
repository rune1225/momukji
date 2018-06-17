package com.momukji.mfront.controller.menu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.momukji.mfront.domain.CustomerVO;
import com.momukji.mfront.domain.MenuReviewVO;
import com.momukji.mfront.domain.MenuVO;
import com.momukji.mfront.domain.RestaurantVO;
import com.momukji.mfront.service.menu.MenuService;
import com.momukji.mfront.service.restaurant.RestaurantService;

@RequestMapping(value="/menu")
@Controller
public class MenuController {
	Logger log = Logger.getLogger(this.getClass());

	@Resource(name="menuService")
	private MenuService menuService;
	
	@Resource(name="restaurantService")
	private RestaurantService restaurantService;
	
	/**
	 * 모먹지대표메뉴(NFC진입) 리스트
	 * @param request
	 * @param response
	 * @param session
	 * @param mr_no
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value="/view/{mr_No}")
	public ModelAndView momukjiMenuList(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			@PathVariable(value="mr_No") String mr_No ) throws Exception {
		
		ModelAndView mv = new ModelAndView("/menu/view");
		
		HashMap<String, Object> hashmap = new HashMap<String, Object>();
		
		try {
			
			hashmap.put("mr_no", mr_No);
			
			//업체 방문 카운팅
			if(session.getAttribute("viewcountList") == null){
				
				System.out.println("viewcountList null");
				
				if(0<restaurantService.updateResVisitCount(hashmap)){
				
					ArrayList<Integer> viewcountList = new ArrayList<Integer>();
					
					viewcountList.add(Integer.parseInt(mr_No));
					
					session.setAttribute("viewcountList", viewcountList);
					
					System.out.println("viewcount Listup!:"+viewcountList.size());
					
				}
				
			}else{
				
				@SuppressWarnings("unchecked")
				ArrayList<Integer> viewcountList = (ArrayList<Integer>) session.getAttribute("viewcountList");
				
				boolean countflag = false;
				
				System.out.println("viewcount ListCheck!:"+viewcountList.size());
				
				for(Integer count : viewcountList){
				
					if(count == Integer.parseInt(mr_No)){
						countflag = true;
						break;
					}
					
				}
				
				if(!countflag){
					
					if(0<restaurantService.updateResVisitCount(hashmap)){
						
						viewcountList.add(Integer.parseInt(mr_No));
						
						session.setAttribute("viewcountList", viewcountList);
						
						System.out.println("viewcount Listup!:"+viewcountList.size());
						
					}
					
				}
				
			}
			
			//식당 조회
			List<RestaurantVO> res = restaurantService.selectRestaurant(hashmap);
			//대표메뉴 조회
			List<MenuVO> menuList = menuService.selectMomukjiTypicalMenu(hashmap);
			
			System.out.println(menuList.size());
			
			mv.addObject("res",res.get(0));
			mv.addObject("menuList", menuList);
			
			
			
		} catch (Exception e) {
			log.error("MenuController momukjiMenuList() : " + e.getMessage());
		}
		
		return mv;
	}
	
	/**
	 * 모먹지전체메뉴 리스트
	 * @param request
	 * @param response
	 * @param session
	 * @param mr_no
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value="/entiremenu")
	public ModelAndView entireMenuList(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			@RequestParam(value="mr_No", required=false, defaultValue="")String mr_No,
			@RequestParam(value="ajaxYn", required=false, defaultValue="")String ajaxYn) throws Exception{
		
		System.out.println(mr_No+"/"+ajaxYn);
		
		ModelAndView mv = new ModelAndView("/menu/entiremenu");
	    
	    HashMap<String, Object> hashmap = new HashMap<String, Object>();
	    
		try {
			
			hashmap.put("mr_no", mr_No);
			
			//모먹지나우 식당 조회
			List<MenuVO> menuList = menuService.selectMomukjiEntireMenu(hashmap);
			
			mv.addObject("menuList", menuList);
			
		} catch (Exception e) {
			log.error("MenuController entireMenuList() : " + e.getMessage());
		}
	    
	    return mv;
	    
	}
	
	/**
	 * 모먹지 메뉴 리뷰 리스트
	 * @param request
	 * @param response
	 * @param session
	 * @param mr_no
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value="/menureview")
	public ModelAndView menuReviewList(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			@RequestParam(value="mr_No", required=false, defaultValue="")String mr_No,
			@RequestParam(value="mm_No", required=false, defaultValue="")String mm_No,
			@RequestParam(value="page", required=false, defaultValue="")String page) throws Exception{
		
		int limit = 10;
		
		
		ModelAndView mv = new ModelAndView("/menu/menureview");
	    
		HashMap<String, Object> hashmap = new HashMap<String, Object>();
	    
		try {
			
			hashmap.put("mr_no", mr_No);
			hashmap.put("mm_no", mm_No);
			hashmap.put("limit", limit);
			hashmap.put("page", limit*Integer.parseInt(page));
			
			//모먹지나우 식당 조회
			List<MenuReviewVO> menureviewList = menuService.selectMenuReview(hashmap);
			
			//System.out.println(menureviewList.size());
			
			mv.addObject("menureviewList", menureviewList);
			mv.addObject("page", page);
			if(session.getAttribute("customerVO") != null)
				mv.addObject("sessionflag", "true");
			
			
		} catch (Exception e) {
			log.error("MenuController menuReviewList() : " + e.getMessage());
		}
	    
	    return mv;
	    
	}
	
	/**
	 * 전체메뉴명 리스트 json
	 * @param request
	 * @param response
	 * @param session
	 * @param mr_no
	 * @return json
	 * @throws Exception
	 */
	@RequestMapping(value="/menunamelist")
	@ResponseBody
	public HashMap<String, Object> menuNameList(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			@RequestParam(value="mr_No", required=false, defaultValue="")String mr_No) throws Exception {	
		
		HashMap<String, Object> responseJSON = new HashMap<String, Object>();
		
		//http://www.nextree.co.kr/p11205/ json 리턴 참고
		
		try {
			
			HashMap<String, Object> hashmap = new HashMap<String, Object>();
			
			hashmap.put("mr_no", mr_No);
			
			//모먹지나우 식당 조회
			List<MenuVO> menuList = menuService.selectMomukjiEntireMenu(hashmap);
			
			System.out.println("메뉴명리스트"+menuList.size());
			
			for (MenuVO menu : menuList){
				responseJSON.put(Integer.toString(menu.getMm_No()), menu.getMm_Name());
			}
			
		} catch (Exception e) {
			log.error("MenuController menuNameList() : " + e.getMessage());
		}
		
		response.setContentType("application/json;charset=UTF-8");
		
		return responseJSON;
	}
	
	/**
	 * 메뉴리뷰 저장
	 * @param request
	 * @param response
	 * @param session
	 * @param mr_no
	 * @return json
	 * @throws Exception
	 */
	@RequestMapping(value="/insertmenureview")
	@ResponseBody
	public HashMap<String, Object> insertMenuReview(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			@RequestParam(value="mm_No", required=false, defaultValue="")String mm_No,
			@RequestParam(value="mmr_Score", required=false, defaultValue="")String mmr_Score,
			@RequestParam(value="mmr_Contents", required=false, defaultValue="")String mmr_Contents) throws Exception {	
		
		HashMap<String, Object> responseJSON = new HashMap<String, Object>();
		
		try {
			
			MenuReviewVO review = new MenuReviewVO();
			
			CustomerVO cus = (CustomerVO)session.getAttribute("customerVO");
			
			System.out.println("세션:"+cus.getMc_Name()+"/"+cus.getMc_No());
			
			review.setMc_No(cus.getMc_No());
			review.setMm_No(Integer.parseInt(mm_No));
			review.setMmr_Score(Double.parseDouble(mmr_Score));
			review.setMmr_Contents(mmr_Contents);
			
			System.out.println(mmr_Contents);
			
			responseJSON.put("result", menuService.insertMenuReview(review));
			
			System.out.println("result:"+responseJSON.get("result"));
			
		} catch (Exception e) {
			log.error("MenuController insertMenuReview() : " + e.getMessage());
		}
		
		response.setContentType("application/json;charset=UTF-8");
		
		return responseJSON;
	}
	
	/**
	 * 메뉴 카테고리 리스트
	 * @param request
	 * @param response
	 * @param session
	 * @return json
	 * @throws Exception
	 */
	@RequestMapping(value="/menucategorylist")
	@ResponseBody
	public HashMap<String, Object> menuCategoryList(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {	
		
		HashMap<String, Object> responseJSON = new HashMap<String, Object>();
		
		try {
			
			for(HashMap<String, Object> maplist : menuService.selectMenuCategory()){
				responseJSON.put(maplist.get("MMC_NO").toString(), maplist.get("MMC_NAME"));
			}
			
			System.out.println("result:"+responseJSON.size());
			
		} catch (Exception e) {
			log.error("MenuController menuCategoryList() : " + e.getMessage());
		}
		
		response.setContentType("application/json;charset=UTF-8");
		
		return responseJSON;
	}
	
	
}
