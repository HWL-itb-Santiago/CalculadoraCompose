package cat.itb.m78.exercices.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cat.itb.m78.exercices.viewModels.FinalResult
import m78exercices.composeapp.generated.resources.Calculator_icon
import m78exercices.composeapp.generated.resources.Res
import org.jetbrains.compose.resources.painterResource

@Composable
fun ScreenResult()
{
    val finalResult = FinalResult.get().finalResult
    val image = painterResource(Res.drawable.Calculator_icon)
    Box(
        modifier = Modifier
            .background(color = Color.Yellow)
            .fillMaxSize()
    )
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Text(
            text = " The result is\n${finalResult}",
            style = MaterialTheme.typography.headlineLarge,
            textAlign = TextAlign.Center
        )
        Image(
            painter = image,
            contentDescription = "",
            contentScale = ContentScale.Fit,
            modifier = Modifier.size(300.dp)
        )
    }
}