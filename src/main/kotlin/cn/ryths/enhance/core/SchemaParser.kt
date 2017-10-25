package cn.ryths.enhance.core

import cn.ryths.enhance.annotation.*
import java.lang.reflect.Method

class SchemaParser {

    private var classSet: Set<Class<*>> = HashSet()
    private var querySet: Set<Method> = HashSet()
    private var mutationSet: Set<Method> = HashSet()
    private var subscriptionSet: Set<Method> = HashSet()

    /**
     * 解析指定的class
     */
    fun parse(clazz: Class<*>) {
        //判断clazz是否有GraphQLController注解
        if (!clazz.isAnnotationPresent(GraphQLController::class.java)) {
            return
        }
        //获取所有的方法
        val methods = clazz.declaredMethods
        for (method in methods) {
            when {
                method.isAnnotationPresent(GraphQLQuery::class.java) -> querySet += method
                method.isAnnotationPresent(GraphQLMutation::class.java) -> mutationSet += method
                method.isAnnotationPresent(GraphQLSubscription::class.java) -> subscriptionSet += method
            }
        }
    }

    fun generateQuerySchema(method: Method){

        val queryAnno = method.getAnnotation(GraphQLQuery::class.java)
        val name = if(queryAnno.name.isNullOrEmpty()) method.name else queryAnno.name
        val params = method.parameters
        val paramMap:Map<String,String> = HashMap()
        for (param in params){
            val paramAnno = param.getAnnotation(GraphQLParam::class.java)
            if(paramAnno != null){
                val paramName = if(paramAnno.name.isNullOrEmpty()) param.name else paramAnno.name
                val paramType = if (paramAnno.type.isNullOrEmpty()) getTypeString(param.type) else paramAnno.type
            }
        }
    }

    private fun getTypeString(type: Class<*>?): String {
        //TODO complete it
        return "String"
    }

}