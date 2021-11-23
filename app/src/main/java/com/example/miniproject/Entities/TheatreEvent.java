package com.example.miniproject.Entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "theatre_table")
public class TheatreEvent {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "event")
    private String theatreEvent;

    public TheatreEvent(@NonNull String theatreEvent) {this.theatreEvent = theatreEvent;}

    public String getTheatreEvent(){return this.theatreEvent;}
}
