# Steps to reproduce

1. Clone the [graphql-java tutorial](https://github.com/graphql-java/tutorials) and start the service `./gradlew bootRun`
2. On clone of this repo run: `/gradlew downloadApolloSchema -Pcom.apollographql.apollo.endpoint=http://localhost:8080/graphql -Pcom.apollographql.apollo.schema=src/main/graphql/com/example/schema.json` to download the schema
3. Run `./gradlew generateApolloSources` this fails with "Can't query \`Book\` on type \`Query\`"