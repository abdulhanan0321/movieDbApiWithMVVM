package com.example.myapplication.Retrofit;

import com.example.myapplication.Model.MovieDetailModel;
import com.example.myapplication.Model.UpcomingMoviesModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetrofitService {


    @GET("movie/upcoming")
    Call<UpcomingMoviesModel> getMovieCategoryList(
            @Query("api_key") String api_key);

    @GET("movie/{movieID}")
    Call<MovieDetailModel> getMovieDetail(
            @Path("movieID") int movieID,
            @Query("api_key") String api_key);

}
