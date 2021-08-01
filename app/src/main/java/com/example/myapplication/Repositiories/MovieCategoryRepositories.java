package com.example.myapplication.Repositiories;

import android.app.Application;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.example.myapplication.Model.UpcomingMoviesModel;
import com.example.myapplication.Retrofit.RetrofitFactory;
import com.example.myapplication.Retrofit.RetrofitService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MovieCategoryRepositories {

    private static MovieCategoryRepositories movieCategoryRepositories;

    public static MovieCategoryRepositories getInstance(){
        if (movieCategoryRepositories == null){
            movieCategoryRepositories = new MovieCategoryRepositories();
        }
        return movieCategoryRepositories;
    }

    private RetrofitService retrofitServices;

    public MovieCategoryRepositories(){
        Retrofit retrofit = RetrofitFactory.getRetrofit();
        retrofitServices = retrofit.create(RetrofitService.class);
    }

    public MutableLiveData<UpcomingMoviesModel> getMoviesCategoryList(String apiKey){

        MutableLiveData<UpcomingMoviesModel> newsData = new MutableLiveData<>();
        retrofitServices.getMovieCategoryList(apiKey).enqueue(new Callback<UpcomingMoviesModel>() {
            @Override
            public void onResponse(Call<UpcomingMoviesModel> call, Response<UpcomingMoviesModel> response) {
                if (response.isSuccessful()){
                    newsData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<UpcomingMoviesModel> call, Throwable t) {
                newsData.setValue(null);
            }
        });
        return newsData;
    }
}
