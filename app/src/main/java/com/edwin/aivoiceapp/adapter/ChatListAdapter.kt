package com.imooc.aivoiceapp.adapter

import com.edwin.aivoiceapp.R
import com.edwin.aivoiceapp.data.ChatList
import com.edwin.aivoiceapp.entity.AppConstants
import com.edwin.lib_base.base.adapter.CommonAdapter
import com.edwin.lib_base.base.adapter.CommonViewHolder


/**
 *  对话列表适配器
 */
class ChatListAdapter(mList: List<ChatList>) :
    CommonAdapter<ChatList>(mList, object : OnMoreBindDataListener<ChatList> {


        override fun onBindViewHolder(
            model: ChatList,
            viewHolder: CommonViewHolder,
            type: Int,
            position: Int
        ) {
            when (type) {
                AppConstants.TYPE_MINE_TEXT -> viewHolder.setText(R.id.tv_mine_text, model.text)
                AppConstants.TYPE_AI_TEXT -> viewHolder.setText(R.id.tv_ai_text, model.text)
                AppConstants.TYPE_AI_WEATHER_TEXT -> {
                }
            }


        }

        override fun getLayoutId(type: Int): Int {
            return when (type) {
                AppConstants.TYPE_MINE_TEXT -> R.layout.layout_mine_text
                AppConstants.TYPE_AI_TEXT -> R.layout.layout_ai_text
                AppConstants.TYPE_AI_WEATHER_TEXT -> R.layout.layout_ai_weather_text
                else -> 0
            }
        }

        override fun getItemType(position: Int): Int {
            return mList[position].type
        }

    }) {

}