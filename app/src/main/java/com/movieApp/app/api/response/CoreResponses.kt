package com.movieApp.app.api.response

import com.movieApp.app.core.CoreResp
import com.movieApp.app.data.Auth
import com.movieApp.app.data.User

/** Response with message only*/
class CoreRes : CoreResp<Any>()

/** Response with specific object*/
class AuthRes : CoreResp<Auth>()
class UserRes : CoreResp<User>()