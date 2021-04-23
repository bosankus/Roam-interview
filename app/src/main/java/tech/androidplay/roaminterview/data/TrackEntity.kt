package tech.androidplay.roaminterview.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "track_table")
data class TrackEntity(
    @ColumnInfo(name = "latitude")
    var latitude: String,

    @ColumnInfo(name = "longitude")
    var longitude: String,

    @ColumnInfo(name = "accuracy")
    var accuracy: Double,
) : Serializable {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}
