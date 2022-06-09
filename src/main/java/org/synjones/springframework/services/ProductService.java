package org.synjones.springframework.services;


import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.synjones.springframework.domain.Product;

public interface ProductService {
    Iterable<Product> listAllProducts();

    Product getProductById(Integer id);

    Product saveProduct(Product product);

    // 排序分页
    Page<Product> getPageSort(String name, String wendu,Integer pageNum, Integer pageLimit);
    Map<String, Object> findAllByname(String name, String wendu,Integer pageNum, Integer pageLimit);
	Page<Product> getPageSortTTT(String name, Integer pageNum, Integer pageLimit);
    
}
