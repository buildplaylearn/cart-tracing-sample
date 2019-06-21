package dev.william.samples.kotlin.cart.cart.controller

import dev.william.samples.kotlin.cart.cart.controller.request.AddItemRequest
import dev.william.samples.kotlin.cart.cart.entity.Cart
import dev.william.samples.kotlin.cart.cart.entity.CartItem
import dev.william.samples.kotlin.cart.cart.service.CartService
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/customer/{customerId}/cart", produces = [MediaType.APPLICATION_JSON_VALUE])
class CartController(val cartService: CartService) {
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun getCustomerCart(@PathVariable customerId: String): Cart {
        return cartService.getByCustomerId(customerId)
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteCustomerCart(@PathVariable customerId: String) {
        cartService.deactivateCustomerCart(customerId)
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun addItemCustomerCart(@PathVariable customerId: String, @RequestBody addItemRequest: AddItemRequest) {
        CartItem(itemId = addItemRequest.itemId, quantity = addItemRequest.quantity)
            .let { cartService.addItemToCustomerCart(customerId, it) }
    }
}
