package com.quackAboutIt.workingequipmentapp.notifications.presentation.notification_list.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.quackAboutIt.workingequipmentapp.R
import com.quackAboutIt.workingequipmentapp.core.presentation.components.Container
import com.quackAboutIt.workingequipmentapp.notifications.domain.Notification
import com.quackAboutIt.workingequipmentapp.notifications.domain.NotificationIcon
import java.time.format.DateTimeFormatter

@Composable
fun NotificationItem(
    notification: Notification,
    modifier: Modifier = Modifier
) {
    Container(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(
                    id = when (notification.icon) {
                        NotificationIcon.ICON_TIME -> R.drawable.icon_time
                        NotificationIcon.ICON_DONE -> R.drawable.icon_done
                        NotificationIcon.ICON_PROCESSED -> R.drawable.icon_processed
                        NotificationIcon.ICON_PROCESSING -> R.drawable.icon_processing
                    }
                ),
                contentDescription = notification.title
            )
            Spacer(
                modifier = Modifier
                    .width(18.dp)
            )
            Column {
                val formatter = DateTimeFormatter.ofPattern("HH:mm · dd.MM.yyyy")
                Text(
                    text = buildAnnotatedString {
                        withStyle(
                            SpanStyle(
                                fontWeight = FontWeight.Light,
                                fontSize = 14.sp
                            )
                        ) {
                            append(notification.time.format(formatter) + "\n")
                        }
                        append(notification.title + "\n")
                        withStyle(
                            SpanStyle(
                                fontWeight = FontWeight.Medium
                            )
                        ) {
                            append(notification.content)
                        }
                    },
                    style = MaterialTheme.typography.labelMedium
                )
            }
        }
    }
}