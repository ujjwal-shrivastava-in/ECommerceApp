package com.ujjwal.ecommerce.activity

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.ujjwal.ecommerce.activity.adapter.CartAdapter
import com.ujjwal.ecommerce.activity.adapter.ProductAdapter
import com.ujjwal.ecommerce.model.Product
import com.ujjwal.ecommerce.api.ProductViewModel
import com.ujjwal.ecommerce.database.CartItem
import com.ujjwal.ecommerce.database.CartViewModel
import com.ujjwal.ecommerce.databinding.ActivityMainBinding
import com.ujjwal.ecommerce.databinding.CartBottomSheetBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
	private lateinit var binding: ActivityMainBinding
	private val viewModel: ProductViewModel by viewModels()
	private val cartViewModel: CartViewModel by viewModels()
	private lateinit var list: MutableList<Product>
	// onCreate
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = ActivityMainBinding.inflate(layoutInflater)
		setContentView(binding.root)
		list = mutableListOf()
		val productAdapter = ProductAdapter(list){ product: Product ->
			val cartItem = CartItem(
				productId = product.id,
				productName = product.title,
				productImage = product.image,
				productPrice = product.price,
				quantity = 1
			)
			cartViewModel.addOrUpdateCartItem(cartItem)
			openCart()
		}
		binding.recyclerView.layoutManager = LinearLayoutManager(this)
		binding.recyclerView.adapter = productAdapter
		binding.openCart.setOnClickListener {
			openCart()
		}
		viewModel.products.observe(this) { products ->
			productAdapter.updateProducts(products)
		}
		viewModel.loading.observe(this) { isLoading ->
			binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
		}
		viewModel.error.observe(this) { error ->
			error?.let { showError(it) }
		}
		viewModel.fetchProducts()
	}
	// showError
	private fun showError(message: String) {
		Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
	}
	// openCart
	private fun openCart(){
		val bottomSheetBinding = CartBottomSheetBinding.inflate(layoutInflater)
		val bottomSheetDialog = BottomSheetDialog(this)
		bottomSheetDialog.setContentView(bottomSheetBinding.root)
		val cartAdapter = CartAdapter { cartItem, action ->
			when (action) {
				CartAdapter.Action.INCREASE -> {
					cartViewModel.updateQuantity(cartItem.productId, cartItem.quantity + 1)
				}
				CartAdapter.Action.DECREASE -> {
					if (cartItem.quantity > 1) {
						cartViewModel.updateQuantity(cartItem.productId, cartItem.quantity - 1)
						bottomSheetBinding.totalPriceLayout.visibility = View.INVISIBLE
					} else {
						bottomSheetBinding.totalPriceLayout.visibility = View.VISIBLE
						cartViewModel.removeCartItem(cartItem.productId)
					}
				}
				CartAdapter.Action.REMOVE -> {
					cartViewModel.removeCartItem(cartItem.productId)
				}
			}
		}
		bottomSheetBinding.recyclerView.layoutManager = LinearLayoutManager(this)
		bottomSheetBinding.recyclerView.adapter = cartAdapter
		cartViewModel.cartItems.observe(this) { cartItems ->
			cartAdapter.submitList(cartItems)
			bottomSheetBinding.progressBar.visibility = View.INVISIBLE
			bottomSheetBinding.emptyCartMessage.visibility = if (cartItems.isEmpty()) View.VISIBLE else View.GONE
			if(cartItems.isEmpty()) bottomSheetBinding.totalPriceLayout.visibility = View.INVISIBLE
			else bottomSheetBinding.totalPriceLayout.visibility = View.VISIBLE
			var totalPrice = 0.0
			for(item in cartItems){
				val price = item.productPrice * item.quantity
				totalPrice+=price
			}
			bottomSheetBinding.totalPriceOfCartItems.text = "₹$totalPrice"
		}
		bottomSheetBinding.checkoutButton.setOnClickListener {
			Toast.makeText(this, "Checkout functionality coming soon!", Toast.LENGTH_SHORT).show()
		}
		bottomSheetDialog.show()
	}
}