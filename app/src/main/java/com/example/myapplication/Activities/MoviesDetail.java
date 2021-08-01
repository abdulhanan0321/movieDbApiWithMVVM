package com.example.myapplication.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.myapplication.Model.MovieDetailModel;
import com.example.myapplication.Model.UpcomingMoviesModel;
import com.example.myapplication.R;
import com.example.myapplication.Utils.Constant;
import com.example.myapplication.Utils.Util;
import com.example.myapplication.ViewModels.MovieCategoryViewModel;
import com.example.myapplication.ViewModels.MovieDetailViewModel;

import java.util.List;

public class MoviesDetail extends AppCompatActivity implements View.OnClickListener {

    MovieDetailViewModel movieDetailViewModel;
    MovieDetailModel myMovieDetailModel;
    int movieID;
    String movieTitle, movieReleaseDate, movieOverView, moviePoster;

    ImageView toolbarBackBtn, movieImage;
    TextView toolbarName, name, releaseDate, description, movieTime, movieStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies_detail);

        initView();
        setOnClick();
        if (Util.checkConnection(this)) {
            settingViewModel();
        } else {
            myMovieDetailModel = new MovieDetailModel();
            myMovieDetailModel.setId(movieID);
            myMovieDetailModel.setOriginal_title(movieTitle);
            myMovieDetailModel.setRelease_date(movieReleaseDate);
            myMovieDetailModel.setOverview(movieOverView);
            myMovieDetailModel.setPoster_path(moviePoster);
            myMovieDetailModel.setRuntime(0);
            myMovieDetailModel.setStatus("not");
            setValues();
        }
    }

    /**
     * function to initiate view items
     * */
    private void initView(){
        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            movieID = bundle.getInt("movieID",0);
            movieTitle = bundle.getString("movieTitle","");
            movieReleaseDate = bundle.getString("movieReleaseDate","");
            movieOverView = bundle.getString("movieOverView","");
            moviePoster = bundle.getString("moviePoster","");
        }

        toolbarBackBtn = findViewById(R.id.toolbarBackBtn);
        toolbarName = findViewById(R.id.toolbarName);
        toolbarName.setText("Movie Detail");

        movieImage = findViewById(R.id.movieImage);
        name = findViewById(R.id.name);
        releaseDate = findViewById(R.id.releaseDate);
        description = findViewById(R.id.description);
        movieTime = findViewById(R.id.movieTime);
        movieStatus = findViewById(R.id.movieStatus);

    }

    /**
     * function to set click listener to some view
     * */
    private void setOnClick(){
        toolbarBackBtn.setOnClickListener(this::onClick);
    }

    /**
     * function to initiate viewModel Provider Class
     * */
    private void settingViewModel(){
        movieDetailViewModel = ViewModelProviders.of(this).get(MovieDetailViewModel.class);
        movieDetailViewModel.init(movieID, Constant.API_KEY);
        movieDetailViewModel.getMovieDetailRepository().observe(this, movieDetailModel -> {
            myMovieDetailModel = movieDetailModel;
            setValues();
        });
    }

    /**
     * function to set Values from model to views
     * */
    private void setValues(){

        if (myMovieDetailModel.getPoster_path() != null){

            Glide.with(this).load(Constant.IMAGE_BASE_URL + myMovieDetailModel.getPoster_path())
                    .placeholder(R.drawable.poster_placeholder)
                    .error(R.drawable.poster_placeholder)
                    .into(movieImage);
        }

        name.setText(myMovieDetailModel.getOriginal_title());
        releaseDate.setText("Release Date: "+myMovieDetailModel.getRelease_date());
        description.setText(myMovieDetailModel.getOverview());
        movieTime.setText("Runtime: "+myMovieDetailModel.getRuntime());
        movieStatus.setText("Status: "+myMovieDetailModel.getStatus());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.toolbarBackBtn:
                onBackPressed();
                break;
        }
    }
}