package uz.gita.memorygame.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import uz.gita.memorygame.data.LeaderDatabase
import uz.gita.memorygame.data.LeaderboardDao


@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    fun provideNewsDatabase(@ApplicationContext context: Context): LeaderDatabase =
        LeaderDatabase.init(context)

    @Provides
    fun provideNewsDao(newsDatabase: LeaderDatabase): LeaderboardDao = newsDatabase.getDao()


}