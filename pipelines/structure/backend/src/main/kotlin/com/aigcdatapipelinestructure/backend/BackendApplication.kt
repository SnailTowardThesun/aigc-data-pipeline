package com.aigcdatapipelinestructure.backend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(scanBasePackages = ["com.aigcdatapipelinestructure.backend.controller"])
class BackendApplication

fun main(args: Array<String>) {
	runApplication<BackendApplication>(*args)
}
