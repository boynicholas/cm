package cn.zhihang.cm.base.security;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;

import com.google.code.kaptcha.Constants;

import cn.zhihang.cm.base.common.Logs;

public class FormAuthenticationCaptchaFilter extends FormAuthenticationFilter {
    private static final Logger logger = Logs.get();
    public static final String DEFAULT_USER_NAME = "login_user_name";
    public static final String DEFAULT_USER_PASS = "login_user_pass";
    public static final String DEFAULT_CAPTCHA = "login_verify";
    private String userNameParam = DEFAULT_USER_NAME;
    private String userPassParam = DEFAULT_USER_PASS;
    private String captchaParam = DEFAULT_CAPTCHA;
    
    public String getUserNameParam(){
        return userNameParam;
    }
    
    public String getUserPassParam(){
        return userPassParam;
    }
    
    public String getCaptchaParam(){
        return captchaParam;
    }
    
    protected String getUsername(ServletRequest request){
        return WebUtils.getCleanParam(request, getUserNameParam());
    }
    
    protected String getPassword(ServletRequest request){
        return WebUtils.getCleanParam(request, getUserPassParam());
    }
    
    protected String getCaptcha(ServletRequest request){
        return WebUtils.getCleanParam(request, getCaptchaParam());
    }
    
    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, org.apache.shiro.subject.Subject subject, ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest)request;
        HttpServletResponse httpServletResponse = (HttpServletResponse)response;
        
        if(!"XMLHttpRequest".equalsIgnoreCase(httpServletRequest.getHeader("X-Requested-With"))){ // 不是ajax请求
            issueSuccessRedirect(request, response);
        }else{
            httpServletRequest.setCharacterEncoding("utf-8");
            PrintWriter out = httpServletResponse.getWriter();
            out.println("{\"success\":true,\"message\":\"登入成功\"}");
            out.flush();
            out.close();
        }
        return false;
    }

    /* 主要针对登录失败的处理方法 */
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response){
        HttpServletRequest httpServletRequest = (HttpServletRequest)request;
        HttpServletResponse httpServletResponse = (HttpServletResponse)response;
        
        if(!"XMLHttpRequest".equalsIgnoreCase(httpServletRequest.getHeader("X-Requested-With"))){
            setFailureAttribute(request, e);
            return true;        
        }
        
        try{
            response.setCharacterEncoding("utf-8");
            PrintWriter out = httpServletResponse.getWriter();
            String message = e.getClass().getSimpleName();
            
            if("IncorrectCredentialsException".equals(message)){
                out.println("{\"success\":false,\"message\":\"密码错误\"}");
            }else if("UnknownAccountException".equals(message)){
                out.println("{\"success\":false,\"message\":\"帐号不存在\"}");
            }else if("LockedAccountException".equals(message)){
                out.println("{\"success\":false,\"message\":\"帐号被锁定，请联系系统管理员\"}");
            }else{
                out.println("{\"success\":false,\"message\":\"未知错误\"}");
                e.printStackTrace();
            }
            out.flush();
            out.close();
        }catch(IOException e1){
            logger.warn(e1.getMessage(), e1);
        }
        return false;
    }
    
    /* 所有请求都会经过的方法 */
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception{
        if(isLoginRequest(request, response)){
            if(isLoginSubmission(request, response)){
                if(logger.isTraceEnabled()){
                    logger.trace("登录提交检测，试图提交登录");
                }
                if("XMLHttpRequest".equalsIgnoreCase(((HttpServletRequest)request).getHeader("X-Requested-With"))){
                    String captcha = request.getParameter("login_verify");
                    HttpServletRequest httpServletRequest = (HttpServletRequest)request;
                    String sCaptcha = (String)httpServletRequest.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
                    if(sCaptcha == null || "".equals(sCaptcha)){
                        return executeLogin(request, response); 
                    }
                    if(!sCaptcha.equals(captcha)){
                        response.setCharacterEncoding("utf-8");
                        PrintWriter out = response.getWriter();
                        out.println("{\"success\":false,\"message\":\"验证码错误\"}");
                        out.flush();
                        out.close();
                        return false;
                    }
                }
                return executeLogin(request, response); 
            }else{
                return true;
            }
        }else{
            if(logger.isTraceEnabled()){
                logger.trace("Attempting to access a path which requires authentication.  Forwarding to the " + "Authentication url["+ getLoginUrl() +"]");
            }
            
            if(!"XMLHttpRequest".equalsIgnoreCase(((HttpServletRequest)request).getHeader("X-Requested-With"))){
                saveRequestAndRedirectToLogin(request, response);
            }else{
                response.setCharacterEncoding("utf-8");
                PrintWriter out = response.getWriter();
                out.println("{\"islogin\":true, \"message\":\"login\"}");
                out.flush();
                out.close();
            }
            return false;
        }
    }

    @Override
    protected AuthenticationToken createToken(ServletRequest request,
            ServletResponse response) {
        String username = getUsername(request);
        String password = getPassword(request);
        String captcha = getCaptcha(request);
        boolean rememberMe = isRememberMe(request);
        String host = getHost(request);
        
        return new UsernamePasswordCaptchaToken(username, password.toCharArray(), rememberMe, host, captcha);
    }
    
    
}
