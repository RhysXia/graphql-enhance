package cn.ryths

import cn.ryths.entity.User
import graphql.GraphQL
import graphql.schema.GraphQLSchema
import org.junit.Test

class ObjectTypeGeneratorTest {

    private val query: String

    init {
        query = """
            {
                schema{
                    query{
                    }
                }
            }
        """.trimIndent()
    }

    @Test
    fun getObjectBuilder() {
        val user = User()
        var generator = ObjectTypeGenerator().getObjectBuilder(User::class.java).build()
        val graphQL = GraphQL.newGraphQL(GraphQLSchema(generator)).build()
        val result = graphQL.execute(query)
        println(result.getData<String>())

    }

}