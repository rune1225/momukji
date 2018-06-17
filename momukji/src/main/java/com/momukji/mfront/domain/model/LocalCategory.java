package com.momukji.mfront.domain.model;

public class LocalCategory {
	private int mlc1_No; //대지역카테고리
	private int mlc2_No; //소지역카테고리

	private String mlc1_Name; //대지역카테고리명
	private String mlc2_Name; //소지역카테고리명

	private int mlc2_Count;

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

	public int getMlc2_Count() {
		return mlc2_Count;
	}

	public void setMlc2_Count(int mlc2_Count) {
		this.mlc2_Count = mlc2_Count;
	}

}
