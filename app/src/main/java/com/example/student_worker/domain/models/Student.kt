package com.example.student_worker.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "student")
data class Student(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val name: String,
    val age: Int,
    val specialization: String
)
