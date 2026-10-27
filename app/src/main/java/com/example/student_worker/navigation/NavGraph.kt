package com.example.student_worker.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.student_worker.bottom_nav_bar.data.BottomMenuItem
import com.example.student_worker.bottom_nav_bar.ui.BottomMenu
import com.example.student_worker.presentation.screens.person.PeopleList
import com.example.student_worker.presentation.screens.student.StudentList

@Composable
fun NavGraph() {

    val navController = rememberNavController()

    var selectedItemTitle by rememberSaveable {
        mutableStateOf(BottomMenuItem.Person.title)
    }

    Scaffold(
        bottomBar = {
            BottomMenu(
                selectedItemTitle = selectedItemTitle,
                onItemClick = { title ->
                    selectedItemTitle = title
                    val destination = when (title) {
                        BottomMenuItem.Person.title -> NavigationRoutes.PersonList
                        BottomMenuItem.Student.title -> NavigationRoutes.StudentList
                        else -> return@BottomMenu
                    }
                    navController.navigate(destination) {
                        popUpTo(NavigationRoutes.PersonList) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    ) { paddingValues ->
        NavHost(
        navController = navController,
        startDestination = NavigationRoutes.PersonList,
        modifier = Modifier
            .padding(paddingValues)
        ) {
            composable<NavigationRoutes.PersonList> {
                PeopleList(
                    onStudentListNavigate = {
                        navController.navigate(NavigationRoutes.StudentList)
                    }
                )
            }
            composable<NavigationRoutes.StudentList> {
                StudentList(
                    onPeopleListNavigate = {
                        navController.navigate(NavigationRoutes.PersonList)
                    }
                )
            }
        }
    }
}