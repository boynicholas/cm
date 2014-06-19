package cn.zhihang.cm.base.security;

import org.apache.shiro.authc.UsernamePasswordToken;

public class UsernamePasswordCaptchaToken extends UsernamePasswordToken {
    
    private static final long serialVersionUID = 1L;
    
    private String captcha;

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }
    
    public UsernamePasswordCaptchaToken(){
        super();
    }
    
    public UsernamePasswordCaptchaToken(String user_name, char[] user_pass, boolean rememberMe, String host, String captcha){
        super(user_name, user_pass, rememberMe, host);
        this.captcha = captcha;
    }
    
}
