package com.coxassginment.data.local.entity

import androidx.lifecycle.Transformations.map
import com.coxassginment.data.network.CommonResponseDto
import com.coxassginment.data.network.DaoEntityMapper
import com.coxassginment.data.network.Items
fun CommonResponseDto.ResponseItemList.map()=DaoEntityMapper.GitUserListDao(
items.map { it.map() }
)

 fun Items.map()=GitUsers(
    id=id,
    login=login,
    node_id=node_id,
    avatar_url=avatar_url,
    gravatar_id =gravatar_id,
    url=url,
    html_url=html_url,
    followers_url= followers_url

)