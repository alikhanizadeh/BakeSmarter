package com.example.bakesmarter.MyIngredientScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
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
import com.example.bakesmarter.ui.theme.SurfaceDark
import com.example.bakesmarter.ui.theme.SurfaceLight
import com.example.bakesmarter.ui.theme.TextDark
import com.example.bakesmarter.ui.theme.TextLight


@Composable
fun IngredientItem(
    icon: Int,
    name: String,
    price: Double,
    unit : String,
    isDark: Boolean,
    onMore: () -> Unit
) {
    val surface = if (isDark) SurfaceDark else SurfaceLight
    val text = if (isDark) TextDark else TextLight
    val secondary = text.copy(alpha = 0.6f)
    val iconBg = if (isDark) BackgroundDark else BackgroundLight

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(surface, RoundedCornerShape(12.dp))
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .background(iconBg, RoundedCornerShape(8.dp)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(icon),
                contentDescription = null,
                tint = text,
                modifier = Modifier.size(28.dp)
            )
        }

        Spacer(Modifier.width(12.dp))

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = name,
                color = text,
                fontWeight = FontWeight.Medium
            )
            Text(
                text = "$price / $unit",
                color = secondary,
                fontSize = 13.sp
            )
        }

        IconButton(onClick = onMore) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = null,
                tint = secondary
            )
        }
    }
}
