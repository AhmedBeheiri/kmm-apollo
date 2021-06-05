package com.example.justdesserts.shared

import android.util.Log
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.Logger
import com.apollographql.apollo.api.internal.ApolloLogger
import com.apollographql.apollo.interceptor.BearerTokenInterceptor
import com.apollographql.apollo.interceptor.TokenProvider
import com.apollographql.apollo.network.http.ApolloHttpNetworkTransport
import com.example.justdesserts.shared.cache.Database
import com.example.justdesserts.shared.cache.DatabaseDriverFactory
import com.example.justdesserts.shared.logger.LoggingInterceptor
import com.example.justdesserts.shared.logger.MyLogger

class ApolloProvider(databaseDriverFactory: DatabaseDriverFactory,myLogger: MyLogger):TokenProvider{
    internal  val database = Database(databaseDriverFactory)
    internal var apolloClient: ApolloClient = ApolloClient(
        networkTransport = ApolloHttpNetworkTransport(
            serverUrl = "https://desserts-ktor-server.herokuapp.com",
            headers = mapOf(
                "Accept" to "application/json",
                "Content-type" to "application/json"
            ),

        ),
        interceptors = listOf(BearerTokenInterceptor(this), LoggingInterceptor(myLogger))

    )

    override suspend fun currentToken(): String = database.getUserState()?.token ?: ""

    override suspend fun refreshToken(previousToken: String): String = ""

}