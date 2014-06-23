package cn.zhihang.cm.index.web;

import javax.jws.WebParam.Mode;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
    @RequestMapping(value="/index")
    public String index(Mode mode, HttpServletRequest request, HttpServletResponse response){
        return "index/index";
    }
}
