package com.zzw.base.shiro;

import com.zzw.base.entity.UserEntity;
import com.zzw.base.model.RoleModelEntity;
import com.zzw.base.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * shrio的MonitorRealm类
 * @author Administrator
 */
@Service("monitorRealm")
@Transactional
public class MonitorRealm extends AuthorizingRealm
{
    /**
     * 密码允许错误次数
     */
    private static final Integer MAX_WRONG_COUNT = 5;

    /**
     * 用户service
     */
    @Autowired
    private UserService userService;

    public MonitorRealm()
    {
        super();
    }

    /**
     * Authenticator的职责是验证用户帐号，是Shiro API中身份验证核心的入口点：
     * @param principals 凭据
     * @return 校验结果
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(
            final PrincipalCollection principals)
    {
        String userName = (String) principals.fromRealm(getName()).iterator()
                .next();
        UserEntity user = userService.selectUserPermission(userName);
        /* 这里编写授权代码 */
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        if (user != null)
        {
            // 用户的角色集合
            info.setRoles(user.getRolesName());
            List<RoleModelEntity> roleList = user.getRoleList();
            for (RoleModelEntity role : roleList)
            {
                // 返回 权限信息
                info.addStringPermissions(role.getPermissionName());
            }

        }
        return info;

    }



    /**
     * 认证
     * @param authcToken 校验token
     * @return 校验结果
     * @throws AuthenticationException 验证异常
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
            final AuthenticationToken authcToken) throws AuthenticationException
    {
        /* 这里编写认证代码 */
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        // 查询数据库
        UserEntity user = userService.selectUserPermission(token.getUsername());

        char[] password = token.getPassword();
        String chToStr = String.valueOf(password);
        if (user != null && chToStr.equals(user.getUserPassword()))
        {
            userService.upFailCount(user.getUserName(), 0);
            // 若存在，将此用户存到到登录的info中,
            // 返回认证信息 用户名和密码
            return new SimpleAuthenticationInfo(token.getUsername(), password,
                    getName());
        }
        if (user != null && (!chToStr.equals(user.getUserPassword())))
        {
            // 如果连续输错超过5次，则锁定改登录用户
            int loginFailureCount = user.getFailureCount() + 1;
            // 变更失败次数
            userService.upFailCount(user.getUserName(), loginFailureCount);
            if (loginFailureCount > MAX_WRONG_COUNT)
            {
                // 锁定用户
                userService.upIsLocked(user.getUserName(), 1);
            }
            return null;
        }
        return null;

    }

}
