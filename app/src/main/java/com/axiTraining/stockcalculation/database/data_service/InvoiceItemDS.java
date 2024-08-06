package com.axiTraining.stockcalculation.database.data_service;

import android.content.Context;
import android.database.sqlite.SQLiteStatement;

import com.axiTraining.stockcalculation.database.DataBaseHelper;
import com.axiTraining.stockcalculation.database.table_entity.InvoiceItemEntity;

import java.util.List;

public class InvoiceItemDS {

    DataBaseHelper dataBaseHelper;

    public InvoiceItemDS(Context context){
        dataBaseHelper = DataBaseHelper.getInstance(context);
    }

    public boolean createOrUpdate(List<InvoiceItemEntity> list){
        boolean action = false;

        try{
            String sql = "insert or replace into " +
                    tableName + "("+
                    col_Type + ","
                    + col_PriceUID + ","
                    +col_InvoiceUID + ","
                    +col_ItemUID + ","
                    +col_Qty +")"
                    + " values (?,?,?,?,?)";

            dataBaseHelper.getDB().beginTransaction();
            SQLiteStatement statement = dataBaseHelper.getDB().compileStatement(sql);
            list.forEach(invoiceItemEntity -> insertStatement(statement,invoiceItemEntity));
            dataBaseHelper.getDB().setTransactionSuccessful();
            action=true;
        }catch (Exception e){
            System.err.println(e);
        }finally {
            dataBaseHelper.getDB().endTransaction();
        }
        return action;
    }

    private void insertStatement(SQLiteStatement statement, InvoiceItemEntity object){
        statement.bindLong(1,object.getType());
        statement.bindLong(2, object.getPriceUID());
        statement.bindLong(3, object.getInvoiceUID());
        statement.bindLong(4,object.getItemUID());
        statement.bindLong(5, object.getQty());
        statement.execute();
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
