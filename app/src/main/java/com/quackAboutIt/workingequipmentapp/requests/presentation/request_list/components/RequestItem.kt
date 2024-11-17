package com.quackAboutIt.workingequipmentapp.requests.presentation.request_list.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.quackAboutIt.workingequipmentapp.core.presentation.components.Container
import com.quackAboutIt.workingequipmentapp.requests.domain.Request
import com.quackAboutIt.workingequipmentapp.requests.domain.RequestState
import com.quackAboutIt.workingequipmentapp.ui.theme.WorkingEquipmentAppTheme
import java.time.format.DateTimeFormatter

@Composable
fun RequestItem(
    request: Request,
    onRequestClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Container(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Column {
            Text(
                text = buildAnnotatedString {
                    withStyle(SpanStyle(fontWeight = FontWeight.SemiBold)) {
                        append("Заявка №${request.id.toString().padStart(9, '0')}\n")
                    }
                    withStyle(SpanStyle(fontWeight = FontWeight.SemiBold)) {
                        append("Кол-во техники: ")
                    }
                    append("${request.equipmentCount} ед.\n")
                    val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
                    append("К объекту по адресу ${request.workplaceAddress} ${request.date.format(formatter)}")
                },
                style = MaterialTheme.typography.labelLarge
            )
            Spacer(
                modifier = Modifier
                    .height(18.dp)
            )
            if (request.progress != null && request.total != null) {
                RequestProgress(
                    progress = request.progress, total = request.total
                )
                Spacer(
                    modifier = Modifier
                        .height(18.dp)
                )
            }
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.secondary
                ),
                shape = RoundedCornerShape(10.dp),
                onClick = onRequestClick
            ) {
                Text(
                    text = "Подробнее",
                    style = MaterialTheme.typography.labelMedium
                )
            }
        }
    }
}