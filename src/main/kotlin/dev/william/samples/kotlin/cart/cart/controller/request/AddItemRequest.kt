package dev.william.samples.kotlin.cart.cart.controller.request

import com.fasterxml.jackson.annotation.JsonProperty
import org.jetbrains.annotations.NotNull
import javax.validation.constraints.Min
import javax.validation.constraints.NotEmpty

data class AddItemRequest(
    @field:[NotNull NotEmpty JsonProperty("item_id")]
    var itemId: String? = null,

    @field:[NotNull Min(1)]
    var quantity: Int? = null
)
