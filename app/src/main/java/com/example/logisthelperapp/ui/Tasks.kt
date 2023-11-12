package com.example.logisthelperapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.logisthelperapp.R
import com.example.logisthelperapp.domain.model.Contact
import com.example.logisthelperapp.domain.model.MapPoint
import com.example.logisthelperapp.domain.model.MapPointType
import com.example.logisthelperapp.domain.model.Task
import com.example.logisthelperapp.domain.model.TaskInfo
import com.example.logisthelperapp.domain.model.TaskStatus
import com.example.logisthelperapp.ui.theme.AlmostLightGrey
import com.example.logisthelperapp.ui.theme.BackgroundColor
import com.example.logisthelperapp.ui.theme.BrightBlue
import com.example.logisthelperapp.ui.theme.BrightGreen
import com.example.logisthelperapp.ui.theme.BrightOrange
import com.example.logisthelperapp.ui.theme.BrightPurple
import com.example.logisthelperapp.ui.theme.ListBackgroundColor
import com.example.logisthelperapp.ui.theme.VeryLightBlue
import com.example.logisthelperapp.ui.theme.VeryLightGreen
import com.example.logisthelperapp.ui.theme.VeryLightOrange
import com.example.logisthelperapp.ui.theme.VeryLightPurple

@Composable
fun TaskLazyColumn(
    modifier: Modifier = Modifier,
    taskList: List<Task>,
    onItemClick: (Int) -> Unit
){
    LazyColumn(
        modifier = modifier.background(ListBackgroundColor)
    ){
        items(taskList){
            TaskItem(
                task = it,
                onItemClick = onItemClick,
                modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 8.dp)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskItem(
    modifier: Modifier = Modifier,
    task: Task,
    onItemClick: (Int) -> Unit
){
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        onClick = {onItemClick(task.number)}
    ) {
        Column(
            modifier = Modifier.padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row {
                Text(
                    text = stringResource(id = R.string.number_of_task, task.number),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = stringResource(id = R.string.price_ru, task.price),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(top = 5.dp, bottom = 5.dp)
            ) {
                Text(
                    text = task.taskInfo.orderDate,
                    color = Color.DarkGray,
                    fontSize = 14.sp
                )
                Spacer(modifier = Modifier.weight(1f))
                TaskStatusTextField(status = task.taskStatus)
            }
            TaskRouteSmallField(
                route = task.route
            )
        }
    }
}

@Composable
fun TaskRouteSmallField(
    modifier: Modifier = Modifier,
    route: List<MapPoint>
){
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = BackgroundColor
        )
    ) {
        Row() {
            RouteGraph(routeSize = route.size, modifier = Modifier.padding(16.dp))
            Column {
                RouteSmallItem(mapPoint = route[0])
                Spacer(modifier = Modifier.height(15.dp))
                RouteSmallItem(mapPoint = route[route.size - 1])
            }
        }
    }

}

@Composable
fun RouteSmallItem(
    modifier: Modifier = Modifier,
    mapPoint: MapPoint
){
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp, top = 8.dp, end = 8.dp)
    ) {
        Text(
            text = mapPoint.startPoint,
            fontSize = 18.sp
        )
        Text(
            text = mapPoint.dateAndTime,
            fontSize = 14.sp,
            color = Color.Gray
        )
    }
}

@Composable
fun RouteGraph(
    modifier: Modifier = Modifier,
    routeSize: Int
){
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(painter = painterResource(id = R.drawable.ic_start_point), contentDescription = null)
        if (routeSize <= 2){
            Box(modifier = Modifier
                .background(Color.Black)
                .height(64.dp)
                .width(1.dp)) {}
        }else{
            Box(modifier = Modifier
                .background(Color.Black)
                .height(22.dp)
                .width(1.dp))
            Text(
                text = "${routeSize - 2}",
                fontSize = 15.sp
            )
            Box(modifier = Modifier
                .background(Color.Black)
                .height(22.dp)
                .width(1.dp))
        }
        Icon(painter = painterResource(id = R.drawable.ic_end_point), contentDescription = null)

    }
}

@Composable
fun TaskStatusTextField(
    modifier: Modifier = Modifier,
    status: TaskStatus
){
    val stringId = when(status){
        TaskStatus.NEW -> {
            R.string.new_status
        }
        TaskStatus.IN_PROGRESS -> {
            R.string.in_progress_status
        }
        TaskStatus.PLANNED -> {
            R.string.planned_status
        }
        TaskStatus.CHECK -> {
            R.string.check_status
        }
    }

    val backgroundColor = when(status){
        TaskStatus.NEW -> {
            VeryLightGreen
        }
        TaskStatus.IN_PROGRESS -> {
            VeryLightPurple
        }
        TaskStatus.PLANNED -> {
            VeryLightBlue
        }
        TaskStatus.CHECK -> {
            VeryLightOrange
        }
    }

    val textColor = when(status){
        TaskStatus.NEW -> {
            BrightGreen
        }
        TaskStatus.IN_PROGRESS -> {
            BrightPurple
        }
        TaskStatus.PLANNED -> {
            BrightBlue
        }
        TaskStatus.CHECK -> {
            BrightOrange
        }
    }

    Card(
        modifier = modifier
    ) {
        Text(
            text = stringResource(id = stringId),
            color = textColor,
            modifier = Modifier
                .background(backgroundColor)
                .padding(top = 5.dp, bottom = 5.dp, start = 10.dp, end = 10.dp),
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold
        )
    }


}

@Preview(
    showBackground = true
)
@Composable
fun ShowTaskInfo(){
    TaskInfoBlock(task = task)
}

@Preview(
    showBackground = true
)
@Composable
fun ShowTaskItem(){
    TaskItem(task = task, onItemClick = {})
}

@Preview(
    showBackground = true
)
@Composable
fun ShowTaskList(){
    TaskLazyColumn(taskList = listOf(
        task, task, task, task, task
    )) {

    }
}
@Preview(
    showBackground = true
)
@Composable
fun ShowRouteItem(){
    TaskRouteSmallField(route = route3)
}

val contact1 = Contact("Вован", "+7 800 000 00 00")
val contact2 = Contact("Антоха", "+7 800 000 00 00")
val taskInfo = TaskInfo(
    "Тяжелый груз", "Москва", "01.02.2003", "Грузовик",
    2000, 10_000, "Задняя погрузка", true, "Привезите пожалуйста",
    listOf(contact1, contact2)
)

val route2 = listOf(
    MapPoint("Здесь", MapPointType.LOADING, "01.02.2003 12:00", "ООО Зашибись", contact1, "Ноу коммент"),
    MapPoint("Там", MapPointType.LOADING, "01.02.2003 12:01", "ООО Почешись", contact2, "Ноу коммент")
)

val route3 = listOf(
    MapPoint("Здесь", MapPointType.LOADING, "01.02.2003 12:00", "ООО Зашибись", contact1, "Ноу коммент"),
    MapPoint("Там", MapPointType.LOADING, "01.02.2003 12:01", "ООО Почешись", contact2, "Ноу коммент"),
    MapPoint("Там", MapPointType.UNLOADING, "01.02.2003 12:01", "ООО Почешись", contact2, "Ноу коммент")
)

val task = Task(
    1, 100_000, TaskStatus.IN_PROGRESS, taskInfo, route2
)