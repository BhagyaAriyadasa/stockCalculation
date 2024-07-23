package com.axiTraining.stockcalculation.database.table_entity;

import com.google.gson.annotations.SerializedName;

public class DiscountEntity{

	@SerializedName("UID")
	private int uID;

	@SerializedName("Discount Qty")
	private int discountQty;

	@SerializedName("Break Qty")
	private int breakQty;

	@SerializedName("Upper Qty")
	private int upperQty;

	public DiscountEntity(int uid, int discountQty, int breakQty, int upperQty) {
		this.uID = uid;
		this.discountQty = discountQty;
		this.breakQty = breakQty;
		this.upperQty = upperQty;
	}

	public void setUID(int uID){
		this.uID = uID;
	}

	public int getUID(){
		return uID;
	}

	public void setDiscountQty(int discountQty){
		this.discountQty = discountQty;
	}

	public int getDiscountQty(){
		return discountQty;
	}

	public void setBreakQty(int breakQty){
		this.breakQty = breakQty;
	}

	public int getBreakQty(){
		return breakQty;
	}

	public void setUpperQty(int upperQty){
		this.upperQty = upperQty;
	}

	public int getUpperQty(){
		return upperQty;
	}
}