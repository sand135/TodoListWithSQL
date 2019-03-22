package se.iths.sandra.labb2todolistwithsqlight;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

@Database(entities = {Todo.class}, version = 1)
public abstract class TodoRoomDataBase extends RoomDatabase {

        public abstract TodoDao todoDao();

        private static volatile TodoRoomDataBase INSTANCE;

        static TodoRoomDataBase getDatabase(final Context context) {
            if (INSTANCE == null) {
                synchronized (TodoRoomDataBase.class) {
                    if (INSTANCE == null) {
                        // Create database here using Room's databasebuilder using TodoRoomDatabase class context and names it "word_databse"
                        INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                TodoRoomDataBase.class, "word_database")
                                .addCallback(sRoomDatabaseCallback)
                                .build();
                    }
                }
            }
            return INSTANCE;
        }
        private static RoomDatabase.Callback sRoomDatabaseCallback =
                new RoomDatabase.Callback(){

                    @Override
                    public void onOpen (@NonNull SupportSQLiteDatabase db){
                        super.onOpen(db);
                        new PopulateDbAsync(INSTANCE).execute();
                    }
                };

        private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

            private final TodoDao mDao;

            PopulateDbAsync(TodoRoomDataBase db) {
                mDao = db.todoDao();
            }

            @Override
            protected Void doInBackground(final Void... params) {
               //mDao.deleteAll();
                return null;
            }
        }

}

