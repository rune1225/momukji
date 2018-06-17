package com.momukji.mfront.controller.travel;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.momukji.mfront.domain.RestaurantVO;
import com.momukji.mfront.domain.model.LocalCategory;
import com.momukji.mfront.service.now.NowService;
import com.momukji.mfront.service.travel.TravelService;

@RequestMapping(value="/travel")
@Controller
public class TravelController {
	Logger log = Logger.getLogger(this.getClass());

	@Resource(name="travelService")
	private TravelService travelService;
	
	@Resource(name="nowService")
	private NowService nowService;
	
	/**
	 * 모먹지여행리스트
	 * @param request
	 * @param response	 
	 * @param session
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value="/category")
	public ModelAndView momukjiTravelMain(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception{
		
		ModelAndView mv = new ModelAndView("/travel/category");
		
		HashMap<String, Object> hashmap = new HashMap<String, Object>();
		
		//모먹지나우 식당 조회
		List<LocalCategory> localcategoryList = travelService.selectLocalCategoryList(hashmap);
		
		
		
		int temp=0;
		int count_temp=0;
		
		for (LocalCategory lc : localcategoryList){
			if(temp!=lc.getMlc1_No()){
				temp = lc.getMlc1_No();
				count_temp = lc.getMlc2_Count();
				
				hashmap.put(Integer.toString(lc.getMlc1_No()), count_temp);
				
			}else if(temp == lc.getMlc1_No()){
				count_temp += lc.getMlc2_Count();
			}
			
		}
		
		System.out.println("카테고리리스트:"+localcategoryList.size()+"/해시맵"+hashmap.size());
		
		mv.addObject("localcategoryList", localcategoryList);
		mv.addObject("countMap", hashmap);
		
	    return mv;
	    
	}
	
	/**
	 * 모먹지여행메인
	 * @param request
	 * @param response
	 * @param session
	 * @param mlc1_no
	 * @param mlc2_no
	 * @param mfc_no
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value="/main")
	public ModelAndView travelMain(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			@RequestParam(value="mlc1_no", required=false, defaultValue="")String mlc1_no,
			@RequestParam(value="mlc2_no", required=false, defaultValue="")String mlc2_no,
			@RequestParam(value="mfc_no", required=false, defaultValue="")String mfc_no) throws Exception{
		
		ModelAndView mv = new ModelAndView("/travel/main");
	    
	    HashMap<String, Object> hashmap = new HashMap<String, Object>();
	    
	    System.out.println("mlc1_no:"+mlc1_no+"mlc2_no:"+mlc2_no);
	    
	    //페이징 처리 리스트 수
	    hashmap.put("limit", 10);

		try {
			
			hashmap.put("mlc1_no", mlc1_no);
			hashmap.put("mlc2_no", mlc2_no);
			hashmap.put("mfc_no", 1);
			hashmap.put("page", 0);
			
			//모먹지나우 식당 조회
			List<RestaurantVO> restaurantList = travelService.selectMomukjiTravel(hashmap);
			
			//paramMap.put("custNo", userSession.getCustNo());
			//String pushYn = displayService.selectPushYn(userSession.getCustNo());
			
			mv.addObject("restaurantList", restaurantList);
			
			System.out.println("리스트사이즈:"+restaurantList.size());
			
		} catch (Exception e) {
			log.error("MomukjiTravelController travelMain() : " + e.getMessage());
		}
	    
	    return mv;
	    
	}
	
	/**
	 * 모먹지여행 페이징
	 * @param request
	 * @param response
	 * @param session
	 * @param mlc1_no
	 * @param mlc2_no
	 * @param mfc_no
	 * @param page
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value="/ajax")
	public ModelAndView travelMain(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			@RequestParam(value="mlc1_no", required=false, defaultValue="")String mlc1_no,
			@RequestParam(value="mlc2_no", required=false, defaultValue="")String mlc2_no,
			@RequestParam(value="mfc_no", required=false, defaultValue="")String mfc_no,
			@RequestParam(value="page", required=false, defaultValue="")int page) throws Exception{
		
		ModelAndView mv = new ModelAndView("/travel/main");
	    
	    HashMap<String, Object> hashmap = new HashMap<String, Object>();
	    
	    System.out.println("mlc1_no:"+mlc1_no+"mlc2_no:"+mlc2_no);
	    
	    //페이징 처리 리스트 수
	    hashmap.put("limit", 10);
	    
	    page = page * 10;

		try {
			
			hashmap.put("mlc1_no", mlc1_no);
			hashmap.put("mlc2_no", mlc2_no);
			hashmap.put("mfc_no", mfc_no);
			hashmap.put("page", page);
			
			//모먹지나우 식당 조회
			List<RestaurantVO> restaurantList = travelService.selectMomukjiTravel(hashmap);
			
			//paramMap.put("custNo", userSession.getCustNo());
			//String pushYn = displayService.selectPushYn(userSession.getCustNo());
			
			mv.addObject("restaurantList", restaurantList);
			
			System.out.println("리스트사이즈:"+restaurantList.size());
			
		} catch (Exception e) {
			log.error("MomukjiTravelController travelMain() : " + e.getMessage());
		}
	    
	    return mv;
	    
	}
	
	/**
	 * 소분류 지역명 리스트 json
	 * @param request
	 * @param response
	 * @param session
	 * @param mlc1_no
	 * @return json
	 * @throws Exception
	 */
	@RequestMapping(value="/localcategorylist")
	@ResponseBody
	public HashMap<String, Object> localCategoryList(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			@RequestParam(value="mlc1_no", required=false, defaultValue="")String mlc1_no) throws Exception {	
		
		HashMap<String, Object> responseJSON = new HashMap<String, Object>();
		
		try {
			
			HashMap<String, Object> hashmap = new HashMap<String, Object>();
			
			hashmap.put("mlc1_no", mlc1_no);
			
			//모먹지나우 식당 조회
			List<LocalCategory> localcategoryList = travelService.selectLocalCategoryList(hashmap);
			
			System.out.println("카테고리리스트"+localcategoryList.size());
			
			for (LocalCategory lc : localcategoryList){
				responseJSON.put(Integer.toString(lc.getMlc2_No()), lc.getMlc2_Name());
			}
			
		} catch (Exception e) {
			log.error("TravelController localCategoryList() : " + e.getMessage());
		}
		
		response.setContentType("application/json;charset=UTF-8");
		
		return responseJSON;
	}
	
}
