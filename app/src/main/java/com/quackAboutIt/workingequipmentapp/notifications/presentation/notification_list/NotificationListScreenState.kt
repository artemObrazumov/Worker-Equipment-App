package com.quackAboutIt.workingequipmentapp.notifications.presentation.notification_list

import com.quackAboutIt.workingequipmentapp.notifications.domain.Notification

sealed class NotificationListScreenState {
    data object Loading: NotificationListScreenState()
    data class Content(
        val notifications: List<Notification>
    ): NotificationListScreenState()
}