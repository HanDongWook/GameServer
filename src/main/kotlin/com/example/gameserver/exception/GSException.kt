package com.example.gameserver.exception

import java.lang.RuntimeException

class GSException : RuntimeException {
    val errorCode: ErrorCode

    constructor(errorCode: ErrorCode) {
        this.errorCode = errorCode
    }

    constructor(errorCode: ErrorCode, message: String) : super(message) {
        this.errorCode = errorCode
    }

    constructor(errorCode: ErrorCode, cause: Throwable) : super(cause) {
        this.errorCode = errorCode
    }

    constructor(errorCode: ErrorCode, message: String, cause: Throwable) : super(message, cause) {
        this.errorCode = errorCode
    }
}
