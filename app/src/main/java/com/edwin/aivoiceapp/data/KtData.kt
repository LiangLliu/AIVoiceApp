package com.edwin.aivoiceapp.data

data class MainListData(
    val title: String,
    val icon: Int,
    val color: Int
)

/**
 * type ： 回话类型
 */
data class ChatList(
    val type: Int
) {
    lateinit var text: String
}