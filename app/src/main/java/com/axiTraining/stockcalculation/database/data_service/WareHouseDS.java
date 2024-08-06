package com.axiTraining.stockcalculation.database.data_service;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDoneException;
import android.database.sqlite.SQLiteStatement;

import com.axiTraining.stockcalculation.Utils;
import com.axiTraining.stockcalculation.database.DataBaseHelper;
import com.axiTraining.stockcalculation.database.table_entity.WareHouseEntity;

import java.util.List;

public class WareHouseDS {

    DataBaseHelper dataBaseHelper;

    public WareHouseDS(Context context){
        dataBaseHelper = DataBaseHelper.getInstance(context);
    }

//    public boolean createOrUpdate(List<WareHouseEntity> list){
//        boolean action = false;
//        try{
//            String sql = "insert or replace into " + tableName
//                    + "("
//                    + col_PriceUID + ","
//                    + col_ItemUID + ","
//                    + col_Stock + ")"
//                    + " values (?,?,?)";
//
//            dataBaseHelper.getDB().beginTransaction();
//
//            SQLiteStatement statement = dataBaseHelper.getDB().compileStatement(sql);
//            list.forEach(wareHouseEntity -> insertStatement(statement,wareHouseEntity));
//            dataBaseHelper.getDB().setTransactionSuccessful();
//            action = true;
//        }catch (Exception e){
//            System.err.println(e);
//        }finally {
//            dataBaseHelper.getDB().endTransaction();
//        }
//        return action;
//    }

    public boolean createOrUpdate(List<WareHouseEntity> list) {
        boolean action = false;
        try {
            dataBaseHelper.getDB().beginTransaction();

            for (WareHouseEntity wareHouseEntity : list) {
                String checkSql = "SELECT " + col_Stock + " FROM " + tableName
                        + " WHERE " + col_PriceUID + " = ? AND " + col_ItemUID + " = ?";
                SQLiteStatement checkStatement = dataBaseHelper.getDB().compileStatement(checkSql);
                checkStatement.bindString(1, String.valueOf(wareHouseEntity.getPriceUID()));
                checkStatement.bindString(2, String.valueOf(wareHouseEntity.getItemUID()));

                long existingStock = 0;
                try {
                    existingStock = checkStatement.simpleQueryForLong();
                } catch (SQLiteDoneException e) {
                    System.err.println(e);
                }

                if (existingStock > 0) {
                    String updateSql = "UPDATE " + tableName
                            + " SET " + col_Stock + " = ?"
                            + " WHERE " + col_PriceUID + " = ? AND " + col_ItemUID + " = ?";
                    SQLiteStatement updateStatement = dataBaseHelper.getDB().compileStatement(updateSql);
                    if(Utils.checkIsInvoice == 1){
                        updateStatement.bindLong(1, existingStock - wareHouseEntity.getStock());
                    }else{
                        updateStatement.bindLong(1, existingStock + wareHouseEntity.getStock());
                    }
                    updateStatement.bindString(2, String.valueOf(wareHouseEntity.getPriceUID()));
                    updateStatement.bindString(3, String.valueOf(wareHouseEntity.getItemUID()));
                    updateStatement.executeUpdateDelete();
                } else {
                    String insertSql = "INSERT INTO " + tableName
                            + "(" + col_PriceUID + ", " + col_ItemUID + ", " + col_Stock + ")"
                            + " VALUES (?, ?, ?)";
                    SQLiteStatement insertStatement = dataBaseHelper.getDB().compileStatement(insertSql);
                    insertStatement.bindString(1, String.valueOf(wareHouseEntity.getPriceUID()));
                    insertStatement.bindString(2, String.valueOf(wareHouseEntity.getItemUID()));
                    insertStatement.bindLong(3, wareHouseEntity.getStock());
                    insertStatement.executeInsert();
                }
            }

            dataBaseHelper.getDB().setTransactionSuccessful();
            action = true;
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            dataBaseHelper.getDB().endTransaction();
        }
        return action;
    }

    private void insertStatement(SQLiteStatement statement, WareHouseEntity object){
        statement.bindLong(1,object.getPriceUID());
        statement.bindLong(2,object.getItemUID());
        statement.bindLong(3, object.getStock());
        statement.execute();
    }

    @SuppressLint("Range")
    public int getExistingStock(int itemUID, int priceUID){
        int existingStock=0;
            String sql = "SELECT " + col_Stock + " FROM " + tableName
                    + " WHERE " + col_PriceUID + " = ? AND " + col_ItemUID + " = ?";
        Cursor cursor = null;
        try {
            dataBaseHelper.getDB().beginTransaction();
            cursor = dataBaseHelper.getDB().rawQuery(sql, new String[]{String.valueOf(priceUID), String.valueOf(itemUID)});

            if (cursor != null && cursor.moveToFirst()) {
                existingStock = cursor.getInt(cursor.getColumnIndex(col_Stock));
            }
            dataBaseHelper.getDB().setTransactionSuccessful();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            dataBaseHelper.getDB().endTransaction();
            System.out.println(existingStock);
        }
        return existingStock;
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
