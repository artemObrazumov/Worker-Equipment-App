package com.quackAboutIt.workingequipmentapp.notifications.data

import com.quackAboutIt.workingequipmentapp.notifications.domain.Notification
import com.quackAboutIt.workingequipmentapp.notifications.domain.NotificationIcon
import com.quackAboutIt.workingequipmentapp.notifications.domain.NotificationResult
import com.quackAboutIt.workingequipmentapp.notifications.domain.NotificationsRepository
import kotlinx.coroutines.delay
import java.time.ZonedDateTime

class NotificationRemoteRepository : NotificationsRepository {

    override suspend fun getNotifications(token: String): NotificationResult {
        delay(1000L)
        return NotificationResult.Success(
            listOf(
                Notification(
                    id = 1,
                    time = ZonedDateTime.now(),
                    title = "Test",
                    content = "content ".repeat(10),
                    icon = NotificationIcon.ICON_PROCESSED
                ),
                Notification(
                    id = 1,
                    time = ZonedDateTime.now(),
                    title = "Test",
                    content = "content ".repeat(10),
                    icon = NotificationIcon.ICON_PROCESSING
                ),
                Notification(
                    id = 1,
                    time = ZonedDateTime.now(),
                    title = "Test",
                    content = "content ".repeat(10),
                    icon = NotificationIcon.ICON_TIME
                ),
                Notification(
                    id = 1,
                    time = ZonedDateTime.now(),
                    title = "Test",
                    content = "content ".repeat(10),
                    icon = NotificationIcon.ICON_DONE
                )
            )
        )
    }
}