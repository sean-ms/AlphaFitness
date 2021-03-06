package com.seanschlaefli.nanofitness.database;

import android.content.Context;

import com.seanschlaefli.nanofitness.database.dao.LocationRecordDao;
import com.seanschlaefli.nanofitness.database.dao.StepRecordDao;
import com.seanschlaefli.nanofitness.database.dao.WorkoutDao;
import com.seanschlaefli.nanofitness.database.entity.LocationRecord;
import com.seanschlaefli.nanofitness.database.entity.StepRecord;
import com.seanschlaefli.nanofitness.database.entity.Workout;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Workout.class, LocationRecord.class, StepRecord.class},
                        version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract WorkoutDao workoutDao();
    public abstract StepRecordDao stepRecordDao();
    public abstract LocationRecordDao locationRecordDao();

    private static AppDatabase INSTANCE;

    public static AppDatabase getDatabase(final Context context) {
        synchronized (AppDatabase.class) {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context,
                        AppDatabase.class,
                        "nanofitness_database")
                        .fallbackToDestructiveMigration()
                        .enableMultiInstanceInvalidation()
                        .build();
            }
        }
        return INSTANCE;
    }

}
