package com.example.miniproject;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.miniproject.Entities.CinemaEvent;
import com.example.miniproject.Repositories.CinemaEventRepository;

import java.util.List;

public class CinemaEventViewModel extends AndroidViewModel {
    private CinemaEventRepository mRepository;

    private LiveData<List<CinemaEvent>> mAllEvents;

    public CinemaEventViewModel (Application application) {
        super(application);
        mRepository = new CinemaEventRepository(application);
        mAllEvents = mRepository.getmAllEvents();
    }

    LiveData<List<CinemaEvent>> getAllWords() { return mAllEvents; }

    public void insert(CinemaEvent event) { mRepository.insert(event); }
    public void deleteAll() {mRepository.deleteAll();}
    public void deleteWord(CinemaEvent event) {mRepository.deleteEvent(event);}
}
