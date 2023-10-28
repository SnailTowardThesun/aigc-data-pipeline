
@file:JvmName("SimpleApp")

import org.jetbrains.kotlinx.spark.api.*
import org.jetbrains.kotlinx.spark.api.tuples.tupleOf

fun main() {
    withSpark {
        dsOf(1, 2)
            .map { it * it } // creates Tuple2<Int, Int>
            .show()
    }
}