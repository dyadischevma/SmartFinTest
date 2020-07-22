package ru.dyadischevma.smartfintest.ui.tabs;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import ru.dyadischevma.smartfintest.data.Country;
import ru.dyadischevma.smartfintest.data.DataRepository;
import ru.dyadischevma.smartfintest.data.entity.Good;

public class TabViewModel extends AndroidViewModel {

    private LiveData<List<Good>> data;
    private DataRepository dataRepository;

    public TabViewModel(@NonNull Application application) {
        super(application);
        dataRepository = DataRepository.getDataRepository(application);
    }

    public LiveData<List<Good>> getData(String countryName) {
        if (countryName.equals(Country.ALL.name)) {
            data = dataRepository.getAllGoods();
        } else {
            data = dataRepository.getGoods(countryName);
        }
        return data;
    }
}