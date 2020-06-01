package com.example;

import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.ApolloClient;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;
import okhttp3.OkHttpClient;
import org.jetbrains.annotations.NotNull;


public class Launcher {

    private final ApolloClient apolloClient;

    public Launcher(ApolloClient apolloClient) {
        this.apolloClient = apolloClient;
    }

    public void  getBook(String bookId) {
        apolloClient.query(
                BookQuery.builder()
                .id(bookId)
                .build()
        ).enqueue(new ApolloCall.Callback<>() {
            @Override
            public void onResponse(@NotNull Response<BookQuery.Data> response) {
                System.out.println(response.getData().bookById().fragments().bookFragment.name());
            }

            @Override
            public void onFailure(@NotNull ApolloException e) {
                e.printStackTrace();
            }
        });
    }

    public static void main(String[] args)  {
        OkHttpClient okHttpClient =  new OkHttpClient.Builder().build();
        ApolloClient apolloClient = ApolloClient.builder()
                .serverUrl("http://localhost:8080/graphql/")
                .okHttpClient(okHttpClient)
                .build();
        Launcher launcher = new Launcher(apolloClient);
        launcher.getBook("book-1");
    }
}
