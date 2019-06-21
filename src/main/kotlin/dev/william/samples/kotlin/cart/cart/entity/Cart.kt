package dev.william.samples.kotlin.cart.cart.entity

import org.jetbrains.annotations.NotNull
import java.time.LocalDateTime
import javax.persistence.*
import javax.persistence.CascadeType.PERSIST

@Entity
data class Cart(
    @field:[Id NotNull Column(name = "id")]
    var id: String? = null,

    @field:[NotNull Column(name = "customer_id")]
    var customerId: String? = null,

    @field:[NotNull Column(name = "created_at")]
    var createdAt: LocalDateTime? = null,

    @field:[NotNull Column(name = "active")]
    var active: Boolean? = null,

    @field:[OneToMany(cascade = [PERSIST]) JoinColumn(name = "cart_id")]
    var items: List<CartItem> = emptyList()
) {
    fun addItem(item: CartItem): Cart {
        return this.addItems(listOf(item))
    }

    private fun addItems(items: List<CartItem>): Cart {
        return this.also { it.items += items }
    }
}
