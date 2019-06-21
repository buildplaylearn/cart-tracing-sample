package dev.william.samples.kotlin.cart.cart.repository

import dev.william.samples.kotlin.cart.cart.entity.Cart
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CartRepository : JpaRepository<Cart, String> {
    fun findByCustomerId(customerId: String): List<Cart>
    fun findByCustomerIdAndActive(customerId: String, active: Boolean = true): List<Cart>
}
