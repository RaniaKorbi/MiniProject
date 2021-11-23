package com.example.miniproject.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.miniproject.Entities.TheatreEvent;

import java.util.List;
@Dao
public interface TheatreEventDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(TheatreEvent theatreEvent);

    @Query("DELETE FROM theatre_table")
    void deleteAll();
    @Query("SELECT * from theatre_table ORDER BY event ASC")
    LiveData<List<TheatreEvent>> getAllEvents();
    @Delete
    void deleteEvent(TheatreEvent theatreEvent);
}
