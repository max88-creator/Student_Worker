package com.example.student_worker

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CustomDialog() {
    var showDialog by remember { mutableStateOf(false) }

    var message by remember { mutableStateOf("") }
    var alertMessage by remember { mutableStateOf("") }
    Column(modifier = Modifier.padding(16.dp)) {
        Spacer(modifier = Modifier.height(55.dp))
        Button(onClick = { showDialog = true }) {
            Text("Показать диалог")
        }
        Spacer(modifier = Modifier.height(25.dp))
        Text(
            text = message,
            fontSize = 15.sp
        )
        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = { Text("Input specialization") },
                text = {
                    TextField(
                        value = alertMessage,
                        onValueChange = {
                            alertMessage = it
                        }
                    )
                },
                //     text = { Text("Вы уверены, что хотите продолжить?") },
                confirmButton = {
                    Button(onClick = {
                        message = alertMessage
                        showDialog = false
                    }) {
                        Text("Да, продолжить")
                    }
                },
                dismissButton = {
                    Button(
                        onClick = { showDialog = false },
                        colors = androidx.compose.material3.ButtonDefaults.buttonColors(
                            containerColor = androidx.compose.ui.graphics.Color.Transparent
                        )
                    ) {
                        Text("Отмена")
                    }
                }
            )
        }
    }
}