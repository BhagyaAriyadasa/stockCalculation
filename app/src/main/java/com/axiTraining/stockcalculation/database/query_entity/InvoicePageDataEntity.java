package com.axiTraining.stockcalculation.database.query_entity;

import com.google.gson.annotations.SerializedName;

public class InvoicePageDataEntity{

	@SerializedName("PriceUID")
	private int priceUID;

	@SerializedName("ItemUID")
	private int itemUID;

	@SerializedName("ItemName")
	private String itemName;

	@SerializedName("Stock")
	private int stock;

	@SerializedName("RPL")
	private double rPL;

	private int whQTY;

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

	public void setStock(int stock){
		this.stock = stock;
	}

	public int getStock(){
		return stock;
	}

	public void setRPL(double rPL){
		this.rPL = rPL;
	}

	public double getRPL(){
		return rPL;
	}

	public int getWhQTY() {
		return whQTY;
	}

	public void setWhQTY(int whQTY) {
		this.whQTY = whQTY;
	}
}