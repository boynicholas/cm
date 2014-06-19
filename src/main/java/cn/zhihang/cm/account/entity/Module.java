package cn.zhihang.cm.account.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.validator.constraints.NotBlank;

import com.google.common.collect.Lists;

@Entity(name = "t_module")
public class Module {
    
    /**
     * 模块编号
     */
    @Id
    private Integer moduleId;
    
    /**
     * 模块名称
     */
    @NotBlank
    private String moduleName;
    
    /**
     * 模块编码
     */
    @NotBlank
    private String moduleCode;
    
    /**
     * 访问路径
     */
    private String modulePath;
    
    /**
     * 父模块ID
     */
    private Long parentId;
    
    /**
     * 排序
     */
    private String orderby;
    
    /**
     * 描述 
     */
    private String describe;
    
    /**
     * 模块下的权限
     */
    @Transient
    private List<Permission> permissionList = Lists.newArrayList();
    
    /**
     * 子模块
     */
    @Transient
    private List<Module> children = Lists.newArrayList();

    public Integer getModuleId() {
        return moduleId;
    }

    public void setModuleId(Integer moduleId) {
        this.moduleId = moduleId;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getModuleCode() {
        return moduleCode;
    }

    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
    }

    public String getModulePath() {
        return modulePath;
    }

    public void setModulePath(String modulePath) {
        this.modulePath = modulePath;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getOrderby() {
        return orderby;
    }

    public void setOrderby(String orderby) {
        this.orderby = orderby;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }
    
    @OneToMany(cascade = {CascadeType.REFRESH, CascadeType.REMOVE, CascadeType.PERSIST}, mappedBy = "module_id")
    @XmlTransient
    public List<Permission> getPermissionList() {
        return permissionList;
    }

    public void setPermissionList(List<Permission> permissionList) {
        this.permissionList = permissionList;
    }
    
    @Transient
    public List<Module> getChildren() {
        return children;
    }

    public void setChildren(List<Module> children) {
        this.children = children;
    }
}
