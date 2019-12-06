package com.example.vis.entitys;

public class Calendardataone {
	private String CardNo;
	private String Date;
	private String Address;
	
	public String getCardNo() {
		return CardNo;
	}
	public void setCardNo(String cardNo) {
		this.CardNo = cardNo;
	}
	public String getDate() {
		return Date;
	}
	public void setDate(String date) {
		this.Date = date;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		this.Address = address;
	}
	@Override
	public String toString() {
		return "Calendardataone [CardNo=" + CardNo + ", Date=" + Date + ", Address=" + Address + "]";
	}
	
}
