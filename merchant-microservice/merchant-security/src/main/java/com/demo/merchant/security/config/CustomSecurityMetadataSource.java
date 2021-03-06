package com.demo.merchant.security.config;

import com.demo.merchant.client.service.RoleFuture;
import com.demo.merchant.object.ResourceQo;
import com.demo.merchant.object.RoleQo;
import com.demo.merchant.security.util.CommonUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class CustomSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
    private static final Logger logger = LoggerFactory.getLogger(CustomSecurityMetadataSource.class);
    public static final String MERCHANT_CENTER_ROLES_ALL = "MERCHANT_CENTER_ROLES_ALL_";
    private PathMatcher pathMatcher = new AntPathMatcher();
    private RoleFuture roleFuture;
    private CacheComponent cacheComponent;

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    public CustomSecurityMetadataSource(RoleFuture roleFuture, CacheComponent cacheComponent) {
        super();
        this.roleFuture = roleFuture;
        this.cacheComponent = cacheComponent;
    }

    private CompletableFuture<List<RoleQo>> loadResourceWithRoles() {
        return roleFuture.findList().thenApply(roles -> {
            List<RoleQo> list = new Gson().fromJson(roles, new TypeToken<List<RoleQo>>() {}.getType());
            if(list != null)
                cacheComponent.put(MERCHANT_CENTER_ROLES_ALL, "LIST", list, 15);
            return list;
        });
    }

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object)
            throws IllegalArgumentException {
        String url = ((FilterInvocation) object).getRequestUrl();

        //logger.info("???????????????" + url);
        //??????????????????????????????
        Object objects = cacheComponent.get(MERCHANT_CENTER_ROLES_ALL, "LIST");
        List<RoleQo> roleQoList = null;
        if (CommonUtils.isNull(objects)) {
            roleQoList = loadResourceWithRoles().join();//???????????????????????????API?????????????????????
        } else{
            roleQoList = ( List<RoleQo>)objects;
        }

        Collection<ConfigAttribute> roles = new ArrayList<>();//????????????????????????

        //?????????????????????????????????????????????????????????????????????????????????????????????????????????????????????
        if(roleQoList != null && roleQoList.size() > 0) {
            for (RoleQo roleQo : roleQoList) {//??????????????????
                List<ResourceQo> resourceQos = roleQo.getResources();
                if(resourceQos != null && resourceQos.size() > 0) {
                    for (ResourceQo resourceQo : resourceQos) {//??????????????????
                        if (resourceQo.getUrl() != null && pathMatcher.match(resourceQo.getUrl()+"/**", url)) {
                            ConfigAttribute attribute = new SecurityConfig(roleQo.getName());
                            roles.add(attribute);
                            //logger.info("????????????????????????===???????????????{}??????????????????{}===", resourceVo.getUrl(), roleVo.getName());
                            break;
                        }
                    }
                }
            }
        }
        return roles;
    }

    public boolean supports(Class<?> clazz) {
        return true;
    }
}
