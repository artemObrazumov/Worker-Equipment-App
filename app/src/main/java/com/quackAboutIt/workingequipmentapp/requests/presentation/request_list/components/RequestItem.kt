package com.quackAboutIt.workingequipmentapp.requests.presentation.request_list.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.quackAboutIt.workingequipmentapp.core.presentation.components.Container
import com.quackAboutIt.workingequipmentapp.requests.domain.Request
import com.quackAboutIt.workingequipmentapp.requests.domain.RequestState
import com.quackAboutIt.workingequipmentapp.ui.theme.WorkingEquipmentAppTheme

@Composable
fun RequestItem(
    request: Request,
    modifier: Modifier = Modifier
) {
    Container(
        modifier = modifier
            .fillMaxWidth()
    ) {

    }
}

@Preview
@Composable
fun RequestItemPreview() {
    WorkingEquipmentAppTheme {
        RequestItem(
            request = Request(
                id = -1,
                state = RequestState.SENT
            )
        )
    }
}