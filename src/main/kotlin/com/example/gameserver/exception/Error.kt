package com.example.gameserver.exception

enum class ErrorCode(val code: Int, val message: String) {
    UNKNOWN(1000, "에러가 발생하였습니다."),

    //user
    NOT_EXIST_USER(2000, "찾을 수 없는 유저입니다."),

    //credit
    NOT_ENOUGH_CREDIT(3000, "재화가 충분하지 않습니다."),
    NOT_ENOUGH_PAID_CREDIT(3001, "유상재화가 충분하지 않습니다."),
    NOT_ENOUGH_FREE_CREDIT(3002, "무상재화가 충분하지 않습니다."),
    CAN_NOT_DEPOSIT_NEGATIVE(3003, "예금할 수 없는 금액입니다."),

    //item
    FAIL_BUY(4001, "구매에 실패하였습니다.")
}
