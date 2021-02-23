package com.joshrotenberg.covid19

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class Covid19Application

fun main(args: Array<String>) {
	runApplication<Covid19Application>(*args)
}
