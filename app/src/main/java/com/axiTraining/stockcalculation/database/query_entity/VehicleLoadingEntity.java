package com.axiTraining.stockcalculation.database.query_entity;

import com.google.gson.annotations.SerializedName;

public class VehicleLoadingEntity{

	@SerializedName("PriceUID")
	private int priceUID;

	@SerializedName("ItemUID")
	private int itemUID;

	@SerializedName("ItemName")
	private String itemName;

	@SerializedName("RPL")
	private double rPL;

	public void setPriceUID(int priceUID){
		this.priceUID = priceUID;
	}

	public int getPriceUID(){
		return priceUID;
	}

	public void setItemUID(int itemUID){
		this.itemUID = itemUID;
	}

	public int getItemUID(){
		return itemUID;
	}

	public void setItemName(String itemName){
		this.itemName = itemName;
	}

	public String getItemName(){
		return itemName;
	}

	public void setRPL(double rPL){
		this.rPL = rPL;
	}

	public double getRPL(){
		return rPL;
	}
}