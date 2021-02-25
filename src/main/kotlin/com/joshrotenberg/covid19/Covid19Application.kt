package com.joshrotenberg.covid19

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
class Covid19Application

fun main(args: Array<String>) {
	runApplication<Covid19Application>(*args)
}
