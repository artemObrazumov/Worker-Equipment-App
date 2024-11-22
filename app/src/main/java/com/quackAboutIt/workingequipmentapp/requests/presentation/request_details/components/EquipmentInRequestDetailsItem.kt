package com.quackAboutIt.workingequipmentapp.requests.presentation.request_details.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.quackAboutIt.workingequipmentapp.core.presentation.components.Container
import com.quackAboutIt.workingequipmentapp.requests.domain.EquipmentInRequestDetails
import java.time.format.DateTimeFormatter

@Composable
fun EquipmentInRequestDetailsItem(
    equipment: EquipmentInRequestDetails,
    modifier: Modifier = Modifier
) {
    val formatter by remember {
        mutableStateOf(DateTimeFormatter.ofPattern("HH:mm"))
    }
    Container(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Column{
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                ) {
                    AsyncImage(
                        model = equipment.image,
                        contentDescription = equipment.name,
                        modifier  = Modifier
                            .fillMaxWidth()
                    )
                    Spacer(
                        modifier = Modifier
                            .height(12.dp)
                    )
                    Text(
                        text = equipment.name,
                        style = MaterialTheme.typography.labelMedium,
                        textAlign = TextAlign.Center
                    )
                }
                Spacer(modifier = Modifier.width(18.dp))
                Column(
                    modifier = Modifier
                        .weight(1f)
                ) {
                    Text(
                        text = buildAnnotatedString {
                            withStyle(SpanStyle(fontWeight = FontWeight.SemiBold)) {
                                append("Гос. номер:\n")
                            }
                            append(equipment.number)
                        },
                        style = MaterialTheme.typography.labelLarge
                    )
                    Spacer(
                        modifier = Modifier
                            .height(12.dp)
                    )
                    Text(
                        text = buildAnnotatedString {
                            withStyle(SpanStyle(fontWeight = FontWeight.SemiBold)) {
                                append("Время подачи:\n")
                            }
                            append(formatter.format(equipment.arrivalTime))
                        },
                        style = MaterialTheme.typography.labelLarge
                    )
                    Spacer(
                        modifier = Modifier
                            .height(12.dp)
                    )
                    Text(
                        text = buildAnnotatedString {
                            withStyle(SpanStyle(fontWeight = FontWeight.SemiBold)) {
                                append("Время работы:\n")
                            }
                            append("${equipment.duration.toHours() % 24} ч. ${equipment.duration.toMinutes() % 60} мин.")
                        },
                        style = MaterialTheme.typography.labelLarge
                    )
                }
            }
        }
    }
}