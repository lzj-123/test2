package com.sc.mapper;

import com.sc.bean.Users;
import com.sc.bean.UsersExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UsersMapper {
    int countByExample(UsersExample example);

    int deleteByExample(UsersExample example);
    //删除方法
    int deleteByPrimaryKey(Integer uid);
    //添加方法
    int insert(Users record);
    
    int insertSelective(Users record);
    //查询集合
    List<Users> selectByExample(UsersExample example);
    //通过主键查询对象
    Users selectByPrimaryKey(Integer uid);

    int updateByExampleSelective(@Param("record") Users record, @Param("example") UsersExample example);

    int updateByExample(@Param("record") Users record, @Param("example") UsersExample example);

    int updateByPrimaryKeySelective(Users record);
    //修改方法
    int updateByPrimaryKey(Users record);
}