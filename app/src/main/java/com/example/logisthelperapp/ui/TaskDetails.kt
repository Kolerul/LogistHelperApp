package com.example.logisthelperapp.ui

import androidx.annotation.StringRes
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.logisthelperapp.R
import com.example.logisthelperapp.domain.model.Contact
import com.example.logisthelperapp.domain.model.Task
import com.example.logisthelperapp.ui.theme.AlmostBlack
import com.example.logisthelperapp.ui.theme.AlmostLightGrey
import com.example.logisthelperapp.ui.theme.BackgroundColor
import com.example.logisthelperapp.ui.theme.BrightBlue



@Composable
fun TaskDetailScreen(
    modifier: Modifier = Modifier,
    task: Task
){
    var isShowRefuseDialog by rememberSaveable {
        mutableStateOf(false)
    }

    var isTaskAccepted by rememberSaveable {
        mutableStateOf(false)
    }

    Column(
        modifier = modifier.background(BackgroundColor)
    ) {
        LazyColumn(
            modifier = Modifier
                .background(BackgroundColor)
        ) {
            item{
                TaskInfoBlock(task = task)
                Spacer(modifier = Modifier.height(20.dp))
                RouteList(route = task.route)
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        if (isTaskAccepted){
            TwoColumnButtons(
                modifier = modifier.padding(10.dp),
                continueButtonTitle = stringResource(id = R.string.build_route),
                cancelButtonTitle = stringResource(id = R.string.happend_accident),
                onContinueButtonClick = {  },
                onCancelButtonClick = { }
            )
        }else{
            TwoColumnButtons(
                modifier = modifier.padding(10.dp),
                continueButtonTitle = stringResource(id = R.string.accept),
                cancelButtonTitle = stringResource(id = R.string.refuse),
                onContinueButtonClick = {  },
                onCancelButtonClick = { isShowRefuseDialog = true }
            )
        }
    }

    if (isShowRefuseDialog){
        DeclineDialog(
            onDeclineClick = {
                isShowRefuseDialog = false
                isTaskAccepted = true
            },
            onCancelClick = { isShowRefuseDialog = false }
        )
    }
}

@Composable
fun TaskInfoBlock(
    modifier: Modifier = Modifier,
    task: Task
){
    var expanded by rememberSaveable {
        mutableStateOf(false)
    }

    Card(
        modifier = Modifier
            .padding(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = modifier
                .padding(10.dp)
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioLowBouncy,
                        stiffness = Spring.StiffnessLow
                    )
                )
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = R.string.task_info),
                    fontSize = 20.sp
                )
                Spacer(modifier = Modifier.weight(1f))
                MoreIconButton(
                    expanded = expanded,
                    onClick = { expanded = !expanded }
                )
            }
            if (expanded){
                InfoPoint(
                    Modifier.padding(bottom = 12.dp),
                    titleStringId = R.string.type_of_cargo,
                    info = task.taskInfo.typeOfCargo
                )
                InfoPoint(
                    Modifier.padding(bottom = 12.dp),
                    titleStringId = R.string.city,
                    info = task.taskInfo.city
                )
                InfoPoint(
                    Modifier.padding(bottom = 12.dp),
                    titleStringId = R.string.order_date,
                    info = task.taskInfo.orderDate
                )
                InfoPoint(
                    Modifier.padding(bottom = 12.dp),
                    titleStringId = R.string.body_type,
                    info = task.taskInfo.bodyType
                )
                InfoPoint(
                    Modifier.padding(bottom = 12.dp),
                    titleStringId = R.string.cargo_weight,
                    info = task.taskInfo.cargoWeight.toString()
                )
                InfoPoint(
                    Modifier.padding(bottom = 12.dp),
                    titleStringId = R.string.load_capacity,
                    info = task.taskInfo.loadCapacity.toString()
                )
                InfoPoint(
                    Modifier.padding(bottom = 12.dp),
                    titleStringId = R.string.type_of_loading,
                    info = task.taskInfo.typeOfLoading
                )
                InfoPoint(
                    Modifier.padding(bottom = 12.dp),
                    titleStringId = R.string.medical_book,
                    info = if (task.taskInfo.medicalBook) stringResource(id = R.string.yes)
                    else stringResource(id = R.string.no)
                )
                InfoPoint(
                    Modifier.padding(bottom = 12.dp),
                    titleStringId = R.string.order_details,
                    info = task.taskInfo.orderDetails
                )
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(Color.LightGray)
                )
                ContactList(
                    contacts = task.taskInfo.contacts
                )
                Box(modifier = Modifier
                    .padding(12.dp)
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(Color.LightGray)
                )
                Row {
                    Text(
                        text = stringResource(id = R.string.client_rules),
                        fontSize = 22.sp
                    )
                    Spacer(modifier = Modifier.weight(1f))
                }
                Spacer(modifier = Modifier.height(12.dp))
                TextButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(
                        containerColor = AlmostLightGrey,
                        contentColor = AlmostBlack
                    )
                ) {
                    Icon(painter = painterResource(id = R.drawable.ic_download), contentDescription = null)
                    Text(text = stringResource(id = R.string.download))
                }
            }
        }
    }
}

@Composable
fun ContactList(
    modifier: Modifier = Modifier,
    contacts: List<Contact>
){
    Column(
        modifier = modifier
    ) {
        Row(
            modifier = modifier
        ) {
            Text(
                text = stringResource(id = R.string.contacts),
                fontSize = 20.sp
            )
            Spacer(modifier = Modifier.weight(1f))
        }
        Column {
            contacts.indices.forEach { i ->
                Contact(contact = contacts[i])
            }
        }
    }


}

@Composable
fun Contact(
    modifier: Modifier = Modifier,
    contact: Contact
){
    Column(
        modifier = modifier
    ) {
        InfoPoint(
            Modifier.padding(top = 12.dp, bottom = 12.dp),
            titleStringId = R.string.contact_face,
            info = contact.name
        )
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(id = R.string.phone_number),
            fontSize = 16.sp,
            color = Color.Gray
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = contact.phoneNumber,
            fontSize = 18.sp,
            color = BrightBlue
        )
    }
}

@Composable
fun InfoPoint(
    modifier: Modifier = Modifier,
    @StringRes titleStringId: Int,
    info: String
){
    Column(
        modifier = modifier
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(id = titleStringId),
            fontSize = 16.sp,
            color = Color.Gray
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = info,
            fontSize = 18.sp
        )
    }
}

@Preview(
    showBackground = true
)
@Composable
fun ShowDetailTaskScreen(){
    TaskDetailScreen(task = task)
}