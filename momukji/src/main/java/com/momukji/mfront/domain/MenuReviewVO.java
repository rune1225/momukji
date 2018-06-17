package com.momukji.mfront.domain;

import com.momukji.mfront.domain.model.MenuReview;

public class MenuReviewVO extends MenuReview{
	
	//작성자명
	private String mc_Name;
	
	//메뉴명
	private String mm_Name;
	
	//메뉴 이미지
	private String mmi_Img_Url;
	private String mmi_MenuImg_Name;
	private String mmi_MenuImg_Thumb;
	
	public String getMc_Name() {
		return mc_Name;
	}
	public void setMc_Name(String mc_Name) {
		this.mc_Name = mc_Name;
	}
	public String getMm_Name() {
		return mm_Name;
	}
	public void setMm_Name(String mm_Name) {
		this.mm_Name = mm_Name;
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
	public String getMmi_MenuImg_Thumb() {
		return mmi_MenuImg_Thumb;
	}
	public void setMmi_MenuImg_Thumb(String mmi_MenuImg_Thumb) {
		this.mmi_MenuImg_Thumb = mmi_MenuImg_Thumb;
	}

}
