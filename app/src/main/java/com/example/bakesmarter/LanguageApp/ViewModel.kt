//package com.example.bakesmarter.LanguageApp
//
//import android.content.Context
//import android.content.res.Configuration
//import androidx.datastore.preferences.core.edit
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import kotlinx.coroutines.flow.Flow
//import kotlinx.coroutines.flow.map
//import kotlinx.coroutines.launch
//import java.util.Locale
//
//class LanguageViewModel(private val context: Context) : ViewModel() {
//
//    private val dataStore = context.languageDataStore
//
//    val languageFlow: Flow<AppLanguage> = dataStore.data
//        .map { prefs  ->
//            when (prefs[LanguagePreferences.LANGUAGE_KEY]) {
//                "fa" -> AppLanguage.FA
//                else -> AppLanguage.EN
//            }
//        }
//
//    fun changeLanguage(language: String) {
//        viewModelScope.launch {
//            dataStore.edit {
//                it[LanguagePreferences.LANGUAGE_KEY] = language
//            }
//        }
//    }
//}
//
//
//fun Context.updateLocale(languageCode: String): Context {
//    val locale = Locale(languageCode)
//    Locale.setDefault(locale)
//
//    val config = Configuration(resources.configuration)
//    config.setLocale(locale)
//    config.setLayoutDirection(locale)
//
//    return createConfigurationContext(config)
//}
//
