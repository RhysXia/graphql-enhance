package cn.ryths.enhance.annotation

import graphql.schema.GraphQLScalarType
import kotlin.reflect.KClass

/**
 * 用于需要被解析成schema的类上
 */
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FIELD)
annotation class GraphQLField(val name: String, val type: KClass<GraphQLScalarType>)