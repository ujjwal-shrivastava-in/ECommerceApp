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
// ProductViewModel
class ProductViewModel @Inject constructor(private val repository: ProductRepository) : ViewModel() {
	private val _products = MutableLiveData<List<Product>>()
	val products: LiveData<List<Product>> = _products

	private val _loading = MutableLiveData<Boolean>()
	val loading: LiveData<Boolean> = _loading

	private val _error = MutableLiveData<String?>()
	val error: LiveData<String?> = _error
	// fetchProducts
	fun fetchProducts() {
		_loading.value = true
		viewModelScope.launch {
			try {
				val productList = repository.fetchProducts()
				_products.value = productList
				_error.value = null
			} catch (e: Exception) {
				_error.value = e.message
			} finally {
				_loading.value = false
			}
		}
	}
}
