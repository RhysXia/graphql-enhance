package cn.ryths.enhance.annotation

/**
 * Subscription的方法上
 */
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION)
annotation class GraphQLSubscription(val name: String)