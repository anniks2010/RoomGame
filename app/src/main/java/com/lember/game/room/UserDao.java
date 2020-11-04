package com.lember.game.room;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface UserDao {
    @Query("select * from scores order by id")
    List<User> getAllScores();

    @Insert
    void insertScores(User user);

    //if want to get individual user score
    @Query("select * from scores where id= :id")
    User loaduserById(int id);
}
