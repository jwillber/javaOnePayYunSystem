package com.eypg.service;

import java.util.List;

import com.eypg.pojo.Producttype;

public interface ProducttypeService extends TService<Producttype, Integer> {
	
	public List<Producttype> queryAllProductType();
	
	public List<Producttype> listByProductList();
	
	public List<Producttype> listByProductListBybrand(String id); 
	
	public List<Producttype> listByBrand(String id);
	
	public Producttype findBrandById(String id);
	
	public Producttype findTypeByName(String name);
	
	public Producttype findBrandByName(String name);

}
