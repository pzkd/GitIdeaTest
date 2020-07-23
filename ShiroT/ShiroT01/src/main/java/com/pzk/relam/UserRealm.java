package com.pzk.relam;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ${user}
 */
public class UserRealm extends AuthorizingRealm {

    /**
     * @param token token信息
     * @return 认证信息
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        User user = new User("zhangsan","3285541c519ec7cef7077b06baae58d5");
        if (null!=token.getPrincipal() && token.getPrincipal().toString().equals(user.getUserName())){
           // AuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user,user.getPassword() ,this.getName());
            ByteSource byteSource = ByteSource.Util.bytes("mark");
            AuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user, user.getPassword(), byteSource, getName());
            System.out.println(authenticationInfo.getCredentials());
            //验证密码
            return  authenticationInfo;
        }
        return null;
    }

    /**
     * 授权
     * @param principals
     * @return
     */
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        AuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        ArrayList<String> roles = new ArrayList<String>();
        roles.add("role1");
        roles.add("role2");
        ((SimpleAuthorizationInfo) authorizationInfo).addRoles(roles);
        ((SimpleAuthorizationInfo) authorizationInfo).addStringPermission("*:*");
        return null;
    }
}
