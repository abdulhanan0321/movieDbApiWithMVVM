package com.example.myapplication.Repositiories;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.myapplication.DB.DAO.UpComingMovieDao;
import com.example.myapplication.DB.MyAppDB;
import com.example.myapplication.DB.Table.UpComingMovieTable;

import java.util.List;

public class UpComingMovieDatabaseRepo {

    private UpComingMovieDao upComingMovieDao;
    private LiveData<List<UpComingMovieTable>> allUpComingMovies;

    public UpComingMovieDatabaseRepo(Application application) { //application is subclass of context
//        AppDataBase database = new AppDataBase(application);
        MyAppDB database = MyAppDB.getInstance(application);
        upComingMovieDao = database.movieDao();
        allUpComingMovies = upComingMovieDao.getAllUpComingMovie();
    }

    /**
     * methods for database operations
     * */
    public void insertMovie(UpComingMovieTable note) {
        new InsertMovieAsyncTask(upComingMovieDao).execute(note);
    }
    public void deleteAllMovies() {
        new DeleteAllMovieAsyncTask(upComingMovieDao).execute();
    }
    public LiveData<List<UpComingMovieTable>> getAllMovies() {
        return allUpComingMovies;
    }


    private static class InsertMovieAsyncTask extends AsyncTask<UpComingMovieTable, Void, Void> {
        //static : doesnt have reference to the
        // repo itself otherwise it could cause memory leak!
        private UpComingMovieDao upComingMovieDao;

        private InsertMovieAsyncTask(UpComingMovieDao movieDao) {
            this.upComingMovieDao = movieDao;
        }

        @Override
        protected Void doInBackground(UpComingMovieTable... movies) { // ...  is similar to array
            upComingMovieDao.insertUpComingMovie(movies[0]); //single movies
            return null;
        }
    }

    private static class DeleteAllMovieAsyncTask extends AsyncTask<Void, Void, Void> {
        private UpComingMovieDao upComingMovieDao;

        private DeleteAllMovieAsyncTask(UpComingMovieDao movieDao) {
            this.upComingMovieDao = movieDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            upComingMovieDao.deleteAllUpComingMovie();
            return null;
        }
    }
}

