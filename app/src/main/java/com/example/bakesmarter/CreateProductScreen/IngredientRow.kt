package com.example.bakesmarter.CreateProductScreen

import androidx.compose.foundation.background
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bakesmarter.ui.theme.TextSecondaryDark
import com.example.bakesmarter.ui.theme.TextSecondaryLight
import com.example.bakesmarter.ui.theme.TextDark
import com.example.bakesmarter.ui.theme.TextLight
import com.example.bakesmarter.ui.theme.Primary
import com.example.bakesmarter.ui.theme.SurfaceDark
import com.example.bakesmarter.ui.theme.SurfaceLight

@Composable
fun IngredientRow(
    iconRes: Int,
    name: String,
    amount: String,
    unit: String,
    onAmountChange: (String) -> Unit,
    onDelete: () -> Unit,
    isDark: Boolean
) {
    val background = if (isDark) SurfaceDark else SurfaceLight
    val text = if (isDark) TextDark else TextLight
    val secondary = if (isDark) TextSecondaryDark else TextSecondaryLight

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(background, RoundedCornerShape(12.dp))
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Box(
            modifier = Modifier
                .size(48.dp)
                .background(
                    Primary.copy(alpha = 0.2f),
                    RoundedCornerShape(8.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(iconRes),
                contentDescription = null,
                tint = Primary,
                modifier = Modifier.size(24.dp)
            )
        }

        Spacer(Modifier.width(12.dp))

        Text(
            text = name,
            modifier = Modifier.weight(1f),
            fontWeight = FontWeight.Medium,
            color = text
        )

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {

            OutlinedTextField(
                value = amount,
                onValueChange = onAmountChange,
                singleLine = true,
                modifier = Modifier.width(72.dp),
                textStyle = LocalTextStyle.current.copy(
                    textAlign = TextAlign.Center,
                    fontSize = 14.sp
                ),
                shape = RoundedCornerShape(8.dp)
            )

            Spacer(Modifier.width(6.dp))

            Text(
                text = unit,
                fontSize = 14.sp,
                color = secondary
            )
        }

        IconButton(onClick = onDelete) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = null,
                tint = secondary
            )
        }
    }
}