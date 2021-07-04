package com.wc.orange.admin.dao;

import com.wc.orange.admin.model.SysUserDept;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysUserDeptMapper {
    int deleteByPrimaryKey(Long id);

    int deleteByUserId(Long userId);

    int insert(SysUserDept record);

    int insertSelective(SysUserDept record);

    SysUserDept selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysUserDept record);

    int updateByPrimaryKey(SysUserDept record);

    List<SysUserDept> findUserDepts(@Param(value = "userId") Long userId);
}
