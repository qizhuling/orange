package com.wc.orange.admin.service;

import com.wc.orange.admin.model.SysDict;
import com.wc.orange.core.service.CurdService;

import java.util.List;

/**
 * 字典管理
 */
public interface SysDictService extends CurdService<SysDict> {

	/**
	 * 根据名称查询
	 * @param label
	 * @return
	 */
	List<SysDict> findBylabel(String label);
}
