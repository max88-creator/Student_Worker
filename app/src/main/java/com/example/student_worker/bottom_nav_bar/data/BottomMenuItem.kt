package com.example.student_worker.bottom_nav_bar.data

import com.example.student_worker.R


sealed class BottomMenuItem(
    val title: String,
    val iconId: Int
) {
data object Person: BottomMenuItem(title = "Person", iconId = R.drawable.sharp_person_24)
data object Student: BottomMenuItem(title = "Student", iconId = R.drawable.ic_student)
}