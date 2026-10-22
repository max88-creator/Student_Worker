package com.example.student_worker.data.repository

import com.example.student_worker.domain.dao.StudentDao
import com.example.student_worker.domain.models.Student
import com.example.student_worker.domain.repository.StudentRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class StudentRepositoryImpl @Inject constructor(
    private val dao: StudentDao
) : StudentRepository {
    override suspend fun insertStudent(item: Student) {
        dao.insertPerson(item)
    }

    override suspend fun deleteStudent(id: Int): Int {
        return dao.deletePerson(id)
    }

    override suspend fun incrementAge(id: Int) {
        dao.incrementAge(id)
    }

    override suspend fun decrementAge(id: Int) {
        dao.decrementAge(id)
    }

    override fun observeStudents(): Flow<List<Student>> {
        return dao.observePeople()
    }
}