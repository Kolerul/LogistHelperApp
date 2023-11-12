package com.example.logisthelperapp.ui


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.logisthelperapp.domain.model.Message
import com.example.logisthelperapp.ui.theme.AlmostBlack
import com.example.logisthelperapp.ui.theme.BackgroundColor


@Composable
fun Message(
    modifier: Modifier = Modifier,
    message: Message
){
    val isYour = message.author == "You"

    val containerColor = if (isYour) Color.White else AlmostBlack
    val contentColor = if (isYour) AlmostBlack else Color.White

    if (!isYour){
        Row(
            modifier = modifier
                .padding(start = 8.dp, top = 8.dp, bottom = 8.dp, end = 30.dp)
                .fillMaxWidth()
        ) {
            Card(
                modifier = modifier,
                colors = CardDefaults.cardColors(
                    containerColor = containerColor,
                    contentColor = contentColor
                )
            ) {
                MessageCloud(
                    message = message,
                    contentColor = contentColor
                )
            }
            Spacer(modifier = Modifier
                .weight(1f))
        }
    }else{
        Row(
            modifier = modifier
                .padding(start = 30.dp, top = 8.dp, bottom = 8.dp, end = 8.dp)
                .fillMaxWidth()
        ) {
            Spacer(modifier = Modifier
                .weight(1f))

            Card(
                modifier = modifier,
                colors = CardDefaults.cardColors(
                    containerColor = containerColor,
                    contentColor = contentColor
                )
            ) {
                MessageCloud(
                    message = message,
                    contentColor = contentColor
                )
            }
        }
    }
}

@Composable
fun MessageCloud(
    modifier: Modifier = Modifier,
    message: Message,
    contentColor: Color
){
    ConstraintLayout(
        modifier = modifier.padding(8.dp)
    ) {
        val (text, time) = createRefs()


        Text(
            text = message.text,
            modifier
            = Modifier.constrainAs(text){
                start.linkTo(parent.start, margin = 4.dp)
                end.linkTo(parent.end, margin = 4.dp)
            },
            color = contentColor,
            fontSize = 19.sp
        )

        Text(
            text = message.time,
            Modifier.constrainAs(time) {
                top.linkTo(text.bottom)
                end.linkTo(text.end)
            },
            color = Color.Gray,
            fontSize = 14.sp
        )
    }
}

@Preview(
    showBackground = true
)
@Composable
fun ShowMessage(){
    Message(message = message)
}

val message = Message(
    text = "JOJOJOJOJOJOJO",
    time = "14:41",
    author = "Vovan"
)