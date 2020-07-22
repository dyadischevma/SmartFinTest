package ru.dyadischevma.smartfintest.ui.home.tabs;

import android.app.Application;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import ru.dyadischevma.smartfintest.R;
import ru.dyadischevma.smartfintest.data.DataRepository;
import ru.dyadischevma.smartfintest.data.entity.Good;
import ru.dyadischevma.smartfintest.data.entity.ReceiptItem;

public class GoodsAdapter extends RecyclerView.Adapter<GoodsAdapter.MyViewHolder> {

    List<Good> goodsList;

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
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.name.setText(goodsList.get(position).getName());
        holder.good = goodsList.get(position);
    }

    @Override
    public int getItemCount() {
        return goodsList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        ImageView imageView;
        View view;
        Good good;

        public MyViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.textViewGoodName);
            imageView = itemView.findViewById(R.id.imageViewGood);
            view = itemView;

            view.setOnClickListener(v -> {
                DecimalFormat df = new DecimalFormat("0.00##");
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());

                View customLayout = LayoutInflater.from(view.getContext()).inflate(R.layout.layout_add_good_dialog, null);
                builder.setView(customLayout);
                AlertDialog dialog = builder.create();
                dialog.show();

                TextView textViewAddGoodName = customLayout.findViewById(R.id.textViewAddGoodName);
                textViewAddGoodName.setText(good.getName());

                TextView textViewAddGoodTotalPrice = customLayout.findViewById(R.id.textViewAddGoodTotalPrice);
                textViewAddGoodTotalPrice.setText(df.format(good.getPrice() / 100));
                TextView textViewAddGoodQuantity = customLayout.findViewById(R.id.textViewAddGoodQuantity);

                TextInputEditText textInputEditText = customLayout.findViewById(R.id.textInputEditText);
                textInputEditText.setText("0");
                textInputEditText.setOnTouchListener((v13, event) -> true);

                customLayout.findViewById(R.id.buttonAddGood0).setOnClickListener(v14 -> addText(textInputEditText, "0"));
                customLayout.findViewById(R.id.buttonAddGood1).setOnClickListener(v14 -> addText(textInputEditText, "1"));
                customLayout.findViewById(R.id.buttonAddGood2).setOnClickListener(v14 -> addText(textInputEditText, "2"));
                customLayout.findViewById(R.id.buttonAddGood3).setOnClickListener(v14 -> addText(textInputEditText, "3"));
                customLayout.findViewById(R.id.buttonAddGood4).setOnClickListener(v14 -> addText(textInputEditText, "4"));
                customLayout.findViewById(R.id.buttonAddGood5).setOnClickListener(v14 -> addText(textInputEditText, "5"));
                customLayout.findViewById(R.id.buttonAddGood6).setOnClickListener(v14 -> addText(textInputEditText, "6"));
                customLayout.findViewById(R.id.buttonAddGood7).setOnClickListener(v14 -> addText(textInputEditText, "7"));
                customLayout.findViewById(R.id.buttonAddGood8).setOnClickListener(v14 -> addText(textInputEditText, "8"));
                customLayout.findViewById(R.id.buttonAddGood9).setOnClickListener(v14 -> addText(textInputEditText, "9"));
                customLayout.findViewById(R.id.buttonAddGoodDot).setOnClickListener(v14 -> textInputEditText.append("."));
                customLayout.findViewById(R.id.buttonAddGoodBackSpace).setOnClickListener(v15 -> {
                    if (textInputEditText.getText() == null || textInputEditText.getText().length() == 1) {
                        textInputEditText.setText("0");
                    } else {
                        textInputEditText.getText().delete(textInputEditText.getText().length() - 1, textInputEditText.getText().length());
                    }
                });

                textInputEditText.setOnEditorActionListener((v12, actionId, event) -> {
                    String value = textInputEditText.getText().toString();
                    textViewAddGoodQuantity.setText(value);
                    textViewAddGoodTotalPrice.setText(df.format(good.getPrice() * Double.parseDouble(value) / 100));
                    return false;
                });

                Button buttonAdd = customLayout.findViewById(R.id.buttonAddGoodAdd);
                Button buttonCancel = customLayout.findViewById(R.id.buttonAddGoodCancel);

                buttonCancel.setOnClickListener(v1 -> dialog.cancel());
                buttonAdd.setOnClickListener(v1 -> {
                    double quantity = Double.parseDouble(textInputEditText.getText().toString());

                    ArrayList<ReceiptItem> currentReceipt =
                            DataRepository.getDataRepository((Application) view.getContext().getApplicationContext()).getReceipt().getValue();
                    currentReceipt.add(new ReceiptItem(good, quantity));
                    DataRepository.getDataRepository((Application) view.getContext().getApplicationContext()).setReceipt(currentReceipt);

                    dialog.cancel();
                });

            });
        }

        private void addText(TextInputEditText textInputEditText, String text) {
            if (textInputEditText.getText() != null && textInputEditText.getText().toString().equals("0")) {
                textInputEditText.getText().clear();
            }
            textInputEditText.getText().append(text);
        }
    }
}