package com.quackAboutIt.workingequipmentapp.auth.presentation.login

import android.widget.ProgressBar
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.quackAboutIt.workingequipmentapp.core.presentation.LoadingScreen
import com.quackAboutIt.workingequipmentapp.core.presentation.components.Container
import com.quackAboutIt.workingequipmentapp.ui.theme.WorkingEquipmentAppTheme

@Composable
fun LoginScreen(
    state: LoginScreenState,
    onLoginChanged: (login: String) -> Unit,
    onPasswordChanged: (password: String) -> Unit,
    onLoginAttempt: () -> Unit,
    onLoggedIn: () -> Unit,
    modifier: Modifier = Modifier
) {
    LaunchedEffect(state.hasLoggedIn) {
        if (state.hasLoggedIn) {
            onLoggedIn()
        }
    }
    Column(
        modifier = modifier
            .padding(horizontal = 16.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(144.dp))
        Text(
            text = "Вход в аккаунт",
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.height(60.dp))
        Container {
            if (state.email.isEmpty()) {
                Text(
                    text = "Логин",
                    style = MaterialTheme.typography.bodySmall,
                )
            }
            BasicTextField(
                value = state.email,
                onValueChange = onLoginChanged,
                textStyle = MaterialTheme.typography.bodyMedium,
                maxLines = 1,
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
        Spacer(modifier = Modifier.height(28.dp))
        Container {
            if (state.password.isEmpty()) {
                Text(
                    text = "Пароль",
                    style = MaterialTheme.typography.bodySmall,
                )
            }
            BasicTextField(
                value = state.password,
                onValueChange = onPasswordChanged,
                textStyle = MaterialTheme.typography.bodyMedium,
                maxLines = 1,
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
        Spacer(modifier = Modifier.height(36.dp))
        if (state.isLoading) {
            LoadingScreen(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
            )
        } else {
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.secondary
                ),
                shape = RoundedCornerShape(10.dp),
                onClick = onLoginAttempt
            ) {
                Text(
                    text = "Войти",
                    style = MaterialTheme.typography.labelMedium
                )
            }
        }
        Spacer(modifier = Modifier.height(36.dp))
        Text(
            text = "",
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            text = buildAnnotatedString {
                withStyle(
                    SpanStyle(fontWeight = FontWeight.SemiBold)
                ) {
                    append("Забыли пароль?\n")
                }
                append("Обращайтесь к администратору:\n")
                append("8-800-555-35-35")
            },
            modifier = Modifier
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null
                ) {  },
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
@Preview
fun LoginScreenPreview() {
    WorkingEquipmentAppTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            LoginScreen(
                state = LoginScreenState(isLoading = false, email = "11", password = "pa"),
                onLoginChanged = {},
                onPasswordChanged = {},
                onLoginAttempt = {},
                onLoggedIn = {}
            )
        }
    }
}