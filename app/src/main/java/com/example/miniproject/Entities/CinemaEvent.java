package com.example.miniproject.Entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "cinema_table")
public class CinemaEvent {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "event")
    private String mCinemaEvent;

    public CinemaEvent(@NonNull String cinemaEvent) {this.mCinemaEvent = cinemaEvent;}

    public String getMCinemaEvent(){return this.mCinemaEvent;}
}
