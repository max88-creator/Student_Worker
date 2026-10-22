package com.example.student_worker.domain.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.student_worker.domain.models.Person
import kotlinx.coroutines.flow.Flow

@Dao
interface PersonDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPerson(item: Person)

    @Query("DELETE FROM person WHERE id = :id")
    suspend fun deletePerson(id: Int): Int

    @Query("UPDATE person SET age = age + 1 WHERE id = :id")
    suspend fun incrementAge(id: Int)

    @Query("UPDATE person SET age = age - 1 WHERE id = :id")
    suspend fun decrementAge(id: Int)

    @Query("SELECT * FROM person")
    fun observePeople(): Flow<List<Person>>


//    @Query("UPDATE vegetable SET count = count + 1 WHERE id = :id")
//    suspend fun incrementCount(id: Int)
//
//    @Query("UPDATE vegetable SET count = count - 1 WHERE id = :id")
//    suspend fun decrementCount(id: Int)
//
//    @Query("SELECT * FROM vegetable")
//    fun getAllVegetable(): Flow<List<Vegetable>>
//@Query("DELETE FROM basket_item WHERE id = :id")
//suspend fun delete(id: Int): Int
}