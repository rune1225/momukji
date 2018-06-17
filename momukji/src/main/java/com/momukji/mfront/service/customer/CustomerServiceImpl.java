package com.momukji.mfront.service.customer;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.momukji.common.util.ParamMap;
import com.momukji.mfront.dao.CustomerDAO;
import com.momukji.mfront.domain.CustomerVO;

@Service("customerService")
public class CustomerServiceImpl implements CustomerService{
	
	@Resource(name="customerDAO")
    private CustomerDAO customerDAO;
	
	public int joinSave(CustomerVO cus) throws Exception {
		return customerDAO.joinSave(cus);
	}
	
	public List<CustomerVO> selectCustomer(String mc_Email) throws Exception {
		return customerDAO.selectCustomer(mc_Email);
	}
	
	public int duplicationEmailCheck(String mc_Email) throws Exception {
		return customerDAO.duplicationEmailCheck(mc_Email);
	}
	
	public int updateRestaurant(CustomerVO res) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	public List<CustomerVO> selectCustomerListbySearch(ParamMap paramMap) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
