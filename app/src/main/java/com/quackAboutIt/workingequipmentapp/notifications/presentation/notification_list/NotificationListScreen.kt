package com.quackAboutIt.workingequipmentapp.notifications.presentation.notification_list

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.quackAboutIt.workingequipmentapp.core.presentation.components.DetailsTopBar
import com.quackAboutIt.workingequipmentapp.core.presentation.components.LoadingScreen
import com.quackAboutIt.workingequipmentapp.notifications.presentation.notification_list.component.NotificationItem

@Composable
fun NotificationListScreen(
    state: NotificationListScreenState,
    onBackPressed: () -> Unit,
    modifier: Modifier = Modifier
) {
    when (state) {
        is NotificationListScreenState.Loading -> {
            LoadingScreen(
                modifier = modifier
            )
        }
        is NotificationListScreenState.Content -> {
            LazyColumn {
                item {
                    DetailsTopBar(
                        modifier = Modifier
                            .padding(horizontal = 16.dp),
                        title = "Уведомления",
                        onBackPressed = onBackPressed
                    )
                }
                items(
                    items = state.notifications
                ) { notification ->
                    NotificationItem(
                        modifier = Modifier
                            .padding(horizontal = 16.dp),
                        notification = notification
                    )
                    Spacer(
                        modifier = Modifier
                            .height(16.dp)
                    )
                }
            }
        }
    }
}