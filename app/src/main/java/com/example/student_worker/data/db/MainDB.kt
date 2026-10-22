package com.example.student_worker.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.student_worker.domain.dao.PersonDao
import com.example.student_worker.domain.dao.StudentDao
import com.example.student_worker.domain.models.Person
import com.example.student_worker.domain.models.Student

@Database(entities = [Person::class, Student::class],
    version = 1,
    exportSchema = false
)
abstract class MainDB: RoomDatabase() {
    abstract fun providePersonDao(): PersonDao
    abstract fun provideStudentDao(): StudentDao
}