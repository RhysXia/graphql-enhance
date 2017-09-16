package cn.ryths

import cn.ryths.annotation.*
import graphql.schema.GraphQLArgument
import graphql.schema.GraphQLFieldDefinition
import graphql.schema.GraphQLObjectType
import java.lang.reflect.Method
import java.lang.reflect.Parameter

class ResolverGenerator {

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
        //设置field的定义
        val methods = getMethods(clazz)
        for (method in methods) {
            builder.field(getFieldBuilder(method))
        }
        return builder
    }


    /**
     * 获取类名
     */
    private fun getClassName(clazz: Class<*>): String {
        val annotation = clazz.getDeclaredAnnotation(GraphQLObject::class.java)
        return if (annotation != null && annotation.value.isNullOrBlank()) {
            annotation.value
        } else clazz.simpleName
    }

    /**
     * 获取方法名
     */
    private fun getMethodName(method: Method): String {
        val annotation = method.getDeclaredAnnotation(GraphQLField::class.java)
        return if (annotation != null && annotation.name.isNullOrBlank()) {
            annotation.name
        } else method.name
    }

    /**
     * 获取参数名
     */
    private fun getParameterName(parameter: Parameter): String {
        val annotation = parameter.getDeclaredAnnotation(GraphQLParameter::class.java)
        return if (annotation != null && annotation.value.isNullOrBlank()) {
            annotation.value
        } else parameter.name

    }

    /**
     * 获取类中所有符合条件的方法
     */
    private fun getMethods(clazz: Class<*>): List<Method> {
        //获取所有class中非继承的方法
        val original = clazz.declaredMethods
        //过滤掉所有不可访问的，桥接的，自动生成的，明确表示要忽略的方法
        return original.filter { !it.isBridge && !it.isSynthetic && !it.isAnnotationPresent(GraphQLFieldIngore::class.java) }
    }


    /**
     * 根据method获取schema中field的定义
     */
    private fun getFieldBuilder(method: Method): GraphQLFieldDefinition.Builder {
        val builder = GraphQLFieldDefinition.newFieldDefinition()
        //设置字段名
        builder.name(getMethodName(method))
        //如果存在描述，则设置描述信息
        val description = method.getDeclaredAnnotation(GraphQLDescription::class.java)
        if (description != null) {
            builder.description(description.value)
        }
        //设置参数信息
        val parameters = method.parameters
        for (parameter in parameters) {
            builder.argument(getArgsBuilder(parameter))
        }
        //设置默认值
        val annotation = method.getDeclaredAnnotation(GraphQLField::class.java)
        if (annotation != null && annotation.defaultValue.isNullOrBlank()) {
            builder.staticValue(annotation.defaultValue)
        }
        return builder
    }

    /**
     * 根据parameter设置field参数的定义
     */
    private fun getArgsBuilder(parameter: Parameter): GraphQLArgument.Builder {
        val builder = GraphQLArgument.newArgument()
        //设置名称
        builder.name(getParameterName(parameter))
        //设置描述
        val description = parameter.getDeclaredAnnotation(GraphQLDescription::class.java)
        if (description != null) {
            builder.description(description.value)
        }
        val annotation = parameter.getDeclaredAnnotation(GraphQLParameter::class.java)
        if (annotation != null && annotation.defaultValue.isNullOrBlank()) {
            builder.defaultValue(annotation.defaultValue)
        }
        return builder
    }

}