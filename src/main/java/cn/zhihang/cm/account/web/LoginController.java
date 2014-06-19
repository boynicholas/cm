package cn.zhihang.cm.account.web;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cn.zhihang.cm.account.entity.User;
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
    public void loginPost(String user_name, String user_pass, String captcha, boolean rememberMe, HttpServletRequest request){
        SecurityUtils.getSubject().login(new UsernamePasswordCaptchaToken(user_name, user_pass.toCharArray(), rememberMe, request.getRemoteHost() , captcha));
    }
}
