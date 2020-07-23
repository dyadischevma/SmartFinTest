package ru.dyadischevma.smartfintest.ui.tabs;

import android.app.Application;
import android.content.Context;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import ru.dyadischevma.smartfintest.R;
import ru.dyadischevma.smartfintest.data.entity.Good;
import ru.dyadischevma.smartfintest.data.entity.ReceiptItem;
import ru.dyadischevma.smartfintest.data.repositories.DataRepository;

public class GoodsAdapter extends RecyclerView.Adapter<GoodsAdapter.MyViewHolder> {

    private List<Good> goodsList;
    private Context context;

    public void setListData(List<Good> dataItemList) {
        if (goodsList == null) {
            goodsList = new ArrayList<>();
        }
        goodsList.clear();
        goodsList.addAll(dataItemList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_good, parent, false);
        context = parent.getContext();
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.name.setText(goodsList.get(position).getName());
        holder.good = goodsList.get(position);
        holder.viewGoodColor.setBackgroundColor(goodsList.get(position).getCountry().color);
        Picasso.with(context).load(holder.good.getLink()).error(R.drawable.basket).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return goodsList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        View view;
        TextView name;
        ImageView imageView;
        View viewGoodColor;

        Good good;

        public MyViewHolder(View itemView) {
            super(itemView);
            view = itemView;

            name = itemView.findViewById(R.id.textViewGoodName);
            imageView = itemView.findViewById(R.id.imageViewGood);
            viewGoodColor = itemView.findViewById(R.id.viewGoodColor);

            view.setOnClickListener(v -> {
                MyDialogBuilder myDialogBuilder = new MyDialogBuilder(good, view.getContext());
                AlertDialog dialog = myDialogBuilder.create();
                dialog.show();

                Button buttonAdd = myDialogBuilder.getButtonAdd();
                Button buttonCancel = myDialogBuilder.getButtonCancel();

                buttonCancel.setOnClickListener(v1 -> dialog.cancel());
                buttonAdd.setOnClickListener(v1 -> {
                    Editable editable = myDialogBuilder.getTextInputEditText().getEditableText();
                    if (editable != null) {
                        double quantity = Double.parseDouble(editable.toString());

                        ArrayList<ReceiptItem> currentReceipt =
                                DataRepository.getDataRepository((Application) view.getContext().getApplicationContext()).getReceipt().getValue();
                        if (currentReceipt != null) {
                            currentReceipt.add(new ReceiptItem(good, quantity));
                            DataRepository.getDataRepository((Application) view.getContext().getApplicationContext()).setReceipt(currentReceipt);
                        }
                    }
                    dialog.cancel();
                });
            });
        }
    }
}