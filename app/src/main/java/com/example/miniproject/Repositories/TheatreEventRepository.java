package com.example.miniproject.Repositories;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.miniproject.Dao.ConcertEventDao;
import com.example.miniproject.Dao.TheatreEventDao;
import com.example.miniproject.Entities.ConcertEvent;
import com.example.miniproject.Entities.TheatreEvent;

import java.util.List;

public class TheatreEventRepository {
    private TheatreEventDao mTheatreEventDao;
    private LiveData<List<TheatreEvent>> mAllEvents;

    public TheatreEventRepository(Application application) {
        WordRoomDatabase db = WordRoomDatabase.getDatabase(application);
        mTheatreEventDao = db.theatreEventDao();
        mAllEvents = mTheatreEventDao.getAllEvents();
    }
    private static class deleteAllWordsAsyncTask extends AsyncTask<Void, Void, Void> {
        private TheatreEventDao mAsyncTaskDao;

        deleteAllWordsAsyncTask(TheatreEventDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mAsyncTaskDao.deleteAll();
            return null;
        }
    }
    public LiveData<List<TheatreEvent>> getmAllEvents() {
        return mAllEvents;
    }

    public void insert (TheatreEvent theatreEvent) {
        new TheatreEventRepository.insertAsyncTask(mTheatreEventDao).execute(theatreEvent);
    }

    private static class insertAsyncTask extends AsyncTask<TheatreEvent, Void, Void> {

        private TheatreEventDao mAsyncTaskDao;

        insertAsyncTask(TheatreEventDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final TheatreEvent... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }


    }
    public void deleteAll()  {
        new TheatreEventRepository.deleteAllWordsAsyncTask(mTheatreEventDao).execute();
    }
    private static class deleteWordAsyncTask extends AsyncTask<TheatreEvent, Void, Void> {
        private TheatreEventDao mAsyncTaskDao;

        deleteWordAsyncTask(TheatreEventDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final TheatreEvent... params) {
            mAsyncTaskDao.deleteEvent(params[0]);
            return null;
        }
    }
    public void deleteEvent(TheatreEvent theatreEvent)  {
        new TheatreEventRepository.deleteWordAsyncTask(mTheatreEventDao).execute(theatreEvent);
    }
}

