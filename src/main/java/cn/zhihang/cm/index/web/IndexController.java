package cn.zhihang.cm.index.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.zhihang.cm.base.common.ShiroUtil;
import cn.zhihang.cm.base.security.ShiroDbRealm.ShiroUser;

@Controller
@RequestMapping(value="/index")
public class IndexController {
    
    @RequestMapping(value="")
    public String index(Model model, HttpServletRequest request, HttpServletResponse response){
        ShiroUser shiroUser = ShiroUtil.getCurrUser();
        model.addAttribute("user", shiroUser);
        return "index/index";
    }
    
    @RequestMapping(value="/logout")
    public String logout(){
        Subject currentUser = SecurityUtils.getSubject();
        currentUser.logout();
        
        return "redirect:/login";
    }
}
