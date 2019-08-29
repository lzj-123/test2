package com.sc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sc.bean.SysUser;
import com.sc.bean.SysUserExample;
import com.sc.bean.SysUserExample.Criteria;
import com.sc.bean.Users;
import com.sc.mapper.SysUserMapper;
import com.sc.mapper.UsersMapper;
import com.sc.service.SysUserService;

@Service
public class SysUserServiceImpl implements SysUserService {
	@Autowired
	SysUserMapper sysUserMapper;

	@Override
	public SysUser login(String usercode) {
		if(usercode != ""){
			SysUserExample e = new SysUserExample();
			Criteria qbc = e.createCriteria();
			qbc.andUsercodeEqualTo(usercode);
			List<SysUser> list = this.sysUserMapper.selectByExample(e);
			if(list != null && list.size() > 0){
				return list.get(0);
			}
		}
		return null;
	}

}
