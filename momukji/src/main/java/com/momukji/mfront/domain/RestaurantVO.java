package com.momukji.mfront.domain;

import com.momukji.mfront.domain.model.Restaurant;

public class RestaurantVO extends Restaurant{
	
	//시퀀스
	private int rowNum;
	
	//지역카테고리
	private String mlc1_Name;
	private String mlc2_Name;
	
	//음식카테고리이미지명
	private String mfc_Name;
	private String mfc_Img_Name;
	
	//식당이미지
	private String mri_Img_Url;
	private String mri_ResImg_Name;
	
	//대표메뉴
	private char mm_Type;
	private String mm_Name;
	
	//대표메뉴이미지
	private String mmi_Img_Url;
	private String mmi_MenuImg_Name;
	private String mmi_MenuImg_Thumb;
	
	//거리차
	private double distance;
	
	//평점합
	private int mr_Score_Sum;
	
	//방문카운트
	private int mrvc_todayCount;
	private int mrvc_totalCount;
	
	public int getRowNum() {
		return rowNum;
	}
	public void setRowNum(int rowNum) {
		this.rowNum = rowNum;
	}
	public String getMlc1_Name() {
		return mlc1_Name;
	}
	public void setMlc1_Name(String mlc1_Name) {
		this.mlc1_Name = mlc1_Name;
	}
	public String getMlc2_Name() {
		return mlc2_Name;
	}
	public void setMlc2_Name(String mlc2_Name) {
		this.mlc2_Name = mlc2_Name;
	}
	public String getMfc_Name() {
		return mfc_Name;
	}
	public void setMfc_Name(String mfc_Name) {
		this.mfc_Name = mfc_Name;
	}
	public String getMfc_Img_Name() {
		return mfc_Img_Name;
	}
	public void setMfc_Img_Name(String mlc_Img_Name) {
		this.mfc_Img_Name = mlc_Img_Name;
	}
	public String getMri_Img_Url() {
		return mri_Img_Url;
	}
	public void setMri_Img_Url(String mri_Img_Url) {
		this.mri_Img_Url = mri_Img_Url;
	}
	public String getMri_ResImg_Name() {
		return mri_ResImg_Name;
	}
	public void setMri_ResImg_Name(String mri_ResImg_Name) {
		this.mri_ResImg_Name = mri_ResImg_Name;
	}
	public char getMm_Type() {
		return mm_Type;
	}
	public void setMm_Type(char mm_Type) {
		this.mm_Type = mm_Type;
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
	public double getDistance() {
		return distance;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}
	public int getMr_Score_Sum() {
		return mr_Score_Sum;
	}
	public void setMr_Score_Sum(int mr_Score_Sum) {
		this.mr_Score_Sum = mr_Score_Sum;
	}
	public int getMrvc_todayCount() {
		return mrvc_todayCount;
	}
	public void setMrvc_todayCount(int mrvc_todayCount) {
		this.mrvc_todayCount = mrvc_todayCount;
	}
	public int getMrvc_totalCount() {
		return mrvc_totalCount;
	}
	public void setMrvc_totalCount(int mrvc_totalCount) {
		this.mrvc_totalCount = mrvc_totalCount;
	}

}
