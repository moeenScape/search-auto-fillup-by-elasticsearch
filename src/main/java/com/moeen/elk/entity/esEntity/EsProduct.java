package com.moeen.elk.entity.esEntity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;

@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "product")
@JsonIgnoreProperties(ignoreUnknown = true)
public class EsProduct {
    private Long id;
    @Getter
    private String name;
    private int price;
    private String type;
    private int qty;

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
}
