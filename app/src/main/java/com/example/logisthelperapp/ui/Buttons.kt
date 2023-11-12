package com.example.logisthelperapp.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.logisthelperapp.R
import com.example.logisthelperapp.ui.theme.AlmostBlack
import com.example.logisthelperapp.ui.theme.AlmostLightGrey

@Composable
fun MoreIconButton(
    expanded: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick = onClick,
        modifier = modifier
    ) {
        Icon(
            imageVector = if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
            contentDescription = null
        )
    }
}

@Composable
fun TwoColumnButtons(
    modifier: Modifier = Modifier,
    continueButtonTitle: String,
    cancelButtonTitle: String,
    onContinueButtonClick: () -> Unit,
    onCancelButtonClick: () -> Unit
){
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        TextButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            onClick = onContinueButtonClick,
            colors = ButtonDefaults.buttonColors(
                containerColor = AlmostBlack,
                contentColor = Color.White
            ),
            border = BorderStroke(1.dp, AlmostBlack)
        ) {
            Text(
                text = continueButtonTitle,
                fontSize = 18.sp
            )
        }
        TextButton(
            modifier = Modifier
                .fillMaxWidth(),
            onClick = onCancelButtonClick,
            colors = ButtonDefaults.buttonColors(
                containerColor = AlmostLightGrey,
                contentColor = AlmostBlack
            ),
            border = BorderStroke(1.dp, AlmostBlack)
        ) {
            Text(
                text = cancelButtonTitle,
                fontSize = 18.sp
            )
        }
    }
}

@Composable
fun TwoRowButtons(
    modifier: Modifier = Modifier,
    continueButtonTitle: String,
    cancelButtonTitle: String,
    onContinueButtonClick: () -> Unit,
    onCancelButtonClick: () -> Unit
){
    Row(
        modifier = modifier
    ) {
        TextButton(
            modifier = Modifier
                .padding(end = 4.dp)
                .weight(1f),
            onClick = onContinueButtonClick,
            colors = ButtonDefaults.buttonColors(
                containerColor = AlmostBlack,
                contentColor = Color.White
            ),
            border = BorderStroke(1.dp, AlmostBlack)
        ) {
            Text(
                text = continueButtonTitle,
                fontSize = 18.sp
            )
        }
        TextButton(
            modifier = Modifier
                .padding(end = 4.dp)
                .weight(1f),
            onClick = onCancelButtonClick,
            colors = ButtonDefaults.buttonColors(
                containerColor = AlmostLightGrey,
                contentColor = AlmostBlack
            ),
            border = BorderStroke(1.dp, AlmostBlack)
        ) {
            Text(
                text = cancelButtonTitle,
                fontSize = 18.sp
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewPageButton(
    modifier: Modifier = Modifier,
    buttonText: String,
    textColor: Color = AlmostBlack,
    onButtonClick: () -> Unit
){
    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
            contentColor = textColor
        ),
        onClick = onButtonClick
    ) {
        Row(
            modifier = modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = buttonText,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
            Spacer(modifier = Modifier.weight(1f))
            Icon(imageVector = Icons.Filled.KeyboardArrowRight, contentDescription = null)
        }
    }
}

@Composable
fun ContinueButton(
    isEnabled: Boolean,
    onClick: () -> Unit
){
    TextButton(
        modifier = Modifier
            .fillMaxWidth(),
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = AlmostBlack,
            contentColor = Color.White
        ),
        enabled = isEnabled,
        border = BorderStroke(1.dp, AlmostBlack)
    ) {
        Text(
            text = stringResource(id = R.string.contin),
            fontSize = 18.sp
        )
    }
}

@Preview(
    showBackground = true
)
@Composable
fun ShowTwoButtons(){
    TwoColumnButtons(
        continueButtonTitle = "Продолжить",
        cancelButtonTitle = "Отменить",
        onContinueButtonClick = { }) {
        
    }
}

@Preview(
    showBackground = true
)
@Composable
fun ShowAgainTwoButtons(){
    TwoRowButtons(
        continueButtonTitle = "Продолжить",
        cancelButtonTitle = "Отменить",
        onContinueButtonClick = { }) {

    }
}