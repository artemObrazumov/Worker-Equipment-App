package com.quackAboutIt.workingequipmentapp.requests.presentation.request_list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.min
import com.quackAboutIt.workingequipmentapp.R
import com.quackAboutIt.workingequipmentapp.ui.theme.WorkingEquipmentAppTheme

@Composable
fun RequestProgress(
    progress: Int,
    total: Int,
    modifier: Modifier = Modifier
) {
    BoxWithConstraints(
        modifier = modifier.fillMaxWidth()
    ) {
        val part = maxWidth / (total+1)
        val filledProgress = maxWidth / (total+1) * progress
        val maxWidth = maxWidth
        Icon(
            painterResource(id = R.drawable.car),
            modifier = Modifier
                .padding(start = part * progress),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 18.dp)
                .border(
                    1.dp,
                    MaterialTheme.colorScheme.primary,
                    shape = RoundedCornerShape(2.dp)
                )
        ) {
            Box(
                modifier = Modifier
                    .height(10.dp)
                    .background(
                        MaterialTheme.colorScheme.primary,
                        shape = RoundedCornerShape(2.dp)
                    )
                    .width( min(filledProgress + 11.dp, maxWidth) )
            )
        }
    }
}

@Composable
@Preview
fun RequestProgressPreview() {
    WorkingEquipmentAppTheme {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            RequestProgress(
                progress = 8,
                total = 12
            )
        }
    }
}