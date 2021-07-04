package com.wc.orange.admin.service;

import com.wc.orange.admin.model.SysUserDept;
import com.wc.orange.core.service.CurdService;

import java.util.List;

/**
 * 用户机构管理
 * @author Louis
 * @date Jan 13, 2019
 */
public interface SysUserDeptService extends CurdService<SysUserDept> {

	/**
	 * 查找用户的机构集合
	 * @param userid
	 * @return
	 */
	List<SysUserDept> findUserDepts(Long userId);

  int saveUserDepts(List<SysUserDept> records, Long userId);

}
