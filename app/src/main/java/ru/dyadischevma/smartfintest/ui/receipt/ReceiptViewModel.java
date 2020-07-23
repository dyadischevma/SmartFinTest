package ru.dyadischevma.smartfintest.ui.receipt;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.ArrayList;

import ru.dyadischevma.smartfintest.data.entity.ReceiptItem;
import ru.dyadischevma.smartfintest.data.repositories.DataRepository;

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