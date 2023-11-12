package com.example.logisthelperapp.ui.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.logisthelperapp.R

sealed class Screen(val route: String, @StringRes val resourceId: Int, @DrawableRes val icon: Int) {
    object Tasks: Screen("tasks", R.string.tasks, R.drawable.ic_task)
    object Schedule: Screen("schedule", R.string.shift_schedule, R.drawable.ic_shedule)
    object Chat: Screen("chat", R.string.chat, R.drawable.ic_chat)
    object Profile: Screen("profile", R.string.profile, R.drawable.ic_profile)
}