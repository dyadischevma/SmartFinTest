package ru.dyadischevma.smartfintest.ui.tabs;

import android.content.Context;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.google.android.material.textfield.TextInputEditText;

import java.text.DecimalFormat;
import java.util.regex.Pattern;

import ru.dyadischevma.smartfintest.R;
import ru.dyadischevma.smartfintest.data.entity.Good;

public class MyDialogBuilder extends AlertDialog.Builder {
    private final DecimalFormat df = new DecimalFormat("0.00##");
    private final View customLayout;
    private final TextInputEditText textInputEditText;
    private final Button buttonAdd;
    private final Button buttonCancel;

    public MyDialogBuilder(Good good, Context context) {
        super(context);

        customLayout = LayoutInflater.from(context).inflate(R.layout.layout_add_good_dialog, null);
        this.setView(customLayout);

        TextView textViewAddGoodName = customLayout.findViewById(R.id.textViewAddGoodName);
        textViewAddGoodName.setText(good.getName());

        TextView textViewAddGoodTotalPrice = customLayout.findViewById(R.id.textViewAddGoodTotalPrice);
        textViewAddGoodTotalPrice.setText(df.format(good.getPrice() / 100));
        TextView textViewAddGoodQuantity = customLayout.findViewById(R.id.textViewAddGoodQuantity);

        textInputEditText = customLayout.findViewById(R.id.textInputEditText);
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
        customLayout.findViewById(R.id.buttonAddGoodDot).setOnClickListener(v14 -> {
            if (textInputEditText.getText() != null && !textInputEditText.getText().toString().contains(".")) {
                textInputEditText.append(".");
            }
        });
        customLayout.findViewById(R.id.buttonAddGoodBackSpace).setOnClickListener(v15 -> {
            if (textInputEditText.getText() == null || textInputEditText.getText().length() == 1) {
                textInputEditText.setText("0");
            } else {
                textInputEditText.getText().delete(textInputEditText.getText().length() - 1, textInputEditText.getText().length());
            }
        });

        textInputEditText.setOnEditorActionListener((v12, actionId, event) -> {
            Editable editable = textInputEditText.getText();
            if (editable != null) {
                String value = editable.toString();
                textViewAddGoodQuantity.setText(value);
                textViewAddGoodTotalPrice.setText(df.format(good.getPrice() * Double.parseDouble(value) / 100));
            }
            return false;
        });

        buttonAdd = customLayout.findViewById(R.id.buttonAddGoodAdd);
        buttonCancel = customLayout.findViewById(R.id.buttonAddGoodCancel);
    }

    private void addText(TextInputEditText textInputEditText, String text) {
        Editable editable = textInputEditText.getText();
        if (editable != null) {
            if (textInputEditText.getText().toString().equals("0")) {
                textInputEditText.getText().clear();
            }
            String[] currentText = editable.toString().split(Pattern.quote("."));
            if (currentText.length < 2 || (currentText.length == 2 && currentText[1].length() < 2)) {
                editable.append(text);
            }
        }
    }

    public TextInputEditText getTextInputEditText() {
        return textInputEditText;
    }

    public Button getButtonAdd() {
        return buttonAdd;
    }

    public Button getButtonCancel() {
        return buttonCancel;
    }
}