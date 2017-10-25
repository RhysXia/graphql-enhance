package cn.ryths.enhance.annotation

/**
 * 用于需要被解析成schema的类上
 */
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
annotation class GraphQLObject(val name:String)