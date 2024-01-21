package com.aigc_data_pipelines.backend.dao

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Lob
import jakarta.persistence.Table
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service

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
interface  DataSetsMySqlRepository: CrudRepository<DataSetsEntity, String>,PagingAndSortingRepository<DataSetsEntity, String>{
    fun findDataSetsById(id: String): DataSetsEntity? {
        return findById(id).orElse(null)
    }
}

@Service
class DatasetService (val repo: DataSetsMySqlRepository) {
    fun findDataSetsById(id: String): DataSetsEntity? {
        return repo.findDataSetsById(id)
    }

    fun findAll(pageNumber: Int, pageSize: Int): Iterable<DataSetsEntity> {
        return repo.findAll(PageRequest.of(pageNumber, pageSize)).toList()
    }
}