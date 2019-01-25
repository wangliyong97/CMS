package com.cms.shiro;

import com.cms.pojo.BackendUser;
import com.cms.service.IUserManagerService;
import com.cms.util.CipherUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

/**
 * Created by wangliyong on 2019/1/9.
 */
@Slf4j
public class ShiroDbRealm extends AuthorizingRealm {
    private static final String ALGORITHM = "MD5";

    @Autowired
    private IUserManagerService iUserManagerService;

    public ShiroDbRealm() {
        super();
    }

    /**
     * 验证登陆
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        BackendUser backendUser = iUserManagerService.findUserByLoginName(token.getUsername());
        CipherUtil cipher = new CipherUtil();    //MD5加密
        if (backendUser != null) {
            return new SimpleAuthenticationInfo(backendUser.getUsername(), cipher.generatePassword(backendUser.getPassword()), getName());
        } else {
            throw new AuthenticationException();
        }
    }

    /**
     * 清除所有用户授权信息缓存.
     */
    public void clearCachedAuthorizationInfo(String principal) {
        SimplePrincipalCollection principals = new SimplePrincipalCollection(
                principal, getName());
        clearCachedAuthorizationInfo(principals);
    }

    /**
     * 清除所有用户授权信息缓存.
     */
    public void clearAllCachedAuthorizationInfo() {
        Cache<Object, AuthorizationInfo> cache = getAuthorizationCache();
        if (cache != null) {
            for (Object key : cache.keys()) {
                cache.remove(key);
            }
        }
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
        // TODO Auto-generated method stub
        return null;
    }

}
