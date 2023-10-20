package com.moeen.elk.repository.esRepository;

import com.moeen.elk.entity.esEntity.EsProduct;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@EnableElasticsearchRepositories
public interface ProductEsRepository extends ElasticsearchRepository<EsProduct, Long> {
}
