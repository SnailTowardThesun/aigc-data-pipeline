// Author: hankun1991@outlook.com

package com.aigc_data_pipelines.backend.controller

import com.aigc_data_pipelines.backend.dao.DataSetsEntity
import com.aigc_data_pipelines.backend.dao.DataSetsMySqlRepository
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@Serializable
data class ListDatasetsResponseData (
    @SerialName("Total")
    val total: Int,
    @SerialName("Datasets")
    val datasets: List<DataSetsEntity>
)

@Serializable
data class ListDatasetsResponse(
    @SerialName("Code")
    val code: Int,
    @SerialName("Message")
    val message: String,
    @SerialName("Data")
    val data: ListDatasetsResponseData
)


@RestController
class DatasetsController(@Autowired val repo: DataSetsMySqlRepository) {
    @CrossOrigin(origins = ["*"])
    @GetMapping("apis/v1/datasets", produces = ["application/json"])
    fun getDatasets(@RequestParam(required = false, defaultValue = "0") pageNumber: String,
                    @RequestParam(required = false, defaultValue = "10") pageSize: String): String {

        val pageAble = PageRequest.of(pageNumber.toInt(),
            pageSize.toInt(),
            Sort.by(Sort.Direction.DESC, "updatedAt"))

        val datasets = repo.findAll(pageAble)


        val ret = ListDatasetsResponse(
            code = 0,
            message = "success",
            data = ListDatasetsResponseData(
                total = datasets.totalPages,
                datasets = datasets.toList()
            )
        )

        return Json.encodeToString(ret)
    }

    @CrossOrigin(origins = ["*"])
    @GetMapping("apis/v1/dataset", produces = ["application/json"])
    fun getDatasetById(@RequestParam id: String): String {
        val d = repo.findDataSetsById(id)
        return Json.encodeToString(d)
    }

    @CrossOrigin(origins = ["*"])
    @PostMapping("apis/v1/dataset", produces = ["application/json"])
    fun createDataset(): String {
        return "createDataset"
    }
}