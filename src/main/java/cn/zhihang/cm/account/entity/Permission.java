package cn.zhihang.cm.account.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.validator.constraints.NotBlank;

@Entity(name = "t_permission")
public class Permission {
    /**
     * 权限ID
     */
    @Id
    private Integer permissionId;
    
    /**
     * 权限名称
     */
    @NotBlank
    private String permissionName;
    
    /**
     * 权限编码
     */
    @NotBlank
    private String permissionCode;
    
    /**
     * 描述
     */
    private String permissionDescribe;

    /**
     * 模块
     */
    @Transient
    private Module module;

    public Integer getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public String getPermissionCode() {
        return permissionCode;
    }

    public void setPermissionCode(String permissionCode) {
        this.permissionCode = permissionCode;
    }
    
    @ManyToOne(cascade = {CascadeType.REFRESH}, optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "moudle_id")
    @XmlTransient
    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }
    
    public String getPermissionDescribe() {
        return permissionDescribe;
    }

    public void setPermissionDescribe(String permissionDescribe) {
        this.permissionDescribe = permissionDescribe;
    }
}
