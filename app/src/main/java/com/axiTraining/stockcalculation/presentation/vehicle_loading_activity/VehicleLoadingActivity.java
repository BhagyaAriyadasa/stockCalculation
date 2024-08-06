package com.axiTraining.stockcalculation.presentation.vehicle_loading_activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.axiTraining.stockcalculation.R;
import com.axiTraining.stockcalculation.presentation.invoice_activity.InvoiceActivity;
import com.axiTraining.stockcalculation.presentation.vehicle_loading_activity.sub_activity.VehicleLoadingAdapter;

public class VehicleLoadingActivity extends AppCompatActivity {
    TextView itemTextView,rplTextView,stockTextView;
    RecyclerView recyclerView;
    Button submitBtn,goToInvoice;
    VehicleLoadingActivityViewModel viewModel;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_loading);
        viewInt();
        loadVehicleLoadingData();
    }

    private void viewInt(){
        itemTextView = findViewById(R.id.itemTextView);
        rplTextView = findViewById(R.id.rplTextView);
        stockTextView = findViewById(R.id.stockTextView);
        recyclerView = findViewById(R.id.recyclerListView);
        submitBtn = findViewById(R.id.buttonSubmit);
        goToInvoice = findViewById(R.id.buttonToInvoice);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        viewModel= new ViewModelProvider(this).get(VehicleLoadingActivityViewModel.class);

        submitBtn.setOnClickListener(view -> submitFunction());
        goToInvoice.setOnClickListener(view -> goToInvoiceFunction());
    }

    private void loadVehicleLoadingData() {
        viewModel.fetchItemListWithPrice(this);
        VehicleLoadingAdapter adapter = new VehicleLoadingAdapter(this, viewModel);
        recyclerView.setAdapter(adapter);
    }

    private void submitFunction(){
        viewModel.postWareHouse(this);
    }

    private void goToInvoiceFunction(){
        Intent intent=new Intent(VehicleLoadingActivity.this,InvoiceActivity.class);
        startActivity(intent);
    }
}
