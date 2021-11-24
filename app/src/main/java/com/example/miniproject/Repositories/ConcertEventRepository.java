package com.example.miniproject.Repositories;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.miniproject.Dao.CinemaEventDao;
import com.example.miniproject.Dao.ConcertEventDao;

import com.example.miniproject.Entities.CinemaEvent;
import com.example.miniproject.Entities.ConcertEvent;

import java.util.List;

public class ConcertEventRepository {
    private ConcertEventDao mConcertEventDao;
    private LiveData<List<ConcertEvent>> mAllEvents;

    public ConcertEventRepository(Application application) {
        WordRoomDatabase db = WordRoomDatabase.getDatabase(application);
        mConcertEventDao = db.concertEventDao();
        mAllEvents = mConcertEventDao.getAllEvents();
    }
    private static class deleteAllWordsAsyncTask extends AsyncTask<Void, Void, Void> {
        private ConcertEventDao mAsyncTaskDao;

        deleteAllWordsAsyncTask(ConcertEventDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mAsyncTaskDao.deleteAll();
            return null;
        }
    }
    public LiveData<List<ConcertEvent>> getmAllEvents() {
        return mAllEvents;
    }

    public void insert (ConcertEvent concertEvent) {
        new ConcertEventRepository.insertAsyncTask(mConcertEventDao).execute(concertEvent);
    }

    private static class insertAsyncTask extends AsyncTask<ConcertEvent, Void, Void> {

        private ConcertEventDao mAsyncTaskDao;

        insertAsyncTask(ConcertEventDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final ConcertEvent... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }


    }
    public void deleteAll()  {
        new ConcertEventRepository.deleteAllWordsAsyncTask(mConcertEventDao).execute();
    }
    private static class deleteWordAsyncTask extends AsyncTask<ConcertEvent, Void, Void> {
        private ConcertEventDao mAsyncTaskDao;

        deleteWordAsyncTask(ConcertEventDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final ConcertEvent... params) {
            mAsyncTaskDao.deleteEvent(params[0]);
            return null;
        }
    }
    public void deleteEvent(ConcertEvent concertEvent)  {
        new ConcertEventRepository.deleteWordAsyncTask(mConcertEventDao).execute(concertEvent);
    }
}
