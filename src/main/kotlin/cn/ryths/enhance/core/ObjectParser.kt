package cn.ryths.enhance.core

import cn.ryths.enhance.annotation.GraphQLField
import cn.ryths.enhance.annotation.GraphQLObject
import graphql.schema.GraphQLObjectType
import graphql.schema.GraphQLSchema
import graphql.schema.GraphQLType

class ObjectParser {

    fun parser(clazz: Class<*>) {

        val objectAnno = clazz.getAnnotation(GraphQLObject::class.java)

        val name = if (objectAnno.name.isNullOrEmpty()) clazz.simpleName else objectAnno.name
        val fields = clazz.declaredFields

        val fieldNameAndType:Map<String,GraphQLObjectType> = HashMap()

        for (field in fields){
            val fieldAnno = field.getAnnotation(GraphQLField::class.java) ?: continue
            val fieldName = fieldAnno.name
            val fieldType = fieldAnno.type
        }
        val builder = GraphQLObjectType.newObject().name(name)
        for (fieldItem in fieldNameAndType){
        }

    }
}