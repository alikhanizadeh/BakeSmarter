package com.example.bakesmarter.LanguageApp

import android.content.Context
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore

// DataStore مخصوص تنظیمات برنامه
val Context.appSettingsDataStore by preferencesDataStore(
    name = "app_settings"
)

object AppPreferences {

    // زبان انتخاب شده توسط کاربر
    val LANGUAGE_KEY = stringPreferencesKey("app_language")

    // تم انتخاب شده توسط کاربر
    val THEME_KEY = stringPreferencesKey("app_theme")

    // نام کاربری پروفایل
    val USERNAME_KEY = stringPreferencesKey("app_username")

    // مسیر فایلِ کپی‌شدهٔ عکس پروفایل در storage داخلی اپ
    // (نه Uri خامِ Photo Picker، چون اون مجوزش موقتیه و با
    // کشته‌شدن پروسه باطل می‌شه)
    val PHOTO_PATH_KEY = stringPreferencesKey("app_photo_path")
}