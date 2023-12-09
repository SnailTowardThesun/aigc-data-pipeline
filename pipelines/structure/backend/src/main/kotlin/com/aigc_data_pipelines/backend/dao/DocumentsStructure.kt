package com.aigc_data_pipelines.backend.dao

import kotlinx.serialization.Serializable
import org.springframework.data.mongodb.core.mapping.Document

@Serializable
@Document(collection = "col_aigc_data_users")
data class User (
    var uid: String = "",
    var name: String = "",
    var email: String = "",
)