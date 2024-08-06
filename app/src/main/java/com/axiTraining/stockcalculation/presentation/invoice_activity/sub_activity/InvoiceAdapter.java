package com.axiTraining.stockcalculation.presentation.invoice_activity.sub_activity;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.axiTraining.stockcalculation.R;
import com.axiTraining.stockcalculation.Utils;
import com.axiTraining.stockcalculation.database.query_entity.InvoicePageDataEntity;
import com.axiTraining.stockcalculation.presentation.invoice_activity.InvoiceActivityViewModel;

public class InvoiceAdapter extends RecyclerView.Adapter<InvoiceAdapter.ViewHolder> {
    private Context context;
    private InvoiceActivityViewModel viewModel;

    public InvoiceAdapter(Context context,InvoiceActivityViewModel viewModel){
        this.context=context;
        this.viewModel=viewModel;
    }
    @NonNull
    @Override
    public InvoiceAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_invoice, parent, false);
        return new InvoiceAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InvoiceAdapter.ViewHolder holder, int position) {
        InvoicePageDataEntity entity = viewModel.invoicePageDataEntityList.get(position);
        holder.itemNameTextView.setText(entity.getItemName());
        holder.rplTextView.setText(String.valueOf(entity.getRPL()));
        holder.stockTextView.setText(String.valueOf(entity.getStock()));

        holder.qtyEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                entity.setWhQTY(Integer.parseInt(charSequence.toString()));
                if (!charSequence.toString().isEmpty()) {
                    try {
                        int enteredQty = Integer.parseInt(charSequence.toString());
                        entity.setWhQTY(enteredQty);
                        int existingStock = viewModel.getExistingStock(context,entity.getItemUID(), entity.getPriceUID());

                        if (enteredQty > existingStock) {
                            holder.qtyEditText.setError(entity.getItemName() + " with price Rs: " + entity.getRPL() + " Qty is greater than existing qty");
                            entity.setWhQTY(0);
                        } else {
                            holder.qtyEditText.setError(null);
                        }
                    } catch (NumberFormatException e) {
                        holder.qtyEditText.setError("Invalid quantity entered");
                    }
                } else {
                    holder.qtyEditText.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

                entity.setWhQTY(0);
            }
        });
    }

    @Override
    public int getItemCount() {
        return viewModel.invoicePageDataEntityList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView itemNameTextView;
        TextView rplTextView;
        TextView stockTextView;
        EditText qtyEditText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemNameTextView = itemView.findViewById(R.id.itemNameTextView);
            rplTextView = itemView.findViewById(R.id.rplTextView);
            stockTextView = itemView.findViewById(R.id.stockTextView);
            qtyEditText = itemView.findViewById(R.id.qtyEditText);
        }
    }
}
