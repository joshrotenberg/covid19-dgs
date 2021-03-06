package com.joshrotenberg.covid19.controllers

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.mvc.support.RedirectAttributes
import org.springframework.web.servlet.view.RedirectView

@Controller
@RequestMapping
class RedirectController {

    @GetMapping("/")
    fun redirectToGraphiQL(attributes: RedirectAttributes): RedirectView {
        return RedirectView("/graphiql")
    }
}