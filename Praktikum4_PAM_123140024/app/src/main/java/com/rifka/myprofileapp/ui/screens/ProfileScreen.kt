package com.rifka.myprofileapp.ui.screens

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.rifka.myprofileapp.ui.components.*
import com.rifka.myprofileapp.ui.theme.*
import com.rifka.myprofileapp.viewmodel.ProfileViewModel

@Composable
fun ProfileScreen(viewModel: ProfileViewModel = viewModel()) {
    val uiState by viewModel.uiState.collectAsState()

    var isEditing by remember { mutableStateOf(false) }
    var editName by remember { mutableStateOf(uiState.name) }
    var editBio by remember { mutableStateOf(uiState.bio) }
    var showContactInfo by remember { mutableStateOf(false) }

    val bgColor by animateColorAsState(
        targetValue = if (uiState.isDarkMode) Color(0xFF121212) else PastelPinkBackground,
        animationSpec = tween(500),
        label = "bgAnim"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(bgColor)
            .padding(24.dp)
            .imePadding()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = if (uiState.isDarkMode) "Dark Mode" else "Light Mode",
                color = if (uiState.isDarkMode) Color.White else Color.Black
            )
            Spacer(modifier = Modifier.width(8.dp))
            Switch(
                checked = uiState.isDarkMode,
                onCheckedChange = { viewModel.toggleDarkMode(it) }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (isEditing) {
            Text("Edit Profil", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = if(uiState.isDarkMode) Color.White else Color.Black)
            Spacer(modifier = Modifier.height(16.dp))

            LabeledTextField(label = "Nama Lengkap", value = editName, onValueChange = { editName = it }, isDarkMode = uiState.isDarkMode)
            Spacer(modifier = Modifier.height(12.dp))
            LabeledTextField(label = "Bio Profil", value = editBio, onValueChange = { editBio = it }, isDarkMode = uiState.isDarkMode, singleLine = false)

            Spacer(modifier = Modifier.height(24.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                Button(
                    onClick = { isEditing = false },
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Gray)
                ) { Text("Batal") }

                Button(
                    onClick = {
                        viewModel.updateProfile(editName, editBio)
                        isEditing = false
                    },
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(containerColor = PastelPinkPrimary)
                ) { Text("Simpan") }
            }
        } else {
            ProfileHeader(name = uiState.name, isDarkMode = uiState.isDarkMode)
            Spacer(modifier = Modifier.height(24.dp))
            ProfileBioCard(bio = uiState.bio, isDarkMode = uiState.isDarkMode)
            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    editName = uiState.name
                    editBio = uiState.bio
                    isEditing = true
                },
                modifier = Modifier.fillMaxWidth().height(45.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray),
                shape = RoundedCornerShape(12.dp)
            ) { Text("Edit Profil", color = Color.White) }

            Spacer(modifier = Modifier.height(16.dp))

            AnimatedVisibility(
                visible = showContactInfo,
                enter = fadeIn(tween(500)) + expandVertically(tween(500)),
                exit = fadeOut(tween(500)) + shrinkVertically(tween(500))
            ) {
                Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    InfoItem(icon = Icons.Default.Email, text = "Rifka.123140024@student.itera.ac.id", isDarkMode = uiState.isDarkMode)
                    InfoItem(icon = Icons.Default.Phone, text = "0822-7788-9944", isDarkMode = uiState.isDarkMode)
                    InfoItem(icon = Icons.Default.LocationOn, text = "Jl.Way Kandis No.78", isDarkMode = uiState.isDarkMode)
                }
            }

            Spacer(modifier = Modifier.height(32.dp))
            Button(
                onClick = { showContactInfo = !showContactInfo },
                modifier = Modifier.fillMaxWidth().height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = PastelPinkPrimary),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(if (showContactInfo) "Sembunyikan Kontak" else "Connect with Me", color = Color.White, fontWeight = FontWeight.Bold)
            }
        }
    }
}