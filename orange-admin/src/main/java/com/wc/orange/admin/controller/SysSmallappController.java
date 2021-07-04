package com.wc.orange.admin.controller;

import com.wc.orange.admin.model.SysSmallapp;
import com.wc.orange.admin.service.SysSmallappService;
import com.wc.orange.core.http.HttpResult;
import com.wc.orange.core.page.PageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 小程序控制器
 * @author qizl
 * @date Jan 25,2021
 */
@RestController
@RequestMapping("smallapp")
public class SysSmallappController {

  @Autowired
  private SysSmallappService sysSmallappService;

  @PreAuthorize("hasAuthority('sys:smallapp:add') AND hasAuthority('sys:smallapp:edit')")
  @PostMapping(value="/save")
  public HttpResult save(@RequestBody SysSmallapp recode){
    List<SysSmallapp> smallapps=sysSmallappService.findByAppid(recode.getAppId());
    if(recode.getId()==null){
      //新增小程序
      if(smallapps.size()!=0){
        return HttpResult.error("APPID已存在!");
      }
    }else{
      //修改小程序
      if(smallapps.size()!=0){
        if(recode.getId()!=smallapps.get(0).getId()){
          return HttpResult.error("APPID已存在!");
        }
      }
    }
    return HttpResult.ok(sysSmallappService.save(recode));
  }

  @PreAuthorize("hasAuthority('sys:user:delete')")
  @PostMapping(value="/delete")
  public HttpResult delete(@RequestBody List<SysSmallapp> records) {
    return HttpResult.ok(sysSmallappService.delete(records));
  }

  @PreAuthorize("hasAuthority('sys:user:view')")
  @PostMapping(value="/findPage")
  public HttpResult findPage(@RequestBody PageRequest pageRequest) {
    return HttpResult.ok(sysSmallappService.findPage(pageRequest));
  }

  @PreAuthorize("hasAuthority('sys:user:view')")
  @GetMapping(value="/findByUsername")
  public HttpResult findByUsername(@RequestParam String userName) {
    return HttpResult.ok(sysSmallappService.findByUsername(userName));
  }
}
