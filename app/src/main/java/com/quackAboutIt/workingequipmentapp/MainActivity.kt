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
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import coil3.compose.AsyncImage
import coil3.compose.AsyncImagePainter
import coil3.compose.rememberAsyncImagePainter
import coil3.request.ImageRequest
import com.quackAboutIt.workingequipmentapp.auth.presentation.login.LoginScreen
import com.quackAboutIt.workingequipmentapp.auth.presentation.login.LoginScreenViewModel
import com.quackAboutIt.workingequipmentapp.notifications.presentation.notification_list.NotificationListScreen
import com.quackAboutIt.workingequipmentapp.notifications.presentation.notification_list.NotificationListScreenViewModel
import com.quackAboutIt.workingequipmentapp.requests.domain.Request
import com.quackAboutIt.workingequipmentapp.requests.presentation.request_details.RequestDetailsScreen
import com.quackAboutIt.workingequipmentapp.requests.presentation.request_details.RequestDetailsScreenViewModel
import com.quackAboutIt.workingequipmentapp.requests.presentation.request_editor.RequestEditorScreen
import com.quackAboutIt.workingequipmentapp.requests.presentation.request_editor.RequestEditorScreenViewModel
import com.quackAboutIt.workingequipmentapp.requests.presentation.request_list.RequestListScreen
import com.quackAboutIt.workingequipmentapp.requests.presentation.request_list.RequestListScreenState
import com.quackAboutIt.workingequipmentapp.requests.presentation.request_list.RequestListScreenViewModel
import com.quackAboutIt.workingequipmentapp.requests.presentation.request_list.components.MainTopBar
import com.quackAboutIt.workingequipmentapp.ui.theme.WorkingEquipmentAppTheme
import io.ktor.http.parametersOf
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

                var showActionButton by remember {
                    mutableStateOf(false)
                }
                navController.addOnDestinationChangedListener(
                    listener = { _, _, _ ->
                        showActionButton = navController.currentDestination?.route ==
                                Requests.javaClass.toString().split(" ")[1]
                    }
                )

                Scaffold(
                    modifier = Modifier
                        .fillMaxSize(),
                    floatingActionButton = {
                        AnimatedVisibility(
                            visible = showActionButton,
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
                                    navController.navigate(Requests)
                                }
                            )
                        }

                        composable<Requests> {
                            val viewModel: RequestListScreenViewModel = koinViewModel()
                            val state by viewModel.state.collectAsState()

                            RequestListScreen(
                                state = state,
                                onRequestClicked = {
                                    navController.navigate(
                                        RequestDetails(69)
                                    )
                                },
                                topBar = {
                                    MainTopBar(
                                        modifier = Modifier
                                            .padding(horizontal = 16.dp),
                                        name = "Иванов Иван Иванович",
                                        onNotificationClicked = {
                                            navController.navigate(Notifications)
                                        },
                                        onExitClicked = { }
                                    )
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