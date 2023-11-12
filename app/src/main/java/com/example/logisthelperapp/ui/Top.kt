package com.example.logisthelperapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.IconButton
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.logisthelperapp.R
import com.example.logisthelperapp.domain.model.Task
import com.example.logisthelperapp.ui.theme.AlmostBlack

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskDetailScreenWithTopAppBar(
    modifier: Modifier = Modifier,
    task: Task,
    onBackPressed: () -> Unit
){

    val scrollBehavior =
        TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

    Scaffold(
        topBar = {
            TopAppBarWithBackButton(
                title = stringResource(id = R.string.task_number, task.number),
                scrollBehavior = scrollBehavior,
                onBackClick = onBackPressed
            )
        },
    ){ innerPadding ->
        TaskDetailScreen(task = task, modifier = Modifier.padding(innerPadding))
    }
}

@Composable
fun TaskTabScreen(
    modifier: Modifier = Modifier,
    oldList: List<Task>,
    newList: List<Task>,
    onItemClick: (Int) -> Unit
){
    var tabIndex by rememberSaveable {
        mutableStateOf(0)
    }

    val tabs = listOf(
        R.string.tab_incoming,
        R.string.tab_at_work
    )

    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        TabRow(
            selectedTabIndex = tabIndex,
            backgroundColor = Color.White
        ) {
            tabs.forEachIndexed { index, title ->
                Tab(text = {
                    Text(
                        stringResource(title),
                        color = AlmostBlack,
                        fontSize = 20.sp
                    )
                },
                    selected = tabIndex == index,
                    onClick = { tabIndex = index }
                )
            }
        }
        when (tabIndex) {
            0 -> TaskLazyColumn(
                taskList = newList,
                onItemClick = onItemClick
            )
            1 -> TaskLazyColumn(
                taskList = oldList,
                onItemClick = onItemClick
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(
    title: String,
    scrollBehavior: TopAppBarScrollBehavior,
    modifier: Modifier = Modifier
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = title,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = AlmostBlack,
                fontWeight = FontWeight.Bold,
                fontSize = 26.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            )
        },
        scrollBehavior = scrollBehavior,
        modifier = modifier,
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Color.White,
            titleContentColor = AlmostBlack
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarWithBackButton(
    title: String,
    scrollBehavior: TopAppBarScrollBehavior,
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit
) {
    CenterAlignedTopAppBar(
        title = {
            Row {
                Spacer(modifier = Modifier.width(25.dp))
                Text(
                    text = title,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = AlmostBlack,
                    fontWeight = FontWeight.Bold,
                    fontSize = 26.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
        },
        navigationIcon = {
            IconButton(onClick = onBackClick,) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = null
                )
            }
        },
        scrollBehavior = scrollBehavior,
        modifier = modifier,
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Color.White,
            titleContentColor = AlmostBlack
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarWithImage(
    title: String,
    scrollBehavior: TopAppBarScrollBehavior,
    modifier: Modifier = Modifier,
    imageId: Int,
    onImageClick: () -> Unit
) {
    CenterAlignedTopAppBar(
        title = {
            Row {
                Spacer(modifier = Modifier.width(25.dp))
                Text(
                    text = title,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = AlmostBlack,
                    fontWeight = FontWeight.Bold,
                    fontSize = 26.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
        },
        navigationIcon = {
            IconButton(onClick = onImageClick,) {
                Image(
                    painter = painterResource(id = imageId),
                    contentDescription = null,
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
            }
        },
        scrollBehavior = scrollBehavior,
        modifier = modifier,
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Color.White,
            titleContentColor = AlmostBlack
        )
    )
}