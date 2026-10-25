package com.example.student_worker.presentation.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.student_worker.domain.models.Person
import com.example.student_worker.domain.repository.PersonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PersonViewModel @Inject constructor(
    private val repository: PersonRepository,
): ViewModel() {
    private val _uiPersonState = MutableStateFlow(
        Person()
    )

    val uiPersonState = _uiPersonState.asStateFlow()

    val getAllPerson: StateFlow<List<Person>> = repository.observePeople()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(3000),
            initialValue = emptyList()
        )

    fun addPerson(item: Person) {
        viewModelScope.launch {
            repository.insertPerson(item)
        }
    }

    fun deletePerson(id: Int) {
        viewModelScope.launch {
            repository.deletePerson(id)
        }
    }

    fun incrementAge(id: Int?) {
       viewModelScope.launch {
           repository.incrementAge(id)
       }
    }

    fun decrementAge(id: Int?) {
        viewModelScope.launch {
            repository.decrementAge(id)
        }
    }
}