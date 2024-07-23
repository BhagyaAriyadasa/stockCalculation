package com.axiTraining.stockcalculation.database.data_service;

import android.content.Context;
import android.database.sqlite.SQLiteStatement;

import com.axiTraining.stockcalculation.database.DataBaseHelper;
import com.axiTraining.stockcalculation.database.table_entity.DiscountEntity;

import java.util.ArrayList;
import java.util.List;

public class DiscountDS {

    DataBaseHelper dataBaseHelper;

    public DiscountDS(Context context){
        dataBaseHelper=DataBaseHelper.getInstance(context);
    }

    private boolean createOrUpdate(List<DiscountEntity> list){
        boolean action = false;
        try {
            String sql = "insert or replace into " +
                    tableName + "("
                    + col_UID + ","
                    + col_DiscountQty + ","
                    + col_BreakQty + ","
                    + col_UpperQty
                    +")" +
                    " values (?,?,?,?)";

            dataBaseHelper.getDB().beginTransaction();

            SQLiteStatement statement = dataBaseHelper.getDB().compileStatement(sql);
            list.forEach(discountEntity -> insertStatement(statement,discountEntity));
            dataBaseHelper.getDB().setTransactionSuccessful();
            action=true;
        }catch (Exception e){
            System.err.println(e);
        }finally {
            dataBaseHelper.getDB().endTransaction();
        }
        return action;
    }

    private void insertStatement(SQLiteStatement statement, DiscountEntity object){
        statement.bindLong(1,object.getUID());
        statement.bindLong(2,object.getDiscountQty());
        statement.bindLong(3,object.getBreakQty());
        statement.bindLong(4,object.getUpperQty());
        statement.execute();
    }

    public void insertDiscounts(){
        List<DiscountEntity> discounts = new ArrayList<>();

        discounts.add(new DiscountEntity(1,1,0,20));
        discounts.add(new DiscountEntity(2,3,21,100));
        discounts.add(new DiscountEntity(3,5,101,999));

        createOrUpdate(discounts);
    }

    public static String tableName = "Discount";
    public static String col_UID = "UID";
    public static String col_DiscountQty = "DiscountQty";
    public static String col_BreakQty = "BreakQty";
    public static String col_UpperQty = "UpperQty";

    public static String create ="create table if not exists "+
            tableName + "(" +
            col_UID + " integer primary key autoincrement ,"+
            col_DiscountQty + " integer ,"+
            col_BreakQty + " integer ,"+
            col_UpperQty + " integer "+
            ");";
}
