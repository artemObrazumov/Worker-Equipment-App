package com.quackAboutIt.workingequipmentapp.requests.presentation.request_editor

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.quackAboutIt.workingequipmentapp.R
import com.quackAboutIt.workingequipmentapp.core.presentation.components.Container
import com.quackAboutIt.workingequipmentapp.core.presentation.components.DetailsTopBar
import com.quackAboutIt.workingequipmentapp.core.presentation.LoadingScreen
import com.quackAboutIt.workingequipmentapp.requests.domain.Equipment
import com.quackAboutIt.workingequipmentapp.requests.domain.Workplace
import com.quackAboutIt.workingequipmentapp.requests.presentation.equipment_list.EquipmentListScreen
import com.quackAboutIt.workingequipmentapp.requests.presentation.equipment_list.EquipmentListScreenViewModel
import com.quackAboutIt.workingequipmentapp.requests.presentation.request_editor.component.EquipmentInRequestItem
import com.quackAboutIt.workingequipmentapp.requests.presentation.workplace_list.WorkplaceListScreen
import com.quackAboutIt.workingequipmentapp.requests.presentation.workplace_list.WorkplaceListScreenViewModel
import org.koin.androidx.compose.koinViewModel
import java.text.NumberFormat
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RequestEditorScreen(
    state: RequestEditorScreenState,
    onBackPressed: () -> Unit,
    onWorkplaceMenuOpened: () -> Unit,
    onWorkplaceMenuClosed: () -> Unit,
    onWorkplaceSelected: (workplace: Workplace) -> Unit,
    onEquipmentMenuOpened: () -> Unit,
    onEquipmentMenuClosed: () -> Unit,
    onEquipmentSelected: (equipment: Equipment) -> Unit,
    onDistanceChanged: (distanceString: String) -> Unit,
    modifier: Modifier = Modifier
) {
    when (state) {
        is RequestEditorScreenState.Loading -> {
            LoadingScreen(
                modifier = modifier
            )
        }

        is RequestEditorScreenState.Content -> {
            LazyColumn(
                modifier = modifier
            ) {
                item {
                    DetailsTopBar(
                        modifier = Modifier
                            .padding(horizontal = 16.dp),
                        title = "Создание заявки",
                        onBackPressed = onBackPressed
                    )
                }
                item {
                    Text(
                        modifier = Modifier
                            .padding(horizontal = 16.dp),
                        text = buildAnnotatedString {
                            withStyle(SpanStyle(fontWeight = FontWeight.SemiBold)) {
                                append("Данные о мастере\n")
                            }
                            append(state.workerName)
                        },
                        style = MaterialTheme.typography.labelLarge
                    )
                }
                item {
                    Spacer(
                        modifier = Modifier
                            .height(28.dp)
                    )
                }
                item {
                    Text(
                        modifier = Modifier
                            .padding(horizontal = 16.dp),
                        text = buildAnnotatedString {
                            withStyle(SpanStyle(fontWeight = FontWeight.SemiBold)) {
                                append("Данные о подразделении\n")
                            }
                            append(state.unitAddress)
                        },
                        style = MaterialTheme.typography.labelLarge
                    )
                }
                item {
                    Spacer(
                        modifier = Modifier
                            .height(28.dp)
                    )
                }
                item {
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
                            text = state.workplaceAddress,
                            style = MaterialTheme.typography.labelLarge
                        )
                        Spacer(
                            modifier = Modifier
                                .width(20.dp)
                        )
                        Icon(
                            painterResource(id = R.drawable.pencil),
                            contentDescription = null,
                            modifier = Modifier
                                .size(40.dp)
                                .clip(CircleShape)
                                .clickable {
                                    onWorkplaceMenuOpened()
                                }
                                .padding(8.dp)
                        )
                    }
                }
                item {
                    Spacer(
                        modifier = Modifier
                            .height(28.dp)
                    )
                }
                item {
                    Text(
                        modifier = Modifier
                            .padding(horizontal = 16.dp),
                        text = buildAnnotatedString {
                            withStyle(SpanStyle(fontWeight = FontWeight.SemiBold)) {
                                append("Расстояние до объекта\n")
                            }
                        },
                        style = MaterialTheme.typography.labelLarge
                    )
                    Container(
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                    ) {
                        BasicTextField(
                            value = state.distanceString,
                            onValueChange = { onDistanceChanged(it) },
                            textStyle = MaterialTheme.typography.bodyMedium,
                            maxLines = 1,
                            modifier = Modifier.fillMaxWidth(),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                        )
                    }
                }
                item {
                    Spacer(
                        modifier = Modifier
                            .height(28.dp)
                    )
                }
                item {
                    Text(
                        modifier = Modifier
                            .padding(horizontal = 16.dp),
                        text = buildAnnotatedString {
                            withStyle(SpanStyle(fontWeight = FontWeight.SemiBold)) {
                                append("Техника\n")
                            }
                        },
                        style = MaterialTheme.typography.labelLarge
                    )
                    Container(
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .clickable(
                                interactionSource = remember {
                                    MutableInteractionSource()
                                },
                                indication = null
                            ) {
                                onEquipmentMenuOpened()
                            }
                    ) {
                        Text(
                            text = "Добавить технику",
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
                item {
                    Spacer(
                        modifier = Modifier
                            .height(18.dp)
                    )
                }
                items(
                    items = state.equipment
                ) {
                    EquipmentInRequestItem(
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .padding(bottom = 18.dp),
                        equipmentInRequest = it,
                        onItemDeleted = {},
                        onItemEdited = {

                        }
                    )
                }
            }

            val workplaceDialogState = rememberModalBottomSheetState()
            if (state.isWorkplaceDialogOpened) {
                ModalBottomSheet(
                    sheetState = workplaceDialogState,
                    onDismissRequest = onWorkplaceMenuClosed,
                    containerColor = MaterialTheme.colorScheme.background
                ) {
                    val viewModel: WorkplaceListScreenViewModel = koinViewModel()
                    val workplaceScreenState by viewModel.state.collectAsState()

                    WorkplaceListScreen(
                        state = workplaceScreenState,
                        onWorkplaceClicked = {
                            onWorkplaceSelected(it)
                        }
                    )
                }
            }

            val equipmentDialogState = rememberModalBottomSheetState()
            if (state.isEquipmentDialogOpened) {
                ModalBottomSheet(
                    sheetState = equipmentDialogState,
                    onDismissRequest = onEquipmentMenuClosed,
                    containerColor = MaterialTheme.colorScheme.background
                ) {
                    val viewModel: EquipmentListScreenViewModel = koinViewModel()
                    val equipmentScreenState by viewModel.state.collectAsState()

                    EquipmentListScreen(
                        state = equipmentScreenState,
                        onEquipmentClicked = {
                            onEquipmentSelected(it)
                        }
                    )
                }
            }
        }
    }
}