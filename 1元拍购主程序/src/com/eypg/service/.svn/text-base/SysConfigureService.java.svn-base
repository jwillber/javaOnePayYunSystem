package com.eypg.service;

import java.util.List;

import com.eypg.dao.Pagination;
import com.eypg.pojo.IndexImg;
import com.eypg.pojo.Suggestion;
import com.eypg.pojo.SysConfigure;

public interface SysConfigureService extends TService<SysConfigure, Integer> {
	
	public List initializationIndexImgAll();
	
	public List IndexImgAll();
	
	public void addIndexImg(IndexImg indexImg);
	
	public IndexImg findByIndexImgId(String id);
	
	public void delIndexImg(Integer id);
	
	public void doSuggestion(Suggestion suggestion);
	
	public Pagination suggestionList(int pageNo, int pageSize) ;

}
