package uz.gita.memorygame.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.memorygame.data.LeaderboardDao
import uz.gita.memorygame.data.LeaderboardRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface AppModule {

    @Binds
    @Singleton
    fun provideRepository(db:LeaderboardDao): LeaderboardRepository

}