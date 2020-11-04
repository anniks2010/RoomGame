package com.lember.game.room;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "scores")

public class User {
    @PrimaryKey(autoGenerate = true)
    int id;

    @ColumnInfo(name="username")
    String userName;

    @ColumnInfo (name="score")
    int userScore;

    @Ignore
    public User(String userName, int userScore) {
        this.userName = userName;
        this.userScore = userScore;
    }

    public User(int id, String userName, int userScore) {
        this.id = id;
        this.userName = userName;
        this.userScore = userScore;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserScore() {
        return userScore;
    }

    public void setUserScore(int userScore) {
        this.userScore = userScore;
    }

    @NonNull
    @Override
    public String toString() {
        return userName+" "+userScore;
    }
}
