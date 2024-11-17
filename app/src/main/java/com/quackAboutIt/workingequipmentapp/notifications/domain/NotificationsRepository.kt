package com.quackAboutIt.workingequipmentapp.notifications.domain

interface NotificationsRepository {

    suspend fun getNotifications(token: String): NotificationResult
}