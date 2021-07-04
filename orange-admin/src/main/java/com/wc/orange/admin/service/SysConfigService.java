package com.wc.orange.admin.service;

import com.wc.orange.admin.model.SysConfig;
import com.wc.orange.core.service.CurdService;

import java.util.List;

/**
 * 系统配置管理
 */
public interface SysConfigService extends CurdService<SysConfig> {

	/**
	 * 根据名称查询
	 * @param label
	 * @return
	 */
	List<SysConfig> findBylabel(String label);
}
