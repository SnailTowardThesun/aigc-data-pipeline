package com.aigc_data_pipelines.backend.tools

class IDGenerator: IDGeneratorInterface {
    override fun generate(): String {
        return "1234567890"
    }
}