package com.example.logisthelperapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.logisthelperapp.R
import com.example.logisthelperapp.domain.model.Message
import com.example.logisthelperapp.ui.theme.AlmostBlack
import com.example.logisthelperapp.ui.theme.BackgroundColor


@Composable
fun ChatScreen(
    modifier: Modifier = Modifier,
    messages: List<Message>,
    onSendClick: (String) -> Unit
){
    Column(
        modifier = modifier
            .background(BackgroundColor)
    ) {
        Column(modifier = Modifier.weight(1f)){
            Spacer(modifier = Modifier.weight(1f))
            LazyColumn(
            ){
                items(messages){ message ->
                    Message(
                        message = message
                    )
                }
            }
        }
        SendMessageField(
            onSendClick = onSendClick
        )
    }
}

@Composable
fun SendMessageField(
    modifier: Modifier = Modifier,
    onSendClick: (String) -> Unit
){
    var text by rememberSaveable {
        mutableStateOf("")
    }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
       TextField(
           value = text,
           onValueChange = { newText ->
               text = newText
           },
           placeholder = {
               Text(
                   text = stringResource(id = R.string.write_message),
                   fontSize = 19.sp
               )
           },
           modifier = Modifier
               .weight(1f),
           colors = TextFieldDefaults.textFieldColors(
               backgroundColor = Color.White,
               focusedIndicatorColor = AlmostBlack
           )
       )
        if (text.isNotEmpty()){
            Spacer(modifier = Modifier.width(8.dp))
            IconButton(
                onClick = {
                    onSendClick(text)
                    text = ""
                },
                modifier = Modifier
                    .size(40.dp)
                    .background(BackgroundColor)
                    .clip(RoundedCornerShape(100))
            ) {
                Icon(
                    Icons.Filled.Send,
                    contentDescription = null,
                    modifier = Modifier
                        .size(24.dp)
                )
            }
        }
    }
}

@Preview(
    showBackground = true
)
@Composable
fun showSendMessageField(){
    Column {
        Spacer(modifier = Modifier.weight(1f))
        SendMessageField{}
    }

}

@Preview(
    showBackground = true
)
@Composable
fun showChatScreen(){
    ChatScreen(messages = listOf(
        message1, message2, message3, message4, message5
    )) { }
}

val message1 = Message(
    text = "Ты где?!",
    time = "16:27",
    author = "Unknown"
)

val message2 = Message(
    text = "Ты кто?!",
    time = "16:27",
    author = "Unknown"
)

val message3 = Message(
    text = "Хто я!",
    time = "16:27",
    author = "Unknown"
)

val message4 = Message(
    text = "Простите, я не общаюсь с шизофрениками",
    time = "16:30",
    author = "You"
)

val message5 = Message(
    text = "Да ты! Да я! Да я тебя!!!",
    time = "16:38",
    author = "Unknown"
)