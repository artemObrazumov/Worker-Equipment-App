package com.quackAboutIt.workingequipmentapp.notifications.domain

import java.time.ZonedDateTime

data class Notification(
    val id: Long,
    val time: ZonedDateTime,
    val title: String,
    val content: String,
    val icon: NotificationIcon
)

enum class NotificationIcon {
    ICON_TIME, ICON_DONE, ICON_PROCESSING, ICON_PROCESSED
}