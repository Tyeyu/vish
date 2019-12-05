package com.example.vis.entitys;

public class Calendardataone {
	private String CardNo;
	private String Date;
	private String Dept;
	
	public String getCardNo() {
		return CardNo;
	}
	public void setCardNo(String cardNo) {
		CardNo = cardNo;
	}
	public String getDate() {
		return Date;
	}
	public void setDate(String date) {
		Date = date;
	}
	public String getDept() {
		return Dept;
	}
	public void setDept(String dept) {
		Dept = dept;
	}
	@Override
	public String toString() {
		return "Calendardataone [CardNo=" + CardNo + ", Date=" + Date + ", Dept=" + Dept + "]";
	}
	
}
