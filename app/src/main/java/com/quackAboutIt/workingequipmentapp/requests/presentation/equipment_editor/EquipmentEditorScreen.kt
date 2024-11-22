package com.quackAboutIt.workingequipmentapp.requests.presentation.equipment_editor

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
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
    onWorkTimeClicked: () -> Unit,
    onArrivalTimeClicked: () -> Unit,
    onSave: () -> Unit
) {
    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        item {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(equipmentInRequest.image)
                    .build(),
                contentDescription = equipmentInRequest.equipmentName,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1.5f)
            )
            Spacer(
                modifier = Modifier
                    .height(18.dp)
            )
            Text(
                text = equipmentInRequest.equipmentName,
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(
                modifier = Modifier
                    .height(18.dp)
            )
            FlowRow(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement
                    .spacedBy(
                        space = 16.dp,
                        alignment = Alignment.CenterHorizontally
                    ),
            ) {
                equipmentInRequest.types.forEach {
                    val isActive = it.id == equipmentInRequest.equipmentType.id
                    Container(
                        modifier = Modifier
                            .width(IntrinsicSize.Min)
                            .background(
                                if (isActive) {
                                    MaterialTheme.colorScheme.secondary
                                } else {
                                    Color.Transparent
                                },
                                shape = RoundedCornerShape(10.dp)
                            )
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
                            style = MaterialTheme.typography.labelMedium,
                            color = if (isActive) {
                                Color.White
                            } else {
                                Color.Black
                            }
                        )
                    }
                }
            }
            Spacer(
                modifier = Modifier
                    .height(18.dp)
            )
            Text(
                text = buildAnnotatedString {
                    withStyle(SpanStyle(fontWeight = FontWeight.SemiBold)) {
                        append("Количество")
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
                        textStyle = MaterialTheme.typography.labelMedium.copy(textAlign = TextAlign.Center),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        maxLines = 1,
                        modifier = Modifier.weight(1f),
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
                text = buildAnnotatedString {
                    withStyle(SpanStyle(fontWeight = FontWeight.SemiBold)) {
                        append("Время подачи")
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
                val formatter = DateTimeFormatter.ofPattern("HH:mm")
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
                text = buildAnnotatedString {
                    withStyle(SpanStyle(fontWeight = FontWeight.SemiBold)) {
                        append("Время работы")
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
                        onWorkTimeClicked()
                    }
            ) {
                Row {
                    Text(
                        text = "${equipmentInRequest.workHours} ч. " +
                                "${equipmentInRequest.workMinutes.toString().padStart(2, '0')} мин.",
                        style = MaterialTheme.typography.labelMedium,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                }
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
                    text = "Сохранить",
                    style = MaterialTheme.typography.labelMedium
                )
            }
        }
    }
}