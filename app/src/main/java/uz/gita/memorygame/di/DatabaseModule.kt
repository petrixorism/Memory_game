package uz.gita.memorygame.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import uz.gita.memorygame.data.LeaderDatabase
import uz.gita.memorygame.data.LeaderboardDao
import uz.gita.memorygame.data.LeaderboardRepository
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideNewsDatabase(@ApplicationContext context: Context): LeaderDatabase =
        LeaderDatabase.init(context)

    @Provides
    @Singleton
    fun provideNewsDao(newsDatabase: LeaderDatabase): LeaderboardDao = newsDatabase.getDao()


    @Provides
    @Singleton
    fun provideRepository(db:LeaderboardDao): LeaderboardRepository = LeaderboardRepository(db)

}