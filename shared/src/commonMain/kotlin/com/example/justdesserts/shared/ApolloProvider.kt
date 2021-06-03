package com.example.justdesserts.shared

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.network.http.ApolloHttpNetworkTransport

class ApolloProvider {
    internal var apolloClient: ApolloClient = ApolloClient(
        networkTransport = ApolloHttpNetworkTransport(
            serverUrl = "https://desserts-ktor-server.herokuapp.com",
            headers = mapOf(
                "Accept" to "application/json",
                "Content-type" to "application/json"
            )

        )

    )
}