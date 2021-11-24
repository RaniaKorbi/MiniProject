package com.example.miniproject;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.example.miniproject.Entities.ConcertEvent;
import com.example.miniproject.Entities.TheatreEvent;
import com.example.miniproject.Repositories.ConcertEventRepository;
import com.example.miniproject.Repositories.TheatreEventRepository;

import java.util.List;

public class ConcertEventViewModel extends AndroidViewModel {
    private ConcertEventRepository mRepository;
    private LiveData<List<ConcertEvent>> mAllEvents;

    public ConcertEventViewModel (Application application) {
        super(application);
        mRepository = new ConcertEventRepository(application);
        mAllEvents = mRepository.getmAllEvents();
    }

    LiveData<List<ConcertEvent>> getAllWords() { return mAllEvents; }
    public void insert(ConcertEvent event) { mRepository.insert(event); }
    public void deleteAll() {mRepository.deleteAll();}
    public void deleteWord(ConcertEvent event) {mRepository.deleteEvent(event);}
}
