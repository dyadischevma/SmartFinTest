package ru.dyadischevma.smartfintest.ui.receipt;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.util.ArrayList;

import ru.dyadischevma.smartfintest.R;
import ru.dyadischevma.smartfintest.data.entity.ReceiptItem;

public class ReceiptFragment extends Fragment {

    private ReceiptViewModel mViewModel;
    private RecyclerView recyclerView;
    private ReceiptAdapter receiptAdapter;
    private ArrayList<ReceiptItem> receiptItemsList = new ArrayList<>();

    private ImageView imageView;
    private TextView textViewReceiptLabelNoGoods;
    private TextView textViewReceiptLabelNoGoodsSmall;

    private TextView textViewReceiptCheckNumber;
    private TextView textViewReceiptClearAll;

    private TextView textViewReceiptTotal;
    private Button buttonReceiptPay;

    private final DecimalFormat df = new DecimalFormat("0.00##");

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_receipt, container, false);

        imageView = view.findViewById(R.id.imageViewReceiptIcon);
        textViewReceiptLabelNoGoods = view.findViewById(R.id.textViewReceiptLabelNoGoods);
        textViewReceiptLabelNoGoodsSmall = view.findViewById(R.id.textViewReceiptLabelNoGoodsSmall);

        textViewReceiptCheckNumber = view.findViewById(R.id.textViewReceiptCheckNumber);
        textViewReceiptClearAll = view.findViewById(R.id.textViewReceiptClearAll);

        textViewReceiptTotal = view.findViewById(R.id.textViewReceiptTotal);
        buttonReceiptPay = view.findViewById(R.id.buttonReceiptPay);

        recyclerView = view.findViewById(R.id.recyclerViewReceipt);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        receiptAdapter = new ReceiptAdapter();
        if (receiptItemsList != null) {
            receiptAdapter.setListData(receiptItemsList);
        }
        recyclerView.setAdapter(receiptAdapter);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ReceiptViewModel.class);
        mViewModel.getData().observe(getViewLifecycleOwner(), dataItems ->
        {
            if (dataItems != null) {
                manageVisible(dataItems);

                receiptItemsList.clear();
                receiptItemsList.addAll(dataItems);
                receiptAdapter.setListData(receiptItemsList);

                double totalPrice = 0;
                for (ReceiptItem r : receiptItemsList) {
                    totalPrice = totalPrice + r.getTotalPrice();
                }
                textViewReceiptTotal.setText(df.format(totalPrice/100));
            }
        });

        textViewReceiptClearAll.setOnClickListener(v -> mViewModel.clearReceipt());
    }

    private void manageVisible(ArrayList<ReceiptItem> dataItems) {
        if (dataItems.isEmpty()) {
            textViewReceiptCheckNumber.setVisibility(View.INVISIBLE);
            textViewReceiptClearAll.setVisibility(View.INVISIBLE);

            imageView.setVisibility(View.VISIBLE);
            textViewReceiptLabelNoGoods.setVisibility(View.VISIBLE);
            textViewReceiptLabelNoGoodsSmall.setVisibility(View.VISIBLE);

            buttonReceiptPay.setEnabled(false);
        } else {
            textViewReceiptCheckNumber.setVisibility(View.VISIBLE);
            textViewReceiptClearAll.setVisibility(View.VISIBLE);

            imageView.setVisibility(View.INVISIBLE);
            textViewReceiptLabelNoGoods.setVisibility(View.INVISIBLE);
            textViewReceiptLabelNoGoodsSmall.setVisibility(View.INVISIBLE);

            buttonReceiptPay.setEnabled(true);
        }
    }
}