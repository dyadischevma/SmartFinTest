package ru.dyadischevma.smartfintest.data.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import ru.dyadischevma.smartfintest.data.dbases.DataRoomDbase;
import ru.dyadischevma.smartfintest.data.dao.DataDAO;
import ru.dyadischevma.smartfintest.data.entity.Good;
import ru.dyadischevma.smartfintest.data.entity.ReceiptItem;

public class DataRepository {
    private static DataRepository INSTANCE;
    private DataDAO mDataDao;
    private MutableLiveData<ArrayList<ReceiptItem>> receipt = new MutableLiveData<>();

    public static DataRepository getDataRepository(Application application) {
        if (INSTANCE == null) {
            INSTANCE = new DataRepository(application);
        }
        return INSTANCE;
    }

    private DataRepository(Application application) {
        DataRoomDbase dataRoombase = DataRoomDbase.getDatabase(application);
        this.mDataDao = dataRoombase.dataDAO();
        receipt.setValue(new ArrayList<>());
    }

    public LiveData<ArrayList<ReceiptItem>> getReceipt() {
        return receipt;
    }

    public void setReceipt(ArrayList<ReceiptItem> receipt) {
        this.receipt.setValue(receipt);
    }

    public Single<Long> insertItem(Good item) {
        return mDataDao.insertItem(item)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public LiveData<List<Good>> getAllGoods() {
        return mDataDao.getAllGoods();
    }

    public LiveData<List<Good>> getGoods(String countryName) {
        return mDataDao.getGoods(countryName);
    }

    public Completable deleteAll() {
        return mDataDao.deleteAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
