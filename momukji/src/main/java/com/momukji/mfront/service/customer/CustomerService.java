package com.momukji.mfront.service.customer;

import java.util.List;

import com.momukji.common.util.ParamMap;
import com.momukji.mfront.domain.CustomerVO;

public interface CustomerService {
	
	/**
	 * 회원가입 - 저장
	 * @param CustomerVO
	 * @return Integer
	 * @throws Exception
	 */
	int joinSave(CustomerVO customerVO) throws Exception;
	
	/**
	 * 회원정보update
	 * @param RestaurantVO
	 * @return Integer
	 * @throws Exception
	 */
	int updateRestaurant(CustomerVO cus) throws Exception;
	
	/**
	 * 회원검색list
	 * @param paramMap
	 * @return ModelAndView
	 * @throws Exception
	 */
	List<CustomerVO> selectCustomerListbySearch(ParamMap paramMap) throws Exception;

	/**
	 * 로그인 회원정보 비교
	 * @param paramMap
	 * @return ModelAndView
	 * @throws Exception
	 */
	List<CustomerVO> selectCustomer(String mc_Email) throws Exception;

	/**
	 * EMAIL 중복 체크
	 * @param String
	 * @return Integer
	 * @throws Exception
	 */
	int duplicationEmailCheck(String mc_Email) throws Exception;

}