package com.ujjwal.ecommerce.application

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
// ECommerceApp: Application class for setting up Hilt dependency injection
// Reference for Hilt setup: https://developer.android.com/training/dependency-injection/hilt-android
class ECommerceApp : Application()