package com.aigc_data_pipelines.backend.dao

import org.springframework.data.mongodb.repository.MongoRepository

interface UserRepo : MongoRepository<User, String> {
    fun findByUid(uid: String): User?
}
