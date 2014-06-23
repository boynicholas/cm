package cn.zhihang.cm.account.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.validator.constraints.NotBlank;

import com.google.common.collect.Lists;

@Entity
@Table(name = "t_role")
public class Role {
    /**
     * 超级管理员角色
     */
    public static final long SUPER_ADMIN_ROLE_ID = 1;
    
    /**
     * 普通管理员角色
     */
    public static final long ADMIN_ROLE_ID = 2;
    
    /**
     * 普通用户角色
     */
    public static final long USER_ROLE_ID = 3;
    
    /**
     * 角色ID
     */
    @Id
    private Integer roleId;
    
    /**
     * 角色名
     */
    @NotBlank
    private String roleName;
    
    /**
     * 描述
     */
    private String roleDescribe;
    
    /**
     * 关联权限
     */
    @Transient
    private List<Permission> permissionList = Lists.newArrayList();

    /**
     * 拥有当前角色的用户
     */
    @Transient
    private List<User>user = Lists.newArrayList();
    
    
    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDescribe() {
        return roleDescribe;
    }

    public void setRoleDescribe(String roleDescribe) {
        this.roleDescribe = roleDescribe;
    }
    
    @ManyToMany
    @JoinTable(name = "t_role_permission", joinColumns = {@JoinColumn(name = "role_id")}, inverseJoinColumns = {@JoinColumn(name = "permission_id")})
    public List<Permission> getPermissionList() {
        return permissionList;
    }

    public void setPermissionList(List<Permission> permissionList) {
        this.permissionList = permissionList;
    }
    
    @ManyToMany(cascade={CascadeType.REFRESH})
    @JoinTable(name = "t_user_role", joinColumns = {@JoinColumn(name = "role_id")}, inverseJoinColumns = {@JoinColumn(name = "user_id")})
    public List<User> getUser() {
        return user;
    }

    public void setUser(List<User> user) {
        this.user = user;
    }
    
    /**
     * 取得角色具有的权限编码，供验证权限使用
     */
    @Transient
    public List<String> getPermissionCodeList(){
        List<String> codeList = Lists.newArrayList();
        
        for(Permission p : permissionList){
            Module module = p.getModule();
            codeList.add(module.getModuleCode() + p.getPermissionCode());
        }
        return codeList;
    }
}
