package dev.william.samples.kotlin.cart.cart.controller

import dev.william.samples.kotlin.cart.cart.entity.Cart
import dev.william.samples.kotlin.cart.cart.service.CartService
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/cart/{cartId}", produces = [MediaType.APPLICATION_JSON_VALUE])
class CartController(val cartService: CartService) {
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun getCustomerCart(@PathVariable cartId: String, @RequestHeader("x-zup-circle-id", required = true) circleId: String): Cart {
        println("Circle ID is $circleId")
        return cartService.getCartById(cartId)
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteCustomerCart(@PathVariable cartId: String) {
        cartService.deactivateCart(cartId)
    }
}
