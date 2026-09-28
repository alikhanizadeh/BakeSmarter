//package com.example.bakesmarter.AddIngredientScreen
//
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.material3.Button
//import androidx.compose.material3.OutlinedTextField
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.unit.dp
//import com.example.bakesmarter.MyIngredientScreen.IngredientViewModel
//import com.example.bakesmarter.data.local.ingredient.IngredientEntity
//
//@Composable
//fun AddIngredientScreen(
//    isDark: Boolean,
//    ingredientViewModel: IngredientViewModel,
//    onBack: () -> Unit,
//    onSave: () -> Unit
//) {
//    var name by remember { mutableStateOf("") }
//    var price by remember { mutableStateOf("") }
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(20.dp),
//        verticalArrangement = Arrangement.Top
//    ) {
//
//        Text(
//            text = "Add Ingredient"
//        )
//
//        Spacer(
//            modifier = Modifier.height(20.dp)
//        )
//
//        OutlinedTextField(
//            value = name,
//            onValueChange = {
//                name = it
//            },
//            modifier = Modifier.fillMaxWidth(),
//            label = {
//                Text("Ingredient Name")
//            },
//            singleLine = true
//        )
//
//        Spacer(
//            modifier = Modifier.height(12.dp)
//        )
//
//        OutlinedTextField(
//            value = price,
//            onValueChange = {
//                price = it
//            },
//            modifier = Modifier.fillMaxWidth(),
//            label = {
//                Text("Price")
//            },
//            singleLine = true
//        )
//
//        Spacer(
//            modifier = Modifier.height(24.dp)
//        )
//
//        Button(
//            onClick = {
//
//                if (name.isBlank() || price.isBlank()) {
//                    return@Button
//                }
//
//                val ingredient = IngredientEntity(
//                    name = name,
//                    price = price,
//                    iconResId = com.example.bakesmarter.R.drawable.grain
//                )
//
//                ingredientViewModel.insertIngredient(
//                    ingredient
//                )
//
//                onSave()
//            },
//            modifier = Modifier.fillMaxWidth()
//        ) {
//            Text("Save")
//        }
//
//        Spacer(
//            modifier = Modifier.height(12.dp)
//        )
//
//        Button(
//            onClick = onBack,
//            modifier = Modifier.fillMaxWidth()
//        ) {
//            Text("Cancel")
//        }
//    }
//}