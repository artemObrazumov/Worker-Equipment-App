package com.quackAboutIt.workingequipmentapp.requests.presentation.request_list.components

import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.EaseOutCubic
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
        var part by remember {
            mutableStateOf(0.dp)
        }
        val partAnimated by animateDpAsState(
            targetValue = part * progress,
            animationSpec = tween(
                durationMillis = 1200,
                easing = EaseOutCubic
            ),
            label = ""
        )
        var filledProgress by remember {
            mutableStateOf(0.dp)
        }
        val filledProgressAnimated by animateDpAsState(
            targetValue = filledProgress,
            animationSpec = tween(
                durationMillis = 1200,
                easing = EaseOutCubic
            ),
            label = ""
        )
        val maxWidth = maxWidth
        LaunchedEffect(true) {
            part = maxWidth / (total + 1)
            filledProgress = maxWidth / (total + 1) * progress + 11.dp
        }
        Icon(
            painterResource(id = R.drawable.car),
            modifier = Modifier
                .padding(start = partAnimated),
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
                    .width(min(filledProgressAnimated, maxWidth))
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