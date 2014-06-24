package cn.zhihang.cm.base.common;

import org.apache.shiro.SecurityUtils;

import cn.zhihang.cm.account.entity.Role;
import cn.zhihang.cm.base.security.ShiroDbRealm.ShiroUser;

/**
  * 当前用户的工具类 
  * @author cnlyml
  * @date 2014-6-24
 */
public class ShiroUtil {
     /**
      * 取得当前用户
      * @return
      */
    public static ShiroUser getCurrUser(){
        ShiroUser currentUser = (ShiroUser)SecurityUtils.getSubject().getPrincipal();
        return currentUser;
    }
    
    /**
     * 取得当前用户ID
     * @return
     */
    public static Integer getCurrUserId(){
        ShiroUser user = getCurrUser();
        return user==null?0:user.getUserId();
    }
    
    /**
     * 判断当前用户是否超级管理员
     * @return
     */
    public static boolean hasSuperAdminRole(){
        return SecurityUtils.getSubject().hasRole("" + Role.SUPER_ADMIN_ROLE_ID);
    }
    
    /**
     * 判断当前用户是否管理员
     * @return
     */
    public static boolean hasAdminRole(){
        return SecurityUtils.getSubject().hasRole("" + Role.ADMIN_ROLE_ID);
    }
}
