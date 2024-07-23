package com.axiTraining.stockcalculation.database.data_service;

import android.content.Context;

import com.axiTraining.stockcalculation.database.DataBaseHelper;

public class WareHouseDS {

    DataBaseHelper dataBaseHelper;

    public WareHouseDS(Context context){
        dataBaseHelper = DataBaseHelper.getInstance(context);
    }

    public static String tableName = "WareHouse";
    public static String col_UID = "UID";
    public static String col_PriceUID = "PriceUID";
    public static String col_ItemUID = "ItemUID";
    public static String col_Stock = "Stock";

    public static String create = "create table if not exists " +
            tableName + "(" +
            col_UID + " integer primary key autoincrement ,"+
            col_PriceUID + " integer ,"+
            col_ItemUID + " integer ,"+
            col_Stock + " integer "+
            ");";
}
