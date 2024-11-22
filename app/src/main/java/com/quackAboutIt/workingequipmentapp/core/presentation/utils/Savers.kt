package com.quackAboutIt.workingequipmentapp.core.presentation.utils

import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.SaverScope
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

object DpSaver : Saver<Dp, Float> {
    override fun SaverScope.save(value: Dp): Float {
        return value.value // Save as Float
    }

    override fun restore(value: Float): Dp {
        return value.dp // Restore as Dp
    }
}