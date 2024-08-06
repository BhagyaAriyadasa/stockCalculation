package com.axiTraining.stockcalculation.database.data_service;

import android.content.Context;
import android.database.sqlite.SQLiteStatement;

import com.axiTraining.stockcalculation.Utils;
import com.axiTraining.stockcalculation.database.DataBaseHelper;
import com.axiTraining.stockcalculation.database.query_entity.InvoicePageDataEntity;
import com.axiTraining.stockcalculation.database.query_entity.VehicleLoadingEntity;
import com.axiTraining.stockcalculation.database.table_entity.ItemEntity;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class ItemDS {

    DataBaseHelper dataBaseHelper;

    public ItemDS(Context context){
        dataBaseHelper = DataBaseHelper.getInstance(context);
    }

    public boolean createOrUpdate(List<ItemEntity>list){
        boolean action = false;
        try{
            String sql = "insert or replace into " + tableName
                    + "("
                    + col_UID + ","
                    + col_Name + ")"
                    + " values (?,?)";

            dataBaseHelper.getDB().beginTransaction();

            SQLiteStatement statement =dataBaseHelper.getDB().compileStatement(sql);
            list.forEach(itemEntity -> insertStatement(statement,itemEntity));
            dataBaseHelper.getDB().setTransactionSuccessful();
            action=true;
        }catch (Exception e){
            System.err.println(e);
        }finally {
            dataBaseHelper.getDB().endTransaction();
        }
        return action;
    }

    private void insertStatement(SQLiteStatement statement, ItemEntity object){
        statement.bindLong(1,object.getUID());
        statement.bindString(2,object.getName());
        statement.execute();
    }

    public void insertItems(){
        List<ItemEntity> items = new ArrayList<>();
        items.add(new ItemEntity(1,"Test 1"));
        items.add(new ItemEntity(2,"Test 2"));

        createOrUpdate(items);
    }

    public List<InvoicePageDataEntity> getAllItemWithStockAndPrice() throws JSONException{
        List<InvoicePageDataEntity> list = new ArrayList<>();
        String sql = "select i.UID as ItemUID, p.UID as PriceUID,i.Name as ItemName,p.RPL,w.Stock from Item i join Price p on i.UID=p.ItemUID join WareHouse w on i.UID=w.ItemUID" +
                " where p.UID = w.PriceUID group by i.UID, p.UID, i.Name";
        try{
            dataBaseHelper.getDB().beginTransaction();
            JSONArray array = Utils.getArray(dataBaseHelper.getDB().rawQuery(sql,null));

            for(int i = 0; i<array.length(); i++){
                list.add(new Gson().fromJson(array.getJSONObject(i).toString(), InvoicePageDataEntity.class));
            }
            dataBaseHelper.getDB().setTransactionSuccessful();
        }catch (Exception e){
            System.err.println(e);
        }finally {
            dataBaseHelper.getDB().endTransaction();
        }
        return list;
    }

    public List<VehicleLoadingEntity> getAllItemWithPrice() throws JSONException {
        List<VehicleLoadingEntity> list = new ArrayList<>();
        String sql = "select Item.UID as ItemUID, Price.UID as PriceUID, Item.Name as ItemName, Price.RPL from Item join Price on Item.UID = Price.ItemUID";
        try {
            dataBaseHelper.getDB().beginTransaction();
            JSONArray array = Utils.getArray(dataBaseHelper.getDB().rawQuery(sql, null));

            for (int i = 0; i < array.length(); i++) {
                list.add(new Gson().fromJson(array.getJSONObject(i).toString(), VehicleLoadingEntity.class));
            }
            dataBaseHelper.getDB().setTransactionSuccessful();
        }catch (Exception e){
            System.err.println(e);
        }finally {
            dataBaseHelper.getDB().endTransaction();
        }
        return list;
    }

    public static String tableName = "Item";
    public static String col_UID = "UID";
    public static String col_Name = "Name";

    public static String create = "create table if not exists " +
            tableName + "(" +
            col_UID + " integer primary key autoincrement ,"+
            col_Name + " text "+
        ");";
}
