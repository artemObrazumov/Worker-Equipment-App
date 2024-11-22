package com.quackAboutIt.workingequipmentapp.core.presentation

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.StartOffset
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.quackAboutIt.workingequipmentapp.R
import kotlinx.coroutines.delay

@Composable
fun LoadingScreen(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        CircularProgressIndicator(
            modifier = Modifier.align(Alignment.Center)
        )
//        val animatedFloat = remember { Animatable(1f) }
//        val animatedFloat2 = remember { Animatable(1f) }
//
//        LaunchedEffect(animatedFloat) {
//            delay(200)
//            animatedFloat.animateTo(
//                targetValue = 0.4f, animationSpec = infiniteRepeatable(
//                    animation = tween(200, easing = FastOutSlowInEasing, delayMillis = 200),
//                    repeatMode = RepeatMode.Reverse
//                )
//            )
//        }
//        LaunchedEffect(animatedFloat) {
//            delay(300)
//            animatedFloat2.animateTo(
//                targetValue = 0.4f, animationSpec = infiniteRepeatable(
//                    animation = tween(200, easing = FastOutSlowInEasing, delayMillis = 200),
//                    repeatMode = RepeatMode.Reverse
//                )
//            )
//        }
//        Icon(
//            painter = painterResource(id = R.drawable.car),
//            contentDescription = null,
//            modifier = Modifier
//                .size(96.dp)
//                .scale(
//                    scaleX = animatedFloat2.value,
//                    scaleY = animatedFloat.value
//                )
//                .align(Alignment.Center)
//        )
    }
}