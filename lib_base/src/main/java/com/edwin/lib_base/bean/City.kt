package com.edwin.lib_base.bean


data class City(
    val error_code: Int,
    val reason: String,
    val result: List<Result>
)

data class Result(
    val city: String,
    val district: String,
    val id: String,
    val province: String
)