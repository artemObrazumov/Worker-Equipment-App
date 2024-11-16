package com.quackAboutIt.workingequipmentapp.requests.presentation.request_list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.quackAboutIt.workingequipmentapp.requests.presentation.request_list.components.MainTopBar

@Composable
fun RequestListScreen(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        MainTopBar(
            name = "Test",
            onNotificationClicked = {  },
            onExitClicked = {  }
        )
    }
}