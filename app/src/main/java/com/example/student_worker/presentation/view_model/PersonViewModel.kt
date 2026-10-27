package com.example.student_worker.presentation.view_model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.student_worker.diialog.DialogController
import com.example.student_worker.diialog.DialogEvent
import com.example.student_worker.domain.models.Person
import com.example.student_worker.domain.models.Student
import com.example.student_worker.domain.repository.PersonRepository
import com.example.student_worker.domain.repository.StudentRepository
import com.example.student_worker.presentation.screens.person.PersonListEvent
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
    private val studentRepository: StudentRepository
) : ViewModel(), DialogController {
    private val _uiPersonState = MutableStateFlow(
        Person()
    )

    val uiPersonState = _uiPersonState.asStateFlow()

    override var dialogTitle = mutableStateOf("")
        private set
    override var editableText = mutableStateOf("")
        private set
    override var openDialog = mutableStateOf(false)
        private set
    override var showEditableText = mutableStateOf(false)
        private set
    val getAllPerson: StateFlow<List<Person>> = repository.observePeople()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(3000),
            initialValue = emptyList()
        )

    fun onEvent(
        //event: PersonListEvent
         ) {
        openDialog.value = true
        if (editableText.value.isEmpty()) {
            return
        } else {
            viewModelScope.launch {
                studentRepository.insertStudent(
                    Student(
                        id = uiPersonState.value.id,
                        name = uiPersonState.value.name,
                        age = uiPersonState.value.age,
                        specialization = editableText.value
                    )
                )
            }
        }
    }

    override fun onDialogEvent(event: DialogEvent) {
        when (event) {
            is DialogEvent.OnCancel -> {
                openDialog.value = false
            }

            is DialogEvent.OnConfirm -> {
            //    onEvent(PersonListEvent.OnItemSave)
                openDialog.value = false
            }

            is DialogEvent.OnTextChange -> {
                editableText.value = event.text
            }
        }
    }

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