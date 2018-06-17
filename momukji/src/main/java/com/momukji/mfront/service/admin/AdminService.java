package com.momukji.mfront.service.admin;

import java.util.List;

import com.momukji.mfront.domain.CustomerVO;

public interface AdminService {
	
	/**
	 * 회원가입 - 저장
	 * @param CustomerVO
	 * @return Integer
	 * @throws Exception
	 */
	int joinAdminSave(CustomerVO customerVO) throws Exception;

	/**
	 * 로그인 어드민 회원정보 비교
	 * @param paramMap
	 * @return ModelAndView
	 * @throws Exception
	 */
	List<CustomerVO> selectAdmin(String mc_Email) throws Exception;

}