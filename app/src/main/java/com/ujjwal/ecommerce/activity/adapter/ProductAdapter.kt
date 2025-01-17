package com.ujjwal.ecommerce.activity.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ujjwal.ecommerce.databinding.ItemProductBinding
import com.ujjwal.ecommerce.model.Product
// ProductAdapter
class ProductAdapter(private val productList: MutableList<Product>,
										 private val onAddToCart: (Product) -> Unit)
	: RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {
	// ProductViewHolder
	inner class ProductViewHolder(private val binding: ItemProductBinding) : RecyclerView.ViewHolder(binding.root) {
		fun bind(product: Product) {
			binding.productName.text = product.title
			binding.productPrice.text = "₹${product.price}"
			// Load image with Glide or any image loading library
			Glide.with(binding.productImage.context)
				.load(product.image)
				.into(binding.productImage)
			binding.addToCartBtn.setOnClickListener {
				onAddToCart(product)
			}
		}
	}
	// onCreateViewHolder
	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
		val binding = ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
		return ProductViewHolder(binding)
	}
	// onBindViewHolder
	override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
		holder.bind(productList[position])
	}
	// getItemCount
	override fun getItemCount(): Int = productList.size
	// updateProducts
	fun updateProducts(newProducts: List<Product>){
		productList.clear()
		productList.addAll(newProducts)
		notifyDataSetChanged()
	}
}
