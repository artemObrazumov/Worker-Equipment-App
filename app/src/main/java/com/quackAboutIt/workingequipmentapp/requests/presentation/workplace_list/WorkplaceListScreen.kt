package com.quackAboutIt.workingequipmentapp.requests.presentation.workplace_list

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.quackAboutIt.workingequipmentapp.core.presentation.LoadingScreen
import com.quackAboutIt.workingequipmentapp.requests.domain.Workplace
import com.quackAboutIt.workingequipmentapp.requests.presentation.workplace_list.components.WorkplaceItem

@Composable
fun WorkplaceListScreen(
    state: WorkplaceListScreenState,
    modifier: Modifier = Modifier,
    onWorkplaceClicked: (workplace: Workplace) -> Unit
) {
    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
    ) {
        if (state.isLoading) {
            item {
                LoadingScreen(
                    modifier = modifier
                )
            }
        } else {
            items(
                items = state.workplaces
            ) { workplace ->
                WorkplaceItem(
                    modifier = Modifier,
                    workplace = workplace,
                    onClick = {
                        onWorkplaceClicked(workplace)
                    }
                )
            }
        }
    }
}