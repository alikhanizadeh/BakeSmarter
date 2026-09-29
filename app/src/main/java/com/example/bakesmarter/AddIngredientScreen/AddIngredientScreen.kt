package com.example.bakesmarter.AddIngredientScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.ExposedDropdownMenuDefaults.textFieldColors
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import com.example.bakesmarter.MyIngredientScreen.IngredientViewModel
import com.example.bakesmarter.R
import com.example.bakesmarter.data.local.ingredient.IngredientEntity

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddIngredientScreen(
    isDark: Boolean,
    ingredientViewModel: IngredientViewModel,
    onBack: () -> Unit,
    onSave: () -> Unit
) {
    val defaultUnit = stringResource(R.string.Kilogram_kg)


    var name by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }
    var unit by remember { mutableStateOf(defaultUnit)}

    val background =
        if (isDark) Color(0xFF221E10) else Color(0xFFFAF8F5)
    val cardBackground =
        if (isDark) Color(0xFF2C281C) else Color.White
    val textColor =
        if (isDark) Color(0xFFFAF8F5) else Color(0xFF333333)


    Box(
        modifier = Modifier.fillMaxSize()
            .padding(top = 30.dp)
    ) {

        Scaffold(
            containerColor = background,
            topBar = {
                CenterAlignedTopAppBar(
                    navigationIcon = {
                        IconButton(onClick = onBack) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack, // ← این مهمه
                                contentDescription = "Back",
                                tint = textColor
                            )
                        }
                    },
                    title = {
                        Text(
                            text = stringResource(R.string.ingredients_new),
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = textColor
                        )
                    },
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = background.copy(alpha = 0.95f)
                    )
                )
            },
            bottomBar = {
                val priceValue = price.toDoubleOrNull()
                val isFormValid =
                    name.isNotBlank() &&
                            unit.isNotBlank() &&
                            priceValue != null &&
                            priceValue > 0

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(background.copy(alpha = 0.9f))
                        .padding(16.dp)
                ) {
                    Button(
                        enabled = isFormValid,
                        onClick = {
                            if (!isFormValid) return@Button

                            val ingredient = IngredientEntity(
                                name = name.trim(),
                                price = priceValue!!,
                                unit = unit,
                                iconResId = com.example.bakesmarter.R.drawable.grain
                            )

                            ingredientViewModel.insertIngredient(ingredient)
                            onSave()
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp),
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFE87A5D)
                        )
                    ) {
                        Spacer(Modifier.width(8.dp))
                        Text(
                            text = stringResource(R.string.ingredients_seve),
                            fontSize = 18.sp
                        )
                    }
                }
            }
        ) { padding ->

            Column(
                modifier = Modifier
                    .padding(padding)
                    .padding(horizontal = 16.dp)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {

                Spacer(Modifier.height(8.dp))

                /* 🔹 Name */
                Text(
                    stringResource(R.string.ingredients_name),
                    fontWeight = FontWeight.SemiBold,
                    color = textColor
                )
                Card(
                    colors = CardDefaults.cardColors(containerColor = cardBackground),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    TextField(
                        value = name,
                        onValueChange = { name = it },
                        placeholder = { Text(stringResource(R.string.ingredient_name_input)) },
                        modifier = Modifier.fillMaxWidth(),
                        colors = textFieldColors(
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        )
                    )
                }

                /* 🔹 Unit */
                Text(
                    stringResource(R.string.ingredient_UnitOfMeasurement),
                    fontWeight = FontWeight.SemiBold,
                    color = textColor
                )

                ExposedDropdownMenuBox(
                    expanded = expanded,
                    onExpandedChange = { expanded = !expanded }
                ) {
                    TextField(
                        value = unit,
                        onValueChange = {},
                        readOnly = true,
                        trailingIcon = {
                            ExposedDropdownMenuDefaults.TrailingIcon(expanded)
                        },
                        modifier = Modifier
                            .menuAnchor()
                            .fillMaxWidth(),
                        colors = textFieldColors(
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        ),
                        shape = RoundedCornerShape(16.dp)
                    )

                    ExposedDropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        listOf(
                            stringResource(R.string.Kilogram_kg),
                            stringResource(R.string.Gram_g),
                            stringResource(R.string.Liter_L),
                            stringResource(R.string.Milliliter_ml),
                            stringResource(R.string.Piece),
                        ).forEach {
                            DropdownMenuItem(
                                text = { Text(it) },
                                onClick = {
                                    unit = it
                                    expanded = false
                                }
                            )
                        }
                    }
                }

                /* 🔹 Cost */
                Text(
                    stringResource(R.string.Cost_per_Unit),
                    fontWeight = FontWeight.SemiBold,
                    color = textColor
                )

                Card(
                    colors = CardDefaults.cardColors(containerColor = cardBackground),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(start = 16.dp)
                    ) {
                        Text("$", fontSize = 18.sp, color = textColor.copy(alpha = 0.6f))
                        Spacer(Modifier.width(8.dp))
                        TextField(
                            value = price,
                            onValueChange = { price = it },
                            placeholder = { Text("0") },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            modifier = Modifier.fillMaxWidth(),
                            colors = textFieldColors(
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent
                            )
                        )
                    }
                }
                Spacer(Modifier.height(120.dp)) // برای اینکه محتوا زیر Save نره
            }
        }

    }
}
