package com.eypg.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.eypg.dao.BaseDAO;
import com.eypg.pojo.Producttype;
import com.eypg.service.ProducttypeService;

@Service
public class ProducttypeServiceImpl implements ProducttypeService {
	@Autowired
	@Qualifier("baseDao")
	BaseDAO baseDao;

	public void add(Producttype t) {
		baseDao.saveOrUpdate(t);

	}

	public void delete(Integer id) {
		baseDao.delById(Producttype.class, id);

	}

	public Producttype findById(String id) {
		StringBuffer hql = new StringBuffer("from Producttype p where 1=1 and p.attribute70='type'");
		if(StringUtils.isNotBlank(id)){
			hql.append(" and p.typeId='"+id+"'");
		}
		return (Producttype) baseDao.loadObject(hql.toString());
	}

	public List<Producttype> query(String hql) {
		// TODO Auto-generated method stub
		return null;
	}

	public void update(String hql) {
		// TODO Auto-generated method stub

	}

	public List<Producttype> queryAllProductType() {
		String hql = " from Producttype p where 1=1 and p.attribute70='type'";
		return (List<Producttype>)baseDao.query(hql);
	}

	public List<Producttype> listByProductList() {
		String hql = " from Producttype p where 1=1 and p.ftypeId = '1000' and p.attribute70='type'";
		return (List<Producttype>)baseDao.query(hql);
	}
	
	public List<Producttype> listByProductListBybrand(String id){
		String hql = " from Producttype p where 1=1 and p.ftypeId = '"+id+"' and p.attribute70='type'";
		return (List<Producttype>)baseDao.query(hql);
	}

	public List<Producttype> listByBrand(String id) {
		StringBuffer hql = new StringBuffer("from Producttype p where 1=1 and p.attribute70='brand'");
		if(StringUtils.isNotBlank(id)){
			hql.append(" and p.ftypeId='"+id+"' ");
		}
		return (List<Producttype>)baseDao.query(hql.toString());
	}

	public Producttype findBrandById(String id) {
		StringBuffer hql = new StringBuffer("from Producttype p where 1=1 and p.attribute70='brand'");
		if(StringUtils.isNotBlank(id)){
			hql.append(" and p.typeId='"+id+"'");
		}
		return (Producttype) baseDao.loadObject(hql.toString());
	}

	public Producttype findBrandByName(String name) {
		StringBuffer hql = new StringBuffer("from Producttype p where 1=1 and p.typeName='"+name+"' and p.attribute70='brand'");
		return (Producttype) baseDao.loadObject(hql.toString());
	}

	public Producttype findTypeByName(String name) {
		StringBuffer hql = new StringBuffer("from Producttype p where 1=1 and p.typeName='"+name+"' and p.attribute70='type'");
		return (Producttype) baseDao.loadObject(hql.toString());
	}

}
