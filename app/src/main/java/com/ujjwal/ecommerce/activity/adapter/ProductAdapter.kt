package com.ujjwal.ecommerce.activity.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ujjwal.ecommerce.databinding.ItemProductBinding
import com.ujjwal.ecommerce.model.Product

/**
 * Adapter class for displaying a list of products in a RecyclerView.
 *
 * Handles binding product data to item views and provides an option
 * for adding products to the cart through a callback.
 */
class ProductAdapter(
	private val productList: MutableList<Product>, // List of products to display
	private val onAddToCart: (Product) -> Unit     // Lambda for handling add-to-cart action
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

	/**
	 * ViewHolder class for displaying product details in each RecyclerView item.
	 */
	inner class ProductViewHolder(private val binding: ItemProductBinding) :
		RecyclerView.ViewHolder(binding.root) {

		/**
		 * Binds the product data to the item layout.
		 *
		 * @param product The product data to bind to the view.
		 */
		fun bind(product: Product) {
			// Set product title and price
			binding.productName.text = product.title
			binding.productPrice.text = "₹${product.price}"

			// Load product image using Glide
			Glide.with(binding.productImage.context)
				.load(product.image)
				.into(binding.productImage)

			// Set click listener for the "Add to Cart" button
			binding.addToCartBtn.setOnClickListener {
				onAddToCart(product) // Trigger the callback with the selected product
			}
		}
	}

	/**
	 * Inflates the item layout and creates a ViewHolder for the RecyclerView.
	 *
	 * @param parent The parent view group.
	 * @param viewType The type of the view (unused here).
	 * @return A ProductViewHolder instance.
	 */
	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
		val binding = ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
		return ProductViewHolder(binding)
	}

	/**
	 * Binds the data to the ViewHolder at the specified position.
	 *
	 * @param holder The ViewHolder instance.
	 * @param position The position of the item in the list.
	 */
	override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
		holder.bind(productList[position])
	}

	/**
	 * Returns the total number of items in the product list.
	 */
	override fun getItemCount(): Int = productList.size

	/**
	 * Updates the product list with new data and refreshes the RecyclerView.
	 *
	 * @param newProducts The new list of products to display.
	 */
	fun updateProducts(newProducts: List<Product>) {
		productList.clear()  // Clear the current list
		productList.addAll(newProducts) // Add new products
		notifyDataSetChanged() // Notify the RecyclerView to refresh
	}
}