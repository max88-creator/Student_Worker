package com.example.student_worker.data.repository

import com.example.student_worker.domain.dao.PersonDao
import com.example.student_worker.domain.models.Person
import com.example.student_worker.domain.repository.PersonRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PersonRepositoryImpl @Inject constructor(
    private val dao: PersonDao
): PersonRepository {
    override suspend fun insertPerson(item: Person) {
        dao.insertPerson(item)
    }

    override suspend fun deletePerson(id: Int): Int {
        return dao.deletePerson(id)
    }

    override suspend fun incrementAge(id: Int) {
        dao.incrementAge(id)
    }

    override suspend fun decrementAge(id: Int) {
        dao.decrementAge(id)
    }

    override fun observePeople(): Flow<List<Person>> {
        return dao.observePeople()
    }
}