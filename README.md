# **E-Commerce App**

This project demonstrates a fully functional e-commerce application that meets the task requirements. The app provides features such as product listing, shopping cart management, and local storage using modern Android development tools.

---

## **Task Overview**

The task involved creating an e-commerce app with the following requirements:



### **APK**
app-debug.apk is directly available to download and test the app



### **1. Product Listing**
- Fetch products from an API and display them in a user-friendly interface.
- Show details like product name, image, price, and an "Add to Cart" button.


### **2. Cart Management**
- Add products to the cart and update their quantities.
- View all items in the cart with total price calculations.
- Remove individual items or clear the entire cart.


### **3. Room Database Integration**
- Store cart items locally using Room Database to ensure offline functionality.


### **4. MVVM Architecture**
- Use the MVVM pattern for better separation of concerns and code scalability.


### **5. Additional Requirements**
- Use modern tools like Hilt for dependency injection.
- Implement responsive and intuitive UI designs.


## **Dependencies Used**

Here are the main libraries and tools used in this project:

---

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