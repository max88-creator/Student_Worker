package com.example.student_worker.domain.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.student_worker.domain.models.Student
import kotlinx.coroutines.flow.Flow

@Dao
interface StudentDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPerson(item: Student)

    @Query("DELETE FROM student WHERE id = :id")
    suspend fun deletePerson(id: Int): Int

    @Query("UPDATE student SET age = age + 1 WHERE id = :id")
    suspend fun incrementAge(id: Int)

    @Query("UPDATE student SET age = age - 1 WHERE id = :id")
    suspend fun decrementAge(id: Int)

    @Query("SELECT * FROM student")
    fun observePeople(): Flow<List<Student>>
}