package com.moeen.elk.service;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import com.moeen.elk.entity.esEntity.EsProduct;
import com.moeen.elk.esUtils.esUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

@Service
@RequiredArgsConstructor
public class EsService {

    private final ElasticsearchClient elasticsearchClient;

    public SearchResponse<EsProduct> autoSearchProduct(String partialProductName) throws IOException {
        Supplier<Query> supplier = esUtils.createSupplierQuery(partialProductName);

        return elasticsearchClient.search(s -> s.index("product").query(supplier.get()), EsProduct.class);
    }

    public List<String> getResultFromSearch(String partialProductName) throws IOException {
        SearchResponse<EsProduct> searchResponse = autoSearchProduct(partialProductName);
        List<Hit<EsProduct>> hits = searchResponse.hits().hits();
        List<EsProduct> autoSearch = new ArrayList<>();
        for (Hit<EsProduct> hit : hits) {
            autoSearch.add(hit.source());
        }

        return autoSearch.stream().map(EsProduct::getName).toList();
    }
}
