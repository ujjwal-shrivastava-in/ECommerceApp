# **E-Commerce App**

This project demonstrates a fully functional e-commerce application developed using modern Android development tools. It includes features like product listing, shopping cart management, and local storage.

---

## **Table of Contents**

1. [Task Overview](#task-overview)
2. [Features](#features)
3. [APK](#apk)
4. [Dependencies Used](#dependencies-used)
5. [Project Structure](#project-structure)
6. [Screenshots](#screenshots)
7. [Installation and Build](#installation-and-build)

---

## **Task Overview**

The app meets the following task requirements:

1. **Product Listing**:
    - Fetch and display products from an API.
    - Include product details: name, image, price, and an "Add to Cart" button.

2. **Cart Management**:
    - Add items to the cart, update quantities, view total prices, and remove items.

3. **Room Database Integration**:
    - Ensure offline functionality by storing cart items locally using Room Database.

4. **MVVM Architecture**:
    - Enhance scalability and maintainability with the MVVM pattern.

5. **Modern Android Tools**:
    - Hilt for dependency injection.
    - Intuitive and responsive UI.

---

## **Features**

- 📦 **Product Listing**: Browse products with their details.
- 🛒 **Shopping Cart**: Manage your cart with ease—add, update, or remove items.
- 💾 **Offline Support**: Cart items are stored locally.
- 📐 **Scalable Architecture**: Clean and scalable MVVM pattern.
- 🛠 **Modern Tools**: Retrofit, Room, Hilt, and Coroutines for efficient development.

---

## **APK**

You can directly download the [app-debug.apk](#) to test the app.

---

## **Dependencies Used**

Below are the primary libraries used in the project:

```gradle
// Glide
implementation("com.github.bumptech.glide:glide:4.16.0")

// Retrofit
implementation("com.squareup.retrofit2:retrofit:2.9.0")
implementation("com.squareup.retrofit2:converter-gson:2.9.0")

// Room
implementation("androidx.room:room-runtime:2.6.1")
implementation("androidx.room:room-ktx:2.6.1")
kapt("androidx.room:room-compiler:2.6.1")

// Hilt
implementation("com.google.dagger:hilt-android:2.48")
kapt("com.google.dagger:hilt-android-compiler:2.48")

// Coroutines
implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")

// LiveData and ViewModel
implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.8.7")
implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.7")