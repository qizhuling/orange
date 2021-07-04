package com.wc.orange.admin.service;

import com.wc.orange.admin.model.SysSmallapp;
import com.wc.orange.core.page.PageRequest;
import com.wc.orange.core.page.PageResult;
import com.wc.orange.core.service.CurdService;

import java.util.List;

/**
 * 用户管理
 * @author Louis
 * @date Jan 13, 2019
 */
public interface SysSmallappService extends CurdService<SysSmallapp> {
  int save(SysSmallapp record);
  int delete(SysSmallapp record);
  int delete(List<SysSmallapp> records);
  SysSmallapp findById(Long id);
  List<SysSmallapp> findByAppid(String appId);
  PageResult findPage(PageRequest pageRequest);
  List<SysSmallapp> findByUsername(String userName);
}
