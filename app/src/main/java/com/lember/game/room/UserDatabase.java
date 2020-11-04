package com.lember.game.room;

import android.content.Context;
import android.util.Log;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {User.class},version = 1,exportSchema = false)
public abstract class UserDatabase extends RoomDatabase {
    private static final String LOG_TAG = UserDatabase.class.getSimpleName();
    private static final Object LOCK = new Object();
    private static final String DATABASE_NAME="scores";
    private static UserDatabase sInstance;

    public static UserDatabase getInstance(Context context){
        if (sInstance==null){
            synchronized (LOCK) {
                Log.d(LOG_TAG, "Creating new database instance");
                sInstance = Room.databaseBuilder(context.getApplicationContext(), UserDatabase.class, UserDatabase.DATABASE_NAME).build();
            }
        }
        return  sInstance;
    }
    public  abstract  UserDao getUserDAO();
}
