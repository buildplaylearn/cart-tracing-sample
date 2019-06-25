package dev.william.samples.kotlin.cart.cart.repository

import dev.william.samples.kotlin.cart.cart.entity.CartItem
import org.springframework.data.jpa.repository.JpaRepository

interface CartItemRepository : JpaRepository<CartItem, String>
