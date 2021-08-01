package com.example.myapplication.ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.myapplication.DB.Table.UpComingMovieTable;
import com.example.myapplication.Repositiories.UpComingMovieDatabaseRepo;

import java.util.List;

public class UpComingMovieDatabaseViewModel extends AndroidViewModel {

    private UpComingMovieDatabaseRepo upComingMovieDatabaseRepo ;
    private LiveData<List<UpComingMovieTable>> allMovies;
    public UpComingMovieDatabaseViewModel(@NonNull Application application) {
        super(application);
        upComingMovieDatabaseRepo = new UpComingMovieDatabaseRepo(application);
        allMovies = upComingMovieDatabaseRepo.getAllMovies();
    }



    public void insert(UpComingMovieTable movie) {
        upComingMovieDatabaseRepo.insertMovie(movie);
    }
    public void deleteAllMovies() {
        upComingMovieDatabaseRepo.deleteAllMovies();
    }
    public LiveData<List<UpComingMovieTable>> getAllMovies() {
        return allMovies;
    }
}
