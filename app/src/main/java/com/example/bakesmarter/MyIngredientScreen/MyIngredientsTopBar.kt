package com.example.bakesmarter.MyIngredientScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bakesmarter.CreateProductScreen.AddIngredientButton
import com.example.bakesmarter.CreateProductScreen.CreateRecipeTopBar
import com.example.bakesmarter.CreateProductScreen.IngredientRow
import com.example.bakesmarter.CreateProductScreen.TotalCostBar
import com.example.bakesmarter.R
import com.example.bakesmarter.ui.theme.BackgroundDark
import com.example.bakesmarter.ui.theme.BackgroundLight
import com.example.bakesmarter.ui.theme.BakeSmarterTheme
import com.example.bakesmarter.ui.theme.TextDark
import com.example.bakesmarter.ui.theme.TextLight

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyIngredientsTopBar(
    isDark: Boolean,
    onMenuClick: () -> Unit
) {
    val textColor = if (isDark) TextDark else TextLight
    val background = if (isDark) BackgroundDark else BackgroundLight

    TopAppBar(
        title = {
            Text(
                text = stringResource(R.string.ingredients_title),
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = textColor
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = background
        )
    )
}
