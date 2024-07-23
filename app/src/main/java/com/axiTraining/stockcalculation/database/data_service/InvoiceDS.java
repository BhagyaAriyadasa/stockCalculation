package com.axiTraining.stockcalculation.database.data_service;

import android.content.Context;

import com.axiTraining.stockcalculation.database.DataBaseHelper;

public class InvoiceDS {

    DataBaseHelper dataBaseHelper;

    public InvoiceDS(Context context){
        dataBaseHelper = DataBaseHelper.getInstance(context);
    }

    public static String tableName = "Invoice";
    public static String col_UID = "UID";
    public static String col_TotalPrice = "TotalPrice";
    public static String col_Qty = "Qty";
    public static String col_Date = "Date";

    public static String create = "create table if not exists " +
            tableName+ "(" +
            col_UID+ " integer primary key autoincrement ,"+
            col_TotalPrice+ " double ,"+
            col_Qty+ " integer ,"+
            col_Date+ " text "+
        ");";

}
