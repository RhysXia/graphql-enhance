package cn.ryths

import graphql.schema.GraphQLType

/**
 * 获取class对应的graphql type
 */
interface TypeFunction {

    /**
     * 获取当前类型
     */
    fun getType(): GraphQLType

    /**
     * 根据类型名判断是否为当前类型
     */
    fun isType(typeName: String): Boolean

    /**
     * 根据class的类型判断是否为当前类型
     */
    fun isType(clazz: Class<*>): Boolean

}