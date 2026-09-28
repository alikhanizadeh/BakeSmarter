package com.example.bakesmarter.CreateProductScreen

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bakesmarter.R
import com.example.bakesmarter.ui.theme.BackgroundDark
import com.example.bakesmarter.ui.theme.BackgroundLight
import com.example.bakesmarter.ui.theme.TextDark
import com.example.bakesmarter.ui.theme.TextLight
import com.example.bakesmarter.ui.theme.Primary



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateRecipeTopBar(
    title: String,
    isDark: Boolean,
    onBack: () -> Unit,
    isSaveEnabled: Boolean,
    onSave: () -> Unit
) {
    val textColor = if (isDark) TextDark else TextLight
    val background = if (isDark) BackgroundDark else BackgroundLight

    CenterAlignedTopAppBar(   // ← تغییر اول
        navigationIcon = {
            IconButton(onClick = onBack) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,  // ← تغییر دوم
                    contentDescription = "Back",
                    tint = textColor
                )
            }
        },
        title = {
            Text(
                text = title,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = textColor
            )
        },
        actions = {
            Button(
                onClick = onSave,
                enabled = isSaveEnabled,
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Primary,
                    contentColor = TextLight,
                    disabledContainerColor = Primary.copy(alpha = 0.4f),  // اختیاری
                    disabledContentColor = TextLight.copy(alpha = 0.6f)   // اختیاری
                ),
                contentPadding = PaddingValues(horizontal = 20.dp, vertical = 8.dp)
            ) {
                Text(
                    text = stringResource(R.string.ingredients_seve),
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(  // ← تغییر سوم
            containerColor = background
        )
    )
}