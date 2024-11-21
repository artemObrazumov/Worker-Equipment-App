package com.quackAboutIt.workingequipmentapp.requests.presentation.equipment_editor

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.quackAboutIt.workingequipmentapp.R
import com.quackAboutIt.workingequipmentapp.core.presentation.components.Container
import com.quackAboutIt.workingequipmentapp.requests.domain.EquipmentType
import com.quackAboutIt.workingequipmentapp.requests.presentation.request_editor.EquipmentInRequestState
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun EquipmentEditorScreen(
    equipmentInRequest: EquipmentInRequestState,
    modifier: Modifier = Modifier,
    onTypeSelected: (type: EquipmentType) -> Unit,
    onQuantityChanged: (quantity: Int) -> Unit,
    onWorkHoursChanged: (hours: Int) -> Unit,
    onWorkMinutesChanged: (minutes: Int) -> Unit,
    onArrivalTimeClicked: () -> Unit,
    onSave: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        Spacer(
            modifier = Modifier
                .height(18.dp)
        )
        FlowRow(
            horizontalArrangement = Arrangement
                .spacedBy(
                    space = 16.dp,
                    alignment = Alignment.CenterHorizontally
                ),
        ) {
            equipmentInRequest.types.forEach {
                Container(
                    modifier = Modifier
                        .clickable(
                            interactionSource = remember {
                                MutableInteractionSource()
                            },
                            indication = null
                        ) {
                            onTypeSelected(it)
                        }
                ) {
                    Text(
                        text = it.name,
                        style = MaterialTheme.typography.labelMedium
                    )
                }
            }
        }
        Spacer(
            modifier = Modifier
                .height(18.dp)
        )
        Text(
            modifier = Modifier
                .padding(horizontal = 16.dp),
            text = buildAnnotatedString {
                withStyle(SpanStyle(fontWeight = FontWeight.SemiBold)) {
                    append("Количество\n")
                }
            },
            style = MaterialTheme.typography.labelLarge
        )
        Spacer(
            modifier = Modifier
                .height(18.dp)
        )
        Container {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.minus),
                    contentDescription = null,
                    modifier = Modifier
                        .clickable(
                            interactionSource = remember {
                                MutableInteractionSource()
                            },
                            indication = null
                        ) {
                            onQuantityChanged(equipmentInRequest.quantity - 1)
                        }
                )
                BasicTextField(
                    value = if (equipmentInRequest.quantity == 0) {
                        ""
                    } else {
                        equipmentInRequest.quantity.toString()
                    },
                    onValueChange = { newQ -> onQuantityChanged(newQ.toIntOrNull() ?: 0) },
                    textStyle = MaterialTheme.typography.bodyMedium.copy(textAlign = TextAlign.Center),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    maxLines = 1
                )
                Icon(
                    painter = painterResource(id = R.drawable.plus),
                    contentDescription = null,
                    modifier = Modifier.clickable(
                        interactionSource = remember {
                            MutableInteractionSource()
                        },
                        indication = null
                    ) {
                        onQuantityChanged(equipmentInRequest.quantity + 1)
                    }
                )
            }
        }
        Spacer(
            modifier = Modifier
                .height(18.dp)
        )
        Text(
            modifier = Modifier
                .padding(horizontal = 16.dp),
            text = buildAnnotatedString {
                withStyle(SpanStyle(fontWeight = FontWeight.SemiBold)) {
                    append("Время подачи\n")
                }
            },
            style = MaterialTheme.typography.labelLarge
        )
        Spacer(
            modifier = Modifier
                .height(18.dp)
        )
        Container(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(
                    interactionSource = remember {
                        MutableInteractionSource()
                    },
                    indication = null
                ) {
                    onArrivalTimeClicked()
                }
        ) {
            val formatter = DateTimeFormatter.ofPattern("hh:mm")
            Text(
                text = formatter.format(equipmentInRequest.arrivalTime),
                style = MaterialTheme.typography.labelMedium,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }
        Spacer(
            modifier = Modifier
                .height(18.dp)
        )
        Text(
            modifier = Modifier
                .padding(horizontal = 16.dp),
            text = buildAnnotatedString {
                withStyle(SpanStyle(fontWeight = FontWeight.SemiBold)) {
                    append("Время работы\n")
                }
            },
            style = MaterialTheme.typography.labelLarge
        )
        Spacer(
            modifier = Modifier
                .height(18.dp)
        )
        Container(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(
                    interactionSource = remember {
                        MutableInteractionSource()
                    },
                    indication = null
                ) {
                    onArrivalTimeClicked()
                }
        ) {
            BasicTextField(
                value = if (equipmentInRequest.workHours == 0) {
                    ""
                } else {
                    equipmentInRequest.workHours.toString()
                },
                onValueChange = { onWorkHoursChanged(it.toIntOrNull() ?: 0) },
                textStyle = MaterialTheme.typography.bodyMedium.copy(textAlign = TextAlign.Center),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                maxLines = 1
            )
            Spacer(
                modifier = Modifier
                    .width(1.dp)
                    .fillMaxHeight()
                    .background(MaterialTheme.colorScheme.primary)
            )
            BasicTextField(
                value = if (equipmentInRequest.workMinutes == 0) {
                    ""
                } else {
                    equipmentInRequest.workMinutes.toString()
                },
                onValueChange = { onWorkMinutesChanged(it.toIntOrNull() ?: 0) },
                textStyle = MaterialTheme.typography.bodyMedium.copy(textAlign = TextAlign.Center),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                maxLines = 1
            )
        }
        Spacer(
            modifier = Modifier
                .height(18.dp)
        )
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.secondary
            ),
            shape = RoundedCornerShape(10.dp),
            onClick = onSave
        ) {
            Text(
                text = "Добавить",
                style = MaterialTheme.typography.labelMedium
            )
        }
    }
}