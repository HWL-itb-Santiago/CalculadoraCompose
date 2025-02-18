package cat.itb.m78.exercices.screens

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import cat.itb.m78.exercices.viewModels.CalculatorViewModel

@Composable
fun ScreenCalculator(endCalculator: () -> Unit)
{
    val calculator = viewModel { CalculatorViewModel() }

    val result by calculator.currentResult.collectAsState()

    ScreenCalculator(
        endCalculator,
        {calculator.validateInput(it)},
        {calculator.calculate(it)},
        {calculator.setType(it)},
        result
    )
}
@Composable
fun ScreenCalculator(
    endCalculator: () -> Unit,
    validateInput: (String) -> Boolean,
    calculate: (Float) -> Unit,
    newType: (Int) -> Unit,
    currentResult: Float)
{
    val listOfOptions = listOf("+", "-", "*", "/")
    var inputNumber =  mutableStateOf("")
    Box(
        modifier = Modifier.background(color = Color.Yellow)
            .fillMaxSize()
    )
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    )
    {
        Text(
            text ="$currentResult",
            style = MaterialTheme.typography.headlineLarge
        )
        Card(
            modifier = Modifier
                .fillMaxWidth(0.45f)
                .fillMaxHeight(0.45f)
                .align(Alignment.CenterHorizontally)
                .padding(top = 25.dp)
        )
        {
            Row(
                modifier = Modifier
                    .padding(top = 30.dp)
                    .align(Alignment.CenterHorizontally),
            )
            {
                listOfOptions.forEachIndexed {
                    i, item ->
                    CalculatorButton(item, validateInput(inputNumber.value)) { newType(i + 1) }
                }
            }

            OutlinedTextField(
                value = inputNumber.value,
                onValueChange = {
                    inputNumber.value = it
                },
                modifier = Modifier.fillMaxWidth(0.85f)
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 10.dp, bottom = 10.dp),
                label = {Text("Cadena vacia no valida")},
            )
            Row(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
            )
            {
                Button(
                    onClick = {
                        endCalculator()
                    },
                    modifier = Modifier.padding(start = 30.dp, end = 30.dp),
                )
                {
                    Text(
                        text = "End"
                    )
                }
                Button(
                    onClick = {
                        if (!inputNumber.value.all { it.isLetter() || it.isWhitespace()})
                            calculate(inputNumber.value.toFloat())
                    }
                )
                {
                    Text(
                        text = "Calculate"
                    )
                }

            }
        }
    }
}

@Composable
fun CalculatorButton(text: String, validate: Boolean, operation: () -> Unit)
{
    var isSelected by remember { mutableStateOf(false)}
    val backgroundColor by animateColorAsState(
        targetValue = if (isSelected) Color.LightGray else Color.Gray, label = "buttonColor"
    )

    Button(
        onClick = {
            if (validate) {
                isSelected = !isSelected
                operation()
            }
                  },
        modifier = Modifier
            .padding(start = 10.dp, end = 10.dp),
        colors = ButtonDefaults.buttonColors(containerColor = backgroundColor)
    )
    {
        Text(
            text = text
        )
    }
}