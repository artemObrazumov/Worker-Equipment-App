package com.quackAboutIt.workingequipmentapp.notifications.domain

sealed class NotificationResult {

    data class Success(
        val notifications: List<Notification>
    ): NotificationResult()

    data class Failure(
        val errorMessage: String
    ): NotificationResult()
}