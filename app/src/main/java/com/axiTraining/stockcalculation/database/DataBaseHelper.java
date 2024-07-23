package com.axiTraining.stockcalculation.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.axiTraining.stockcalculation.database.data_service.DiscountDS;
import com.axiTraining.stockcalculation.database.data_service.InvoiceDS;
import com.axiTraining.stockcalculation.database.data_service.InvoiceItemDS;
import com.axiTraining.stockcalculation.database.data_service.ItemDS;
import com.axiTraining.stockcalculation.database.data_service.PriceDS;
import com.axiTraining.stockcalculation.database.data_service.WareHouseDS;

public class DataBaseHelper extends SQLiteOpenHelper {

    private static final int databaseVersion = 1;
    private static final String databaseName = "StockCalculationDatabase";

    private static DataBaseHelper instance;
    private static SQLiteDatabase database;

    public DataBaseHelper(@Nullable Context context) {
        super(context,databaseName,null,databaseVersion);
    }

    public static synchronized DataBaseHelper getInstance(Context context){
        if(instance == null){
            instance = new DataBaseHelper(context);
        }
        return instance;
    }

    public synchronized SQLiteDatabase getDB() {
        if(database == null || !database.isOpen()) {
            database = instance.getWritableDatabase();
            database.disableWriteAheadLogging();
        }

        return database;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try{
            db.execSQL(DiscountDS.create);
        }catch (Exception e){
            System.err.println(e);
        }

        try{
            db.execSQL(InvoiceDS.create);
        }catch (Exception e){
            System.err.println(e);
        }

        try{
            db.execSQL(InvoiceItemDS.create);
        }catch (Exception e){
            System.err.println(e);
        }

        try{
            db.execSQL(ItemDS.create);
        }catch (Exception e){
            System.err.println(e);
        }

        try{
            db.execSQL(PriceDS.create);
        }catch (Exception e){
            System.err.println(e);
        }

        try{
            db.execSQL(WareHouseDS.create);
        }catch (Exception e){
            System.err.println(e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
