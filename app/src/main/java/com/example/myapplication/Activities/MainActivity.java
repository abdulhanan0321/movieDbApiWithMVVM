package com.example.myapplication.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.DB.Table.UpComingMovieTable;
import com.example.myapplication.R;
import com.example.myapplication.Utils.Util;
import com.example.myapplication.ViewModels.MovieCategoryViewModel;
import com.example.myapplication.ViewModels.UpComingMovieDatabaseViewModel;
import com.example.myapplication.adapters.MovieCategoryAdapter;
import com.example.myapplication.Model.UpcomingMoviesModel;
import com.example.myapplication.Utils.Constant;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView movieCategoryRecycler;
    List<UpcomingMoviesModel.Result> movieCategoryList;
    MovieCategoryAdapter movieCategoryAdapter;

    MovieCategoryViewModel movieCategoryViewModel;
    UpComingMovieDatabaseViewModel upComingMovieDatabaseViewModel;
    ImageView toolbarBackBtn;
    TextView toolbarName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        if (Util.checkConnection(this)) {
            settingViewModel();
        }else {
            settingDatabaseViewModel();
        }

    }

    /**
     * function to initaite view items
     * */
    private void initView(){
        toolbarBackBtn = findViewById(R.id.toolbarBackBtn);
        toolbarBackBtn.setVisibility(View.GONE);
        toolbarName = findViewById(R.id.toolbarName);
        toolbarName.setText("Upcoming Movies");

        movieCategoryRecycler = findViewById(R.id.movieCategoryRecycler);
        movieCategoryRecycler.setLayoutManager(new LinearLayoutManager(this));

        movieCategoryList = new ArrayList<>();
        movieCategoryAdapter = new MovieCategoryAdapter(movieCategoryList);
        movieCategoryRecycler.setAdapter(movieCategoryAdapter);
        movieCategoryAdapter.setOnClick(new MovieCategoryAdapter.OnClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(MainActivity.this, MoviesDetail.class);
                intent.putExtra("movieID", movieCategoryList.get(position).getId());
                intent.putExtra("movieTitle", movieCategoryList.get(position).getOriginal_title());
                intent.putExtra("movieReleaseDate", movieCategoryList.get(position).getRelease_date());
                intent.putExtra("movieOverView", movieCategoryList.get(position).getOverview());
                intent.putExtra("moviePoster", movieCategoryList.get(position).getPoster_path());
                startActivity(intent);
            }
        });

        upComingMovieDatabaseViewModel = new ViewModelProvider(this).get(UpComingMovieDatabaseViewModel.class);
    }

    /**
     * function to initiate viewModel Provider Class
     * */
    private void settingViewModel(){
        movieCategoryViewModel = ViewModelProviders.of(this).get(MovieCategoryViewModel.class);
        movieCategoryViewModel.init(Constant.API_KEY);
        movieCategoryViewModel.getMovieCategoryRepository().observe(this, movieCategoryModel -> {
            List<UpcomingMoviesModel.Result> newsArticles = movieCategoryModel.getResults();
            movieCategoryList.addAll(newsArticles);
            movieCategoryAdapter.notifyDataSetChanged();

            if (newsArticles.size() > 0){
                upComingMovieDatabaseViewModel.deleteAllMovies();

                for (int i=0; i<newsArticles.size(); i++) {
                    UpComingMovieTable model = new UpComingMovieTable();
                    model.setMovieID(newsArticles.get(i).getId());
                    model.setMovieTitle(newsArticles.get(i).getOriginal_title());
                    model.setMovieReleaseDate(newsArticles.get(i).getRelease_date());
                    model.setMovieOverView(newsArticles.get(i).getOverview());

                    upComingMovieDatabaseViewModel.insert(model);
                }
            }
        });
    }

    private void settingDatabaseViewModel(){
        upComingMovieDatabaseViewModel.getAllMovies().observe(this, new Observer<List<UpComingMovieTable>>() {
            @Override
            public void onChanged(List<UpComingMovieTable> notes) { //called every time data changes

                if (movieCategoryList != null)
                    movieCategoryList.clear();

                for (int i=0; i<notes.size(); i++) {
                    UpcomingMoviesModel.Result model = new UpcomingMoviesModel.Result();

                    model.setId(notes.get(i).getId());
                    model.setOriginal_title(notes.get(i).getMovieTitle());
                    model.setRelease_date(notes.get(i).getMovieReleaseDate());
                    model.setOverview(notes.get(i).getMovieOverView());

                    movieCategoryList.add(model);
                }
                movieCategoryAdapter.notifyDataSetChanged();

            }
        }); //get all Movie data
    }
}