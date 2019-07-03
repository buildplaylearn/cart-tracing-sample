package dev.william.samples.kotlin.cart.cart

import io.opentracing.Span
import io.opentracing.contrib.web.servlet.filter.ServletFilterSpanDecorator
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@SpringBootApplication
class CartApplication {
    @Bean
    fun httpServletDecorator() = object : ServletFilterSpanDecorator {
        override fun onResponse(
            httpServletRequest: HttpServletRequest?,
            httpServletResponse: HttpServletResponse?,
            span: Span?
        ) {
        }

        override fun onRequest(httpServletRequest: HttpServletRequest?, span: Span?) {
            httpServletRequest?.getHeader("X-Circle-ID")?.let { circleId ->
                println("Processing request for circle '$circleId'")
                span?.setTag("x.circle.id", circleId)
            }
        }

        override fun onTimeout(
            httpServletRequest: HttpServletRequest?,
            httpServletResponse: HttpServletResponse?,
            timeout: Long,
            span: Span?
        ) {
        }

        override fun onError(
            httpServletRequest: HttpServletRequest?,
            httpServletResponse: HttpServletResponse?,
            exception: Throwable?,
            span: Span?
        ) {
        }
    }
}

fun main(args: Array<String>) {
    runApplication<CartApplication>(*args)
}
