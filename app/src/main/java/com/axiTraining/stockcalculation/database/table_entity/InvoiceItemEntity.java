package com.axiTraining.stockcalculation.database.table_entity;

import com.google.gson.annotations.SerializedName;

public class InvoiceItemEntity{

	@SerializedName("UID")
	private int uID;

	@SerializedName("Type")
	private int type;

	@SerializedName("PriceUID")
	private int priceUID;

	@SerializedName("InvoiceUID")
	private int invoiceUID;

	@SerializedName("ItemUID")
	private int itemUID;

	@SerializedName("Qty")
	private int qty;

    public InvoiceItemEntity(int type, int priceUID, int invoiceUID, int itemUID, int qty) {
		this.type=type;
		this.priceUID=priceUID;
		this.invoiceUID=invoiceUID;
		this.itemUID=itemUID;
		this.qty=qty;
    }

    public void setUID(int uID){
		this.uID = uID;
	}

	public int getUID(){
		return uID;
	}

	public void setType(int type){
		this.type = type;
	}

	public int getType(){
		return type;
	}

	public void setPriceUID(int priceUID){
		this.priceUID = priceUID;
	}

	public int getPriceUID(){
		return priceUID;
	}

	public void setInvoiceUID(int invoiceUID){
		this.invoiceUID = invoiceUID;
	}

	public int getInvoiceUID(){
		return invoiceUID;
	}

	public void setItemUID(int itemUID){
		this.itemUID = itemUID;
	}

	public int getItemUID(){
		return itemUID;
	}

	public void setQty(int qty){
		this.qty = qty;
	}

	public int getQty(){
		return qty;
	}
}