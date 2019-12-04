package com.example.vis.entitys;

public class studentInfo {
	private String CardNo;//校园卡号
	private String Sex;//性别
	private String Major;//专业名称
	private String AccessCardNo;//门禁卡号
	public String getCardNo() {
		return CardNo;
	}
	public void setCardNo(String cardNo) {
		CardNo = cardNo;
	}
	public String getSex() {
		return Sex;
	}
	public void setSex(String sex) {
		Sex = sex;
	}
	public String getMajor() {
		return Major;
	}
	public void setMajor(String major) {
		Major = major;
	}
	public String getAccessCardNo() {
		return AccessCardNo;
	}
	public void setAccessCardNo(String accessCardNo) {
		AccessCardNo = accessCardNo;
	}
	@Override
	public String toString() {
		return "studentInfo [CardNo=" + CardNo + ", Sex=" + Sex + ", Major=" + Major + ", AccessCardNo=" + AccessCardNo
				+ "]";
	}
	
}
