package cn.zhihang.cm.account.web;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.zhihang.cm.account.entity.User;
import cn.zhihang.cm.account.service.UserService;
import cn.zhihang.cm.base.security.ShiroDbRealm;
import cn.zhihang.cm.base.security.UsernamePasswordCaptchaToken;

@Controller
public class LoginController {
    @Autowired
    private ShiroDbRealm shiroDbRealm;
    
    @Autowired
    private UserService userService;
    
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model){
        Subject currentUser = SecurityUtils.getSubject();
        
        if(currentUser.isAuthenticated()){
            return "redirect:/index";
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
    
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public String RegisterPost(@ModelAttribute User user, HttpServletRequest request) throws InvalidKeyException, NoSuchAlgorithmException, IOException{
        Subject currentUser = SecurityUtils.getSubject();
        
        if(currentUser.isAuthenticated()){
            return "{\"success\":false, \"message\":\"操作错误\"}"; 
        }
        
        /* 检查输入情况 */
        try{
            userService.checkUser(user);
        }catch(Exception e){
            return "{\"success\":false, \"message\":\""+e.getMessage()+"\"}";
        }
        String userpass = user.getUserPass();
        
        userService.saveUser(user);
        
        /* 注册成功后，直接进行登录 */
        UsernamePasswordCaptchaToken token = new UsernamePasswordCaptchaToken(user.getUserName(), userpass.toCharArray(), false, request.getRemoteHost(), null);
        
        try{
            currentUser.login(token);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        
        return "{\"success\":true, \"message\":\"注册成功\"}"; 
    }
    
    @RequestMapping(value = "/register/checkUser", method = RequestMethod.POST)
    @ResponseBody
    public String checkUser(@RequestParam String userName){
        User user = userService.getUserByUserName(userName, false);
        
        if(user == null){
            return "true";
        }else{
            return "false";
        }
    }
    
    @RequestMapping(value = "/register/checkEmail", method = RequestMethod.POST)
    @ResponseBody
    public String checkEmail(@RequestParam String userEmail){
    	User user = userService.getUserByUserEmail(userEmail, false);
    	
    	if(user == null){
    		return "true";
    	}else{
    		return "false";
    	}
    }
}
