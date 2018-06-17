package com.momukji.mfront.controller.now;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.momukji.mfront.domain.RestaurantVO;
import com.momukji.mfront.service.now.NowService;

@Controller
public class NowController {
	Logger log = Logger.getLogger(this.getClass());

	@Resource(name="nowService")
	private NowService nowService;
	
	/**
	 * 모먹지나우리스트
	 * @param request
	 * @param response	 
	 * @param session
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value="/now/main")
	public ModelAndView nowMain(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception{
		
		ModelAndView mv = new ModelAndView("/now/main");
		
	    return mv;
	    
	}
	
	/**
	 * 모먹지나우리스트_비동기
	 * @param request
	 * @param response
	 * @param session
	 * @param nowLat
	 * @param nowLng
	 * @param sort
	 * @param mfc_no
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value="/now/ajax")
	public ModelAndView nowAjax(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			@RequestParam(value="nowlat", required=false, defaultValue="")String nowlat,
			@RequestParam(value="nowlng", required=false, defaultValue="")String nowlng,
			@RequestParam(value="mainsort", required=false, defaultValue="")String mainsort,
			@RequestParam(value="mfc_no", required=false, defaultValue="")String mfc_no,
			@RequestParam(value="page", required=false, defaultValue="")int page) throws Exception{
		
		ModelAndView mv = new ModelAndView("/now/ajax");
	    
	    HashMap<String, Object> hashmap = new HashMap<String, Object>();
	    
	    //페이징 처리 리스트 수
	    hashmap.put("limit", 10);

	    page=page*10;
	    
	    if(mainsort.equals(""))
	    	mainsort="1";
	    
		try {
			
			hashmap.put("nowlat", nowlat);
			hashmap.put("nowlng", nowlng);
			hashmap.put("mainsort", mainsort);
			hashmap.put("mfc_no", mfc_no);
			hashmap.put("page", page);
			
			//모먹지나우 식당 조회
			List<RestaurantVO> restaurantList = nowService.selectMomukjiNow(hashmap);
			
			session.setAttribute("nowlat", nowlat);
			session.setAttribute("nowlng", nowlng);
			
			mv.addObject("restaurantList", restaurantList);
			mv.addObject("page", page);
			
			System.out.println("리스트사이즈:"+restaurantList.size());
			
		} catch (Exception e) {
			log.error("MomukjiNowController nowAjax() : " + e.getMessage());
		}
	    
	    return mv;
	    
	}
	
	/**
	 * 모먹지나우지도_비동기
	 * @param request
	 * @param response
	 * @param session
	 * @param nowLat
	 * @param nowLng
	 * @param sort
	 * @param mfc_no
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value="/now/map")
	public ModelAndView nowMap(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			@RequestParam(value="nowlat", required=false, defaultValue="")String nowlat,
			@RequestParam(value="nowlng", required=false, defaultValue="")String nowlng,
			@RequestParam(value="mr_no", required=false, defaultValue="")String mr_no) throws Exception{
		
		ModelAndView mv = new ModelAndView("/now/map");
	    
	    HashMap<String, Object> hashmap = new HashMap<String, Object>();
	    
		try {
			
			hashmap.put("nowlat", nowlat);
			hashmap.put("nowlng", nowlng);
			hashmap.put("mr_no", mr_no);
			
			//모먹지나우 지도보기 식당 조회
			List<RestaurantVO> restaurantList = nowService.selectMomukjiNowMap(hashmap);
			
			System.out.println("주소:"+restaurantList.get(0).getMr_Address());
			
			mv.addObject("restaurantList", restaurantList);
			
		} catch (Exception e) {
			log.error("MomukjiNowController nowMap() : " + e.getMessage());
		}
	    
	    return mv;
	    
	}
	
}
