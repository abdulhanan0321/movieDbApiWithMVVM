package com.example.myapplication.Repositiories;

import androidx.lifecycle.MutableLiveData;

import com.example.myapplication.Model.MovieDetailModel;
import com.example.myapplication.Model.UpcomingMoviesModel;
import com.example.myapplication.Retrofit.RetrofitFactory;
import com.example.myapplication.Retrofit.RetrofitService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MovieDetailRepository {

    private static MovieDetailRepository movieDetailRepository;

    public static MovieDetailRepository getInstance(){
        if (movieDetailRepository == null){
            movieDetailRepository = new MovieDetailRepository();
        }
        return movieDetailRepository;
    }

    private RetrofitService retrofitServices;

    public MovieDetailRepository(){
        Retrofit retrofit = RetrofitFactory.getRetrofit();
        retrofitServices = retrofit.create(RetrofitService.class);
    }

    public MutableLiveData<MovieDetailModel> getMoviesDetail(int movieID, String apiKey){

        MutableLiveData<MovieDetailModel> newsData = new MutableLiveData<>();
        retrofitServices.getMovieDetail(movieID,apiKey).enqueue(new Callback<MovieDetailModel>() {
            @Override
            public void onResponse(Call<MovieDetailModel> call, Response<MovieDetailModel> response) {
                if (response.isSuccessful()){
                    newsData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<MovieDetailModel> call, Throwable t) {
                newsData.setValue(null);
            }
        });
        return newsData;
    }
}
