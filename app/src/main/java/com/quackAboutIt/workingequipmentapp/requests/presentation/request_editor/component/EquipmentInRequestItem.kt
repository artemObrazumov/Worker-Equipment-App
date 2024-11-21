package com.quackAboutIt.workingequipmentapp.requests.presentation.request_editor.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.quackAboutIt.workingequipmentapp.R
import com.quackAboutIt.workingequipmentapp.core.presentation.components.Container
import com.quackAboutIt.workingequipmentapp.requests.presentation.request_editor.EquipmentInRequestState
import java.time.format.DateTimeFormatter

@Composable
fun EquipmentInRequestItem(
    equipmentInRequest: EquipmentInRequestState,
    onItemDeleted: () -> Unit,
    onItemEdited: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val formatter by remember {
        mutableStateOf(DateTimeFormatter.ofPattern("hh:mm"))
    }
    Container(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Column{
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "${equipmentInRequest.equipmentName} ${equipmentInRequest.equipmentType.name}",
                    style = MaterialTheme.typography.labelMedium,
                    modifier = Modifier.weight(1f)
                )
                Icon(
                    painterResource(id = R.drawable.trash),
                    contentDescription = null,
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .clickable {
                            onItemDeleted()
                        }
                        .padding(8.dp)
                )
                Icon(
                    painterResource(id = R.drawable.pencil),
                    contentDescription = null,
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .clickable {
                            onItemEdited()
                        }
                        .padding(8.dp)
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "Количество:\n${equipmentInRequest.quantity} ед.",
                        style = MaterialTheme.typography.labelMedium
                    )
                    Spacer(
                        modifier = Modifier
                            .height(8.dp)
                    )
                    Text(
                        text = "Время подачи:\n${formatter.format(equipmentInRequest.arrivalTime)}",
                        style = MaterialTheme.typography.labelMedium
                    )
                    Spacer(
                        modifier = Modifier
                            .height(8.dp)
                    )
                    Text(
                        text = "Время работы:\n${equipmentInRequest.workHours} ч. " +
                                "${equipmentInRequest.workMinutes} мин.",
                        style = MaterialTheme.typography.labelMedium
                    )
                }
            }
        }
    }
}