package com.example.miniproject.Entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "concert_table")
public class ConcertEvent {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "event")
    private String concertEvent;

    public ConcertEvent(@NonNull String concertEvent) {this.concertEvent = concertEvent;}

    @NonNull
    public String getConcertEvent() {
        return concertEvent;
    }

    public void setConcertEvent(@NonNull String concertEvent) {
        this.concertEvent = concertEvent;
    }
}
