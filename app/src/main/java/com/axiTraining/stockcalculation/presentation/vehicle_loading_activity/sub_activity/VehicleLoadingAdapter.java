package com.axiTraining.stockcalculation.presentation.vehicle_loading_activity.sub_activity;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.axiTraining.stockcalculation.R;
import com.axiTraining.stockcalculation.database.query_entity.VehicleLoadingEntity;
import com.axiTraining.stockcalculation.presentation.vehicle_loading_activity.VehicleLoadingActivityViewModel;

import java.util.List;

public class VehicleLoadingAdapter extends RecyclerView.Adapter<VehicleLoadingAdapter.ViewHolder> {
    private Context context;
    private VehicleLoadingActivityViewModel viewModel;

    public VehicleLoadingAdapter(Context context, VehicleLoadingActivityViewModel viewModel) {
        this.context = context;
        this.viewModel = viewModel;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_vehicle_loading, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        VehicleLoadingEntity entity = viewModel.vehicleLoadingEntities.get(position);
        holder.itemNameTextView.setText(entity.getItemName());
        holder.rplTextView.setText(String.valueOf(entity.getRPL()));
        if(entity.getWhQTY()==0){
            holder.stockEditText.setText("");
        }
//        holder.stockEditText.setText(String.valueOf(entity.getWhQTY()));
        holder.stockEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                entity.setWhQTY(Integer.parseInt(charSequence.toString()));
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return viewModel.vehicleLoadingEntities.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView itemNameTextView;
        TextView rplTextView;
        EditText stockEditText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemNameTextView = itemView.findViewById(R.id.itemNameTextView);
            rplTextView = itemView.findViewById(R.id.rplTextView);
            stockEditText = itemView.findViewById(R.id.stockEditText);
        }
    }
}

