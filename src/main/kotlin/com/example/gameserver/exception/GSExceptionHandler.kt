package com.example.gameserver.exception

import com.example.gameserver.response.CommonResponse
import mu.KotlinLogging
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.lang.Exception
import javax.servlet.http.HttpServletRequest

@RestControllerAdvice
class GSExceptionHandler {
    private val logger = KotlinLogging.logger{}

    @ExceptionHandler
    private fun handleException(request: HttpServletRequest, ex: Exception): CommonResponse<Any> {
        if (ex is GSException) {
            logger.error("[GSExceptionHandler] request : ${request.requestURI}, errorCode : ${ex.errorCode.code}, msg : ${ex.message}", ex)
            return CommonResponse(
                result = null,
                isSuccess = false,
                errorCode = "${ex.errorCode.code}",
                message = ex.errorCode.message
            )
        } else {
            logger.error("[GSExceptionHandler] request : ${request.requestURI}, msg : ${ex.message}", ex)
            return CommonResponse(
                result = null,
                isSuccess = false,
                errorCode = "${ErrorCode.UNKNOWN.code}",
                message = ErrorCode.UNKNOWN.message
            )
        }
    }
}