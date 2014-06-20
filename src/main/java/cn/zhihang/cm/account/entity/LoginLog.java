package cn.zhihang.cm.account.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "t_login_log")
public class LoginLog {
    /**
     * 日志ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    private String userName;
    
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String uName) {
        this.userName = uName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
