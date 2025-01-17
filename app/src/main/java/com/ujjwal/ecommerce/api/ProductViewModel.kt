package com.ujjwal.ecommerce.api

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ujjwal.ecommerce.model.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
// ViewModel responsible for fetching and holding product data, managing loading and error states.
// Reference: https://developer.android.com/topic/libraries/architecture/viewmodel
class ProductViewModel @Inject constructor(private val repository: ProductRepository) : ViewModel() {

	private val _products = MutableLiveData<List<Product>>()
	val products: LiveData<List<Product>> = _products  // Exposes products data to the UI
	// LiveData documentation: https://developer.android.com/topic/libraries/architecture/livedata

	private val _loading = MutableLiveData<Boolean>()
	val loading: LiveData<Boolean> = _loading  // Exposes loading state to the UI

	private val _error = MutableLiveData<String?>()
	val error: LiveData<String?> = _error  // Exposes error state to the UI

	// Fetches products from the repository and handles success or failure
	// Reference for coroutines in ViewModel: https://developer.android.com/topic/libraries/architecture/coroutines
	fun fetchProducts() {
		_loading.value = true  // Show loading indicator
		viewModelScope.launch {
			try {
				val productList = repository.fetchProducts()
				_products.value = productList  // Set the products data
				_error.value = null  // Clear any previous errors
			} catch (e: Exception) {
				_error.value = e.message  // Set error message
			} finally {
				_loading.value = false  // Hide loading indicator
			}
		}
	}
}