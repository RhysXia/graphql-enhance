package cn.ryths.enhance.annotation

/**
 * mutation的方法上
 */
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION)
annotation class GraphQLMutation(val name: String)