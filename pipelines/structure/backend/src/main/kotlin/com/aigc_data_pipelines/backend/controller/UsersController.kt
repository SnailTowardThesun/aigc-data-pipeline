package com.aigc_data_pipelines.backend.controller

import com.aigc_data_pipelines.backend.dao.UserEntity
import com.aigc_data_pipelines.backend.dao.UserService
import kotlinx.serialization.Serializable
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import kotlinx.serialization.json.Json
import kotlinx.serialization.encodeToString
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam

@Serializable
data class UserResponse(
    val uid: String,
    val username: String,
    val nickname: String,
    val email: String,
    val createdAt: String,
    val updatedAt: String,
)
@Component
class UserMapper {
    fun asResponse(entity: UserEntity):UserResponse {
        return UserResponse(
            uid = entity.id,
            username = entity.username,
            nickname = entity.nickname,
            email = entity.email,
            createdAt = entity.createdAt,
            updatedAt = entity.updatedAt,
        )
    }
}
@RestController
@RequestMapping("apis/v1")
class UsersController(@Autowired val svrs: UserService, val mp: UserMapper) {
    @GetMapping("/users")
    fun getUsers(@RequestParam(required = false, defaultValue = "0") pageNumber: Int,
                 @RequestParam(required = false, defaultValue = "10") pageSize: Int): String {
        val users = svrs.findAll(pageNumber, pageSize)
        return Json.encodeToString(users.toList())
    }

    @GetMapping("/user")
    fun getUserById(@RequestParam uid: String): String {
        val exists = svrs.isUserExisted(uid)
        if (exists == 0) {
            return "user not found"
        }

        val u = svrs.findById(uid) ?: return "user not found"

        return Json.encodeToString(mp.asResponse(u))
    }

    @PostMapping("/user")
    fun createUser(): String {
        return "createUser"
    }
}