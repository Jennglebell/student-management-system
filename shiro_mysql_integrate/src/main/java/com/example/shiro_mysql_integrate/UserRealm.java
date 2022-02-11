package com.example.shiro_mysql_integrate;

import com.example.shiro_mysql_integrate.dao.UserDao;
import com.example.shiro_mysql_integrate.mapper.RoleMapper;
import com.example.shiro_mysql_integrate.model.User;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//告诉shiro怎么做验证, dependency injection的时候spring就会new一个realm出来
//给authorizer这个名字是必要的，因为在ShiroConfig里java spring会用dependency给new出来一个security manager
//security manager需要一个instance of AuthorizingRealm，然后默认去找叫authorizer的class
@Component("authorizer")
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleMapper roleMapper;

    @Override
    //对用户权限的检查
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        User user = userDao.findUserByUsername(principalCollection.toString());
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setRoles(user.getRoleNames());
        info.setStringPermissions(roleMapper.getPermissionByRoleId(user.getRole().getId())); //用mybatis写一些语句->mapper

        return info;
    }

    @Override
    //对用户的认证
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        User user = userDao.findUserByUsername(token.getUsername());

        if (user == null) {
            throw new UnknownAccountException();
        }

        //它会帮我们做认证，看用户名和密码对不对
        // getName() is to get the name of this UserRealm
        return new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(), getName());
    }
}
