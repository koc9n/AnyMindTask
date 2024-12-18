package org.test.anymindtask.controller

import org.springframework.boot.web.error.ErrorAttributeOptions
import org.springframework.boot.web.servlet.error.ErrorAttributes
import org.springframework.boot.web.servlet.error.ErrorController
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.context.request.WebRequest

@Controller
class CustomErrorController(private val errorAttributes: ErrorAttributes) : ErrorController {

    @RequestMapping("/error")
    fun handleError(webRequest: WebRequest): ResponseEntity<Map<String, Any>> {
        val errorAttributes = errorAttributes.getErrorAttributes(webRequest, ErrorAttributeOptions.defaults())
        val status = HttpStatus.valueOf(errorAttributes["status"] as Int)
        return ResponseEntity(errorAttributes, status)
    }
}