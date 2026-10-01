package com.rifka.myprofileapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.rifka.myprofileapp.ui.screens.ProfileScreen
import com.rifka.myprofileapp.ui.theme.MyProfileAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyProfileAppTheme {
                // Memanggil antarmuka utama
                ProfileScreen()
            }
        }
    }
}