package com.example.vis.entitys;
//门禁记录
public class AccessRecord {
	private String AccessCarDNo;
	private String Date;
	private String Address;
	private String Access;
	private String Describe;
	public String getAccessCarDNo() {
		return AccessCarDNo;
	}
	public void setAccessCarDNo(String accessCarDNo) {
		AccessCarDNo = accessCarDNo;
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
	public String getAccess() {
		return Access;
	}
	public void setAccess(String access) {
		Access = access;
	}
	public String getDescribe() {
		return Describe;
	}
	public void setDescribe(String describe) {
		Describe = describe;
	}
	@Override
	public String toString() {
		return "AccessRecord [AccessCarDNo=" + AccessCarDNo + ", Date=" + Date + ", Address=" + Address + ", Access="
				+ Access + ", Describe=" + Describe + "]";
	}
	
}
