package com.momukji.mfront.service.admin;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.momukji.mfront.dao.AdminDAO;
import com.momukji.mfront.dao.CustomerDAO;
import com.momukji.mfront.domain.CustomerVO;

@Service("adminService")
public class AdminServiceImpl implements AdminService{
	
	@Resource(name="adminDAO")
    private AdminDAO adminDAO;
	
	@Resource(name="customerDAO")
    private CustomerDAO customerDAO;

	public int joinAdminSave(CustomerVO customerVO) throws Exception {
		// TODO Auto-generated method stub
		return customerDAO.joinSave(customerVO);
	}

	public List<CustomerVO> selectAdmin(String mc_Email) throws Exception {
		// TODO Auto-generated method stub
		return customerDAO.selectCustomer(mc_Email);
	}
	
}
