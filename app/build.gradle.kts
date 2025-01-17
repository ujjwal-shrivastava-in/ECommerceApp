plugins {
	alias(libs.plugins.android.application)
	alias(libs.plugins.jetbrains.kotlin.android)
	id("com.google.dagger.hilt.android")
	kotlin("kapt")
}

android {
	namespace = "com.ujjwal.ecommerce"
	compileSdk = 34

	defaultConfig {
		applicationId = "com.ujjwal.ecommerce"
		minSdk = 24
		targetSdk = 34
		versionCode = 1
		versionName = "1.0"
		testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
	}
	buildFeatures {
		viewBinding = true
	}
	buildTypes {
		release {
			isMinifyEnabled = false
			proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
		}
	}
	compileOptions {
		sourceCompatibility = JavaVersion.VERSION_1_8
		targetCompatibility = JavaVersion.VERSION_1_8
	}
	kotlinOptions {
		jvmTarget = "1.8"
	}
}

dependencies {
	implementation(libs.androidx.core.ktx)
	implementation(libs.androidx.appcompat)
	implementation(libs.material)
	implementation(libs.androidx.activity)
	implementation(libs.androidx.constraintlayout)
	testImplementation(libs.junit)
	androidTestImplementation(libs.androidx.junit)
	androidTestImplementation(libs.androidx.espresso.core)
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
}