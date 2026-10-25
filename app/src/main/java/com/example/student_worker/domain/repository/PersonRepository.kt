package com.example.student_worker.domain.repository

import com.example.student_worker.domain.models.Person
import kotlinx.coroutines.flow.Flow

interface PersonRepository {
    suspend fun insertPerson(item: Person)
    suspend fun deletePerson(id: Int): Int
    suspend fun incrementAge(id: Int?)
    suspend fun decrementAge(id: Int?)
    fun observePeople(): Flow<List<Person>>
}