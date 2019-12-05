package com.example.vis.entitys;

public class Calendardatatwo {
	private String AccessCardNo;
	private String Access;
	private String CardNo;
	private String Date;
	private String Address;
	public String getAccessCardNo() {
		return AccessCardNo;
	}
	public void setAccessCardNo(String accessCardNo) {
		AccessCardNo = accessCardNo;
	}
	public String getAccess() {
		return Access;
	}
	public void setAccess(String access) {
		Access = access;
	}
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
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	@Override
	public String toString() {
		return "Calendardatatwo [AccessCardNo=" + AccessCardNo + ", Access=" + Access + ", CardNo=" + CardNo + ", Date="
				+ Date + ", Address=" + Address + "]";
	}
	
}
