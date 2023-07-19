package com.example.ndkstudy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.ndkstudy.ui.theme.NdkStudyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NdkStudyTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    InputBar(this)
                }
            }
        }
    }

    external fun performCalculation(num1: Int, num2: Int): String

    companion object {
        // Used to load the 'ndkstudy2' library on application startup.

        init {
            System.loadLibrary("ndkstudy2")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputBar(activity: MainActivity) {
    val state = remember {
        mutableStateOf("")
    }
    val state1 = remember {
        mutableStateOf("")
    }
    val result = remember {
        mutableStateOf("")
    }
    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(value = state.value, onValueChange = { state.value = it })
        OutlinedTextField(value = state1.value, onValueChange = { state1.value = it })
        Button(onClick = {
            result.value = activity.performCalculation(state.value.toInt(), state1.value.toInt())
        }) {
            Text(text = "calculate")
        }
        Text(text = "Result: ${result.value}")
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NdkStudyTheme {
    }
}