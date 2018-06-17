package com.momukji.mfront.domain.model;

import java.sql.Timestamp;

public class MenuReview {
	
	private int mmr_No;
	private double mmr_Score;
	private String mmr_Contents;
	private int mm_No;
	private int mc_No;
	private Timestamp mmr_Insert;
	private Timestamp mmr_Update;
	
	public int getMmr_No() {
		return mmr_No;
	}
	public void setMmr_No(int mmr_No) {
		this.mmr_No = mmr_No;
	}
	public double getMmr_Score() {
		return mmr_Score;
	}
	public void setMmr_Score(double mmr_Score) {
		this.mmr_Score = mmr_Score;
	}
	public String getMmr_Contents() {
		return mmr_Contents;
	}
	public void setMmr_Contents(String mmr_Contents) {
		this.mmr_Contents = mmr_Contents;
	}
	public int getMm_No() {
		return mm_No;
	}
	public void setMm_No(int mm_No) {
		this.mm_No = mm_No;
	}
	public int getMc_No() {
		return mc_No;
	}
	public void setMc_No(int mc_No) {
		this.mc_No = mc_No;
	}
	public Timestamp getMmr_Insert() {
		return mmr_Insert;
	}
	public void setMmr_Insert(Timestamp mmr_Insert) {
		this.mmr_Insert = mmr_Insert;
	}
	public Timestamp getMmr_Update() {
		return mmr_Update;
	}
	public void setMmr_Update(Timestamp mmr_Update) {
		this.mmr_Update = mmr_Update;
	}
	 
}
