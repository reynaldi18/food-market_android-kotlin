package com.fMarket.app.api.response

import com.fMarket.app.core.CoreResp
import com.fMarket.app.data.Auth
import com.fMarket.app.data.Product
import com.fMarket.app.data.User

/** Response with message only*/
class CoreRes : CoreResp<Any>()

/** Response with specific object*/
class AuthRes : CoreResp<Auth>()
class UserRes : CoreResp<User>()
class ProductsRes : CoreResp<List<Product>>()