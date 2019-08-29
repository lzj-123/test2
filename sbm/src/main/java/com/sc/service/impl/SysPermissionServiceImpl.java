package com.sc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sc.bean.SysPermission;
import com.sc.mapper.SysPermissionMapper;
import com.sc.service.SysPermissionService;

@Service
public class SysPermissionServiceImpl implements SysPermissionService {
	@Autowired
	SysPermissionMapper spm;
	
	
	@Override
	public List<SysPermission> selectByUid(String uid) {
		// TODO Auto-generated method stub
		return spm.selectByUid(uid);
	}


	@Override
	public List<SysPermission> selectAllSysPermission() {
		// TODO Auto-generated method stub
		return spm.selectByExample(null);
	}
	
}
