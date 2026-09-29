package com.example.bakesmarter.CreateProductScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bakesmarter.CreateProductScreen.coponents.BottomSheetContent
import com.example.bakesmarter.MyIngredientScreen.IngredientViewModel
import com.example.bakesmarter.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateRecipeScreen(
    isDark: Boolean,
    ingredientViewModel: IngredientViewModel,
    createRecipeViewModel: CreateRecipeViewModel,
    productId: Long? = null,
    onBack: () -> Unit,
    onSave: () -> Unit
) {

    // تمام مواد اولیه‌ای که در Room ذخیره شده‌اند
    val ingredients by ingredientViewModel.ingredients.collectAsState()

    var sellingPrice by remember { mutableStateOf("") }
    var productName by remember { mutableStateOf("") }

    // عکس محصول: در حالت ویرایش همون عکس قبلی محصول حفظ می‌شود،
    // در حالت ساخت جدید مقدار پیش‌فرض است
    var imageResId by remember {
        mutableStateOf(com.example.bakesmarter.R.drawable.imagewelcome)
    }

    var showSheet by remember { mutableStateOf(false) }

    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )

    // مواد اولیه انتخاب شده برای این محصول
    val selectedIngredients = remember {
        mutableStateListOf<RecipeIngredientUiModel>()
    }

    // ID مواد انتخاب شده در BottomSheet
    val selectedIds = remember {
        mutableStateListOf<Long>()
    }

    // اگر در حالت ویرایش هستیم، یک‌بار دیتای محصول موجود را لود و فرم را پر می‌کنیم
    LaunchedEffect(productId) {
        if (productId != null) {
            createRecipeViewModel.loadProductForEdit(productId) { product, loadedIngredients ->
                productName = product.name
                sellingPrice = product.price.toString()
                imageResId = product.imageResId

                selectedIngredients.clear()
                selectedIngredients.addAll(loadedIngredients)

                selectedIds.clear()
                selectedIds.addAll(loadedIngredients.map { it.ingredient.id })
            }
        }
    }

    // محاسبه هزینه کل تولید
    val totalCost = selectedIngredients.sumOf { it.totalCost }

    Box(
        modifier = if (showSheet) {
            Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.25f))
                .blur(18.dp)
                .padding(top = 30.dp)
        } else {
            Modifier.fillMaxSize()
                .padding(top = 30.dp)
        }
    ) {


        val price = sellingPrice.toDoubleOrNull()

        val isSaveEnabled = productName.isNotBlank() &&
                selectedIngredients.isNotEmpty() &&
                price != null &&
                price > 0

        Scaffold(
            topBar = {
                CreateRecipeTopBar(
                    title = if (productId != null) stringResource(R.string.EditRecipe) else stringResource(R.string.NewRecipe),
                    isDark = isDark,
                    onBack = onBack,
                    isSaveEnabled = isSaveEnabled,
                    onSave = {

                        createRecipeViewModel.saveProduct(
                            productId = productId,
                            name = productName,
                            cost = totalCost,
                            price = price!!,
                            imageResId = imageResId,
                            ingredients = selectedIngredients,
                            onSuccess = onSave
                        )
                    }
                )
            },
            bottomBar = {
                TotalCostBar(
                    total = totalCost.toString(),
                    isDark = isDark
                )
            }
        ) { padding ->

            LazyColumn(
                modifier = Modifier
                    .padding(padding)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                item {
                    OutlinedTextField(
                        value = productName,
                        onValueChange = { productName = it },
                        placeholder = { Text(stringResource(R.string.CreateRecipe_name)) },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        singleLine = true
                    )
                }

                item {
                    OutlinedTextField(
                        value = sellingPrice,
                        onValueChange = { sellingPrice = it },
                        placeholder = { Text(stringResource(R.string.CreateRecipe_Price)) },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        singleLine = true
                    )
                }

                item {
                    Text(
                        text = stringResource(R.string.ingredients_title),
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                }

                items(
                    count = selectedIngredients.size,
                    key = { selectedIngredients[it].ingredient.id }
                ) { index ->

                    val recipeIngredient = selectedIngredients[index]

                    IngredientRow(
                        iconRes = recipeIngredient.ingredient.iconResId,
                        name = recipeIngredient.ingredient.name,
                        amount = recipeIngredient.quantity.toString(),
                        unit = recipeIngredient.ingredient.unit,

                        onAmountChange = { newAmount ->
                            val quantity = newAmount.toDoubleOrNull() ?: 0.0
                            selectedIngredients[index] =
                                recipeIngredient.copy(quantity = quantity)
                        },

                        onDelete = {
                            selectedIds.remove(recipeIngredient.ingredient.id)
                            selectedIngredients.removeAt(index)
                        },

                        isDark = isDark
                    )

                    Text(
                        text = "Cost: $%.2f".format(recipeIngredient.totalCost),
                        modifier = Modifier.padding(start = 60.dp, top = 2.dp),
                        fontSize = 12.sp
                    )
                }

                item {
                    AddIngredientButton(onClick = { showSheet = true })
                }
            }
        }

        if (showSheet) {
            ModalBottomSheet(
                onDismissRequest = { showSheet = false },
                sheetState = sheetState,
                modifier = Modifier
                    .fillMaxHeight(0.7f)
                    .align(Alignment.BottomCenter)
            ) {
                BottomSheetContent(
                    items = ingredients,
                    selectedItems = selectedIds,
                    onConfirm = {
                        selectedIds.forEach { ingredientId ->
                            val ingredient = ingredients.find { it.id == ingredientId }

                            if (
                                ingredient != null &&
                                selectedIngredients.none { it.ingredient.id == ingredient.id }
                            ) {
                                selectedIngredients.add(
                                    RecipeIngredientUiModel(
                                        ingredient = ingredient,
                                        quantity = 1.0
                                    )
                                )
                            }
                        }
                        showSheet = false
                    }
                )
            }
        }
    }
}