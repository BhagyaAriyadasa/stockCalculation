package com.axiTraining.stockcalculation.presentation.invoice_activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.axiTraining.stockcalculation.R;
import com.axiTraining.stockcalculation.presentation.invoice_activity.sub_activity.InvoiceAdapter;
import com.axiTraining.stockcalculation.presentation.vehicle_loading_activity.sub_activity.VehicleLoadingAdapter;


public class InvoiceActivity extends AppCompatActivity {
    TextView itemTextView,rplTextView,stockTextView,qtyTextView;
    RecyclerView recyclerView;
    Button submitBtn,goToWarehouse;
    InvoiceActivityViewModel viewModel;

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice);
        viewInt();
        loadInvoiceData();
    }

    private void viewInt(){
        itemTextView = findViewById(R.id.itemTextView);
        rplTextView = findViewById(R.id.rplTextView);
        stockTextView = findViewById(R.id.stockTextView);
        qtyTextView = findViewById(R.id.qtyTextView);
        submitBtn = findViewById(R.id.buttonSubmit);
        goToWarehouse = findViewById(R.id.buttonToWarehouse);
        recyclerView = findViewById(R.id.recyclerListView);
        try{
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        }catch (Exception e){
            System.err.println(e);
        }

        viewModel = new ViewModelProvider(this).get(InvoiceActivityViewModel.class);

        submitBtn.setOnClickListener(view -> submitFunction());
    }

    private void loadInvoiceData() {
        viewModel.fetchInvoicePageData(this);
        InvoiceAdapter adapter = new InvoiceAdapter(this, viewModel);
        recyclerView.setAdapter(adapter);
    }

    private void submitFunction(){
        viewModel.postInvoice(this);
//        viewModel.postInvoiceItem(this);
    }

}
