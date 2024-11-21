package com.quackAboutIt.workingequipmentapp.requests.presentation.equipment_list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.quackAboutIt.workingequipmentapp.core.presentation.LoadingScreen
import com.quackAboutIt.workingequipmentapp.requests.domain.Equipment
import com.quackAboutIt.workingequipmentapp.requests.presentation.equipment_list.components.EquipmentItem

@Composable
fun EquipmentListScreen(
    state: EquipmentListScreenState,
    modifier: Modifier = Modifier,
    onEquipmentClicked: (equipment: Equipment) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        if (state.isLoading) {
            item(
                span = { GridItemSpan(2) }
            ) {
                LoadingScreen(
                    modifier = modifier
                )
            }
        } else {
            items(
                items = state.equipment
            ) { equipment ->
                EquipmentItem(
                    equipment = equipment,
                    onClick = {
                        onEquipmentClicked(equipment)
                    }
                )
            }
        }
    }
}