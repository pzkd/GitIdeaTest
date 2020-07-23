package com.pzk.shiro;

import com.pzk.relam.UserRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.Authenticator;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.support.DelegatingSubject;

/**
 * @author ${user}
 */
public class AuthencationTest {
    public static void main(String[] args) {
        String userName = "zhangsan";
        String password = "123456";
        //身份认证
        AuthenticationToken authenticationToken = new UsernamePasswordToken(userName,password);
        DefaultSecurityManager securityManager = new DefaultSecurityManager();
        //IniRealm iniRealm = new IniRealm("classpath:shiro.ini");
        Realm realm = new UserRealm();
        securityManager.setRealm(realm);
        HashedCredentialsMatcher md5 = new HashedCredentialsMatcher("md5");
        md5.setHashIterations(2);
       ((UserRealm) realm).setCredentialsMatcher(md5);
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();
        subject.login(authenticationToken);
        System.out.println("是否认证成功"+subject.isAuthenticated());
        System.out.println(subject.getPrincipal());
        //权限查看
       if (subject.isAuthenticated()){
           System.out.println( "当前subject是否包含role1角色："+subject.hasRole("role1"));
           System.out.println( "当前subject是否包含查询权限："+subject.isPermitted("user.query"));
       }
    }
}
