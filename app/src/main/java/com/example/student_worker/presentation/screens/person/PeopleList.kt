package com.example.student_worker.presentation.screens.person

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.student_worker.diialog.MainDialog
import com.example.student_worker.domain.models.Person
import com.example.student_worker.presentation.view_model.PersonViewModel

@Composable
fun PeopleList(
    viewModel: PersonViewModel = hiltViewModel(),
    onStudentListNavigate: () -> Unit
) {
    val people = viewModel.getAllPerson.collectAsState()

    var personName by remember { mutableStateOf("") }
    var personAge by remember { mutableStateOf("") }
    var studentSpecialization by remember { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(false) }
    var dialogMessage by remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 9.dp)
    ) {
        Spacer(modifier = Modifier.height(35.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "People list",
                fontSize = 17.sp,
                fontWeight = FontWeight(700)
            )
            Spacer(modifier = Modifier.height(12.dp))
            CustomNameTextField(
                value = personName,
                onValueChange = { personName = it }
            )
            Spacer(modifier = Modifier.height(12.dp))
            CustomAgeTextField(
                value = personAge,
                onValueChange = { personAge = it }
            )
            Spacer(modifier = Modifier.height(25.dp))
            Button(
                onClick = {
                    if (personName.isNotEmpty() && personAge.isNotEmpty()) {
                        viewModel.addPerson(
                            item = Person(
                                name = personName,
                                age = personAge.toInt()
                            )
                        )
                    }
                    personName = ""
                    personAge = ""
                },
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .height(60.dp)
            ) {
                Text(
                    text = "Add person",
                    fontSize = 19.sp,
                    fontWeight = FontWeight(700)
                )
            }
        }
        Spacer(modifier = Modifier.height(25.dp))
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 5.dp)
        ) {
            items(people.value) { person ->
                PersonItem(
                    name = person.name,
                    age = person.age.toString(),
                    onDecrement = {
                        viewModel.decrementAge(person.id)
                    },
                    onIncrement = {
                        viewModel.incrementAge(person.id)
                    },
                    onStudentClick = {
                        viewModel.onEvent()
                        onStudentListNavigate()
                    }
                )
                Spacer(modifier = Modifier.height(9.dp))
            }
        }
        Spacer(modifier = Modifier.height(59.dp))
        MainDialog(viewModel)
    }
}

