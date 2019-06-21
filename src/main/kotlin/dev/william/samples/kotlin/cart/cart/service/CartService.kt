package dev.william.samples.kotlin.cart.cart.service

import arrow.core.Try
import arrow.core.getOrElse
import dev.william.samples.kotlin.cart.cart.entity.Cart
import dev.william.samples.kotlin.cart.cart.entity.CartItem
import dev.william.samples.kotlin.cart.cart.repository.CartRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*
import javax.transaction.Transactional

@Service
class CartService(val cartRepository: CartRepository) {
    @Transactional
    fun getByCustomerId(customerId: String): Cart {
        return Try {
            cartRepository.findByCustomerIdAndActive(customerId)
                .firstOrFail { RuntimeException("Could not find first") }
        }.getOrElse { createCartForCustomerId(customerId) }
    }

    fun createCartForCustomerId(customerId: String): Cart {
        deactivateCustomerCarts(customerId)
        return cartRepository.save(
            Cart(
                active = true,
                customerId = customerId,
                createdAt = LocalDateTime.now(),
                id = UUID.randomUUID().toString()
            )
        )
    }

    fun deactivateCustomerCart(customerId: String) {
        deactivateCart(getByCustomerId(customerId).id!!)
    }

    fun deactivateCart(cartId: String): Cart {
        return cartRepository.getOne(cartId)
            .apply { active = false }
            .let { cartRepository.save(it) }
    }

    @Transactional
    fun addItemToCustomerCart(customerId: String, item: CartItem) {
        val cart = getByCustomerId(customerId)
            .addItem(item.copy(id = UUID.randomUUID().toString(), addedAt = LocalDateTime.now()))
        cartRepository.save(cart)
    }

    private fun deactivateCustomerCarts(customerId: String) {
        cartRepository.findByCustomerId(customerId)
            .map { it.copy(active = false) }
            .map { cartRepository.save(it) }
    }

    private fun <T> List<T>.firstOrFail(exGenerator: () -> Exception): T {
        return this.firstOrNull() ?: throw exGenerator()
    }
}
