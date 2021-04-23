package tech.androidplay.roaminterview.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ServiceComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ServiceScoped
import tech.androidplay.roaminterview.data.TrackDatabase

@Module
@InstallIn(ServiceComponent::class)
class ServiceModule {

    @ServiceScoped
    @Provides
    fun provideTrackDatabase(@ApplicationContext app: Context) =
        Room.databaseBuilder(
            app,
            TrackDatabase::class.java,
            "TRACK_DATABASE"
        ).build()

    @ServiceScoped
    @Provides
    fun provideTrackDao(db: TrackDatabase) = db.trackDao()

}