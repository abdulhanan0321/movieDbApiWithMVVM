package com.example.myapplication.DB.DAO

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myapplication.DB.Table.UpComingMovieTable

@Dao
interface UpComingMovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUpComingMovie(history: UpComingMovieTable?)

    @Query("SELECT * from UpComingMovieTable ORDER BY id ASC")
    fun getAllUpComingMovie(): LiveData<List<UpComingMovieTable>>?

    @Query("DELETE FROM UpComingMovieTable")
    fun deleteAllUpComingMovie()
}