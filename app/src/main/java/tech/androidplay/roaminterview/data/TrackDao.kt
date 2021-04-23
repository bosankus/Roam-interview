package tech.androidplay.roaminterview.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface TrackDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAsteroid(trackEntity: TrackEntity)

    @Query("SELECT * FROM track_table")
    fun getPastAsteroids(): LiveData<List<TrackEntity>>
}