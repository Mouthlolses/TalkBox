package com.mytalkbox.talkbox.di

import android.content.Context
import androidx.room.Room
import com.mytalkbox.talkbox.data.local.dao.UserDao
import com.mytalkbox.talkbox.data.local.database.Database
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideRoomDatabase(@ApplicationContext context: Context) : Database {
        return Room.databaseBuilder(
            context,
            Database::class.java,
            "app_database"
        ).build()
    }

    @Provides
    fun provideUserDao(db: Database): UserDao = db.userDao()
}