package com.rabarka.rootchecker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rabarka.rootchecker.ui.theme.RootCheckTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RootCheckTheme {
                ShowImageAndText()
            }
        }
    }
}

@Composable
fun ShowImageAndText() {
    var color: Color by remember {
        mutableStateOf(Color.DarkGray)
    }
    var status by remember {
        mutableStateOf("Check First")
    }
    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
        Column(
            modifier = Modifier
                .padding(24.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            DynamicTextAndImage(color, status)
            Spacer(modifier = Modifier.padding(top = 48.dp))
            Button(
                onClick = {
                    if (Checker.checkRootMethodOne() && Checker.checkRootMethodTwo()) {
                        color = Color.Green
                        status = "# Rooted"
                    } else {
                        color = Color.Red
                        status = "Not Rooted"
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Check", fontSize = 16.sp)
            }
        }
    }
}

@Composable
fun DynamicTextAndImage(color: Color, root_status: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painterResource(id = R.drawable.phone1),
            contentDescription = null,
            Modifier
                .background(color = color, shape = CircleShape)
                .padding(16.dp)
        )
        DynamicSpace(modifier = Modifier.padding(top = 16.dp))
        Text(text = android.os.Build.MANUFACTURER.uppercase() + " " + android.os.Build.MODEL)
        Text(text = "Android Version:" + " " + android.os.Build.VERSION.RELEASE)
        DynamicSpace(modifier = Modifier.padding(top = 48.dp))
        Text(text = root_status, fontSize = 32.sp, color = color)
    }
}

@Composable
fun DynamicSpace(modifier: Modifier) {
    Spacer(modifier = modifier)
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    RootCheckTheme {
        ShowImageAndText()
    }
}