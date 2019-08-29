package com.sc.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sc.bean.SysPermission;

public interface SysPermissionService {
	List<SysPermission> selectByUid(@Param("uid") String uid);
	
	List<SysPermission> selectAllSysPermission();
	
}
