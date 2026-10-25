package com.example.student_worker.age_manager

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject


class AgeManagerImpl @Inject constructor(): AgeManager{
    private val _age = MutableStateFlow(0)
    override val age = _age.asStateFlow()
}