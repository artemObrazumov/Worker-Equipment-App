package com.quackAboutIt.workingequipmentapp.requests.presentation.request_list.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.quackAboutIt.workingequipmentapp.R

@Composable
fun MainTopBar(
    name: String,
    onNotificationClicked: () -> Unit,
    onExitClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 28.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = name,
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(
            modifier = Modifier
                .weight(1f)
        )
        Icon(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .clickable {
                    onNotificationClicked()
                }
                .padding(8.dp),
            painter = painterResource(id = R.drawable.notification),
            contentDescription = "Уведомления"
        )
        Spacer(
            modifier = Modifier
                .width(8.dp)
        )
        Icon(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .clickable {
                    onExitClicked()
                }
                .padding(8.dp),
            painter = painterResource(id = R.drawable.exit),
            contentDescription = "Выход"
        )
    }
}