package com.aigc_data_pipelines.backend.dao

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.springframework.data.domain.PageRequest
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Serializable
@Entity
@Table(name = "tb_users")
data class UserEntity (
    @SerialName("Uid")
    @Id
    var id: String = "",
    @SerialName("Name")
    var username: String = "",
    @SerialName("Password")
    var password: String = "",
    @SerialName("NickName")
    var nickname: String = "",
    @SerialName("Email")
    var email: String = "",
    @SerialName("CreatedAt")
    var createdAt: String = "",
    @SerialName("UpdatedAt")
    var updatedAt: String = "",
)


@Repository
interface UserRepoCustomImpl : CrudRepository<UserEntity, String>,PagingAndSortingRepository<UserEntity, String> {
    @Query("SELECT CASE WHEN count(id) > 0 THEN true ELSE false END from tb_users where id = :uid", nativeQuery = true)
    fun isUserExisted(@Param("uid") uid: String): Number
}

@Service
class UserService(val repo: UserRepoCustomImpl) {
    fun findAll(page: Int, size: Int): Iterable<UserEntity> {
        return repo.findAll(PageRequest.of(page, size)).toList()
    }

    fun findById(uid: String): UserEntity? {
        return repo.findById(uid).orElse(null)
    }

    fun isUserExisted(uid: String): Number {
        return repo.isUserExisted(uid)
    }

    @Transactional
    fun isUserExistedTrans(uid: String): Number {
        throw Exception("test")
    }
}