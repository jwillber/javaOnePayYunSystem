package com.eypg.util;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

import com.eypg.pojo.IndexImg;
import com.eypg.pojo.SysConfigure;
import com.eypg.service.SysConfigureService;

public class ApplicationListenerImpl implements ApplicationListener {
	
	@Autowired
	private SysConfigureService sysConfigureService;
	public static SysConfigure sysConfigureJson;
	public static boolean sysConfigureAuth = true;
	public static List<IndexImg> indexImgAll;
	
	public SysConfigureService getSysConfigureService() {
		return sysConfigureService;
	}

	public void setSysConfigureService(SysConfigureService sysConfigureService) {
		this.sysConfigureService = sysConfigureService;
	}
	public void onApplicationEvent(ApplicationEvent arg0) {
		if (arg0 instanceof ApplicationEvent) {
			sysConfigureJson = sysConfigureService.findById("1");
			indexImgAll = sysConfigureService.initializationIndexImgAll();
			System.err.println(sysConfigureJson.getSaitName());
		}
	}

}
