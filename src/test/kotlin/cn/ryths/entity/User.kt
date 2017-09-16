package cn.ryths.entity

data class User(
        var id: Long? = null,
        var username: String? = null,
        var password: String? = null
) {
    open fun test1(username: String, password: String): String {
        return "username:${username},password:${password}"
    }
}