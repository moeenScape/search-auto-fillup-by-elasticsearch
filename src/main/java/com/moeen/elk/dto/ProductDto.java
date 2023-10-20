package com.moeen.elk.dto;

import com.moeen.elk.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private Long id;
    private String name;
    private int price;
    private String type;
    private int qty;

    public static ProductDto form(Product product) {
        ProductDto dto = new ProductDto();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setPrice(product.getPrice());
        dto.setType(product.getType());
        dto.setQty(product.getQty());
        return dto;
    }
}
