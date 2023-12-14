package com.example.creditscoreapp.database;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.creditscoreapp.dao.UserDao;
import com.example.creditscoreapp.data.User;


@androidx.room.Database(entities = {User.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();

    private static volatile AppDatabase INSTANCE;


    public static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                                    context.getApplicationContext(),
                                    AppDatabase.class,
                                    "Database"
                            )
                            .addMigrations(new Migration(1, 2) {
                                @Override
                                public void migrate(SupportSQLiteDatabase database) {
                                    // Perform the necessary migration steps
                                    // For example, you can create new tables, alter existing ones, etc.
                                    // This method is called when a migration from version 1 to 2 is needed.
                                }
                            })
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
