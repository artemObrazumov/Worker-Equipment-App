package com.quackAboutIt.workingequipmentapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.quackAboutIt.workingequipmentapp.auth.presentation.login.LoginScreen
import com.quackAboutIt.workingequipmentapp.auth.presentation.login.LoginScreenViewModel
import com.quackAboutIt.workingequipmentapp.notifications.presentation.notification_list.NotificationListScreen
import com.quackAboutIt.workingequipmentapp.notifications.presentation.notification_list.NotificationListScreenViewModel
import com.quackAboutIt.workingequipmentapp.requests.presentation.request_details.RequestDetailsScreen
import com.quackAboutIt.workingequipmentapp.requests.presentation.request_details.RequestDetailsScreenViewModel
import com.quackAboutIt.workingequipmentapp.requests.presentation.request_editor.RequestEditorScreen
import com.quackAboutIt.workingequipmentapp.requests.presentation.request_editor.RequestEditorScreenViewModel
import com.quackAboutIt.workingequipmentapp.requests.presentation.request_list.RequestListScreen
import com.quackAboutIt.workingequipmentapp.requests.presentation.request_list.RequestListScreenState
import com.quackAboutIt.workingequipmentapp.requests.presentation.request_list.RequestListScreenViewModel
import com.quackAboutIt.workingequipmentapp.requests.presentation.request_list.components.MainTopBar
import com.quackAboutIt.workingequipmentapp.ui.theme.WorkingEquipmentAppTheme
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.ParametersHolder

class MainActivity : ComponentActivity() {
    @SuppressLint("StateFlowValueCalledInComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WorkingEquipmentAppTheme {

                val navController = rememberNavController()

                var isOnDashboard by remember {
                    mutableStateOf(false)
                }
                var dashboardScreenLoaded by remember {
                    mutableStateOf(false)
                }
                navController.addOnDestinationChangedListener(
                    listener = { _, _, _ ->
                        isOnDashboard = navController.currentDestination?.route ==
                                Requests.javaClass.toString().split(" ")[1]
                    }
                )

                val mainActivityViewModel: MainActivityViewModel = koinViewModel()
                val userDataState by mainActivityViewModel.userData.collectAsState()

                Scaffold(
                    modifier = Modifier
                        .fillMaxSize(),
                    floatingActionButton = {
                        AnimatedVisibility(
                            visible = isOnDashboard && dashboardScreenLoaded,
                            enter = slideInHorizontally { it*2 },
                            exit = slideOutHorizontally { it*2 }
                        ) {
                            Button(
                                modifier = Modifier
                                    .height(40.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = MaterialTheme.colorScheme.tertiary
                                ),
                                shape = RoundedCornerShape(10.dp),
                                onClick = {
                                    navController.navigate(RequestEditor)
                                }
                            ) {
                                Icon(
                                    painterResource(id = R.drawable.pencil),
                                    contentDescription = "Создать заявку",
                                    modifier = Modifier.size(20.dp)
                                )
                                Spacer(
                                    modifier = Modifier
                                        .width(8.dp)
                                )
                                Text(
                                    text = "Создать заявку",
                                    style = MaterialTheme.typography.labelMedium
                                )
                            }
                        }
                    }
                ) { innerPadding ->

                    var showExitDialog by remember {
                        mutableStateOf(false)
                    }

                    NavHost(
                        modifier = Modifier
                            .padding(innerPadding),
                        navController = navController,
                        startDestination = Login
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
                                    navController.navigate(Requests)
                                }
                            )
                        }

                        composable<Requests> {
                            val viewModel: RequestListScreenViewModel = koinViewModel()
                            val state by viewModel.state.collectAsState()
                            dashboardScreenLoaded = state is RequestListScreenState.Content

                            RequestListScreen(
                                state = state,
                                onRequestClicked = {
                                    navController.navigate(
                                        RequestDetails(it.id)
                                    )
                                },
                                topBar = {
                                    MainTopBar(
                                        modifier = Modifier
                                            .padding(horizontal = 16.dp),
                                        name = userDataState?.name ?: "",
                                        onNotificationClicked = {
                                            navController.navigate(Notifications)
                                        },
                                        onExitClicked = {
                                            showExitDialog = true
                                        }
                                    )
                                    if (showExitDialog) {
                                        Dialog(
                                            onDismissRequest = {
                                                showExitDialog = false
                                            },
                                        ) {
                                            Card(
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .padding(16.dp)
                                                    .shadow(
                                                        elevation = 16.dp
                                                    ),
                                                colors = CardDefaults.cardColors().copy(
                                                    containerColor = MaterialTheme.colorScheme.background
                                                ),
                                                shape = RoundedCornerShape(10.dp),
                                            ) {
                                                Column(
                                                    modifier = Modifier
                                                        .fillMaxWidth()
                                                        .padding(vertical = 24.dp)
                                                        .padding(horizontal = 16.dp),
                                                    horizontalAlignment = Alignment.CenterHorizontally
                                                ) {
                                                    Text(
                                                        text = "Выйти из аккаунта?",
                                                        style = MaterialTheme.typography.labelMedium
                                                    )
                                                    Spacer(
                                                        modifier = Modifier
                                                            .height(12.dp)
                                                    )
                                                    Button(
                                                        modifier = Modifier
                                                            .fillMaxWidth()
                                                            .height(40.dp),
                                                        colors = ButtonDefaults.buttonColors(
                                                            containerColor = MaterialTheme.colorScheme.secondary
                                                        ),
                                                        shape = RoundedCornerShape(10.dp),
                                                        onClick = {
                                                            showExitDialog = false
                                                            navController.navigate(Login)
                                                        }
                                                    ) {
                                                        Text(
                                                            text = "Выйти",
                                                            style = MaterialTheme.typography.labelMedium
                                                        )
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            )
                        }

                        composable<RequestDetails>(
                            enterTransition = { slideInVertically{ it } },
                            exitTransition = { slideOutVertically{ it } }
                        ) { backStackEntry ->

                            val profile = backStackEntry.toRoute<RequestDetails>()
                            val viewModel: RequestDetailsScreenViewModel = koinViewModel (
                                parameters = { ParametersHolder(
                                    mutableListOf(profile.id)
                                ) }
                            )
                            val state by viewModel.state.collectAsState()

                            RequestDetailsScreen(
                                state = state,
                                onBackPressed = {
                                    navController.navigateUp()
                                }
                            )
                        }

                        composable<Notifications>(
                            enterTransition = { slideInHorizontally{ it } },
                            exitTransition = { slideOutHorizontally{ it } }
                        ) {
                            val viewModel: NotificationListScreenViewModel = koinViewModel()
                            val state by viewModel.state.collectAsState()

                            NotificationListScreen(
                                state = state,
                                onBackPressed = {
                                    navController.navigateUp()
                                }
                            )
                        }

                        composable<RequestEditor>(
                            enterTransition = { slideInVertically{ it } + fadeIn() },
                            exitTransition = { slideOutVertically{ it } + fadeOut() }
                        ) { 
                            val viewModel: RequestEditorScreenViewModel = koinViewModel()
                            val state by viewModel.state.collectAsState()

                            RequestEditorScreen(
                                state = state,
                                onBackPressed = {
                                    navController.navigateUp()
                                },
                                onWorkplaceMenuOpened = {
                                    viewModel.openWorkplaceDialog()
                                },
                                onWorkplaceMenuClosed = {
                                    viewModel.closeWorkplaceDialog()
                                },
                                onWorkplaceSelected = {
                                    viewModel.selectWorkplace(it)
                                },
                                onEquipmentMenuOpened = {
                                    viewModel.openEquipmentDialog()
                                },
                                onEquipmentMenuClosed = {
                                    viewModel.closeEquipmentDialog()
                                },
                                onEquipmentSelected = {
                                    viewModel.selectEquipment(it)
                                },
                                onDistanceChanged = {
                                    viewModel.changeDistanceString(it)
                                },
                                onEquipmentChanged = {
                                    viewModel.changeEquipment(it)
                                },
                                onArrivalDateChanged = {
                                    viewModel.changeArrivalDate(it)
                                },
                                onEquipmentDetailsMenuOpened = {
                                    viewModel.openEquipmentMenuDetails()
                                },
                                onEquipmentDetailsMenuClosed = {
                                    viewModel.closeEquipmentMenuDetails()
                                },
                                onEquipmentDeleted = {
                                    viewModel.deleteEquipment(it)
                                },
                                onCalendarOpened = {
                                    viewModel.openCalendarDialog()
                                },
                                onCalendarClosed = {
                                    viewModel.closeCalendarDialog()
                                },
                                onWorkTimeCalendarOpened = {
                                    viewModel.openWorkTimeCalendarDialog()
                                },
                                onWorkTimeCalendarClosed = {
                                    viewModel.closeWorkTimeCalendarDialog()
                                },
                                onEditingFinished = {
                                    viewModel.sentForm()
                                },
                                onArrivalDatePickerOpened = {
                                    viewModel.openArrivalDatePicker()
                                },
                                onArrivalDatePickerClosed = {
                                    viewModel.closeArrivalDatePicker()
                                },
                                onFormSent = {
                                    navController.navigateUp()
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}