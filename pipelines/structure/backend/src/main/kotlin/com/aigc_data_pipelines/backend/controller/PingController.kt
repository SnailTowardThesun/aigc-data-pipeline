// Author: hankun1991@outlook.com
package com.aigc_data_pipelines.backend.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class PingController {
    @GetMapping("/ping")
    fun ping(): String {
        return "pong"
    }
}