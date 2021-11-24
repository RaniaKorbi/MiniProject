package com.example.miniproject;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.miniproject.Entities.ConcertEvent;
import com.example.miniproject.Entities.TheatreEvent;
import com.example.miniproject.Repositories.ConcertEventRepository;
import com.example.miniproject.Repositories.TheatreEventRepository;

import java.util.List;

public class TheatreEventViewModel extends AndroidViewModel {
    private TheatreEventRepository mRepository;

    private LiveData<List<TheatreEvent>> mAllEvents;

    public TheatreEventViewModel (Application application) {
        super(application);
        mRepository = new TheatreEventRepository(application);
        mAllEvents = mRepository.getmAllEvents();
    }

    LiveData<List<TheatreEvent>> getAllWords() { return mAllEvents; }

    public void insert(TheatreEvent event) { mRepository.insert(event); }
    public void deleteAll() {mRepository.deleteAll();}
    public void deleteWord(TheatreEvent event) {mRepository.deleteEvent(event);}
}
