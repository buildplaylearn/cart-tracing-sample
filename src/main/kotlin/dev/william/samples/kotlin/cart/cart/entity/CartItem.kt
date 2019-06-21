package dev.william.samples.kotlin.cart.cart.entity

import org.jetbrains.annotations.NotNull
import java.time.LocalDateTime
import javax.persistence.*

@Entity
data class CartItem(
    @field:[Id NotNull Column(name = "id")]
    var id: String? = null,

    @field:[NotNull Column(name = "cart_id")]
    var cartId: String? = null,

    @field:[NotNull Column(name = "item_id")]
    var itemId: String? = null,

    @field:[NotNull Column(name = "quantity")]
    var quantity: Int? = null,

    @field:[NotNull Column(name = "added_at")]
    var addedAt: LocalDateTime? = null
)
