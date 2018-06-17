package com.momukji.mfront.domain;

import com.momukji.mfront.domain.model.Customer;

public class CustomerVO extends Customer{
	
	//즐겨찾기
	private String mfc_FavoriteType;
	private String mr_No;
	private String mr_Name;
	
	public String getMfc_FavoriteType() {
		return mfc_FavoriteType;
	}
	public void setMfc_FavoriteType(String mfc_FavoriteType) {
		this.mfc_FavoriteType = mfc_FavoriteType;
	}
	public String getMr_No() {
		return mr_No;
	}
	public void setMr_No(String mr_No) {
		this.mr_No = mr_No;
	}
	public String getMr_Name() {
		return mr_Name;
	}
	public void setMr_Name(String mr_Name) {
		this.mr_Name = mr_Name;
	}
	
}
