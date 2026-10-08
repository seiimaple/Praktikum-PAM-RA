package com.rifka.myprofileapp.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rifka.myprofileapp.ui.components.*
import com.rifka.myprofileapp.ui.theme.*

@Composable
fun ProfileScreen() {
    val scrollState = rememberScrollState()

    var showContactInfo by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(PastelPinkBackground)
            .padding(24.dp)
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ProfileHeader(name = "Rifka Priseilla")

        Spacer(modifier = Modifier.height(24.dp))

        ProfileBioCard(
            bio = "Mahasiswa semester 7 Program Studi Teknik Informatika di Institut Teknologi Sumatera. Saya memiliki ketertarikan besar terhadap dunia teknologi, senang mempelajari hal baru, dan mengeksplorasi pengalaman baru baik di dalam maupun di luar perkuliahan untuk terus berkembang.\n\n" +
                    "Di luar akademik, saya menyukai warna pink, minuman manis, dan dinosaurus. Saya menikmati musik untuk menyesuaikan suasana hati dan menonton film untuk beristirahat sambil mencari perspektif baru.\n\n" +
                    "Saya senang berinteraksi, berdiskusi, dan bekerja sama dengan orang baru. Sebagai mahasiswa Informatika, saya berkomitmen mengembangkan keterampilan teknis sekaligus komunikasi, kreativitas, dan adaptasi untuk menjadi versi diri yang lebih baik."
        )

        Spacer(modifier = Modifier.height(24.dp))

        AnimatedVisibility(
            visible = showContactInfo,
            enter = fadeIn(animationSpec = tween(500)) + expandVertically(animationSpec = tween(500)),
            exit = fadeOut(animationSpec = tween(500)) + shrinkVertically(animationSpec = tween(500))
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                InfoItem(icon = Icons.Default.Email, text = "Rifka.123140024@student.itera.ac.id")
                InfoItem(icon = Icons.Default.Phone, text = "0822-7788-9944")
                InfoItem(icon = Icons.Default.LocationOn, text = "Jl.Way Kandis No.78")
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = { showContactInfo = !showContactInfo },
            modifier = Modifier.fillMaxWidth().height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = PastelPinkPrimary),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(
                // Teks tombol berubah dinamis berdasarkan status
                text = if (showContactInfo) "Sembunyikan Kontak" else "Connect with Me",
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}