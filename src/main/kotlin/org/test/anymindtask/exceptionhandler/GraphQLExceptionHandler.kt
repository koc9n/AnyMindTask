package org.test.anymindtask.exceptionhandler

import graphql.GraphQLError
import graphql.GraphqlErrorBuilder
import graphql.schema.DataFetchingEnvironment
import jakarta.validation.ConstraintViolationException
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.graphql.execution.DataFetcherExceptionResolverAdapter
import org.springframework.graphql.execution.ErrorType
import org.springframework.stereotype.Component

@Component
class GraphQLExceptionHandler : DataFetcherExceptionResolverAdapter() {
    companion object {
        private val log: Logger = LoggerFactory.getLogger(this::class.java)
    }

    override fun resolveToSingleError(e: Throwable, env: DataFetchingEnvironment): GraphQLError? {
        return when (e) {
            is ConstraintViolationException -> handleConstraintViolationException(e)
            is Exception -> toGraphQLError(e)
            else -> super.resolveToSingleError(e, env)
        }
    }

    private fun toGraphQLError(e: Throwable): GraphQLError? {
        log.warn("Exception while handling request: ${e.message}", e)
        return GraphqlErrorBuilder.newError().message(e.message).errorType(ErrorType.INTERNAL_ERROR).build()
    }

    private fun handleConstraintViolationException(e: ConstraintViolationException): GraphQLError? {
        val errorMessages = mutableSetOf<String>()
        e.constraintViolations.forEach { errorMessages.add("Field '${it.propertyPath}' ${it.message}, but value was [${it.invalidValue}]") }
        val message = errorMessages.joinToString("\n")
        log.warn("Exception while handling request: $message", e)
        return GraphqlErrorBuilder.newError().message(message).errorType(ErrorType.INTERNAL_ERROR).build()
    }
}