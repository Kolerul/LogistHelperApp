package com.example.logisthelperapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.logisthelperapp.R
import com.example.logisthelperapp.domain.model.User
import com.example.logisthelperapp.ui.theme.BackgroundColor

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    user: User,
    onAlertsClick: () -> Unit,
    onExitClick: () -> Unit
){
    LazyColumn(
        modifier = modifier
            .background(BackgroundColor)
            .fillMaxSize()
            .padding(16.dp)
    ) {
        item{
            NameAndImageCard(user = user)
            Spacer(modifier = Modifier.height(12.dp))
            ProfileInfoCard(user = user)
            Spacer(modifier = Modifier.height(12.dp))
            NewPageButton(
                buttonText = stringResource(id = R.string.alerts),
                onButtonClick = onAlertsClick
            )
            Spacer(modifier = Modifier.height(12.dp))
            NewPageButton(
                buttonText = stringResource(id = R.string.exit),
                textColor = Color.Red,
                onButtonClick = onExitClick
            )
        }
    }
}

@Composable
fun NameAndImageCard(
    modifier: Modifier = Modifier,
    user: User
){
    Card(
        modifier = modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(10)
    ) {
        Column(
            modifier = modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Image(
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape),
                painter = painterResource(id = user.imageId),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = user.name,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = user.role,
                fontSize = 18.sp
            )
        }
    }
}

@Composable
fun ProfileInfoCard(
    modifier: Modifier = Modifier,
    user: User
){
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(10)
    ) {
        Column(
            modifier = modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            InfoPoint(
                Modifier.padding(bottom = 12.dp),
                titleStringId = R.string.tabel_number,
                info = user.number.toString()
            )
            InfoPoint(
                Modifier.padding(bottom = 12.dp),
                titleStringId = R.string.phone_number,
                info = user.phoneNumber
            )
            InfoPoint(
                Modifier.padding(bottom = 12.dp),
                titleStringId = R.string.citizenship,
                info = user.citizenship
            )
            InfoPoint(
                Modifier.padding(bottom = 12.dp),
                titleStringId = R.string.car,
                info = user.carType
            )
            InfoPoint(
                Modifier.padding(bottom = 12.dp),
                titleStringId = R.string.car_number,
                info = user.carNumber
            )
        }
    }
}

@Preview(
    showBackground = true
)
@Composable
fun ShowProfileCard(){
    NameAndImageCard(user = user)
}

@Preview(
    showBackground = true
)
@Composable
fun ShowProfileScreen(){
    ProfileScreen(
        user = user,
        onAlertsClick = { }) { }
}

val user = User(
    name = "Элвин",
    imageId = R.drawable.elwin,
    role = "Главный герой",
    number = 1,
    phoneNumber = "+7 111 222 33 44",
    citizenship = "Королевство Балдея",
    carType = "Гужевой",
    carNumber = "11233"
)