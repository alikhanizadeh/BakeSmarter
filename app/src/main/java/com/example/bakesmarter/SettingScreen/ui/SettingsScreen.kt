package com.example.bakesmarter.SettingScreen.ui

import android.app.Activity
import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.bakesmarter.LanguageApp.AppLanguage
import com.example.bakesmarter.LanguageApp.SettingsViewModel
import com.example.bakesmarter.LanguageApp.ThemeMode
import com.example.bakesmarter.R
import com.example.bakesmarter.SettingScreen.*
import com.example.bakesmarter.components.ButtonNavigation.BottomNav
import com.example.bakesmarter.ui.theme.BackgroundDark
import com.example.bakesmarter.ui.theme.BackgroundLight
import kotlin.system.exitProcess

@Composable
fun SettingsScreen(
    isDark: Boolean,
    navController: NavController,
    onBack: () -> Unit,
    settingsViewModel: SettingsViewModel,
    onLanguageChanged: () -> Unit
) {

    val bg = if (isDark) BackgroundDark else BackgroundLight

    val language by settingsViewModel.languageFlow
        .collectAsState(initial = AppLanguage.EN)

    val theme by settingsViewModel.themeFlow
        .collectAsState(initial = ThemeMode.SYSTEM)

    val userName by settingsViewModel.usernameFlow
        .collectAsState(initial = "Marie Claire")

    val photoPath by settingsViewModel.photoPathFlow
        .collectAsState(initial = null)

    val photoUri = remember(photoPath) {
        photoPath?.let { path -> Uri.fromFile(java.io.File(path)) }
    }

    // زبانی که کاربر انتخاب کرده ولی هنوز تأیید نکرده (برای دیالوگ هشدار)
    var pendingLanguage by remember { mutableStateOf<AppLanguage?>(null) }

    val context = LocalContext.current
    val activity = context as? Activity

    Box(modifier = Modifier.fillMaxSize()) {

        Scaffold(
            topBar = { SettingsTopBar(isDark, onBack) },
            containerColor = bg
        ) { padding ->

            Column(
                modifier = Modifier
                    .padding(padding)
                    .verticalScroll(rememberScrollState())
                    .padding(bottom = 100.dp)
            ) {

                ProfileSection(
                    isDark = isDark,
                    name = userName,
                    photoUri = photoUri,
                    onPhotoSelected = { uri -> settingsViewModel.changePhoto(uri) },
                    onNameChanged = { newName -> settingsViewModel.changeUsername(newName) }
                )

                SettingsSection(isDark, stringResource(R.string.section_application)) {

                    SetLanguageRow(
                        isDark = isDark,
                        icon = R.drawable.language_icon,
                        selectedLanguage = language,
                        onLanguageSelected = { selectedLanguage ->
                            // فقط وقتی زبانی متفاوت از زبان فعلی انتخاب شده
                            // دیالوگ هشدار نشون داده می‌شه
                            if (selectedLanguage != language) {
                                pendingLanguage = selectedLanguage
                            }
                        }
                    )

                    SetThemeRow(
                        isDark = isDark,
                        icon = R.drawable.morehoriz,
                        selectedTheme = theme,
                        onThemeSelected = { selectedTheme ->
                            settingsViewModel.changeTheme(selectedTheme)
                        }
                    )
                }
            }
        }

        BottomNav(
            isDark = isDark,
            navController = navController,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 20.dp)
        )
    }

    // دیالوگ تأیید: چون تغییر زبان ساختار اپ رو عوض می‌کنه، کاربر باید
    // بدونه که باید اپ رو ببنده و دوباره باز کنه
    val languageToConfirm = pendingLanguage
    if (languageToConfirm != null) {
        AlertDialog(
            onDismissRequest = { pendingLanguage = null },
            title = { Text("Change language?") },
            text = {
                Text(stringResource(R.string.change_language_message))
            },
            confirmButton = {
                TextButton(onClick = {
                    // onLanguageChanged فقط بعد از تمام شدن واقعی نوشتن
                    // روی DataStore صدا زده می‌شه (رفع باگ قبلی)،
                    // و بعدش کل اپ به‌طور کامل بسته می‌شه تا دفعه‌ی
                    // بعد که کاربر بازش می‌کنه، از صفر با زبان جدید بالا بیاد
                    settingsViewModel.changeLanguage(languageToConfirm) {
                        onLanguageChanged()
                        activity?.finishAffinity()
                        exitProcess(0)
                    }
                    pendingLanguage = null
                }) {
                    Text(stringResource(R.string.action_change))
                }
            },
            dismissButton = {
                TextButton(onClick = { pendingLanguage = null }) {
                    Text(stringResource(R.string.action_cancel))
                }
            }
        )
    }
}