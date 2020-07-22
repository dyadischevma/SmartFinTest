package ru.dyadischevma.smartfintest.ui.home.receipt;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.ArrayList;
import java.util.HashMap;

import ru.dyadischevma.smartfintest.data.DataRepository;
import ru.dyadischevma.smartfintest.data.entity.ReceiptItem;

public class ReceiptViewModel extends AndroidViewModel {
    DataRepository dataRepository;

    public ReceiptViewModel(@NonNull Application application) {
        super(application);
        dataRepository = DataRepository.getDataRepository(application);
    }

    public LiveData<ArrayList<ReceiptItem>> getData() {
        return dataRepository.getReceipt();
    }

    public void clearReceipt() {
        dataRepository.setReceipt(new ArrayList<>());
    }
}