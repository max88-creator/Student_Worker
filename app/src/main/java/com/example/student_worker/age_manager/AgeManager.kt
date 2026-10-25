package com.example.student_worker.age_manager

import kotlinx.coroutines.flow.StateFlow

interface AgeManager {
    val age: StateFlow<Int>
}