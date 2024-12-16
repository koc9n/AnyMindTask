package org.test.anymindtask

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication(exclude = [UserDetailsServiceAutoConfiguration::class])
class AnyMindTaskApplication

fun main(args: Array<String>) {
    runApplication<AnyMindTaskApplication>(*args)
}
