package com.rifka.myprofileapp.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rifka.myprofileapp.R
import com.rifka.myprofileapp.ui.theme.*

@Composable
fun LabeledTextField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    isDarkMode: Boolean,
    modifier: Modifier = Modifier,
    singleLine: Boolean = true
) {
    val textColor by animateColorAsState(targetValue = if (isDarkMode) Color.White else Color.Black, animationSpec = tween(500), label = "textAnim")
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label, color = textColor) },
        modifier = modifier.fillMaxWidth(),
        singleLine = singleLine,
        shape = RoundedCornerShape(12.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = textColor,
            unfocusedTextColor = textColor
        )
    )
}

@Composable
fun ProfileHeader(name: String, isDarkMode: Boolean) {
    val textColor by animateColorAsState(targetValue = if (isDarkMode) Color.White else PastelPinkText, animationSpec = tween(500), label = "headerAnim")
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .size(130.dp)
                .clip(CircleShape)
                .background(PastelPinkPrimary),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.profile_picture),
                contentDescription = "Foto Profil",
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(120.dp).clip(CircleShape)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = name, fontSize = 24.sp, fontWeight = FontWeight.ExtraBold, color = textColor)
    }
}

@Composable
fun ProfileBioCard(bio: String, isDarkMode: Boolean) {
    val cardColor by animateColorAsState(targetValue = if (isDarkMode) Color(0xFF2C2C2C) else PastelPinkCard, animationSpec = tween(500), label = "cardAnim")
    val textColor by animateColorAsState(targetValue = if (isDarkMode) Color.LightGray else PastelPinkText, animationSpec = tween(500), label = "bioAnim")

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = cardColor),
        shape = RoundedCornerShape(16.dp)
    ) {
        Text(
            text = bio,
            modifier = Modifier.padding(16.dp),
            color = textColor,
            textAlign = TextAlign.Justify,
            fontSize = 13.sp,
            lineHeight = 20.sp
        )
    }
}

@Composable
fun InfoItem(icon: ImageVector, text: String, isDarkMode: Boolean) {
    val boxColor by animateColorAsState(targetValue = if (isDarkMode) Color(0xFF2C2C2C) else Color.White, animationSpec = tween(500), label = "boxAnim")
    val textColor by animateColorAsState(targetValue = if (isDarkMode) Color.White else PastelPinkText, animationSpec = tween(500), label = "infoAnim")

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(boxColor, shape = RoundedCornerShape(12.dp))
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(imageVector = icon, contentDescription = null, tint = PastelPinkPrimary, modifier = Modifier.size(24.dp))
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = text, color = textColor, fontSize = 14.sp, fontWeight = FontWeight.Medium)
    }
}