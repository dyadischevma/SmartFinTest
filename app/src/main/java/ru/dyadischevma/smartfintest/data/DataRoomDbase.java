package ru.dyadischevma.smartfintest.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import ru.dyadischevma.smartfintest.data.entity.Good;

@Database(entities = {Good.class}, version = 1, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class DataRoomDbase extends RoomDatabase {
    private static DataRoomDbase INSTANCE;

    public abstract DataDAO dataDAO();

    public static DataRoomDbase getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(
                    context.getApplicationContext(),
                    DataRoomDbase.class,
                    DataRoomDbase.class.getName()
            )
                    .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
}
