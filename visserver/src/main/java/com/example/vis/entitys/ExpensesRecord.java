package com.example.vis.entitys;
//消费记录
public class ExpensesRecord {
	private String CardNo;
	private String peoNo;
	private String Date;
	private String Money;
	private String FundMoney;
	private String Surplus;
	private String CardCount;
	private String Type;
	private String TermNo;
	private String OperNo;
	private String Dept;
	public String getCardNo() {
		return CardNo;
	}
	public void setCardNo(String cardNo) {
		CardNo = cardNo;
	}
	public String getPeoNo() {
		return peoNo;
	}
	public void setPeoNo(String peoNo) {
		this.peoNo = peoNo;
	}
	public String getDate() {
		return Date;
	}
	public void setDate(String date) {
		Date = date;
	}
	public String getMoney() {
		return Money;
	}
	public void setMoney(String money) {
		Money = money;
	}
	public String getFundMoney() {
		return FundMoney;
	}
	public void setFundMoney(String fundMoney) {
		FundMoney = fundMoney;
	}
	public String getSurplus() {
		return Surplus;
	}
	public void setSurplus(String surplus) {
		Surplus = surplus;
	}
	public String getCardCount() {
		return CardCount;
	}
	public void setCardCount(String cardCount) {
		CardCount = cardCount;
	}
	public String getType() {
		return Type;
	}
	public void setType(String type) {
		Type = type;
	}
	public String getTermNo() {
		return TermNo;
	}
	public void setTermNo(String termNo) {
		TermNo = termNo;
	}
	public String getOperNo() {
		return OperNo;
	}
	public void setOperNo(String operNo) {
		OperNo = operNo;
	}
	public String getDept() {
		return Dept;
	}
	public void setDept(String dept) {
		Dept = dept;
	}
	@Override
	public String toString() {
		return "ExpensesRecord [CardNo=" + CardNo + ", peoNo=" + peoNo + ", Date=" + Date + ", Money=" + Money
				+ ", FundMoney=" + FundMoney + ", Surplus=" + Surplus + ", CardCount=" + CardCount + ", Type=" + Type
				+ ", TermNo=" + TermNo + ", OperNo=" + OperNo + ", Dept=" + Dept + "]";
	}
	
}
