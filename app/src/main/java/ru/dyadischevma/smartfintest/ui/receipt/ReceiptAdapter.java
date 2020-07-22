package ru.dyadischevma.smartfintest.ui.receipt;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import ru.dyadischevma.smartfintest.R;
import ru.dyadischevma.smartfintest.data.entity.ReceiptItem;

public class ReceiptAdapter extends RecyclerView.Adapter<ReceiptAdapter.MyViewHolder> {
    private DecimalFormat df = new DecimalFormat("0.00##");
    private List<ReceiptItem> receiptItemList;

    public void setListData(List<ReceiptItem> dataItemList) {
        if (receiptItemList == null) {
            receiptItemList = new ArrayList<>();
        }
        receiptItemList.clear();
        receiptItemList.addAll(dataItemList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_receipt, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.name.setText(receiptItemList.get(position).getGood().getName());
        holder.totalPrice.setText(df.format(
                receiptItemList.get(position).getGood().getPrice() * receiptItemList.get(position).getQuantity() / 100)
        );
        holder.quantity.setText(String.valueOf(receiptItemList.get(position).getQuantity()));
        holder.leftPrice.setText(df.format(receiptItemList.get(position).getGood().getPrice() / 100));
        holder.rightPrice.setText(df.format(receiptItemList.get(position).getGood().getPrice() / 100));
    }

    @Override
    public int getItemCount() {
        return receiptItemList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name, totalPrice, quantity, leftPrice, rightPrice;

        public MyViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.textViewReceiptGoodName);
            totalPrice = itemView.findViewById(R.id.textViewReceiptTotalPrice);
            quantity = itemView.findViewById(R.id.textViewReceiptQuantity);
            leftPrice = itemView.findViewById(R.id.textViewReceiptPrice);
            rightPrice = itemView.findViewById(R.id.textViewReceiptRightPrice);
        }
    }
}
