package com.rifka.myprofileapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.rifka.myprofileapp.ui.screens.ProfileScreen
import com.rifka.myprofileapp.ui.theme.MyProfileAppTheme
import com.rifka.myprofileapp.viewmodel.ProfileViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel: ProfileViewModel = viewModel()
            val uiState by viewModel.uiState.collectAsState()

            MyProfileAppTheme(darkTheme = uiState.isDarkMode) {
                ProfileScreen(viewModel = viewModel)
            }
        }
    }
}