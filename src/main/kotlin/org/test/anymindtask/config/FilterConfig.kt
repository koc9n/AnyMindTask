package org.test.anymindtask.config

import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.test.anymindtask.filter.RequestIdFilter

@Configuration
class FilterConfig {
    @Bean
    fun requestIdFilter(): FilterRegistrationBean<RequestIdFilter> {
        val registrationBean = FilterRegistrationBean<RequestIdFilter>()
        registrationBean.filter = RequestIdFilter()
        registrationBean.order = 1
        return registrationBean
    }
}