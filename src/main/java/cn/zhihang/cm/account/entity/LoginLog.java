package cn.zhihang.cm.account.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "t_login_log")
public class LoginLog {
    /**
     * 日志ID
     */
    @Id
    private Integer logId;
    
    /**
     * 登录时间
     */
    private Date loginTime;
    
    /**
     * 登录IP
     */
    private String loginIp;
    
    /**
     * 登录用户名
     */
    private String uName;
    
    /**
     * 登录状态
     */
    private String status;

    public Integer getLogId() {
        return logId;
    }

    public void setLogId(Integer logId) {
        this.logId = logId;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
