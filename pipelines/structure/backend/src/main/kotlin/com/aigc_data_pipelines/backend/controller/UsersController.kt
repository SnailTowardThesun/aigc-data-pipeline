package com.aigc_data_pipelines.backend.controller

import com.aigc_data_pipelines.backend.dao.UserRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import kotlinx.serialization.json.Json
import kotlinx.serialization.encodeToString
import org.springframework.data.domain.PageRequest
import org.springframework.web.bind.annotation.RequestParam

@RestController
class UsersController(@Autowired val repo: UserRepo) {
    @GetMapping("apis/v1/users")
    fun getUsers(@RequestParam pageNumber: Int = 0, @RequestParam pageSize: Int = 10): String {
        val users = repo.findAll(PageRequest.of(pageNumber, pageSize))
        return Json.encodeToString(users.toList())
    }

    @GetMapping("apis/v1/user")
    fun getUserById(@RequestParam uid: String): String {
        val u = repo.findByUid(uid)
        return Json.encodeToString(u)
    }

    @PostMapping("apis/v1/user")
    fun createUser(): String {
        return "createUser"
    }
}n