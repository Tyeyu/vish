package com.example.vis.entitys;

public class CalendarNodeAll {
	private String Address;
	private String Count;
	private String Date;
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	public String getCount() {
		return Count;
	}
	public void setCount(String count) {
		Count = count;
	}
	public String getDate() {
		return Date;
	}
	public void setDate(String date) {
		Date = date;
	}
	@Override
	public String toString() {
		return "CalendarNodeAll [Address=" + Address + ", Count=" + Count + ", Date=" + Date + "]";
	}
	
}
