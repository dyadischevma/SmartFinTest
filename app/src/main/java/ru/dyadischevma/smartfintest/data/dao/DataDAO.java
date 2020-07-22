package ru.dyadischevma.smartfintest.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;
import ru.dyadischevma.smartfintest.data.entity.Good;

import static androidx.room.OnConflictStrategy.IGNORE;

@Dao
public interface DataDAO {
    //Insert one item
    @Insert(onConflict = IGNORE)
    Single<Long> insertItem(Good item);

    //Get all items
    @Query("SELECT * FROM Good")
    LiveData<List<Good>> getAllGoods();

    //Get items by country
    @Query("SELECT * FROM good WHERE country == :countryName")
    LiveData<List<Good>> getGoods(String countryName);

    //Delete All
    @Query("DELETE FROM good")
    Completable deleteAll();
}
