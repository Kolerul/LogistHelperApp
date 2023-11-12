package com.example.logisthelperapp.ui

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
import androidx.compose.foundation.lazy.items
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
import com.example.logisthelperapp.domain.model.MapPoint
import com.example.logisthelperapp.domain.model.MapPointType
import com.example.logisthelperapp.ui.theme.AlmostBlack
import com.example.logisthelperapp.ui.theme.AlmostLightGrey
import com.example.logisthelperapp.ui.theme.BrightBlue


@Composable
fun RouteList(
    modifier: Modifier = Modifier,
    route: List<MapPoint>
){
    Card(
        modifier = modifier.padding(10.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = modifier
                .padding(10.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = stringResource(id = R.string.route),
                fontSize = 20.sp
            )
            Column{
                route.indices.forEach { i ->
                    RoutePoint(number = i + 1, mapPoint = route[i])
                }
            }
        }
    }
}

@Composable
fun RoutePoint(
    modifier: Modifier = Modifier,
    number: Int,
    mapPoint: MapPoint
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
                    text = stringResource(id = R.string.point, number),
                    fontSize = 18.sp
                )
                Spacer(modifier = Modifier.weight(1f))
                MapPointTypeField(type = mapPoint.type)
                MoreIconButton(
                    expanded = expanded,
                    onClick = { expanded = !expanded }
                )
            }
            if (expanded){
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(id = R.string.start_point),
                    fontSize = 16.sp,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 12.dp),
                    text = mapPoint.startPoint,
                    fontSize = 18.sp,
                    color = BrightBlue
                )
                InfoPoint(
                    titleStringId = R.string.order_date,
                    info = mapPoint.dateAndTime,
                    modifier = Modifier.padding(bottom = 12.dp)
                )
                InfoPoint(
                    titleStringId = R.string.company,
                    info = mapPoint.company,
                    modifier = Modifier.padding(bottom = 12.dp)
                )
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(id = R.string.contact_face),
                    fontSize = 16.sp,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = mapPoint.contact.name,
                    fontSize = 18.sp
                )
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 12.dp),
                    text = mapPoint.contact.phoneNumber,
                    fontSize = 18.sp,
                    color = BrightBlue
                )
                InfoPoint(
                    titleStringId = R.string.commentary,
                    info = mapPoint.comment,
                    modifier = Modifier.padding(bottom = 12.dp)
                )
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(Color.LightGray)
                )
            }
        }
    }
}

@Composable
fun MapPointTypeField(
    modifier: Modifier = Modifier,
    type: MapPointType
){
    val stringId = when(type){
        MapPointType.LOADING -> R.string.loading
        MapPointType.UNLOADING -> R.string.unloading
    }


    val textColor = when(type){
        MapPointType.LOADING -> Color.White
        MapPointType.UNLOADING -> AlmostBlack
    }

    val backgroundColor = when(type){
        MapPointType.LOADING -> AlmostBlack
        MapPointType.UNLOADING -> AlmostLightGrey
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
            fontSize = 16.sp
        )
    }
}

@Preview(
    showBackground = true
)
@Composable
fun ShowRoutePoint(){
    RoutePoint(mapPoint = route2[0], number = 1)
}

@Preview(
    showBackground = true
)
@Composable
fun ShowRoute(){
    RouteList(route = route3)
}