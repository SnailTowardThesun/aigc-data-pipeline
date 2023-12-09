package com.aigc_data_pipelines.backend.tools

interface IDGeneratorInterface {
    fun generate(): String
}

class Tools {
    companion object {
        fun getIDGenerator(): IDGeneratorInterface {
            return IDGenerator()
        }
    }
}