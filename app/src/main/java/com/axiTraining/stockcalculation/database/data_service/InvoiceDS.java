package com.axiTraining.stockcalculation.database.data_service;

import android.content.Context;
import android.database.sqlite.SQLiteStatement;

import com.axiTraining.stockcalculation.Utils;
import com.axiTraining.stockcalculation.database.DataBaseHelper;
import com.axiTraining.stockcalculation.database.table_entity.InvoiceEntity;

import java.util.List;

public class InvoiceDS {

    DataBaseHelper dataBaseHelper;

    public boolean createOrUpdate(List<InvoiceEntity> list){
        boolean action = false;
        try{
            String sql = "insert or replace into " +
                  tableName +  "("
                    + col_TotalPrice +","
                    + col_Qty +","
                    + col_Date + ","
                    + col_ItemUID + ")"
                    + " values (?,?,?,?)";

            dataBaseHelper.getDB().beginTransaction();
            SQLiteStatement statement = dataBaseHelper.getDB().compileStatement(sql);
            list.forEach(invoiceEntity -> insertStatement(statement,invoiceEntity));
            Utils.lastInsertedLoginUId = (int) dataBaseHelper.getDB().compileStatement("SELECT last_insert_rowid()").simpleQueryForLong();
            dataBaseHelper.getDB().setTransactionSuccessful();
            action = true;
        }catch (Exception e){
            System.err.println(e);
        }finally {
            dataBaseHelper.getDB().endTransaction();
        }
        return action;
    }

    private void insertStatement(SQLiteStatement statement, InvoiceEntity object){
        statement.bindDouble(1,object.getTotalPrice());
        statement.bindLong(2,object.getQty());
        statement.bindString(3,object.getDate());
        statement.bindLong(4,object.getItemUID());
        statement.execute();
    }

    public InvoiceDS(Context context){
        dataBaseHelper = DataBaseHelper.getInstance(context);
    }

    public static String tableName = "Invoice";
    public static String col_UID = "UID";
    public static String col_TotalPrice = "TotalPrice";
    public static String col_Qty = "Qty";
    public static String col_Date = "Date";
    public static String col_ItemUID = "ItemUID";

    public static String create = "create table if not exists " +
            tableName+ "(" +
            col_UID+ " integer primary key autoincrement ,"+
            col_TotalPrice+ " double ,"+
            col_Qty+ " integer ,"+
            col_Date+ " text ,"+
            col_ItemUID+ " integer "+
        ");";

}
