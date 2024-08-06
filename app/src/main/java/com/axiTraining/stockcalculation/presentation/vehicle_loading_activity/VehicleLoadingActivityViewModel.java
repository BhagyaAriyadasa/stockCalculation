package com.axiTraining.stockcalculation.presentation.vehicle_loading_activity;

import android.content.Context;

import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

import com.axiTraining.stockcalculation.Utils;
import com.axiTraining.stockcalculation.database.data_service.ItemDS;
import com.axiTraining.stockcalculation.database.data_service.WareHouseDS;
import com.axiTraining.stockcalculation.database.query_entity.VehicleLoadingEntity;
import com.axiTraining.stockcalculation.database.table_entity.WareHouseEntity;
import com.axiTraining.stockcalculation.presentation.vehicle_loading_activity.sub_activity.VehicleLoadingAdapter;

import java.util.ArrayList;
import java.util.List;

public class VehicleLoadingActivityViewModel extends ViewModel {

    public List<VehicleLoadingEntity> vehicleLoadingEntities = new ArrayList<>();
    public List<WareHouseEntity> wareHouseEntity = new ArrayList<>();

    public void fetchItemListWithPrice(Context context) {
        ItemDS itemDS = new ItemDS(context);

        try {
            vehicleLoadingEntities = itemDS.getAllItemWithPrice();
        } catch (Exception e) {
            System.err.println(e);
        }
    }



    public void postWareHouse(Context context){
        WareHouseDS wareHouseDS = new WareHouseDS(context);
        try{
            for(int i=0;i<vehicleLoadingEntities.size();i++){
                int qty =vehicleLoadingEntities.get(i).getWhQTY();
                int itemUID = vehicleLoadingEntities.get(i).getItemUID();
                int priceUID = vehicleLoadingEntities.get(i).getPriceUID();

                if(qty!=0){
                    wareHouseEntity.add(new WareHouseEntity(priceUID,itemUID,qty));
                    wareHouseDS.createOrUpdate(wareHouseEntity);
                    wareHouseEntity.clear();
                }else{
                    System.out.println("Zero qty");
                }
            }
        }catch (Exception e){
            System.err.println(e);
        }

    }

//    public void clearStockField(){
//        VehicleLoadingAdapter.ViewHolder viewHolder;
//        for(int i=0;i<vehicleLoadingEntities.size();i++){
//            viewHolder.
//        }
//    }

}
