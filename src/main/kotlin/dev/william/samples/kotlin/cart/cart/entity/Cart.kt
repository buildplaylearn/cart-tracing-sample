package dev.william.samples.kotlin.cart.cart.entity

import org.jetbrains.annotations.NotNull
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.OneToMany

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

    @field:OneToMany(mappedBy = "cart")
    var items: MutableList<CartItem> = mutableListOf()
) {
    fun deactivate(): Cart {
        active = false
        return this
    }
}
