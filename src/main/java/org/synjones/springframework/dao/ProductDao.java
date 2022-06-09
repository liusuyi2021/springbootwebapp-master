package org.synjones.springframework.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import org.synjones.springframework.domain.Product;
 

@Mapper
public interface ProductDao
{
	public boolean addDept(Product dept);

	public Product findById(Long id);

	public List<Product> findAll();
	
	public List<Product> findAllGW(Map<String, Object> params);
	
	public Long findAllGW_count(Map<String, Object> params);

}
