package com.axiTraining.stockcalculation.presentation.splash_activity;

import android.content.Context;

import androidx.lifecycle.ViewModel;

import com.axiTraining.stockcalculation.database.data_service.DiscountDS;
import com.axiTraining.stockcalculation.database.data_service.ItemDS;
import com.axiTraining.stockcalculation.database.data_service.PriceDS;

public class SplashActivityViewModel extends ViewModel {

    public void saveItems(Context context){
       new ItemDS(context).insertItems();
    }

    public void savePrices(Context context){
        new PriceDS(context).insertPrices();
    }

    public void saveDiscounts(Context context){
        new DiscountDS(context).insertDiscounts();
    }

}