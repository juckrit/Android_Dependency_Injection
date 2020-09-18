package com.example.mytestapp.Activity.Model.Room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mytestapp.Activity.Model.BigSummaryModel

@Dao
interface BigSummaryDao {
    @Query("SELECT * FROM bigSummaryModel_table")
    fun getMovies(): List<BigSummaryModel>

    /**
     * Insert a movie in the database. If the movie already exists, replace it.
     *
     * @param movie the movie to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertMovie(bigSummaryModel: BigSummaryModel)

    @Query("DELETE FROM bigSummaryModel_table")
    fun deleteAllMovies()
}