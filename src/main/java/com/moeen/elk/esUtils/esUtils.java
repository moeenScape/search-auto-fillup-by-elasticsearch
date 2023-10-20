package com.moeen.elk.esUtils;

import co.elastic.clients.elasticsearch._types.query_dsl.MatchQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import lombok.val;

import java.util.function.Supplier;

public class esUtils {

    public static Supplier<Query> createSupplierQuery(String partialProductName) {
        Supplier<Query> supplier = () -> Query.of(q -> q.match(createAutoSuggestionMatchQuery(partialProductName)));
        return supplier;
    }

    public static MatchQuery createAutoSuggestionMatchQuery(String partialProductName) {
        val autoSearch = new MatchQuery.Builder();

        return autoSearch.field("name").query(partialProductName).analyzer("standard").build();
    }
}
