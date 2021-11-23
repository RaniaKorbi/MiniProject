package com.example.miniproject.Repositories;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.miniproject.Dao.CinemaEventDao;
import com.example.miniproject.Entities.CinemaEvent;

import java.util.List;

public class CinemaEventRepository {
    private CinemaEventDao mCinemaEventDao;
    private LiveData<List<CinemaEvent>> mAllEvents;

    public CinemaEventRepository(Application application) {
        WordRoomDatabase db = WordRoomDatabase.getDatabase(application);
        mCinemaEventDao = db.cinemaEventDao();
        mAllEvents = mCinemaEventDao.getAllEvents();
    }
    private static class deleteAllWordsAsyncTask extends AsyncTask<Void, Void, Void> {
        private CinemaEventDao mAsyncTaskDao;

        deleteAllWordsAsyncTask(CinemaEventDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mAsyncTaskDao.deleteAll();
            return null;
        }
    }
    public LiveData<List<CinemaEvent>> getmAllEvents() {
        return mAllEvents;
    }

    public void insert (CinemaEvent cinemaEvent) {
        new insertAsyncTask(mCinemaEventDao).execute(cinemaEvent);
    }

    private static class insertAsyncTask extends AsyncTask<CinemaEvent, Void, Void> {

        private CinemaEventDao mAsyncTaskDao;

        insertAsyncTask(CinemaEventDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final CinemaEvent... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
    public void deleteAll()  {
        new deleteAllWordsAsyncTask(mCinemaEventDao).execute();
    }
    private static class deleteWordAsyncTask extends AsyncTask<CinemaEvent, Void, Void> {
        private CinemaEventDao mAsyncTaskDao;

        deleteWordAsyncTask(CinemaEventDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final CinemaEvent... params) {
            mAsyncTaskDao.deleteEvent(params[0]);
            return null;
        }
    }
    public void deleteEvent(CinemaEvent cinemaEvent)  {
        new deleteWordAsyncTask(mCinemaEventDao).execute(cinemaEvent);
    }
}
