package com.wc.orange.admin.dao;

import com.wc.orange.admin.model.SysSmallapp;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysSmallappMapper {
  SysSmallapp selectByPrimaryKey(Long id);

    int deleteByPrimaryKey(Long id);

    int insert(SysSmallapp record);

    int insertSelective(SysSmallapp record);

    int updateByPrimaryKeySelective(SysSmallapp record);

    List<SysSmallapp> findPage();

  List<SysSmallapp> findByAppid(@Param(value = "appId") String appId);

	  List<SysSmallapp> findPageByAppname(@Param(value = "appName") String appName);

	  List<SysSmallapp> findByUserid(@Param(value = "userId") Long userId);

    List<SysSmallapp> findAll();

  List<SysSmallapp> findPageByAppnameAndAppid(@Param(value = "appId") String appId, @Param(value = "appName") String appName);
}
