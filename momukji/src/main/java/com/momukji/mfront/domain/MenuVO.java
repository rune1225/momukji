package com.momukji.mfront.domain;

import com.momukji.mfront.domain.model.Menu;

public class MenuVO extends Menu{
	
	//메뉴 카테고리명
	private String mmc_Name;
	
	//메뉴 평점
	private double mm_Score_Sum;
	
	//메뉴 이미지
	private String mmi_Img_Url;
	private String mmi_MenuImg_Name;
	private String mmi_MenuImg_OrgName;
	private String mmi_MenuImg_Thumb;
	
	public String getMmc_Name() {
		return mmc_Name;
	}
	public void setMmc_Name(String mmc_Name) {
		this.mmc_Name = mmc_Name;
	}
	public double getMm_Score_Sum() {
		return mm_Score_Sum;
	}
	public void setMm_Score_Sum(double mm_Score_Sum) {
		this.mm_Score_Sum = mm_Score_Sum;
	}
	public String getMmi_Img_Url() {
		return mmi_Img_Url;
	}
	public void setMmi_Img_Url(String mmi_Img_Url) {
		this.mmi_Img_Url = mmi_Img_Url;
	}
	public String getMmi_MenuImg_Name() {
		return mmi_MenuImg_Name;
	}
	public void setMmi_MenuImg_Name(String mmi_MenuImg_Name) {
		this.mmi_MenuImg_Name = mmi_MenuImg_Name;
	}
	public String getMmi_MenuImg_OrgName() {
		return mmi_MenuImg_OrgName;
	}
	public void setMmi_MenuImg_OrgName(String mmi_MenuImg_OrgName) {
		this.mmi_MenuImg_OrgName = mmi_MenuImg_OrgName;
	}
	public String getMmi_MenuImg_Thumb() {
		return mmi_MenuImg_Thumb;
	}
	public void setMmi_MenuImg_Thumb(String mmi_MenuImg_Thumb) {
		this.mmi_MenuImg_Thumb = mmi_MenuImg_Thumb;
	}
	
}
