package com.rifka.myprofileapp.viewmodel

import androidx.lifecycle.ViewModel
import com.rifka.myprofileapp.data.ProfileUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ProfileViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(ProfileUiState())
    val uiState: StateFlow<ProfileUiState> = _uiState.asStateFlow()

    fun updateProfile(name: String, bio: String) {
        _uiState.update { currentState ->
            currentState.copy(
                name = name,
                bio = bio
            )
        }
    }

    fun toggleDarkMode(isDarkMode: Boolean) {
        _uiState.update { currentState ->
            currentState.copy(
                isDarkMode = isDarkMode
            )
        }
    }
}