package com.momukji.mfront.controller.restaurant;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;

import com.momukji.mfront.service.restaurant.RestaurantService;

@Controller
public class RestaurantController {
	Logger log = Logger.getLogger(this.getClass());

	@Resource(name="restaurantService")
    private RestaurantService restaurantService;
	
	/**
	 * 식당정보insert
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	
	/**
	 * 식당정보update
	 * @param request
	 * @param response	 
	 * @param session
	 * @return ModelAndView
	 * @throws Exception
	 */
	
	/**
	 * 식당정보delete
	 * @param request
	 * @param response	 
	 * @param session
	 * @return ModelAndView
	 * @throws Exception
	 */
	
	/**
	 * 식장검색list
	 * @param request
	 * @param response	 
	 * @param session
	 * @return ModelAndView
	 * @throws Exception
	 */
	
	
}
