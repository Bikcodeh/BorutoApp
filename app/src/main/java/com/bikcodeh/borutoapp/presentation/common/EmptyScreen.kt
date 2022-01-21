package com.bikcodeh.borutoapp.presentation.common

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.LoadState
import com.bikcodeh.borutoapp.R
import com.bikcodeh.borutoapp.ui.theme.MEDIUM_PADDING
import com.bikcodeh.borutoapp.ui.theme.NETWORK_ERROR_ICON_HEIGHT
import java.net.ConnectException
import java.net.SocketTimeoutException

@Composable
fun EmptyScreen(error: LoadState.Error) {
    val message by remember {
        mutableStateOf(parseErrorMessage(error = error))
    }

    val icon by remember {
        mutableStateOf(R.drawable.ic_network_error)
    }

    var startAnimation by remember { mutableStateOf(false) }
    val alphaAnim by animateFloatAsState(
        targetValue = if (startAnimation) ContentAlpha.disabled else 0f, animationSpec = tween(
            durationMillis = 1000
        )
    )

    LaunchedEffect(key1 = true) {
        startAnimation = true
    }

    EmptyContent(alphaAnim = alphaAnim, message = message, icon = icon)
}

@Composable
fun EmptyContent(
    alphaAnim: Float,
    message: String,
    icon: Int
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            modifier = Modifier
                .size(NETWORK_ERROR_ICON_HEIGHT)
                .alpha(alpha = alphaAnim),
            painter = painterResource(id = icon),
            contentDescription = stringResource(R.string.network_error),
            tint = if (isSystemInDarkTheme()) Color.LightGray else Color.DarkGray
        )
        Text(
            modifier = Modifier
                .padding(top = MEDIUM_PADDING)
                .alpha(alpha = alphaAnim),
            text = message,
            color = if (isSystemInDarkTheme()) Color.LightGray else Color.DarkGray,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Medium,
            fontSize = MaterialTheme.typography.subtitle1.fontSize
        )
    }
}

fun parseErrorMessage(error: LoadState.Error): String {
    return when {
        error.error is SocketTimeoutException -> {
            "Server Unavailable"
        }
        error.error is ConnectException -> {
            "Internet Unavailable"
        }
        else -> {
            "Unknown Error"
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EmptyScreenPreview() {
    EmptyContent(alphaAnim = 1f, message = "Error", icon = R.drawable.ic_network_error)
}
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun EmptyScreenDarkPreview() {
    EmptyContent(alphaAnim = 1f, message = "Error", icon = R.drawable.ic_network_error)
}