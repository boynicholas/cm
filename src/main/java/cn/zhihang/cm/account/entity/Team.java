package cn.zhihang.cm.account.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.validator.constraints.NotBlank;

import com.google.common.collect.Lists;

@Entity(name = "t_team")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer teamId;
    
    /**
     * 团队名称
     */
    @NotBlank
    private String teamName;
    
    /**
     * 团队管理
     */
    @Transient
    private List<User> adminUserList = Lists.newArrayList();
    
    /**
     * 团队成员
     */
    @Transient
    private List<User> teamUserList = Lists.newArrayList();
    
    /**
     * 创建时间
     */
    private Date createDateTime;
    
    /**
     * 是否删除
     */
    private Integer isdelete;
    
    /**
     * 是否锁定
     */
    private Integer islock;

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }
    
    @OneToMany(cascade = {CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinTable(name="t_team_admin", joinColumns = {@JoinColumn(name = "team_id")}, inverseJoinColumns = {@JoinColumn(name = "user_id")})
    @XmlTransient
    public List<User> getAdminUserList() {
        return adminUserList;
    }

    public void setAdminUserList(List<User> adminUser) {
        this.adminUserList = adminUser;
    }

    @ManyToOne(cascade = {CascadeType.REFRESH}, optional = true, fetch = FetchType.LAZY)
    @JoinTable(name = "t_team_user", joinColumns = {@JoinColumn(name = "team_id")}, inverseJoinColumns = {@JoinColumn(name = "user_id")})
    @XmlTransient
    public List<User> getTeamUserList() {
        return teamUserList;
    }

    public void setTeamUserList(List<User> teamUserList) {
        this.teamUserList = teamUserList;
    }

    public Date getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(Date createDateTime) {
        this.createDateTime = createDateTime;
    }

    public Integer getIsdelete() {
        return isdelete;
    }

    public void setIsdelete(Integer isdelete) {
        this.isdelete = isdelete;
    }

    public Integer getIslock() {
        return islock;
    }

    public void setIslock(Integer islock) {
        this.islock = islock;
    }
}
