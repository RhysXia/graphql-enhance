package cn.ryths

import cn.ryths.annotation.GraphQLDescription
import cn.ryths.annotation.GraphQLField
import cn.ryths.annotation.GraphQLFieldIngore
import cn.ryths.annotation.GraphQLObject
import graphql.schema.GraphQLObjectType
import java.lang.reflect.Field
import java.lang.reflect.Method
import java.util.*
import java.util.stream.Collectors

class ObjectTypeGenerator {

    private val typeAnalyze = TypeAnalyze()

    /**
     * 通过此方法添加自定义的Scalar
     */
    fun registerTypeFunction(typeFunction: TypeFunction) {
        typeAnalyze.register(typeFunction)
    }

    /**
     * 根据class获取schema
     */
    fun getObjectBuilder(clazz: Class<*>): GraphQLObjectType.Builder {
        val builder = GraphQLObjectType.newObject()
        //设置定义的名称
        builder.name(getClassName(clazz))
        //如果存在描述，则设置描述
        val description = clazz.getDeclaredAnnotation(GraphQLDescription::class.java)
        if (description != null) {
            builder.description(description.value)
        }
        //获取所有的method
        val methods = getRightMethods(clazz)
        //获取所有field
        val fields = getRightFields(clazz)

        //判断fields和methods中是否重复，比如属性 name 方法
        TODO("continue")
    }


    /**
     * 获取类名
     */
    private fun getClassName(clazz: Class<*>): String {
        val annotation = clazz.getDeclaredAnnotation(GraphQLObject::class.java)
        return if (annotation != null && !annotation.value.isNullOrBlank()) {
            annotation.value
        } else clazz.simpleName
    }

    /**
     * 获取字段名
     */
    private fun getFieldName(field: Field): String {
        val annotation = field.getDeclaredAnnotation(GraphQLField::class.java)
        return if (annotation != null && !annotation.name.isNullOrBlank()) {
            annotation.name
        } else field.name
    }

    /**
     * 获取方法名
     */
    private fun getMethodName(method: Method): String {
        val annotation = method.getDeclaredAnnotation(GraphQLField::class.java)
        return if (annotation != null && !annotation.name.isNullOrBlank()) {
            annotation.name
        } else method.name
    }

    /**
     * 获取符合条件的methods
     */
    private fun getRightMethods(clazz: Class<*>): List<Method> {
        var methods = clazz.declaredMethods
        return methods.filter { !it.isBridge && !it.isSynthetic && !it.isAnnotationPresent(GraphQLFieldIngore::class.java) }
    }

    /**
     * 获取符合条件的field
     */
    private fun getRightFields(clazz: Class<*>): List<Field> {
        var fields = clazz.declaredFields
        return fields.filter { !it.isSynthetic && !it.isAnnotationPresent(GraphQLFieldIngore::class.java) }
    }

}