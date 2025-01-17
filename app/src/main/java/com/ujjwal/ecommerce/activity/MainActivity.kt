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

/**
 * MainActivity class that serves as the entry point of the eCommerce application.
 * Handles the display of products and the cart's bottom sheet functionality.
 */
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

	// ViewBinding for the activity layout
	private lateinit var binding: ActivityMainBinding

	// ViewModels for managing product data and cart data
	private val viewModel: ProductViewModel by viewModels()
	private val cartViewModel: CartViewModel by viewModels()

	// Mutable list to store products fetched from the API
	private lateinit var list: MutableList<Product>

	/**
	 * onCreate method is called when the activity is created.
	 * Initializes the UI elements and observes the data from the ViewModels.
	 */
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = ActivityMainBinding.inflate(layoutInflater)
		setContentView(binding.root)

		// Initialize the product list and set up the ProductAdapter
		list = mutableListOf()
		val productAdapter = ProductAdapter(list) { product: Product ->
			// When a product is clicked, add it to the cart
			val cartItem = CartItem(
				productId = product.id,
				productName = product.title,
				productImage = product.image,
				productPrice = product.price,
				quantity = 1
			)
			cartViewModel.addOrUpdateCartItem(cartItem)
			openCart() // Open the cart after adding the item
		}

		// Set the RecyclerView with the ProductAdapter
		binding.recyclerView.layoutManager = LinearLayoutManager(this)
		binding.recyclerView.adapter = productAdapter

		// Handle the click event for opening the cart
		binding.openCart.setOnClickListener {
			openCart()
		}

		// Observe products data from ViewModel and update the adapter
		viewModel.products.observe(this) { products ->
			productAdapter.updateProducts(products)
		}

		// Observe loading state and display the progress bar accordingly
		viewModel.loading.observe(this) { isLoading ->
			binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
		}

		// Observe error state and show error message in case of failure
		viewModel.error.observe(this) { error ->
			error?.let { showError(it) }
		}

		// Fetch products from the API
		viewModel.fetchProducts()
	}

	/**
	 * Displays an error message in a Toast.
	 *
	 * @param message The error message to display.
	 */
	private fun showError(message: String) {
		Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
	}

	/**
	 * Opens the Cart in a BottomSheetDialog.
	 * Displays cart items and provides options to increase, decrease, or remove items.
	 */
	private fun openCart() {
		val bottomSheetBinding = CartBottomSheetBinding.inflate(layoutInflater)
		val bottomSheetDialog = BottomSheetDialog(this)
		bottomSheetDialog.setContentView(bottomSheetBinding.root)

		// Set up the CartAdapter for the RecyclerView inside the BottomSheet
		val cartAdapter = CartAdapter { cartItem, action ->
			// Handle actions (increase, decrease, remove)
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

		// Set the RecyclerView layout and adapter for the cart items
		bottomSheetBinding.recyclerView.layoutManager = LinearLayoutManager(this)
		bottomSheetBinding.recyclerView.adapter = cartAdapter

		// Observe cart items and update UI
		cartViewModel.cartItems.observe(this) { cartItems ->
			cartAdapter.submitList(cartItems)
			bottomSheetBinding.progressBar.visibility = View.INVISIBLE
			bottomSheetBinding.emptyCartMessage.visibility = if (cartItems.isEmpty()) View.VISIBLE else View.GONE
			if (cartItems.isEmpty()) bottomSheetBinding.totalPriceLayout.visibility = View.INVISIBLE
			else bottomSheetBinding.totalPriceLayout.visibility = View.VISIBLE

			// Calculate total price of cart items
			var totalPrice = 0.0
			for (item in cartItems) {
				totalPrice += item.productPrice * item.quantity
			}
			bottomSheetBinding.totalPriceOfCartItems.text = "₹$totalPrice"
		}

		// Handle checkout button click (placeholder functionality)
		bottomSheetBinding.checkoutButton.setOnClickListener {
			Toast.makeText(this, "Checkout functionality coming soon!", Toast.LENGTH_SHORT).show()
		}

		// Show the bottom sheet dialog
		bottomSheetDialog.show()
	}
}