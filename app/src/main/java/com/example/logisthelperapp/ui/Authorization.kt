package com.example.logisthelperapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.logisthelperapp.R
import com.example.logisthelperapp.ui.theme.AlmostBlack
import com.example.logisthelperapp.ui.theme.LogistHelperAppTheme

@Composable
fun Authorization(
    modifier: Modifier = Modifier,
    login: String,
    password: String,
    onLoginChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onContinueClick: () -> Unit
){
    var isPasswordField by rememberSaveable {
        mutableStateOf(false)
    }
    var isPhoneContinueButtonEnable by rememberSaveable {
        mutableStateOf(false)
    }

    var isPasswordContinueButtonEnable by rememberSaveable {
        mutableStateOf(false)
    }

    Column(modifier = modifier
        .fillMaxSize()
        .background(Color.White),
        verticalArrangement = Arrangement.Bottom
        ) {

        AppLogo(modifier = Modifier.padding(20.dp))
        Spacer(modifier = Modifier.weight(1f))
        if (!isPasswordField){
            PhoneNumberSegment(
                modifier = Modifier.padding(start = 10.dp, end = 10.dp),
                number = login,
                onNumberChange = { new ->
                    onLoginChange(new)
                    isPhoneContinueButtonEnable = new.length == 10
                }
            )

            Spacer(modifier = Modifier.weight(2f))

            Box(modifier = Modifier.padding(16.dp)){
                ContinueButton(
                    isEnabled = isPhoneContinueButtonEnable,
                    onClick = {isPasswordField = true},
                )
            }



        }else{
            PasswordSegment(
                modifier = Modifier.padding(start = 10.dp, end = 10.dp),
                password = password,
                onPasswordChange = { new ->
                    onPasswordChange(new)
                    isPasswordContinueButtonEnable = new.length == 6
                },
                onBackButton = {
                    isPasswordField = false
                }
            )

            Spacer(modifier = Modifier.weight(2f))

            Box(modifier = Modifier.padding(16.dp)){
                ContinueButton(
                    isEnabled = isPasswordContinueButtonEnable,
                    onClick = onContinueClick
                )
            }
        }
    }
}

@Composable
fun AppLogo(modifier: Modifier = Modifier){
    Row(
        modifier = modifier.background(Color.White),
        verticalAlignment = Alignment.CenterVertically
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
            modifier = Modifier.padding(start = 10.dp),
            fontWeight = FontWeight.ExtraBold,
            fontSize = 20.sp)
    }
}

@Composable
fun PhoneNumberSegment(
    modifier: Modifier = Modifier,
    number: String,
    onNumberChange: (String) -> Unit
){
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = stringResource(id = R.string.greeting),
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            lineHeight = 35.sp
        )
        Text(
            text = stringResource(id = R.string.enter_your_number),
            modifier = Modifier.padding(top = 20.dp, bottom = 20.dp),
            color = Color.DarkGray,
            fontSize = 18.sp
        )
        PhoneNumberField(number, onNumberChange)
    }
}

@Composable
fun PasswordSegment(
    modifier: Modifier = Modifier,
    password: String,
    onPasswordChange: (String) -> Unit,
    onBackButton: () -> Unit
){
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBackButton) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Back arrow",
                    modifier = Modifier.size(40.dp)
                )
            }

            Text(
                text = stringResource(id = R.string.enter_password),
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                lineHeight = 35.sp,
                modifier = Modifier.padding(start = 10.dp)
            )
        }

        Text(
            text = stringResource(id = R.string.password_resource),
            modifier = Modifier.padding(top = 20.dp, bottom = 20.dp),
            color = Color.DarkGray,
            fontSize = 18.sp
        )
        PasswordField(password, onPasswordChange)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordField(
    password: String,
    onPasswordChange: (String) -> Unit
){
    OutlinedTextField(
        value = password,
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(65))
            .border(
                3.dp,
                Color.Black,
                shape = RoundedCornerShape(65)
            ),
        onValueChange = { newPassword ->
            onPasswordChange(newPassword.take(6))
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        visualTransformation = { passwordFilter(it) }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PhoneNumberField(
    number: String,
    onNumberChange: (String) -> Unit
){
    OutlinedTextField(
        value = number,
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(65))
            .border(
                3.dp,
                Color.Black,
                shape = RoundedCornerShape(65)
            ),
        onValueChange = { newNumber ->
            onNumberChange(newNumber.take(10))
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
        visualTransformation = { phoneNumberFilter(it) }
    )
}

const val passwordMask = "                         _ _ _ _ _ _"

fun passwordFilter(text: AnnotatedString): TransformedText {
    val trimmed =
        if (text.text.length >= 7) text.text.substring(0..6) else text.text

    val annotatedString = AnnotatedString.Builder().run {
        if (trimmed.isNotEmpty()){
            append("                         ")
        }

        for (i in trimmed.indices) {
            append(trimmed[i])
            if (i < 5) append(" ")
        }
        pushStyle(SpanStyle(color = AlmostBlack))
        append(passwordMask.takeLast(passwordMask.length - length))
        toAnnotatedString()
    }

    val phoneNumberOffsetTranslator = object : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int {
            return offset
        }

        override fun transformedToOriginal(offset: Int): Int {
            return 0
        }
    }

    return TransformedText(annotatedString, phoneNumberOffsetTranslator)
}

const val phoneNumberMask = "+7 (999) 000 - 00 - 00"

fun phoneNumberFilter(text: AnnotatedString): TransformedText {
    val trimmed =
        if (text.text.length >= 11) text.text.substring(0..10) else text.text

    val annotatedString = AnnotatedString.Builder().run {
        if (trimmed.isNotEmpty()){
            append("+7 (")
        }

        for (i in trimmed.indices) {
            append(trimmed[i])
            if (i == 2) append(") ")
            if (i == 5 || i == 7) append(" - ")
        }
        pushStyle(SpanStyle(color = Color.Gray))
        append(phoneNumberMask.takeLast(phoneNumberMask.length - length))
        toAnnotatedString()
    }

    val phoneNumberOffsetTranslator = object : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int {
            return offset
        }

        override fun transformedToOriginal(offset: Int): Int {
            return 0
        }
    }

    return TransformedText(annotatedString, phoneNumberOffsetTranslator)
}

@Preview(
    showBackground = true
)
@Composable
fun showAuthorizationScreen(){
    LogistHelperAppTheme {
        Authorization(login = "", password = "", onLoginChange = {}, onPasswordChange = {}, onContinueClick = {})
    }
}

@Preview(
    showBackground = true
)
@Composable
fun showPhoneSegment(){
    LogistHelperAppTheme {
        PhoneNumberSegment(number = "0", onNumberChange = {})
    }
}

@Preview(
    showBackground = true
)
@Composable
fun showPasswordSegment(){
    LogistHelperAppTheme {
        PasswordSegment(password = "", onPasswordChange = {}, onBackButton = {})
    }
}

@Preview(
    showBackground = true
)
@Composable
fun showLogo(){
    LogistHelperAppTheme {
        AppLogo()
    }
}
