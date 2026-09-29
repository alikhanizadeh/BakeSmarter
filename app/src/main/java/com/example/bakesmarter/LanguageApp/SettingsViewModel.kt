package com.example.bakesmarter.LanguageApp

import android.content.Context
import android.net.Uri
import androidx.datastore.preferences.core.edit
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.io.File

class SettingsViewModel(
    private val context: Context
) : ViewModel() {

    private val dataStore = context.appSettingsDataStore

    val languageFlow: Flow<AppLanguage> =
        dataStore.data.map { preferences ->
            val storedCode = preferences[AppPreferences.LANGUAGE_KEY]
            AppLanguage.entries.firstOrNull { language ->
                language.code.equals(storedCode, ignoreCase = true)
            } ?: AppLanguage.EN
        }

    val themeFlow: Flow<ThemeMode> =
        dataStore.data.map { preferences ->
            when (preferences[AppPreferences.THEME_KEY]) {
                "light" -> ThemeMode.LIGHT
                "dark" -> ThemeMode.DARK
                else -> ThemeMode.SYSTEM
            }
        }

    // نام کاربری ذخیره‌شده؛ اگه هنوز چیزی ذخیره نشده یه مقدار پیش‌فرض برمی‌گرده
    val usernameFlow: Flow<String> =
        dataStore.data.map { preferences ->
            preferences[AppPreferences.USERNAME_KEY] ?: "Marie Claire"
        }

    // مسیر فایل عکس پروفایل (در storage داخلی اپ)؛ اگه عکسی انتخاب نشده null‌ه
    val photoPathFlow: Flow<String?> =
        dataStore.data.map { preferences ->
            preferences[AppPreferences.PHOTO_PATH_KEY]
        }

    /**
     * نوشتن روی DataStore عملیات async است. onComplete فقط بعد از
     * تمام شدن واقعی نوشتن صدا زده می‌شود.
     */
    fun changeLanguage(language: AppLanguage, onComplete: () -> Unit = {}) {
        viewModelScope.launch {
            dataStore.edit { preferences ->
                preferences[AppPreferences.LANGUAGE_KEY] = language.code
            }
            onComplete()
        }
    }

    fun changeTheme(theme: ThemeMode) {
        viewModelScope.launch {
            dataStore.edit { preferences ->
                preferences[AppPreferences.THEME_KEY] = theme.code
            }
        }
    }

    fun changeUsername(name: String) {
        val trimmed = name.trim()
        if (trimmed.isBlank()) return

        viewModelScope.launch {
            dataStore.edit { preferences ->
                preferences[AppPreferences.USERNAME_KEY] = trimmed
            }
        }
    }

    /**
     * عکس انتخاب‌شده از Photo Picker رو داخل storage خودِ اپ کپی می‌کنه
     * (چون Uri خامِ Picker فقط یه مجوز خواندن موقت داره که با بستن
     * پروسه باطل می‌شه) و مسیر همون کپی رو روی DataStore ذخیره می‌کنه.
     */
    fun changePhoto(sourceUri: Uri) {
        viewModelScope.launch(Dispatchers.IO) {
            // ایجاد نام منحصربه‌فرد برای فایل بر اساس زمان
            val fileName = "profile_photo_${System.currentTimeMillis()}.jpg"
            val destinationFile = File(context.filesDir, fileName)

            try {
                // ۱. پاک کردن عکس‌های قبلی پروفایل برای جلوگیری از پر شدن حافظه
                context.filesDir.listFiles()?.forEach { file ->
                    if (file.name.startsWith("profile_photo_")) {
                        file.delete()
                    }
                }

                // ۲. کپی عکس جدید
                context.contentResolver.openInputStream(sourceUri)?.use { input ->
                    destinationFile.outputStream().use { output ->
                        input.copyTo(output)
                    }
                }
            } catch (e: Exception) {
                android.util.Log.e("SettingsViewModel", "Failed to copy photo", e)
                return@launch
            }

            // ۳. ذخیره مسیر جدید در DataStore (چون مسیر تغییر کرده Flow انتشار می‌یابد)
            dataStore.edit { preferences ->
                preferences[AppPreferences.PHOTO_PATH_KEY] = destinationFile.absolutePath
            }
        }
    }
}