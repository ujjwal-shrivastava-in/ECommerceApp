# **E-Commerce App**

This project demonstrates a fully functional e-commerce application developed using modern Android development tools. It includes features like product listing, shopping cart management, and local storage.

---

## **Table of Contents**

1. [Task Overview](#task-overview)
2. [Features](#features)
3. [APK](#apk)
4. [Project Structure](#project-structure)
5. [Dependencies Used](#dependencies-used)
6. [Challenges Faced](#challenges-faced)

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

## **Project Structure**
```
ECommerceApp/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/ujjwal/ecommerce/
│   │   │   │   ├── activity/
│   │   │   │   │   ├── MainActivity.kt            # Main entry point of the app
│   │   │   │   │   ├── adapter/                   # RecyclerView adapters
│   │   │   │   │   │   ├── CartAdapter.kt         # Adapter for displaying cart items
│   │   │   │   │   │   ├── ProductAdapter.kt      # Adapter for displaying product listings
│   │   │   │   ├── api/
│   │   │   │   │   ├── NetworkModule.kt           # Hilt module for API-related dependencies
│   │   │   │   │   ├── ProductApiService.kt       # Retrofit API service interface
│   │   │   │   │   ├── ProductRepository.kt       # Repository for product-related data
│   │   │   │   │   ├── ProductViewModel.kt        # ViewModel for product-related operations
│   │   │   │   ├── application/
│   │   │   │   │   ├── ECommerceApp.kt            # Application class for global setup
│   │   │   │   ├── database/
│   │   │   │   │   ├── CardDao.kt                 # DAO interface for cart operations
│   │   │   │   │   ├── CartDatabase.kt            # Room database instance
│   │   │   │   │   ├── CartItem.kt                # Data class for cart item
│   │   │   │   │   ├── CartRepository.kt          # Repository for cart-related data
│   │   │   │   │   ├── CartViewModel.kt           # ViewModel for cart-related operations
│   │   │   │   │   ├── DatabaseModule.kt          # Hilt module for database dependencies
│   │   │   │   ├── model/
│   │   │   │   │   ├── Product.kt                 # Data class for product details
│   │   ├── res/
│   │   │   ├── layout/                            # XML layout files
│   │   │   ├── drawable/                          # Icons and images
│   │   │   ├── values/                            # Strings, colors, themes
│   ├── build.gradle                               # Module-level Gradle build script
├── gradle/                                        # Gradle wrapper files
├── README.md                                      # Project documentation
├── build.gradle                                   # Top-level Gradle build script
├── settings.gradle                                # Gradle settings file
```

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
```

## **Challenges Faced**

During the development of this e-commerce application, I encountered the following challenges:

### **1. Understanding Dagger Hilt**
- **Challenge:** As a beginner in dependency injection, understanding the lifecycle of components and how Dagger Hilt works was quite difficult. Setting up modules, injecting dependencies, and managing scopes was initially confusing.
- **Solution:** I referred to the [official Dagger Hilt documentation](https://developer.android.com/training/dependency-injection/hilt-android) and watched tutorials to understand the concepts. Implementing a small sample project with Hilt helped me grasp the basics before integrating it into the app.

---

By overcoming these challenges, I gained valuable experience in implementing dependency injection and improved my understanding of Android's modern development practices.