package com.axiTraining.stockcalculation.database.table_entity;

import com.google.gson.annotations.SerializedName;

public class InvoiceEntity{

	@SerializedName("UID")
	private int uID;

	@SerializedName("Total Price")
	private double totalPrice;

	@SerializedName("Qty")
	private int qty;

	@SerializedName("Date")
	private String date;

	public void setUID(int uID){
		this.uID = uID;
	}

	public int getUID(){
		return uID;
	}

	public void setTotalPrice(double totalPrice){
		this.totalPrice = totalPrice;
	}

	public double getTotalPrice(){
		return totalPrice;
	}

	public void setQty(int qty){
		this.qty = qty;
	}

	public int getQty(){
		return qty;
	}

	public void setDate(String date){
		this.date = date;
	}

	public String getDate(){
		return date;
	}
}