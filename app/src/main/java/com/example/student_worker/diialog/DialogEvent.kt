package com.example.student_worker.diialog

sealed class DialogEvent {
    data class OnTextChange(val  text: String): DialogEvent()
    object OnCancel: DialogEvent()
    object OnConfirm: DialogEvent()
}