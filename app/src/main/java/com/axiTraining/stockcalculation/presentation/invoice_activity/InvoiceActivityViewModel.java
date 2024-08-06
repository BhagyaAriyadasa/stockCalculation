package com.axiTraining.stockcalculation.presentation.invoice_activity;

import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.ViewModel;

import com.axiTraining.stockcalculation.Utils;
import com.axiTraining.stockcalculation.database.data_service.InvoiceDS;
import com.axiTraining.stockcalculation.database.data_service.InvoiceItemDS;
import com.axiTraining.stockcalculation.database.data_service.ItemDS;
import com.axiTraining.stockcalculation.database.data_service.WareHouseDS;
import com.axiTraining.stockcalculation.database.query_entity.InvoicePageDataEntity;
import com.axiTraining.stockcalculation.database.table_entity.InvoiceEntity;
import com.axiTraining.stockcalculation.database.table_entity.InvoiceItemEntity;
import com.axiTraining.stockcalculation.database.table_entity.WareHouseEntity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class InvoiceActivityViewModel extends ViewModel {

    public List<InvoicePageDataEntity> invoicePageDataEntityList = new ArrayList<>();
    public List<InvoiceEntity> invoiceEntity = new ArrayList<>();
    public List<InvoiceItemEntity> invoiceItemEntity = new ArrayList<>();
    public List<WareHouseEntity> wareHouseEntity = new ArrayList<>();

    public void fetchInvoicePageData(Context context){
        ItemDS itemDS = new ItemDS(context);

        try {
            invoicePageDataEntityList = itemDS.getAllItemWithStockAndPrice();
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public void postInvoice(Context context) {
        InvoiceDS invoiceDS = new InvoiceDS(context);
        WareHouseDS wareHouseDS = new WareHouseDS(context);
        Utils.checkIsInvoice=1;

        try {
            for (InvoicePageDataEntity invoicePageDataEntity : invoicePageDataEntityList) {
                int itemUID = invoicePageDataEntity.getItemUID();
                int priceUID = invoicePageDataEntity.getPriceUID();
                int qty = invoicePageDataEntity.getWhQTY();
                double rpl = invoicePageDataEntity.getRPL();
                double totalPrice = rpl * qty;
                int totalQty = 0;
                    if(qty != 0){
                        if(qty>0 && qty<=20){
                            totalQty=qty+1;
                        }else if(qty>20 && qty<=100){
                            totalQty=qty+3;
                        }else{
                            totalQty=qty+5;
                        }
                               String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
                               List<InvoiceEntity> invoiceEntityList = new ArrayList<>();
                               invoiceEntityList.add(new InvoiceEntity(totalPrice, totalQty, date, itemUID));
                               invoiceDS.createOrUpdate(invoiceEntityList);
                               postInvoiceItem(context, invoicePageDataEntity);
                               wareHouseEntity.add(new WareHouseEntity(priceUID,itemUID,totalQty));
                               wareHouseDS.createOrUpdate(wareHouseEntity);
                               wareHouseEntity.clear();
                               Toast.makeText(context, "Enter valid qty", Toast.LENGTH_SHORT).show();
                    }else {
                        System.out.println("zero qty");
                    }
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        Utils.checkIsInvoice=0;
    }

    public void postInvoiceItem(Context context, InvoicePageDataEntity invoicePageDataEntity) {
        InvoiceItemDS invoiceItemDS = new InvoiceItemDS(context);
        try {
            int type = 1;
            int invoiceUID = Utils.lastInsertedLoginUId;
            int itemUID = invoicePageDataEntity.getItemUID();
            int priceUID = invoicePageDataEntity.getPriceUID();
            int qty = invoicePageDataEntity.getWhQTY();
            List<InvoiceItemEntity> invoiceItemEntityList = new ArrayList<>();
            invoiceItemEntityList.add(new InvoiceItemEntity(type, priceUID, invoiceUID, itemUID, qty));
            invoiceItemDS.createOrUpdate(invoiceItemEntityList);
            invoiceItemEntityList.clear();

            if (qty > 0 && qty <= 20) {
                type = 2;
                qty = 1;
                invoiceItemEntityList.add(new InvoiceItemEntity(type, priceUID, invoiceUID, itemUID, qty));
                invoiceItemDS.createOrUpdate(invoiceItemEntityList);
                invoiceItemEntityList.clear();
            } else if (qty > 20 && qty <= 100) {
                type = 2;
                qty = 3;
                invoiceItemEntityList.add(new InvoiceItemEntity(type, priceUID, invoiceUID, itemUID, qty));
                invoiceItemDS.createOrUpdate(invoiceItemEntityList);
                invoiceItemEntityList.clear();
            } else {
                type = 2;
                qty = 5;
                invoiceItemEntityList.add(new InvoiceItemEntity(type, priceUID, invoiceUID, itemUID, qty));
                invoiceItemDS.createOrUpdate(invoiceItemEntityList);
                invoiceItemEntityList.clear();
            }
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public int getExistingStock(Context context,int priceUID,int itemUID){
        WareHouseDS wareHouseDS = new WareHouseDS(context);
        return wareHouseDS.getExistingStock(priceUID,itemUID);
    }
}
