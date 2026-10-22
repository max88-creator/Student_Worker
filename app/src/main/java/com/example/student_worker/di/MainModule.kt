package com.example.student_worker.di

import android.app.Application
import androidx.room.Room
import com.example.student_worker.data.db.MainDB
import com.example.student_worker.domain.dao.PersonDao
import com.example.student_worker.domain.dao.StudentDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MainModule {
    @Provides
    @Singleton
    fun provideMainDb(app: Application): MainDB {
        return Room.databaseBuilder(
            context = app,
            klass = MainDB::class.java,
            name = "main_db"
        )
            .build()
    }

    @Provides
    fun provideStudentDao(mainDB: MainDB): StudentDao {
        return mainDB.provideStudentDao()
    }

    @Provides
    fun providePersonDao(mainDB: MainDB): PersonDao {
        return mainDB.providePersonDao()
    }
}