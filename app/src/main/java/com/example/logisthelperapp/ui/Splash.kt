package com.example.logisthelperapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.logisthelperapp.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    destination: String
){

    LaunchedEffect(key1 = true) {
        delay(1000L)
        navController.navigate(destination)
    }

    Column(
        modifier = modifier
            .background(Color.White)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TextButton(
            onClick = {},
            enabled = false,
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .background(Color.Red)
        ){
            Text(
                text = "BIA",
                color = Color.White,
                fontWeight = FontWeight.ExtraBold
            )
        }
        Text(
            text = stringResource(id = R.string.logist_helper),
            modifier = Modifier.padding(top = 10.dp),
            fontWeight = FontWeight.ExtraBold,
            fontSize = 20.sp)
    }
}
