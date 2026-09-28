package com.example.bakesmarter.MyIngredientScreen


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import com.example.bakesmarter.ui.theme.Primary
import com.example.bakesmarter.ui.theme.SurfaceDark
import com.example.bakesmarter.ui.theme.SurfaceLight
import com.example.bakesmarter.ui.theme.TextDark
import com.example.bakesmarter.ui.theme.TextLight

@Composable
fun IngredientSearchBar(
    value: String,
    onValueChange: (String) -> Unit,
    isDark: Boolean
) {
    val bg = if (isDark) SurfaceDark else SurfaceLight
    val text = if (isDark) TextDark else TextLight
    val hint = text.copy(alpha = 0.6f)

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier
            .fillMaxWidth()
            .height(52.dp),
        placeholder = {
            Text("Find an ingredient", color = hint)
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null,
                tint = hint
            )
        },
        shape = RoundedCornerShape(12.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = bg,
            unfocusedContainerColor = bg,
            focusedBorderColor = Primary.copy(alpha = 0.5f),
            unfocusedBorderColor = Color.Transparent
        )
    )
}
