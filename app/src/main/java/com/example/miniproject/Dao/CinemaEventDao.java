package com.example.miniproject.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.miniproject.Entities.CinemaEvent;

import java.util.List;

@Dao
public interface CinemaEventDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(CinemaEvent cinemaEvent);

    @Query("DELETE FROM cinema_table")
    void deleteAll();
    @Query("SELECT * from cinema_table ORDER BY event ASC")
    LiveData<List<CinemaEvent>> getAllEvents();
    @Delete
    void deleteEvent(CinemaEvent cinemaEvent);
}
