package com.rifka.helloworldkmp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun App() {
    var isClicked by remember { mutableStateOf(false) }

    val pinkMuda = Color(0xFFFFC0CB)
    val biruTombol = Color(0xFF2196F3)
    val warnaTeks = Color(0xFF333333)

    MaterialTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = pinkMuda
        ) {

            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = { isClicked = true },
                    colors = ButtonDefaults.buttonColors(containerColor = biruTombol),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        text = "Open Apps!!!",
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                if (isClicked) {
                    Spacer(modifier = Modifier.height(24.dp))

                    Text(
                        text = "Nice to meet you!",
                        fontSize = 16.sp,
                        color = warnaTeks,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Halo, Rifka Priseilla Br Silitonga!",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = warnaTeks,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "NIM: 123140024",
                        fontSize = 18.sp,
                        color = warnaTeks,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Run on ${getPlatform().name}!",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        color = warnaTeks,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}