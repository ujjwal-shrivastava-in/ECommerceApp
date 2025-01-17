package com.ujjwal.ecommerce.activity.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ujjwal.ecommerce.database.CartItem
import com.ujjwal.ecommerce.databinding.ItemCartBinding
// CartAdapter
class CartAdapter(
	private val onCartItemAction: (CartItem, Action) -> Unit
) : ListAdapter<CartItem, CartAdapter.CartViewHolder>(DiffCallback) {
	// enum for handling item quantity
	enum class Action { INCREASE, DECREASE, REMOVE }
	// CartViewHolder
	inner class CartViewHolder(private val binding: ItemCartBinding) :
		RecyclerView.ViewHolder(binding.root) {
		fun bind(cartItem: CartItem) {
			binding.productName.text = cartItem.productName
			binding.productPrice.text = "₹${cartItem.productPrice}"
			binding.quantity.text = cartItem.quantity.toString()
			// Load product image
			Glide.with(binding.productImage.context)
				.load(cartItem.productImage)
				.into(binding.productImage)
			// Handling actions
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
	// onCreateViewHolder
	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
		val binding = ItemCartBinding.inflate(LayoutInflater.from(parent.context), parent, false)
		return CartViewHolder(binding)
	}
	// onBindViewHolder
	override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
		holder.bind(getItem(position))
	}
	// object
	companion object {
		private val DiffCallback = object : DiffUtil.ItemCallback<CartItem>() {
			override fun areItemsTheSame(oldItem: CartItem, newItem: CartItem): Boolean {
				return oldItem.productId == newItem.productId
			}
			override fun areContentsTheSame(oldItem: CartItem, newItem: CartItem): Boolean {
				return oldItem == newItem
			}
		}
	}
}