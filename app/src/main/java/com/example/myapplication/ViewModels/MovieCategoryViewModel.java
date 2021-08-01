package com.example.myapplication.ViewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapplication.Model.UpcomingMoviesModel;
import com.example.myapplication.Repositiories.MovieCategoryRepositories;

public class MovieCategoryViewModel extends ViewModel {

    private MutableLiveData<UpcomingMoviesModel> mutableLiveData;
    private MovieCategoryRepositories movieCategoryRepositories;

    public void init(String apiKey){
        if (mutableLiveData != null){
            return;
        }
        movieCategoryRepositories = MovieCategoryRepositories.getInstance();
        mutableLiveData = movieCategoryRepositories.getMoviesCategoryList(apiKey);

    }

    public LiveData<UpcomingMoviesModel> getMovieCategoryRepository() {
        return mutableLiveData;
    }
}
