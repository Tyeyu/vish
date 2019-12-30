package com.example.vis.entitys;

public class CalendarStudent {
	private String CardNo;
	private String Date;
	private String Major;
	private String Address;
	private String Sex;
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
	public String getMajor() {
		return Major;
	}
	public void setMajor(String major) {
		Major = major;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	public String getSex() {
		return Sex;
	}
	public void setSex(String sex) {
		Sex = sex;
	}
	@Override
	public String toString() {
		return "CalendarStudent [CardNo=" + CardNo + ", Date=" + Date + ", Major=" + Major + ", Address=" + Address
				+ ", Sex=" + Sex + "]";
	}
	
}
