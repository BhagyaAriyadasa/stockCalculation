package com.axiTraining.stockcalculation.database.data_service;

import android.content.Context;

import com.axiTraining.stockcalculation.database.DataBaseHelper;

public class InvoiceItemDS {

    DataBaseHelper dataBaseHelper;

    public InvoiceItemDS(Context context){
        dataBaseHelper = DataBaseHelper.getInstance(context);
    }

    public static String tableName = "InvoiceItem";
    public static String col_UID = "UID";
    public static String col_Type = "Type";
    public static String col_PriceUID = "PriceUID";
    public static String col_InvoiceUID = "InvoiceUID";
    public static String col_ItemUID = "ItemUID";
    public static String col_Qty = "Qty";

    public static String create = "create table if not exists " +
            tableName + "(" +
            col_UID + " integer primary key autoincrement ,"+
            col_Type + " integer ,"+
            col_PriceUID + " integer ,"+
            col_InvoiceUID + " integer ,"+
            col_ItemUID + " integer ,"+
            col_Qty + " integer "+
        ");";
}
