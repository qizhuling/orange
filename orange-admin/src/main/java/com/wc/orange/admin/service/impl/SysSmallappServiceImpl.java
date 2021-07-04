package com.wc.orange.admin.service.impl;

import com.wc.orange.admin.constant.SysConstants;
import com.wc.orange.admin.dao.SysSmallappMapper;
import com.wc.orange.admin.model.SysSmallapp;
import com.wc.orange.admin.model.SysUser;
import com.wc.orange.admin.service.SysSmallappService;
import com.wc.orange.core.page.MybatisPageHelper;
import com.wc.orange.core.page.PageRequest;
import com.wc.orange.core.page.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SysSmallappServiceImpl implements SysSmallappService {

	@Autowired
	private SysSmallappMapper sysSmallappMapper;
	@Autowired
  private SysUserServiceImpl sysUserServiceImpl;

	@Transactional
	@Override
	public int save(SysSmallapp record) {
		Long id = null;
		if(record.getId() == null || record.getId() == 0) {
			// 新增用户
			sysSmallappMapper.insertSelective(record);
			id = record.getId();
		} else {
			// 更新用户信息
      sysSmallappMapper.updateByPrimaryKeySelective(record);
		}
		return 1;
	}

	@Override
	public int delete(SysSmallapp record) {
		return sysSmallappMapper.deleteByPrimaryKey(record.getId());
	}

	@Override
	public int delete(List<SysSmallapp> records) {
		for(SysSmallapp record:records) {
			delete(record);
		}
		return 1;
	}

	@Override
	public SysSmallapp findById(Long id) { return sysSmallappMapper.selectByPrimaryKey(id);
	}

  @Override
  public List<SysSmallapp> findByAppid(String appId) { return sysSmallappMapper.findByAppid(appId);
  }
	@Override
	public PageResult findPage(PageRequest pageRequest) {
		PageResult pageResult = null;
		String appId = pageRequest.getParamValue("appId");
		String appName = pageRequest.getParamValue("appName");
		if(appId != null&&appId.length()!=0) {
			if(appName != null&&appName.length()!=0) {
				pageResult = MybatisPageHelper.findPage(pageRequest, sysSmallappMapper, "findPageByAppnameAndAppid", appId, appName);
			} else {
				pageResult = MybatisPageHelper.findPage(pageRequest, sysSmallappMapper, "findByAppid", appId);
			}
		} else {
		  if(appName != null&&appName.length()!=0){
        pageResult = MybatisPageHelper.findPage(pageRequest, sysSmallappMapper, "findPageByAppname", appName);
      }else{
        pageResult = MybatisPageHelper.findPage(pageRequest, sysSmallappMapper);
      }
		}
		return pageResult;
	}
  @Override
    public List<SysSmallapp> findByUsername(String userName){
    SysUser sysUser = sysUserServiceImpl.findByName(userName);
    if(SysConstants.ADMIN.equalsIgnoreCase(sysUser.getName())) {
      // 如果是超级管理员，返回全部
      List<SysSmallapp> smallapps=sysSmallappMapper.findAll();
      System.out.println(smallapps.size());
      return smallapps;
    }
	  return sysSmallappMapper.findByUserid(sysUser.getId());
  }

	/*
	@Override
	public File createUserExcelFile(PageRequest pageRequest) {
		PageResult pageResult = findPage(pageRequest);
		return createUserExcelFile(pageResult.getContent());
	}
	
	public static File createUserExcelFile(List<?> records) {
		if (records == null) {
			records = new ArrayList<>();
		}
		Workbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet();
		Row row0 = sheet.createRow(0);
		int columnIndex = 0;
		row0.createCell(columnIndex).setCellValue("No");
		row0.createCell(++columnIndex).setCellValue("ID");
		row0.createCell(++columnIndex).setCellValue("用户名");
		row0.createCell(++columnIndex).setCellValue("昵称");
		row0.createCell(++columnIndex).setCellValue("机构");
		row0.createCell(++columnIndex).setCellValue("角色");
		row0.createCell(++columnIndex).setCellValue("邮箱");
		row0.createCell(++columnIndex).setCellValue("手机号");
		row0.createCell(++columnIndex).setCellValue("状态");
		row0.createCell(++columnIndex).setCellValue("头像");
		row0.createCell(++columnIndex).setCellValue("创建人");
		row0.createCell(++columnIndex).setCellValue("创建时间");
		row0.createCell(++columnIndex).setCellValue("最后更新人");
		row0.createCell(++columnIndex).setCellValue("最后更新时间");
		for (int i = 0; i < records.size(); i++) {
			SysSmallapp user = (SysSmallapp) records.get(i);
			Row row = sheet.createRow(i + 1);
			for (int j = 0; j < columnIndex + 1; j++) {
				row.createCell(j);
			}
			columnIndex = 0;
			row.getCell(columnIndex).setCellValue(i + 1);
			row.getCell(++columnIndex).setCellValue(user.getId());
			row.getCell(++columnIndex).setCellValue(user.getName());
			row.getCell(++columnIndex).setCellValue(user.getNickName());
			row.getCell(++columnIndex).setCellValue(user.getDeptName());
			row.getCell(++columnIndex).setCellValue(user.getRoleNames());
			row.getCell(++columnIndex).setCellValue(user.getEmail());
			row.getCell(++columnIndex).setCellValue(user.getStatus());
			row.getCell(++columnIndex).setCellValue(user.getAvatar());
			row.getCell(++columnIndex).setCellValue(user.getCreateBy());
			row.getCell(++columnIndex).setCellValue(DateTimeUtils.getDateTime(user.getCreateTime()));
			row.getCell(++columnIndex).setCellValue(user.getLastUpdateBy());
			row.getCell(++columnIndex).setCellValue(DateTimeUtils.getDateTime(user.getLastUpdateTime()));
		}
		return PoiUtils.createExcelFile(workbook, "download_user");
	}
*/
}
