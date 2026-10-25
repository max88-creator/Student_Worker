package com.example.student_worker.presentation.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.student_worker.age_manager.AgeManager
import com.example.student_worker.domain.models.Student
import com.example.student_worker.domain.repository.StudentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StudentViewModel @Inject constructor(
    private val repository: StudentRepository
): ViewModel() {
    private val _uiStudentState = MutableStateFlow(
        Student(
            name = "",
            age = 0,
            specialization = ""
        )
    )

    val uiStudentState = _uiStudentState.asStateFlow()

    val getAllStudent: StateFlow<List<Student>> = repository.observeStudents()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(3000),
            initialValue = emptyList()
        )

    fun addStudent(item: Student) {
        viewModelScope.launch {
            repository.insertStudent(item)
        }
    }

    fun deleteStudent(id: Int) {
        viewModelScope.launch {
            repository.deleteStudent(id)
        }
    }

    fun incrementAge(id: Int) {
        viewModelScope.launch {
            repository.incrementAge(id)
        }
    }

    fun decrementAge(id: Int) {
       viewModelScope.launch {
           repository.decrementAge(id)
       }
    }
}