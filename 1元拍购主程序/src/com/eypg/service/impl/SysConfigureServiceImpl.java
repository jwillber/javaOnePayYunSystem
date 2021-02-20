package com.eypg.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eypg.dao.BaseDAO;
import com.eypg.dao.Pagination;
import com.eypg.pojo.Friends;
import com.eypg.pojo.IndexImg;
import com.eypg.pojo.News;
import com.eypg.pojo.Suggestion;
import com.eypg.pojo.SysConfigure;
import com.eypg.pojo.User;
import com.eypg.service.SysConfigureService;

@Service
public class SysConfigureServiceImpl implements SysConfigureService {
	@Autowired
	BaseDAO baseDao;

	public void add(SysConfigure t) {
		this.baseDao.saveOrUpdate(t);
	}

	public void delete(Integer id) {
		// TODO Auto-generated method stub

	}

	public SysConfigure findById(String id) {
		StringBuffer hql = new StringBuffer("from SysConfigure s where 1=1");
		if(StringUtils.isNotBlank(id)){
			hql.append(" and s.id='"+id+"'");
		}
		return (SysConfigure) baseDao.loadObject(hql.toString());
	}

	public List<SysConfigure> query(String hql) {
		// TODO Auto-generated method stub
		return null;
	}

	public void update(String hql) {
		// TODO Auto-generated method stub

	}
	
	public List initializationIndexImgAll(){
		StringBuffer sql = new StringBuffer("from IndexImg ig where 1=1 and ig.status = 0");
		List list = baseDao.find(sql.toString());
		return list;
	}
	
	public List IndexImgAll(){
		StringBuffer sql = new StringBuffer("from IndexImg ig where 1=1");
		List list = baseDao.find(sql.toString());
		return list;
	}
	
	public void addIndexImg(IndexImg indexImg){
		this.baseDao.saveOrUpdate(indexImg);
	}
	
	public IndexImg findByIndexImgId(String id){
		StringBuffer hql = new StringBuffer("from IndexImg ig where 1=1");
		if(StringUtils.isNotBlank(id)){
			hql.append(" and ig.id='"+id+"'");
		}
		return (IndexImg) baseDao.loadObject(hql.toString());
	}
	
	public void delIndexImg(Integer id){
		this.baseDao.delById(IndexImg.class, id);
	}

	public void doSuggestion(Suggestion suggestion) {
		this.baseDao.saveOrUpdate(suggestion);
	}

	public Pagination suggestionList(int pageNo, int pageSize) {
		StringBuffer hql = new StringBuffer("select * from suggestion sn where 1=1");
		StringBuffer sql = new StringBuffer("select count(*) from suggestion where 1=1");
		Pagination page = new Pagination();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		Map<String, Class> entityMap = new HashMap<String,Class>();
		entityMap.put("sn", Suggestion.class);
		List list = baseDao.sqlQuery(hql, entityMap, page);
		int resultCount = baseDao.getAllNum(sql);
		page.setList(list);
		page.setResultCount(resultCount);
		return page;
	}

}
