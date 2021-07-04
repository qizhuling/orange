package com.wc.orange.admin.service.impl;

import com.wc.orange.admin.dao.SysUserDeptMapper;
import com.wc.orange.admin.model.*;
import com.wc.orange.admin.service.SysUserDeptService;
import com.wc.orange.core.page.MybatisPageHelper;
import com.wc.orange.core.page.PageRequest;
import com.wc.orange.core.page.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SysUserDeptServiceImpl implements SysUserDeptService {

	@Autowired
	private SysUserDeptMapper sysUserDeptMapper;
	
	/**
	 * 加载用户机构
	 * @param userId
	 */
  @Override
  public List<SysUserDept> findUserDepts(Long userId) {
    return sysUserDeptMapper.findUserDepts(userId);
  }
  /**
   * 保存用户机构
   * @param
   */
  @Override
  public int save(SysUserDept record) {
    if(record.getId() == null || record.getId() == 0) {
      return sysUserDeptMapper.insertSelective(record);
    }
    return sysUserDeptMapper.updateByPrimaryKeySelective(record);
  }
  @Override
  public int delete(List<SysUserDept> records) {
    for(SysUserDept record:records) {
      delete(record);
    }
    return 1;
  }
  @Override
  public int delete(SysUserDept record) {
    return sysUserDeptMapper.deleteByPrimaryKey(record.getId());
  }

  @Transactional
  @Override
  public int saveUserDepts(List<SysUserDept> records,Long userId) {
    if(records == null || records.isEmpty()) {
      return 1;
    }
    sysUserDeptMapper.deleteByUserId(userId);
    for(SysUserDept record:records) {
      sysUserDeptMapper.insertSelective(record);
    }
    return 1;
  }
  @Override
  public SysUserDept findById(Long id) {
    return sysUserDeptMapper.selectByPrimaryKey(id);
  }

  @Override
  public PageResult findPage(PageRequest pageRequest) {
    PageResult pageResult = null;
    pageResult = MybatisPageHelper.findPage(pageRequest, sysUserDeptMapper);
    return pageResult;
  }
}
