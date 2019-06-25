package dev.william.samples.kotlin.cart.cart.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import org.jetbrains.annotations.NotNull
import java.time.LocalDateTime
import javax.persistence.*

@Entity
data class CartItem(
    @field:[Id NotNull Column(name = "id")]
    var id: String? = null,

    @field:[NotNull ManyToOne JoinColumn(name = "cart_id") JsonIgnore]
    var cart: Cart? = null,

    @field:[NotNull Column(name = "item_id")]
    var itemId: String? = null,

    @field:[NotNull Column(name = "quantity")]
    var quantity: Int? = null,

    @field:[NotNull Column(name = "added_at")]
    var addedAt: LocalDateTime? = null
)
