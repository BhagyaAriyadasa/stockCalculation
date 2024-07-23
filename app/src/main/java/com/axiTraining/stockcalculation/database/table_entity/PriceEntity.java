package com.axiTraining.stockcalculation.database.table_entity;

import com.google.gson.annotations.SerializedName;

public class PriceEntity{

	@SerializedName("UID")
	private int uID;

	@SerializedName("ItemUID")
	private int itemUID;

	@SerializedName("MRP")
	private double mRP;

	@SerializedName("RPL")
	private double rPL;

	public PriceEntity(int uid, int itemUID, double mrp, double rpl) {
		this.uID = uid;
		this.itemUID=itemUID;
		this.mRP = mrp;
		this.rPL = rpl;
	}

	public void setUID(int uID){
		this.uID = uID;
	}

	public int getUID(){
		return uID;
	}

	public void setItemUID(int itemUID){
		this.itemUID = itemUID;
	}

	public int getItemUID(){
		return itemUID;
	}

	public void setMRP(double mRP){
		this.mRP = mRP;
	}

	public double getMRP(){
		return mRP;
	}

	public void setRPL(double rPL){
		this.rPL = rPL;
	}

	public double getRPL(){
		return rPL;
	}
}