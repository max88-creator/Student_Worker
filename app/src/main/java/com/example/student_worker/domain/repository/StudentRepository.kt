package com.example.student_worker.domain.repository

import com.example.student_worker.domain.models.Student
import kotlinx.coroutines.flow.Flow

interface StudentRepository {
    suspend fun insertStudent(item: Student)
    suspend fun deleteStudent(id: Int): Int
    suspend fun incrementAge(id: Int)
    suspend fun decrementAge(id: Int)
    fun observeStudents(): Flow<List<Student>>
}