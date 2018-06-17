package com.momukji.mfront.domain.model;

public class Restaurant {
	
	//기본정보
	private int mr_No;
	private String mr_Id;
	private String mr_Name;
	private String mr_Position_Lat;
	private String mr_Position_Lng;
	private String mr_Address;
	private String mr_Phone;
	
	//부가정보
	private String mr_PartnerYn;
	private String mr_TvYn;
	private String mr_ParkingYn;
	private String mr_DeliveryYn;
	private String mr_DeliveryArea;
	private String mr_Seat;
	private String mr_OpenTime;
	private String mr_ClosingDay;
	
	private String mr_Insert;
	private String mr_Update;
	private String mr_Introduce;
	
	private int mfc_No;//음식카테고리
	private int mlc1_No;//대지역카테고리
	private int mlc2_No;//소지역카테고리
	
	public int getMr_No() {
		return mr_No;
	}
	public void setMr_No(int mr_No) {
		this.mr_No = mr_No;
	}
	public String getMr_Id() {
		return mr_Id;
	}
	public void setMr_Id(String mr_Id) {
		this.mr_Id = mr_Id;
	}
	public String getMr_Name() {
		return mr_Name;
	}
	public void setMr_Name(String mr_Name) {
		this.mr_Name = mr_Name;
	}
	public String getMr_Position_Lat() {
		return mr_Position_Lat;
	}
	public void setMr_Position_Lat(String mr_Position_Lat) {
		this.mr_Position_Lat = mr_Position_Lat;
	}
	public String getMr_Position_Lng() {
		return mr_Position_Lng;
	}
	public void setMr_Position_Lng(String mr_Position_Lng) {
		this.mr_Position_Lng = mr_Position_Lng;
	}
	public String getMr_Address() {
		return mr_Address;
	}
	public void setMr_Address(String mr_Address) {
		this.mr_Address = mr_Address;
	}
	public String getMr_Phone() {
		return mr_Phone;
	}
	public void setMr_Phone(String mr_Phone) {
		this.mr_Phone = mr_Phone;
	}
	public String getMr_PartnerYn() {
		return mr_PartnerYn;
	}
	public void setMr_PartnerYn(String mr_PartnerYn) {
		this.mr_PartnerYn = mr_PartnerYn;
	}
	public String getMr_TvYn() {
		return mr_TvYn;
	}
	public void setMr_TvYn(String mr_TvYn) {
		this.mr_TvYn = mr_TvYn;
	}
	public String getMr_ParkingYn() {
		return mr_ParkingYn;
	}
	public void setMr_ParkingYn(String mr_ParkingYn) {
		this.mr_ParkingYn = mr_ParkingYn;
	}
	public String getMr_DeliveryYn() {
		return mr_DeliveryYn;
	}
	public void setMr_DeliveryYn(String mr_DeliveryYn) {
		this.mr_DeliveryYn = mr_DeliveryYn;
	}
	public String getMr_DeliveryArea() {
		return mr_DeliveryArea;
	}
	public void setMr_DeliveryArea(String mr_DeliveryArea) {
		this.mr_DeliveryArea = mr_DeliveryArea;
	}
	public String getMr_Seat() {
		return mr_Seat;
	}
	public void setMr_Seat(String mr_Seat) {
		this.mr_Seat = mr_Seat;
	}
	public String getMr_OpenTime() {
		return mr_OpenTime;
	}
	public void setMr_OpenTime(String mr_OpenTime) {
		this.mr_OpenTime = mr_OpenTime;
	}
	public String getMr_ClosingDay() {
		return mr_ClosingDay;
	}
	public void setMr_ClosingDay(String mr_ClosingDay) {
		this.mr_ClosingDay = mr_ClosingDay;
	}
	public String getMr_Insert() {
		return mr_Insert;
	}
	public void setMr_Insert(String mr_Insert) {
		this.mr_Insert = mr_Insert;
	}
	public String getMr_Update() {
		return mr_Update;
	}
	public void setMr_Update(String mr_Update) {
		this.mr_Update = mr_Update;
	}
	public String getMr_Introduce() {
		return mr_Introduce;
	}
	public void setMr_Introduce(String mr_Introduce) {
		this.mr_Introduce = mr_Introduce;
	}
	public int getMfc_No() {
		return mfc_No;
	}
	public void setMfc_No(int mfc_No) {
		this.mfc_No = mfc_No;
	}
	public int getMlc1_No() {
		return mlc1_No;
	}
	public void setMlc1_No(int mlc1_No) {
		this.mlc1_No = mlc1_No;
	}
	public int getMlc2_No() {
		return mlc2_No;
	}
	public void setMlc2_No(int mlc2_No) {
		this.mlc2_No = mlc2_No;
	}
	
}
