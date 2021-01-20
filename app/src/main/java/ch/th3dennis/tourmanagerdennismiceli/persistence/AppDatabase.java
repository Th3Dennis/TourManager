package ch.th3dennis.tourmanagerdennismiceli.persistence;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import ch.th3dennis.tourmanagerdennismiceli.model.Tour;

/**
 * Class to create the SQLite database
 */
@Database(entities = {Tour.class}, version = 3)
public abstract class AppDatabase extends RoomDatabase {

    private static final String DB_NAME = "TourDB";
    private static AppDatabase appdb;

    /**
     * Returns a AppDatabase
     *
     * @param context
     * @return
     */
    public static AppDatabase getAppDb(Context context) {
        if (appdb == null) {
            appdb = Room.databaseBuilder(context, AppDatabase.class, DB_NAME)
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return appdb;
    }

    public abstract TourDao getTourDao();
}
