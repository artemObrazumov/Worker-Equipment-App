package com.quackAboutIt.workingequipmentapp.requests.presentation.request_list

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.quackAboutIt.workingequipmentapp.core.presentation.components.LoadingScreen
import com.quackAboutIt.workingequipmentapp.requests.domain.Request
import com.quackAboutIt.workingequipmentapp.requests.presentation.request_list.components.MainTopBar
import com.quackAboutIt.workingequipmentapp.requests.presentation.request_list.components.RequestItem
import com.quackAboutIt.workingequipmentapp.ui.theme.WorkingEquipmentAppTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RequestListScreen(
    state: RequestListScreenState,
    topBar: @Composable () -> Unit,
    onRequestClicked: (request: Request) -> Unit,
    modifier: Modifier = Modifier
) {
    when (state) {
        is RequestListScreenState.Loading -> {
            LoadingScreen(
                modifier = modifier
            )
        }
        is RequestListScreenState.Content -> {
            LazyColumn(
                modifier = modifier
                    .fillMaxSize()
            ) {
                item {
                    topBar()
                }
                if (state.currentRequests.isNotEmpty()) {
                    stickyHeader {
                        Text(
                            text = "Текущие заявки",
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(MaterialTheme.colorScheme.background)
                                .padding(horizontal = 16.dp)
                                .padding(vertical = 18.dp),
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                    items(
                        items = state.currentRequests
                    ) { request ->
                        RequestItem(
                            modifier = Modifier
                                .padding(horizontal = 16.dp)
                                .padding(bottom = 18.dp),
                            request = request,
                            onRequestClick = {
                                onRequestClicked(request)
                            }
                        )
                    }
                    item {
                        Spacer(
                            modifier = Modifier
                                .height(12.dp)
                        )
                    }
                }
                if (state.futureRequests.isNotEmpty()) {
                    stickyHeader {
                        Text(
                            text = "Предстоящие заявки",
                            modifier = Modifier
                                .padding(horizontal = 16.dp)
                                .padding(bottom = 18.dp),
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                    items(
                        items = state.futureRequests
                    ) { request ->
                        RequestItem(
                            modifier = Modifier
                                .padding(horizontal = 16.dp)
                                .padding(bottom = 18.dp),
                            request = request,
                            onRequestClick = {
                                onRequestClicked(request)
                            }
                        )
                    }
                }
                if (state.finishedRequests.isNotEmpty()) {
                    stickyHeader {
                        Text(
                            text = "Завершённые заявки",
                            modifier = Modifier
                                .padding(horizontal = 16.dp)
                                .padding(bottom = 18.dp),
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                    items(
                        items = state.finishedRequests
                    ) { request ->
                        RequestItem(
                            modifier = Modifier
                                .padding(horizontal = 16.dp)
                                .padding(bottom = 18.dp),
                            request = request,
                            onRequestClick = {
                                onRequestClicked(request)
                            }
                        )
                    }
                }
                item {
                    Spacer(
                        modifier = Modifier
                            .height(48.dp)
                    )
                }
            }
        }
    }
}