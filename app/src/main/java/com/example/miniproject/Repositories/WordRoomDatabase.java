package com.example.miniproject.Repositories;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.miniproject.Dao.CinemaEventDao;
import com.example.miniproject.Dao.ConcertEventDao;
import com.example.miniproject.Dao.TheatreEventDao;
import com.example.miniproject.Entities.CinemaEvent;
import com.example.miniproject.Entities.ConcertEvent;
import com.example.miniproject.Entities.TheatreEvent;

@Database(entities = {CinemaEvent.class, ConcertEvent.class, TheatreEvent.class}, version = 1, exportSchema = false)
public abstract class WordRoomDatabase extends RoomDatabase {

    public abstract ConcertEventDao concertEventDao();
    public abstract CinemaEventDao cinemaEventDao();
    public abstract TheatreEventDao theatreEventDao();
    private static WordRoomDatabase INSTANCE;
    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback(){

                @Override
                public void onOpen (@NonNull SupportSQLiteDatabase db){
                    super.onOpen(db);
                    new PopulateDbAsync(INSTANCE).execute();
                }
            };

    static WordRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (WordRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            WordRoomDatabase.class, "event_database")
                            // Wipes and rebuilds instead of migrating
                            // if no Migration object.
                            // Migration is not part of this practical.
                            .fallbackToDestructiveMigration() .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }
    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final CinemaEventDao mCinemaEventDao;
        //private final ConcertEventDao mConcertEventDao;
        //private final TheatreEventDao mTeatreEventDao;
        String [] words = {"Film Dachra","Film Gun","Film Repeat"};

        PopulateDbAsync(WordRoomDatabase db) {
            mCinemaEventDao = db.cinemaEventDao();
        }
        /*PopulateDbAsync1(WordRoomDatabase db1) {
            mConcertEventDao = db1.concertEventDao();
        }
*/
        @Override
        protected Void doInBackground(final Void... params) {
            // Start the app with a clean database every time.
            // Not needed if you only populate on creation.
            mCinemaEventDao.deleteAll();

            for( int i = 0; i <= words.length - 1; i++) {
                CinemaEvent cinemaEvent = new CinemaEvent(words[i]);
                mCinemaEventDao.insert(cinemaEvent);
            }
            return null;
        }
    }
}
