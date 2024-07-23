package com.axiTraining.stockcalculation.database.data_service;

import android.content.Context;
import android.database.sqlite.SQLiteStatement;

import com.axiTraining.stockcalculation.database.DataBaseHelper;
import com.axiTraining.stockcalculation.database.table_entity.PriceEntity;

import java.util.ArrayList;
import java.util.List;

public class PriceDS {

    DataBaseHelper dataBaseHelper;

    public PriceDS(Context context){
        dataBaseHelper = DataBaseHelper.getInstance(context);
    }

    private boolean createOrUpdate(List<PriceEntity> list){
        boolean action = false;
        try{
            String sql = "insert or replace into " + tableName
                    + "("
                    + col_UID + ","
                    + col_ItemUID + ","
                    + col_MRP + ","
                    + col_RPL +")"
                    + " values (?,?,?,?)";

            dataBaseHelper.getDB().beginTransaction();

            SQLiteStatement statement= dataBaseHelper.getDB().compileStatement(sql);
            list.forEach(priceEntity -> insertStatement(statement,priceEntity));
            dataBaseHelper.getDB().setTransactionSuccessful();
            action=true;
        }catch (Exception e){
            System.err.println(e);
        }finally {
            dataBaseHelper.getDB().endTransaction();
        }
        return action;
    }

    private void insertStatement(SQLiteStatement statement, PriceEntity object){
        statement.bindLong(1,object.getUID());
        statement.bindLong(2,object.getItemUID());
        statement.bindDouble(3, object.getMRP());
        statement.bindDouble(4, object.getRPL());
        statement.execute();
    }

    public void insertPrices(){
        List<PriceEntity> prices = new ArrayList<>();

        prices.add(new PriceEntity(1,1,100.00,95.00));
        prices.add(new PriceEntity(2,1,110.00,100.00));
        prices.add(new PriceEntity(3,2,80.00,70.00));

        createOrUpdate(prices);
    }

    public static String tableName = "Price";
    public static String col_UID = "UID";
    public static String col_ItemUID = "ItemUID";
    public static String col_MRP = "MRP";
    public static String col_RPL = "RPL";

    public static String create = "create table if not exists " +
            tableName + "(" +
            col_UID + " integer primary key autoincrement ,"+
            col_ItemUID + " integer ,"+
            col_MRP + " double ,"+
            col_RPL + " double "+
            ");";
}
