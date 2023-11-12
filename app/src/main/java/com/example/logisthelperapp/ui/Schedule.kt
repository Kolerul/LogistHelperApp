package com.example.logisthelperapp.ui

import android.widget.CalendarView
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun CalendarScreen(
    modifier: Modifier = Modifier
){
    var date by rememberSaveable {
        mutableStateOf("")
    }

    var showBottomSheet by rememberSaveable {
        mutableStateOf(false)
    }

    Scaffold{ innerPadding ->
        Card(
            modifier = modifier
                .padding(innerPadding)
                .fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Column(
                modifier = modifier,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AndroidView(
                    factory = {CalendarView(it)},
                    update = { calendar ->
                        calendar.setOnDateChangeListener{ calendarView, year, month, day ->
                            date = "$day - ${month + 1} - $year"
                            showBottomSheet = true
                        }
                    }
                )
            }
        }

    }
}

@Preview(
    showBackground = true
)
@Composable
fun ShowCalendar(){
    CalendarScreen()
}
