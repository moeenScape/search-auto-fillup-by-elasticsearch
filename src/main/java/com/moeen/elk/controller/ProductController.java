package com.moeen.elk.controller;


import com.moeen.elk.dto.ProductDto;
import com.moeen.elk.entity.Product;
import com.moeen.elk.service.EsService;
import com.moeen.elk.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    private final EsService esService;

    @PostMapping("/save")
    public ResponseEntity<?> saveProduct(@RequestBody ProductDto dto) {
        dto = productService.saveProduct(dto);

        return ResponseEntity.ok(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Long id) {
        Product product = productService.getProductById(id);

        return ResponseEntity.ok(ProductDto.form(product));
    }

    @GetMapping()
    public ResponseEntity<?> getProductList() {
        List<ProductDto> productList = productService.getProductList();

        return ResponseEntity.ok(productList);
    }

    @PostMapping("/all")
    public ResponseEntity<?> saveList(@RequestBody List<ProductDto> productDto) {
        List<ProductDto> productDtoList = productService.saveProductList(productDto);

        return ResponseEntity.ok(productDtoList);
    }

    @GetMapping("/auto/search/{partialProductName}")
    public ResponseEntity<?> getProductPartialSearch(@PathVariable String partialProductName) throws IOException {
        List<String> searchResult = esService.getResultFromSearch(partialProductName);

        return ResponseEntity.ok(searchResult);
    }
}
