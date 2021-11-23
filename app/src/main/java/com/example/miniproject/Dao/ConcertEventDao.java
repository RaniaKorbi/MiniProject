package com.example.miniproject.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.miniproject.Entities.ConcertEvent;

import java.util.List;

@Dao
public interface ConcertEventDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(ConcertEvent concertEvent);

    @Query("DELETE FROM concert_table")
    void deleteAll();
    @Query("SELECT * from concert_table ORDER BY event ASC")
    LiveData<List<ConcertEvent>> getAllEvents();
    @Delete
    void deleteEvent(ConcertEvent concertEvent);
}
