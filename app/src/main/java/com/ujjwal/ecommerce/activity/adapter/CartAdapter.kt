package com.ujjwal.ecommerce.activity.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ujjwal.ecommerce.database.CartItem
import com.ujjwal.ecommerce.databinding.ItemCartBinding

/**
 * Adapter class for managing and displaying cart items in a RecyclerView.
 *
 * Uses ListAdapter for efficient updates when data changes. Implements a DiffUtil
 * to optimize RecyclerView performance by identifying changes at the item level.
 * @see [RecyclerView.Adapter](https://developer.android.com/reference/androidx/recyclerview/widget/RecyclerView.Adapter)
 */
class CartAdapter(
	private val onCartItemAction: (CartItem, Action) -> Unit // Lambda to handle actions
) : ListAdapter<CartItem, CartAdapter.CartViewHolder>(DiffCallback) {

	/**
	 * Enum class for representing actions a user can perform on a cart item.
	 * - INCREASE: Increase item quantity.
	 * - DECREASE: Decrease item quantity.
	 * - REMOVE: Remove the item from the cart.
	 */
	enum class Action { INCREASE, DECREASE, REMOVE }

	/**
	 * ViewHolder class for binding and displaying cart item data in a RecyclerView item.
	 */
	inner class CartViewHolder(private val binding: ItemCartBinding) :
		RecyclerView.ViewHolder(binding.root) {

		/**
		 * Binds data from a CartItem to the corresponding views in the layout.
		 *
		 * @param cartItem The data model containing information about the cart item.
		 */
		fun bind(cartItem: CartItem) {
			// Set product name and price
			binding.productName.text = cartItem.productName
			binding.productPrice.text = "₹${cartItem.productPrice}"
			binding.quantity.text = cartItem.quantity.toString()

			// Load product image using Glide
			Glide.with(binding.productImage.context)
				.load(cartItem.productImage)
				.into(binding.productImage)

			// Attach click listeners for action buttons
			binding.increaseButton.setOnClickListener {
				onCartItemAction(cartItem, Action.INCREASE)
			}
			binding.decreaseButton.setOnClickListener {
				onCartItemAction(cartItem, Action.DECREASE)
			}
			binding.removeButton.setOnClickListener {
				onCartItemAction(cartItem, Action.REMOVE)
			}
		}
	}

	/**
	 * Inflates the layout for each cart item and creates a ViewHolder.
	 */
	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
		val binding = ItemCartBinding.inflate(LayoutInflater.from(parent.context), parent, false)
		return CartViewHolder(binding)
	}

	/**
	 * Binds data to the ViewHolder at the specified position.
	 */
	override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
		holder.bind(getItem(position))
	}

	companion object {
		/**
		 * DiffUtil implementation for optimizing RecyclerView updates by calculating
		 * differences between old and new lists.
		 * @see [DiffUtil](https://developer.android.com/reference/androidx/recyclerview/widget/DiffUtil)
		 */
		private val DiffCallback = object : DiffUtil.ItemCallback<CartItem>() {
			override fun areItemsTheSame(oldItem: CartItem, newItem: CartItem): Boolean {
				return oldItem.productId == newItem.productId // Compare unique IDs
			}
			override fun areContentsTheSame(oldItem: CartItem, newItem: CartItem): Boolean {
				return oldItem == newItem // Compare full data equality
			}
		}
	}
}