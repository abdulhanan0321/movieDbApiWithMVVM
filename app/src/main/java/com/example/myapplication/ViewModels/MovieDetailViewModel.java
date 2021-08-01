package com.example.myapplication.ViewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapplication.Model.MovieDetailModel;
import com.example.myapplication.Repositiories.MovieDetailRepository;

public class MovieDetailViewModel extends ViewModel {

    private MutableLiveData<MovieDetailModel> mutableLiveData;
    private MovieDetailRepository movieDetailRepository;

    public void init(int movieID, String apiKey){
        if (mutableLiveData != null){
            return;
        }
        movieDetailRepository = MovieDetailRepository.getInstance();
        mutableLiveData = movieDetailRepository.getMoviesDetail(movieID, apiKey);

    }

    public LiveData<MovieDetailModel> getMovieDetailRepository() {
        return mutableLiveData;
    }
}
