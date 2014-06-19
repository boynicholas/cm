package cn.zhihang.cm.base.security;

import java.io.IOException;
import java.io.PrintWriter;

import javax.security.auth.Subject;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.slf4j.Logger;

import com.google.code.kaptcha.Constants;

import cn.zhihang.cm.base.common.Logs;

public class FormAuthenticationCaptchaFilter extends FormAuthenticationFilter {
    private static final Logger logger = Logs.get();
    
    /* 主要针对登录成功的处理方法 */
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception{
        HttpServletRequest httpServletRequest = (HttpServletRequest)request;
        HttpServletResponse httpServletResponse = (HttpServletResponse)response;
        
        if(!"XMLHttpRequest".equalsIgnoreCase(httpServletRequest.getHeader("X-Requested-With"))){ // 不是ajax请求
            issueSuccessRedirect(request, response);
        }else{
            httpServletRequest.setCharacterEncoding("utf-8");
            PrintWriter out = httpServletResponse.getWriter();
            out.println("{success:true,msg:'登入成功'}");
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
                out.println("{success:false,message:'密码错误'}");
            }else if("UnknownAccountException".equals(message)){
                out.println("{success:false,message:'帐号不存在'}");
            }else if("LockedAccountException".equals(message)){
                out.println("{success:false,message:'帐号被锁定，请联系系统管理员'}");
            }else{
                out.println("{success:false,message:'未知错误'}");
                e.printStackTrace();
            }
            out.flush();
            out.close();
        }catch(IOException e1){
            e1.printStackTrace();
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
                    String captcha = request.getParameter("captcha");
                    HttpServletRequest httpServletRequest = (HttpServletRequest)request;
                    String sCaptcha = (String)httpServletRequest.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
                    if(sCaptcha == null || "".equals(sCaptcha)){
                        return executeLogin(request, response); 
                    }
                    if(!sCaptcha.equals(captcha)){
                        response.setCharacterEncoding("utf-8");
                        PrintWriter out = response.getWriter();
                        out.println("{success:false,message:'验证码错误'}");
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
                out.println("{islogin:true, message:'login'}");
                out.flush();
                out.close();
            }
            return false;
        }
    }
}
