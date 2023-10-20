package com.moeen.elk.service;

import com.moeen.elk.dto.ProductDto;
import com.moeen.elk.entity.Product;
import com.moeen.elk.entity.esEntity.EsProduct;
import com.moeen.elk.repository.JpaProductRepository;
import com.moeen.elk.repository.esRepository.ElasticsearchProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private JpaProductRepository productRepository;
    private ElasticsearchProductRepository productEsRepository;

    public ProductDto saveProduct(ProductDto dto) {
        Product product = getProductFromDto(dto);
        EsProduct esProduct = getEsProduct(product);

        productRepository.save(product);
        productEsRepository.save(esProduct);

        return ProductDto.form(product);
    }

    private EsProduct getEsProduct(Product product) {
        EsProduct esProduct = new EsProduct();
        esProduct.setName(product.getName());
        esProduct.setPrice(product.getPrice());
        esProduct.setType(product.getType());
        esProduct.setQty(product.getQty());

        return esProduct;
    }

    private Product getProductFromDto(ProductDto dto) {
        Product product = new Product();
        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        product.setType(dto.getType());
        product.setQty(dto.getQty());
        return product;
    }

    public Product getProductById(Long id) {
        Optional<Product> getProductById = productRepository.findById(id);
        return getProductById.orElse(null);
    }

    public List<ProductDto> getProductList() {
        List<Product> productList = productRepository.findAll();

        return productList.stream().map(ProductDto::form).toList();
    }
}
