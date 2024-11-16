package com.quackAboutIt.workingequipmentapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.quackAboutIt.workingequipmentapp.auth.presentation.login.LoginScreen
import com.quackAboutIt.workingequipmentapp.auth.presentation.login.LoginScreenViewModel
import com.quackAboutIt.workingequipmentapp.requests.domain.Request
import com.quackAboutIt.workingequipmentapp.requests.presentation.request_list.RequestListScreen
import com.quackAboutIt.workingequipmentapp.ui.theme.WorkingEquipmentAppTheme
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WorkingEquipmentAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val navController = rememberNavController()
                    NavHost(
                        modifier = Modifier
                            .padding(innerPadding),
                        navController = navController,
                        startDestination = Requests
                    ) {
                        composable<Login> {
                            val viewModel: LoginScreenViewModel = koinViewModel()
                            val state by viewModel.state.collectAsState()
                            LoginScreen(
                                state = state,
                                onLoginChanged = {
                                    viewModel.updateEmail(it)
                                },
                                onPasswordChanged = {
                                    viewModel.updatePassword(it)
                                },
                                onLoginAttempt = {
                                    viewModel.doLogin()
                                },
                                onLoggedIn = {

                                }
                            )
                        }

                        composable<Requests> {
                            RequestListScreen()
                        }
                    }
                }
            }
        }
    }
}