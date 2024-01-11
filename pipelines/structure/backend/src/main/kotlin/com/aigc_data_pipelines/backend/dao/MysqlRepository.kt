// Author: hankun1991@outlook.com

package com.aigc_data_pipelines.backend.dao

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Lob
import jakarta.persistence.Table
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository
import kotlinx.datetime.Instant
import org.springframework.data.domain.PageRequest
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
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

@Serializable
@Entity
@Table(name = "tb_datasets")
data class DataSetsEntity (
    @SerialName("Id")
    @Id
    var id: String = "",
    @SerialName("UserId")
    var userId: String = "",
    @SerialName("Name")
    var name: String = "",
    @SerialName("Description")
    var description: String = "",
    @SerialName("CreatedAt")
    var createdAt: String = "",
    @SerialName("UpdatedAt")
    var updatedAt: String = "",
) {
    @Lob
    @SerialName("Structure")
    var structure: Array<Byte> = arrayOf(0)
}

@Repository
interface  DataSetsMySqlRepository: PagingAndSortingRepository<DataSetsEntity, String>, JpaRepository<DataSetsEntity, String> {
    fun findDataSetsById(id: String): DataSetsEntity? {
        return findById(id).orElse(null)
    }
}

@Repository
interface UserRepo : JpaRepository<UserEntity, String>

@Repository
interface UserRepoCustomImpl : CrudRepository<UserEntity, String> {
    @Query("SELECT CASE WHEN count(id) > 0 THEN true ELSE false END from tb_users where id = :uid", nativeQuery = true)
    fun isUserExisted(@Param("uid") uid: String): Number
}

@Service
class UserService(val repo: UserRepo, val custom: UserRepoCustomImpl) {
    fun findAll(page: Int, size: Int): Iterable<UserEntity> {
        return repo.findAll(PageRequest.of(page, size)).toList()
    }

    fun findById(uid: String): UserEntity? {
        return repo.findById(uid).orElse(null)
    }

    fun isUserExisted(uid: String): Number {
        return custom.isUserExisted(uid)
    }

    @Transactional
    fun isUserExistedTrans(uid: String): Number {
        throw Exception("test")
    }
}