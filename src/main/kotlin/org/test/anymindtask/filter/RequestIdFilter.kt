package org.test.anymindtask.filter

import jakarta.servlet.Filter
import jakarta.servlet.FilterChain
import jakarta.servlet.FilterConfig
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import org.slf4j.MDC
import java.util.UUID

class RequestIdFilter : Filter {
    override fun doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain) {
        val requestId = UUID.randomUUID().toString()
        MDC.put("requestId", requestId)
        try {
            chain.doFilter(request, response)
        } finally {
            MDC.remove("requestId")
        }
    }

    override fun init(filterConfig: FilterConfig?) {}
    override fun destroy() {}
}