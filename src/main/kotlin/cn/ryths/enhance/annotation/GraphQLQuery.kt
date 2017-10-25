package cn.ryths.enhance.annotation

/**
 * query的方法上
 */
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION)
annotation class GraphQLQuery(val name: String)