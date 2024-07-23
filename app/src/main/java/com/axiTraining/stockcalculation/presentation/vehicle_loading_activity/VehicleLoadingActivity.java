package com.axiTraining.stockcalculation.presentation.vehicle_loading_activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.axiTraining.stockcalculation.R;
import com.axiTraining.stockcalculation.database.data_service.ItemDS;
import com.axiTraining.stockcalculation.database.query_entity.VehicleLoadingEntity;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class VehicleLoadingActivity extends AppCompatActivity {
    TextView itemTextView,rplTextView,stockTextView;
    EditText stockEditText;
    ListView itemListView, rplListView;
    ItemDS itemDS;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_loading);
        viewInt();
        itemDS = new ItemDS(this);
        loadVehicleLoadingData();
    }

    private void viewInt(){
        itemTextView = findViewById(R.id.itemTextView);
        rplTextView = findViewById(R.id.rplTextView);
        stockTextView = findViewById(R.id.stockTextView);
        stockEditText = findViewById(R.id.stockEditText);
        itemListView = findViewById(R.id.itemList);
        rplListView = findViewById(R.id.priceList);

    }

    private void loadVehicleLoadingData() {
        List<VehicleLoadingEntity> vehicleLoadingEntities;
        try {
            vehicleLoadingEntities = itemDS.getAllItemWithPrice();
            List<String> itemNames = new ArrayList<>();
            List<String> rplValues = new ArrayList<>();

            for (VehicleLoadingEntity entity : vehicleLoadingEntities) {
                itemNames.add(entity.getItemName());
                rplValues.add(String.valueOf(entity.getRPL()));
            }

            Log.d("VehicleLoading", "Item Names: " + itemNames);
            Log.d("VehicleLoading", "RPL Values: " + rplValues);

            ArrayAdapter<String> itemAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, itemNames);
            ArrayAdapter<String> rplAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, rplValues);

            itemListView.setAdapter(itemAdapter);
            rplListView.setAdapter(rplAdapter);

            Log.d("VehicleLoading", "Adapters set successfully.");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
