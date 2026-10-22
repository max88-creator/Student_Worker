package com.example.student_worker.di

import com.example.student_worker.data.repository.PersonRepositoryImpl
import com.example.student_worker.data.repository.StudentRepositoryImpl
import com.example.student_worker.domain.repository.PersonRepository
import com.example.student_worker.domain.repository.StudentRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindPersonRepository(personRepositoryImpl: PersonRepositoryImpl): PersonRepository

    @Binds
    @Singleton
    abstract fun bindStudentRepository(studentRepositoryImpl: StudentRepositoryImpl): StudentRepository
}