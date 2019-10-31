package com.cengenes.kotlin.api.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class IndexController {

    @GetMapping("/")
    fun redirectToSwaggerUi(): String {
        return "redirect:/swagger-ui.html"
    }
}
