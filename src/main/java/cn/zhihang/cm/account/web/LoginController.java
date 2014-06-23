package cn.zhihang.cm.account.web;


import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cn.zhihang.cm.base.security.UsernamePasswordCaptchaToken;

@Controller
public class LoginController {
    
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model){
        Subject currentUser = SecurityUtils.getSubject();
        
        if(currentUser != null){
            currentUser.logout();
        }
        
        return "/login/login";
    }
    
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public void loginPost(@RequestParam String user_name, @RequestParam String user_pass,
            @RequestParam String captcha, @RequestParam boolean rememberMe, HttpServletRequest request){
        Subject currentUser = SecurityUtils.getSubject();
        
        if(!currentUser.isAuthenticated()){
            UsernamePasswordCaptchaToken token = new UsernamePasswordCaptchaToken(user_name, user_pass.toCharArray(), rememberMe, request.getRemoteHost(), captcha);
            try{
                currentUser.login(token);
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
        }
    }
}
