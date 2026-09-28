package com.example.bakesmarter.chip

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bakesmarter.R
import com.example.bakesmarter.ui.theme.ChipDark
import com.example.bakesmarter.ui.theme.ChipLight
import com.example.bakesmarter.ui.theme.Positive

// داخل کارت ها یا ایتم ها 3 تا بخش قرار دارد که اطلاعات خورجی ما رو از محاسبه غذا به ما میدهد

@Composable
fun InfoChip(
    icon: Int,
    text: String,
    isDark: Boolean
) {
    val chipBg = if (isDark) ChipDark else ChipLight

    Surface(
        shape = RoundedCornerShape(50),
        color = chipBg
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(painter = painterResource(icon), contentDescription = null, modifier = Modifier.size(16.dp) )
            Spacer(Modifier.width(6.dp))
            Text(text, fontSize = 12.sp, fontWeight = FontWeight.Medium)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MarginChip(text: String) {
    Surface(
        shape = RoundedCornerShape(50),
        color = Positive.copy(alpha = 0.15f)
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(R.drawable.trending_up),
                contentDescription = null,
                modifier = Modifier.size(16.dp)
            )
            Spacer(Modifier.width(6.dp))
            Text(
                text,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = Positive
            )
        }
    }
}
