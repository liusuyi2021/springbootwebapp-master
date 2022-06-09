package org.synjones.springframework.services;

import org.synjones.springframework.dao.ProductDao;
import org.synjones.springframework.domain.Product;
import org.synjones.springframework.repositories.ProductRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
 

@Service
public class ProductServiceImpl implements ProductService { 

	@Autowired
	private ProductDao dao;

    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    } 
    // 分页排序
       @Override
       @Transactional(readOnly = true)  
       public Page<Product> getPageSortTTT(String name,Integer pageNum, Integer pageLimit) {

    	    Sort.Direction sort =  Sort.Direction.ASC;
    	    Pageable pageable = PageRequest.of(1, 10, sort, "number");
    	    Specification<Product> query = new Specification<Product>() {
    	        @Override
    	        public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
    	            List<Product> predicates = new ArrayList<>();
    	            if(name!=null){
    	                predicates.add((Product) criteriaBuilder.equal(root.get("name"),name)); 
    	            } 
    	            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
    	        }
 
    	    };
    	    
    	    
    	   /*
    	   List<Product> products=productRepository.findAll(query,pageable).getContent();
           return productRepository.findAll( name,pageable);*/
			return null;
       } 
        
	@Override
	public Map<String,Object> findAllByname(String name, String wendu,Integer pageNum, Integer pageLimit) {
		 
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("name", name);
		map.put("size", pageLimit);
		map.put("offset", (pageNum-1) * pageLimit);
		map.put("temp", Float.valueOf(StringUtils.trimToEmpty(wendu)));  
		Map<String,Object> map_r =  new HashMap<String,Object>();
		map_r.put("list", dao.findAllGW(map));
		map_r.put("totalRecord", dao.findAllGW_count(map));
		return map_r;
	}

	@Override
	public Iterable<Product> listAllProducts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Product getProductById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}
 

	@Override
	public Page<Product> getPageSort(String name, String wendu, Integer pageNum, Integer pageLimit) {
		// TODO Auto-generated method stub
		return null;
	}
}
