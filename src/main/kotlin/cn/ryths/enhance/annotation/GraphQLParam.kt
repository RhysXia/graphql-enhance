package cn.ryths.enhance.annotation

/**
 * query的方法上
 */
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.VALUE_PARAMETER)
annotation class GraphQLParam(val name: String, val type: String)
