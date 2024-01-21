package com.aigc_data_pipelines.backend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(scanBasePackages = ["com.aigc_data_pipelines.backend.controller", "com.aigc_data_pipelines.backend.dao", "com.aigc_data_pipelines.backend.tools"])
class BackendApplication

fun main(args: Array<String>) {
	runApplication<BackendApplication>(*args)
}
