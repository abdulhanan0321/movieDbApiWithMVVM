package com.example.myapplication.DB.Table;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "UpComingMovieTable")
public class UpComingMovieTable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "movieID")
    int movieID;
    @ColumnInfo(name = "movieTitle")
    String movieTitle;
    @ColumnInfo(name = "movieReleaseDate")
    String movieReleaseDate;
    @ColumnInfo(name = "movieOverView")
    String movieOverView;
    @ColumnInfo(name = "poster_path")
    String poster_path;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMovieID() {
        return movieID;
    }

    public void setMovieID(int movieID) {
        this.movieID = movieID;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public String getMovieReleaseDate() {
        return movieReleaseDate;
    }

    public void setMovieReleaseDate(String movieReleaseDate) {
        this.movieReleaseDate = movieReleaseDate;
    }

    public String getMovieOverView() {
        return movieOverView;
    }

    public void setMovieOverView(String movieOverView) {
        this.movieOverView = movieOverView;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }
}
