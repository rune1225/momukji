package com.momukji.mfront.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.momukji.common.dao.AbstractDAO;
import com.momukji.mfront.domain.CustomerVO;

@SuppressWarnings("unchecked")
@Repository("adminDAO")
public class AdminDAO extends AbstractDAO{
    
	/**
	 * 고객정보
	 * @param paramMap
	 * @return HashMap<String, Object>
	 */
	public List<Map<String, Object>> selectCustomerListbySearch(Map<String, Object> map) throws Exception{
        return (List<Map<String, Object>>)selectList("front.customer.selectCustomerListbySearch", map);
    }

	/**
	 * 고객정보 저장
	 * @param cus
	 * @return int
	 */
	public int joinSave(CustomerVO cus) {
		return (Integer) insert("front.customer.insertCustomer", cus);
	}
	
	/**
	 * 고객정보 조회
	 * @param mc_No
	 * @return CustomerVO
	 */
	public List<CustomerVO> selectCustomer(String mc_Email) {
		// TODO Auto-generated method stub
		return (List<CustomerVO>) selectList("front.customer.selectCustomer", mc_Email);
	}

	/**
	 * EMAIL 중복 체크
	 * @param mc_No
	 * @return CustomerVO
	 */
	public int duplicationEmailCheck(String mc_Email) {
		return (Integer) selectByPk("front.customer.duplicationEmailCheck", mc_Email);
	}
	 
}