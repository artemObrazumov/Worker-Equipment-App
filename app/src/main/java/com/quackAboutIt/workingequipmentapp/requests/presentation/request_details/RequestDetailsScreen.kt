package com.quackAboutIt.workingequipmentapp.requests.presentation.request_details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.quackAboutIt.workingequipmentapp.R
import com.quackAboutIt.workingequipmentapp.core.presentation.LoadingScreen
import com.quackAboutIt.workingequipmentapp.core.presentation.components.DetailsTopBar
import com.quackAboutIt.workingequipmentapp.requests.presentation.components.RequestProgress
import com.quackAboutIt.workingequipmentapp.requests.presentation.request_details.components.EquipmentInRequestDetailsItem
import java.time.format.DateTimeFormatter

@Composable
fun RequestDetailsScreen(
    state: RequestDetailsScreenState,
    onBackPressed: () -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
    ) {
        item {
            DetailsTopBar(
                modifier = Modifier
                    .padding(horizontal = 16.dp),
                title = "Детали заявки",
                onBackPressed = onBackPressed
            )
        }
        when (state) {
            is RequestDetailsScreenState.Loading -> {
                item {
                    LoadingScreen(
                        modifier = modifier
                            .fillParentMaxSize()
                    )
                }
            }

            is RequestDetailsScreenState.Content -> {
                item {
                    Text(
                        text = "Заявка №${state.requestDetails.id.toString().padStart(9, '0')}\n",
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(MaterialTheme.colorScheme.background)
                            .padding(horizontal = 16.dp)
                            .padding(vertical = 18.dp),
                        style = MaterialTheme.typography.titleMedium
                    )
                    if (state.requestDetails.progress != null && state.requestDetails.total != null) {
                        RequestProgress(
                            progress = state.requestDetails.progress,
                            total = state.requestDetails.total,
                            modifier = Modifier
                                .padding(horizontal = 16.dp)
                        )
                        Spacer(
                            modifier = Modifier
                                .height(28.dp)
                        )
                    }
                    Text(
                        modifier = Modifier
                            .padding(horizontal = 16.dp),
                        text = buildAnnotatedString {
                            withStyle(SpanStyle(fontWeight = FontWeight.SemiBold)) {
                                append("ФИО мастера\n")
                            }
                            append(state.requestDetails.workerName)
                        },
                        style = MaterialTheme.typography.labelLarge
                    )
                    Spacer(
                        modifier = Modifier
                            .height(28.dp)
                    )
                    Text(
                        modifier = Modifier
                            .padding(horizontal = 16.dp),
                        text = buildAnnotatedString {
                            withStyle(SpanStyle(fontWeight = FontWeight.SemiBold)) {
                                append("Адрес подразделения\n")
                            }
                            append(state.requestDetails.unitAddress)
                        },
                        style = MaterialTheme.typography.labelLarge
                    )
                    Spacer(
                        modifier = Modifier
                            .height(28.dp)
                    )
                    Text(
                        modifier = Modifier
                            .padding(horizontal = 16.dp),
                        text = buildAnnotatedString {
                            withStyle(SpanStyle(fontWeight = FontWeight.SemiBold)) {
                                append("Адрес объекта\n")
                            }
                            append(state.requestDetails.workplaceAddress)
                        },
                        style = MaterialTheme.typography.labelLarge
                    )
                    Spacer(
                        modifier = Modifier
                            .height(28.dp)
                    )
                    Text(
                        modifier = Modifier
                            .padding(horizontal = 16.dp),
                        text = buildAnnotatedString {
                            withStyle(SpanStyle(fontWeight = FontWeight.SemiBold)) {
                                append("Расстояние до объекта (в км)\n")
                            }
                            append(state.requestDetails.distance.toString())
                            append(" км")
                        },
                        style = MaterialTheme.typography.labelLarge
                    )
                    Spacer(
                        modifier = Modifier
                            .height(28.dp)
                    )
                    Text(
                        modifier = Modifier
                            .padding(horizontal = 16.dp),
                        text = buildAnnotatedString {
                            withStyle(SpanStyle(fontWeight = FontWeight.SemiBold)) {
                                append("Адрес объекта\n")
                            }
                        },
                        style = MaterialTheme.typography.labelLarge
                    )
                    Row(
                        modifier = Modifier
                            .padding(horizontal = 16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painterResource(id = R.drawable.map),
                            contentDescription = null
                        )
                        Spacer(
                            modifier = Modifier
                                .width(20.dp)
                        )
                        Text(
                            modifier = Modifier
                                .weight(1f),
                            text = state.requestDetails.workplaceAddress,
                            style = MaterialTheme.typography.labelLarge,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                    Spacer(
                        modifier = Modifier
                            .height(28.dp)
                    )
                    Text(
                        modifier = Modifier
                            .padding(horizontal = 16.dp),
                        text = buildAnnotatedString {
                            withStyle(SpanStyle(fontWeight = FontWeight.SemiBold)) {
                                append("Дата подачи на объект\n")
                            }
                            val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
                            append(state.requestDetails.arrivalDate.format(formatter))
                        },
                        style = MaterialTheme.typography.labelLarge
                    )
                    Spacer(
                        modifier = Modifier
                            .height(28.dp)
                    )
                    Text(
                        modifier = Modifier
                            .padding(horizontal = 16.dp),
                        text = buildAnnotatedString {
                            withStyle(SpanStyle(fontWeight = FontWeight.SemiBold)) {
                                append("Техника")
                            }
                        },
                        style = MaterialTheme.typography.labelLarge
                    )
                    Spacer(
                        modifier = Modifier
                            .height(28.dp)
                    )
                }
                items(
                    items = state.requestDetails.equipmentInRequestDetails
                ) { equipment ->
                    EquipmentInRequestDetailsItem(
                        equipment = equipment,
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .padding(bottom = 12.dp)
                    )
                }
            }
        }
    }
}