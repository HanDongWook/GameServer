package com.example.gameserver.response

data class CommonResponse<T>(
    val result: T? = null,
    var isSuccess: Boolean = true,
    val errorCode: String? = null,
    val message: String? = null,
) {
    constructor() : this(null, true, "", "")
    constructor(result: T?) : this(result, true, "", "")
}