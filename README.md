🧁 BakeSmarter

<p align="center">
  <img src="https://img.shields.io/badge/Platform-Android-brightgreen?style=for-the-badge&logo=android" alt="Android">
  <img src="https://img.shields.io/badge/Language-Kotlin-purple?style=for-the-badge&logo=kotlin" alt="Kotlin">
  <img src="https://img.shields.io/badge/UI-Jetpack%20Compose-blue?style=for-the-badge" alt="Jetpack Compose">
  <img src="https://img.shields.io/badge/Database-Room-orange?style=for-the-badge" alt="Room">
  <img src="https://img.shields.io/badge/Storage-DataStore-lightgrey?style=for-the-badge" alt="DataStore">
</p>

<p align="center">
  <b>A modern Android application for calculating recipe costs and managing bakery products.</b>
</p>

📖 About

BakeSmarter is an Android application designed to help bakers and pastry businesses manage ingredients, recipes, products, production costs, selling prices, and profit margins in one place.

The project is built with Kotlin and Jetpack Compose, with local persistence handled through Room and application preferences handled through DataStore.

The application is being developed with a focus on:

🧾 Ingredient cost management

🥐 Recipe and product management

💰 Production-cost calculation

📊 Selling-price and profit-margin information

🌙 Light / Dark / System theme

🌐 Multi-language support

💾 Local data persistence

🧩 Modular and maintainable Android code

✨ Features

🧂 Ingredient Management

Users can create and manage ingredients used in recipes.

Each ingredient can contain:

Ingredient name

Price

Unit of measurement

Icon

Supported units include:

Kilogram (kg)

Gram (g)

Liter (L)

Milliliter (ml)

Piece

🧁 Recipe & Product Management

Products/recipes can be connected to multiple ingredients and quantities.

A product contains information such as:

Product name

Image

Last updated information

Production cost

Selling price

Profit margin

💰 Cost & Price Calculation

The application provides information related to:

Ingredient costs

Total recipe cost

Selling price

Profit margin

Ingredient cost contribution

The project is currently being migrated from dollar-based sample values to Iranian Rial (ریال).

Currency migration status: In progress

📊 Cost Analysis

The project includes cost-analysis areas for:

Ingredient Costs

Other Costs

Profit Margin

Total Cost

Ingredient Cost Contribution

🌙 Theme Support

The application supports:

Light theme

Dark theme

System theme

Theme preferences are stored locally.

🌐 Localization

The project contains English and Persian (fa) resources.

Localized resources are used for:

Screen titles

Buttons

Labels

Ingredient units

Currency labels

Settings

Product and recipe terminology

🏗️ Architecture

The project follows a layered structure separating UI, data, repositories, and local persistence.

app/
└── src/
└── main/
├── java/
│   └── com.example.bakesmarter/
│       ├── data/
│       │   ├── local/
│       │   │   ├── ingredient/
│       │   │   ├── product/
│       │   │   └── recipe/
│       │   └── repository/
│       │
│       ├── pageProductitem/
│       ├── SettingScreen/
│       ├── LanguageApp/
│       ├── components/
│       └── ui/
│           └── theme/
│
└── res/
├── drawable/
├── values/
└── values-fa/

The main data relationship is:

Product
│
└── RecipeIngredient
│
└── Ingredient

This allows a product to contain multiple ingredients and quantities.

🗄️ Local Database

BakeSmarter uses Room for local database persistence.

Product

id
name
lastUpdated
imageResId
cost
price

Ingredient

id
name
price
unit
iconResId

RecipeIngredient

id
productId
ingredientId
quantity

RecipeIngredient connects a product with an ingredient and stores the required quantity.

💾 Preferences

Application preferences are handled using Android DataStore Preferences.

DataStore is used for application-level settings such as:

Selected language

Selected theme

Other user preferences

Relational application data is stored separately in Room.

🧰 Tech Stack

Technology

Purpose

Kotlin

Main programming language

Jetpack Compose

Declarative UI

Material 3

UI components

Room

Local database

DataStore Preferences

Application preferences

Navigation Compose

Screen navigation

Kotlin Coroutines

Asynchronous operations

KSP

Room code generation

Gradle Kotlin DSL

Build configuration

⚙️ Project Configuration

Namespace:      com.example.bakesmarter
Application ID: com.example.bakesmarter
Compile SDK:    36
Target SDK:     36
Minimum SDK:    24
Java:           11
Kotlin JVM:     11

🚀 Getting Started

1. Clone the repository

git clone https://github.com/YOUR_USERNAME/BakeSmarter.git

2. Open the project

Open the project in Android Studio.

3. Sync Gradle

Allow Android Studio to download and configure the required dependencies.

4. Run

Connect an Android device or start an Android Emulator and run the app module.

📱 Main Application Areas

The project currently contains functionality around:

Welcome / onboarding

Products

Product details

Product cost information

Ingredients

Adding ingredients

Creating recipes/products

Editing recipes

Settings

Language selection

Theme selection

Cost analysis

💰 Currency Handling

BakeSmarter is being standardized around Iranian Rial.

The intended data flow is:

User Input
↓
Numeric Rial Value
↓
Room Database
↓
Repository / Calculation
↓
UI
↓
Formatted Rial Value

For example:

2500000

can be displayed as:

2,500,000 Rial

or in Persian:

۲٬۵۰۰٬۰۰۰ ریال

The important design principle is to keep monetary values numeric in the data layer rather than storing formatted strings such as:

"2,500,000 Rial"

This keeps calculations independent from presentation formatting.

🧹 Code Quality Goals

The project aims to keep responsibilities separated and the codebase maintainable.

Important goals:

Keep UI and data responsibilities separated

Avoid storing formatted currency strings in database entities

Centralize repeated formatting logic

Keep localized text inside Android resources

Avoid unnecessary hardcoded user-facing strings

Keep Room entities focused on data

Keep repositories responsible for data operations

Keep Compose screens focused on UI and interaction

🤝 Contributing

For collaborative development:

Create a branch

git checkout -b feature/your-feature

Make and test your changes

Commit

git add .
git commit -m "Add your feature"

Push

git push origin feature/your-feature

Then open a Pull Request and describe the changes.

📌 Project Status

BakeSmarter is currently under active development.

The core Android application structure, local database models, product/ingredient management, settings, localization, and cost-related UI are implemented.

The current development focus is on standardizing currency handling, validating cost calculations, and improving application consistency before further expansion.

👨‍💻 Author

BakeSmarter

Built with ❤️ using Kotlin + Jetpack Compose.

📄 License

License information has not yet been defined for this project.