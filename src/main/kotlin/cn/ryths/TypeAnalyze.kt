package cn.ryths

import cn.ryths.exception.UnknownGraphQLTypeException
import graphql.Scalars
import graphql.schema.GraphQLType
import java.math.BigDecimal
import java.math.BigInteger
import java.util.*

class TypeAnalyze {
    private var types = LinkedList<TypeFunction>()

    init {
        types.add(StringTypeFunction())
        types.add(BooleanTypeFunction())
        types.add(IntTypeFunction())
        types.add(FloatTypeFunction())
        types.add(IdTypeFunction())
        types.add(LongTypeFunction())
        types.add(ShortTypeFunction())
        types.add(ByteTypeFunction())
        types.add(BigDecimalTypeFunction())
        types.add(BigIntTypeFunction())
        types.add(CharTypeFunction())
    }

    fun register(type: TypeFunction) {
        this.types.add(type)
    }

    fun getType(typeName: String?, clazz: Class<*>): GraphQLType {
        return if (typeName != null && !typeName.isNullOrBlank()) {
            getType(typeName)
        } else {
            getType(clazz)
        }
    }

    fun getType(typeName: String): GraphQLType {
        for (type in types) {
            if (type.isType(typeName)) {
                return type.getType()
            }
        }
        throw UnknownGraphQLTypeException("can't find a GraphQLType related to this tyepName:${typeName}")
    }

    fun getType(clazz: Class<*>): GraphQLType {
        for (type in types) {
            if (type.isType(clazz)) {
                return type.getType()
            }
        }
        throw UnknownGraphQLTypeException("can't find a GraphQLType related to this class:${clazz.name}")
    }
}

internal class StringTypeFunction : TypeFunction {
    override fun getType(): GraphQLType {
        return Scalars.GraphQLString
    }

    override fun isType(typeName: String): Boolean {
        return typeName == "String"
    }

    override fun isType(clazz: Class<*>): Boolean {
        return clazz == String::class.java
    }
}

internal class BooleanTypeFunction : TypeFunction {
    override fun getType(): GraphQLType {
        return Scalars.GraphQLBoolean
    }

    override fun isType(typeName: String): Boolean {
        return typeName == "Boolean"
    }

    override fun isType(clazz: Class<*>): Boolean {
        return clazz == Boolean::class.java
    }
}

internal class IntTypeFunction : TypeFunction {
    override fun getType(): GraphQLType {
        return Scalars.GraphQLInt
    }

    override fun isType(typeName: String): Boolean {
        return typeName == "Int"
    }

    override fun isType(clazz: Class<*>): Boolean {
        return clazz == Int::class.java
    }
}

internal class FloatTypeFunction : TypeFunction {
    override fun getType(): GraphQLType {
        return Scalars.GraphQLFloat
    }

    override fun isType(typeName: String): Boolean {
        return typeName == "Float"
    }

    override fun isType(clazz: Class<*>): Boolean {
        return clazz == Float::class.java
    }
}

internal class IdTypeFunction : TypeFunction {
    override fun getType(): GraphQLType {
        return Scalars.GraphQLID
    }

    override fun isType(typeName: String): Boolean {
        return typeName == "ID"
    }

    //不指定的话，总是返回false
    override fun isType(clazz: Class<*>): Boolean {
        return false
    }
}

internal class LongTypeFunction : TypeFunction {
    override fun getType(): GraphQLType {
        return Scalars.GraphQLLong
    }

    override fun isType(typeName: String): Boolean {
        return typeName == "Long"
    }

    override fun isType(clazz: Class<*>): Boolean {
        return clazz == Long::class.java
    }
}

internal class ShortTypeFunction : TypeFunction {
    override fun getType(): GraphQLType {
        return Scalars.GraphQLShort
    }

    override fun isType(typeName: String): Boolean {
        return typeName == "Short"
    }

    override fun isType(clazz: Class<*>): Boolean {
        return clazz == Short::class.java
    }
}

internal class ByteTypeFunction : TypeFunction {
    override fun getType(): GraphQLType {
        return Scalars.GraphQLByte
    }

    override fun isType(typeName: String): Boolean {
        return typeName == "Byte"
    }

    override fun isType(clazz: Class<*>): Boolean {
        return clazz == Byte::class.java
    }
}

internal class BigDecimalTypeFunction : TypeFunction {
    override fun getType(): GraphQLType {
        return Scalars.GraphQLBigDecimal
    }

    override fun isType(typeName: String): Boolean {
        return typeName == "BigDecimal"
    }

    override fun isType(clazz: Class<*>): Boolean {
        return clazz == BigDecimal::class.java
    }
}

internal class BigIntTypeFunction : TypeFunction {
    override fun getType(): GraphQLType {
        return Scalars.GraphQLBigInteger
    }

    override fun isType(typeName: String): Boolean {
        return typeName == "BigInteger"
    }

    override fun isType(clazz: Class<*>): Boolean {
        return clazz == BigInteger::class.java
    }
}

internal class CharTypeFunction : TypeFunction {
    override fun getType(): GraphQLType {
        return Scalars.GraphQLChar
    }

    override fun isType(typeName: String): Boolean {
        return typeName == "Char"
    }

    override fun isType(clazz: Class<*>): Boolean {
        return clazz == Char::class.java
    }
}


