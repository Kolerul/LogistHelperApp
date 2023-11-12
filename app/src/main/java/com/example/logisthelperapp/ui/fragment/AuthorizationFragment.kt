package com.example.logisthelperapp.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

import androidx.navigation.compose.rememberNavController
import androidx.navigation.fragment.findNavController
import com.example.logisthelperapp.LogistHelperApplication
import com.example.logisthelperapp.R
import com.example.logisthelperapp.presentation.AuthorizationViewModel
import com.example.logisthelperapp.ui.Authorization
import com.example.logisthelperapp.ui.SplashScreen
import com.example.logisthelperapp.ui.TaskTabScreen
import com.example.logisthelperapp.ui.theme.LogistHelperAppTheme

class AuthorizationFragment: Fragment() {

    private val viewModel: AuthorizationViewModel by activityViewModels {
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
                    NavHost(navController = navController, startDestination = "splash_screen"){
                        composable("splash_screen"){
                            SplashScreen(navController = navController, destination = "authorization")
                        }
                        composable("authorization"){
                            BackHandler(true) {}

                            val login = viewModel.phoneNumber.observeAsState()
                            val password = viewModel.password.observeAsState()

                            Authorization(
                                login = login.value!!,
                                password = password.value!!,
                                onLoginChange = { new ->
                                    viewModel.phoneNumber.value = new
                                },
                                onPasswordChange = { new ->
                                    viewModel.password.value = new
                                },
                                onContinueClick = {
                                    viewModel.logIn()
                                }
                            )
                        }
                    }
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.user.observe(viewLifecycleOwner){
            findNavController().navigate(R.id.action_global_mainScreenFragment)
        }
    }
}