/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package cn.zhihang.cm.base.security;

import java.io.IOException;
import java.io.Serializable;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import cn.zhihang.cm.account.entity.LoginLog;
import cn.zhihang.cm.account.entity.Role;
import cn.zhihang.cm.account.entity.User;
import cn.zhihang.cm.account.repository.LoginLogDao;
import cn.zhihang.cm.account.service.UserService;
import cn.zhihang.cm.base.common.Coder;
import cn.zhihang.cm.base.common.Logs;

public class ShiroDbRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;
    @Autowired
    private LoginLogDao loginLogDao;

    private Logger logger = Logs.get();

    /**
     * 认证回调函数,登录时调用.
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken authcToken) throws AuthenticationException {

        UsernamePasswordCaptchaToken token = (UsernamePasswordCaptchaToken) authcToken;
        String username = token.getUsername();
        System.out.println(userService);
        User user = userService.getUserByUserName(username);
        String uPass = new String(token.getPassword());

        // 记录登录日志
        LoginLog loginLog = new LoginLog();
        loginLog.setUserName(token.getUsername());
        loginLog.setLoginIp(token.getHost());
        loginLog.setLoginTime(new Date());

        try {
            if (user != null) {
                /* 判断密码是否匹配 */
                String userPass = user.getUserPass();
                String encryPass = Coder.encryptHMAC(uPass, user.getUserScode());

                if (userPass.equals(encryPass)) {
                    if (user.getIslock() == 1) {
                        loginLog.setStatus("帐号锁定");
                        throw new LockedAccountException("帐号已被锁定，请与系统管理员联系");
                    }
                    loginLog.setStatus("登录成功");
                }else{
                    throw new IncorrectCredentialsException("密码错误");
                }

              
            } else {
                loginLog.setStatus("帐号错误");
                throw new UnknownAccountException("找不到该帐号");
            }
        } catch (AuthenticationException e) {
            e.printStackTrace();
            loginLog.setStatus(e.getMessage());
            logger.warn(e.getMessage());
            throw e;
        } catch (InvalidKeyException e) {
            // TODO Auto-generated catch block
            logger.warn(e.getMessage());
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            logger.warn(e.getMessage());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            logger.warn(e.getMessage());
        } finally {
            loginLogDao.save(loginLog);
        }
        
        // 登录成功后
        AuthenticationInfo ai = new SimpleAuthenticationInfo(new ShiroUser(user), uPass, getName());
        return ai;
    }

    /**
     * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用.
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(
            PrincipalCollection principals) {
        ShiroUser shiroUser = (ShiroUser) principals.fromRealm(getName())
                .iterator().next();
        User user = userService.getUserByUserName(shiroUser.getUserName());

        if (user != null) {
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            for (Role role : user.getRoleList()) {
                // 基于Role的权限信息
                info.addRole("" + role.getRoleId());
                // 基于Permission的权限信息
                info.addStringPermissions(role.getPermissionCodeList());
            }
            return info;
        } else {
            return null;
        }
    }

    public AuthorizationInfo getAuthorizationInfo(PrincipalCollection principals) {
        return super.getAuthorizationInfo(principals);
    }

    /**
     * 自定义Authentication对象，使得Subject除了携带用户的登录名外还可以携带更多信息.
     */
    public static class ShiroUser implements Serializable {
        private static final long serialVersionUID = -1373760761780840081L;
        public Integer userId;
        public String userName;
        public String userNick;
        public String userEmail;

        public ShiroUser(User user) {
            this.userId = user.getUserId();
            this.userName = user.getUserName();
            this.userNick = user.getUserNick();
            this.userEmail = user.getUserEmail();
        }

        public Integer getUserId() {
            return userId;
        }

        public void setUserId(Integer userId) {
            this.userId = userId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getUserNick() {
            return userNick;
        }

        public void setUserNick(String userNick) {
            this.userNick = userNick;
        }

        public String getUserEmail() {
            return userEmail;
        }

        public void setUserEmail(String userEmail) {
            this.userEmail = userEmail;
        }

        /**
         * 本函数输出将作为默认的<shiro:principal/>输出.
         */
        @Override
        public String toString() {
            return userName;
        }

        /**
         * 重载hashCode,只计算loginName;
         */
        @Override
        public int hashCode() {
            return HashCodeBuilder.reflectionHashCode(this, "userName");
        }

        /**
         * 重载equals,只计算loginName;
         */
        @Override
        public boolean equals(Object obj) {
            return EqualsBuilder.reflectionEquals(this, obj, "userName");
        }
    }
}
