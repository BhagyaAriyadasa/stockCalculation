package com.axiTraining.stockcalculation.database.table_entity;

import com.google.gson.annotations.SerializedName;

public class WareHouseEntity{

	@SerializedName("UID")
	private int uID;

	@SerializedName("PriceUID")
	private int priceUID;

	@SerializedName("ItemUID")
	private int itemUID;

	@SerializedName("Stock")
	private int stock;

    public WareHouseEntity(int priceUID, int itemUID, int qty) {
		this.priceUID=priceUID;
		this.itemUID=itemUID;
		this.stock=qty;
    }

    public void setUID(int uID){
		this.uID = uID;
	}

	public int getUID(){
		return uID;
	}

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

	public void setStock(int stock){
		this.stock = stock;
	}

	public int getStock(){
		return stock;
	}
}