package com.example.logisthelperapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.logisthelperapp.LogistHelperApplication
import com.example.logisthelperapp.R
import com.example.logisthelperapp.presentation.AuthorizationViewModel
import com.example.logisthelperapp.presentation.ChatViewModel
import com.example.logisthelperapp.presentation.TaskViewModel
import com.example.logisthelperapp.ui.CalendarScreen
import com.example.logisthelperapp.ui.ChatScreen
import com.example.logisthelperapp.ui.ProfileScreen
import com.example.logisthelperapp.ui.TaskDetailScreenWithTopAppBar
import com.example.logisthelperapp.ui.TaskTabScreen
import com.example.logisthelperapp.ui.TopAppBar
import com.example.logisthelperapp.ui.TopAppBarWithImage
import com.example.logisthelperapp.ui.model.Screen
import com.example.logisthelperapp.ui.theme.AlmostBlack
import com.example.logisthelperapp.ui.theme.LogistHelperAppTheme

class MainScreenFragment: Fragment() {

    private val userViewModel: AuthorizationViewModel by activityViewModels {
        (requireActivity().application as LogistHelperApplication).appComponent.viewModelFactory()
    }

    private val taskViewModel: TaskViewModel by viewModels {
        (requireActivity().application as LogistHelperApplication).appComponent.viewModelFactory()
    }

    private val chatViewModel: ChatViewModel by viewModels {
        (requireActivity().application as LogistHelperApplication).appComponent.viewModelFactory()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                LogistHelperAppTheme{
                    val navController = rememberNavController()
                    
                    NavHost(navController = navController, startDestination = "main_screen"){
                        composable("main_screen"){
                            MainScreenBottomNavigation(){ number ->
                                navController.navigate("detail/${number}")
                            }
                        }
                        composable(
                            "detail/{number}",
                            arguments = listOf(navArgument("number") {type = NavType.IntType})
                        ){ backStackEntry ->
                            TaskDetailScreenWithTopAppBar(
                                task = taskViewModel.getTaskByNumber(
                                    backStackEntry.arguments?.getInt("number") ?: 0
                                ),
                                onBackPressed = {navController.popBackStack()}
                            )
                        }
                    }

                }
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun MainScreenBottomNavigation(
        onItemClick: (Int) -> Unit
    ){
        val menuItems = listOf(
            Screen.Tasks,
            Screen.Schedule,
            Screen.Chat,
            Screen.Profile
        )

        val navController = rememberNavController()

        val scrollBehavior =
            TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

        var topBarTitle by rememberSaveable {
            mutableStateOf(getString(menuItems[0].resourceId))
        }

        Scaffold(
            bottomBar = {
                BottomNavigation(
                    modifier = Modifier
                        .height(80.dp),
                    backgroundColor = Color.White
                ) {
                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    val currentDestination = navBackStackEntry?.destination
                    menuItems.forEach { screen ->
                        BottomNavigationItem(
                            selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                            onClick = {
                                navController.navigate(screen.route){
                                    popUpTo(navController.graph.findStartDestination().id){
                                        saveState = true
                                    }
                                    topBarTitle = getString(screen.resourceId)
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            },
                            icon = {
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Icon(
                                        painter = painterResource(id = screen.icon),
                                        contentDescription = null)
                                    Text(
                                        text = stringResource(id = screen.resourceId),
                                        color = AlmostBlack
                                    )
                                }
                            }
                        )
                    }
                }
            }
        ){ innerPadding ->
            NavHost(
                navController = navController,
                startDestination = Screen.Tasks.route,
                modifier = Modifier.padding(innerPadding)
            ){
                composable(Screen.Tasks.route){
                    Scaffold(
                        topBar = {TopAppBar(title = topBarTitle, scrollBehavior = scrollBehavior)}
                    ) { innerPadding ->
                        val newList = taskViewModel.newTaskList.observeAsState()
                        val oldList = taskViewModel.oldTaskList.observeAsState()

                        TaskTabScreen(
                            modifier = Modifier.padding(innerPadding),
                            oldList = oldList.value ?: emptyList(),
                            newList = newList.value ?: emptyList(),
                            onItemClick = onItemClick
                        )
                    }
                }
                composable(Screen.Schedule.route){
                    Scaffold(
                        topBar = {TopAppBar(title = topBarTitle, scrollBehavior = scrollBehavior)}
                    ) { innerPadding ->
                        CalendarScreen(modifier = Modifier.padding(innerPadding))
                    }
                }
                composable(Screen.Chat.route){
                    val interlocutor = chatViewModel.interlocutor.observeAsState()
                    Scaffold(
                        topBar = {
                            TopAppBarWithImage(
                                title = interlocutor.value!!,
                                scrollBehavior = scrollBehavior,
                                imageId = R.drawable.elisey) {}
                        }
                    ) { innerPadding ->
                        val messages = chatViewModel.messages.observeAsState()
                        ChatScreen(
                            messages = messages.value!!,
                            modifier = Modifier.padding(innerPadding)
                        ) { text ->
                            chatViewModel.sendMessage(text)
                        }
                    }
                }
                composable(Screen.Profile.route){
                    Scaffold(
                        topBar = {TopAppBar(title = topBarTitle, scrollBehavior = scrollBehavior)}
                    ) { innerPadding ->
                        val userVM = userViewModel.user.observeAsState()
                        ProfileScreen(
                            modifier = Modifier.padding(innerPadding),
                            user = userVM.value!!,
                            onAlertsClick = { }) { }
                    }
                }
            }
        }
    }

}