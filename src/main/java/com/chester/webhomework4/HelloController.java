package com.chester.webhomework4;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.util.StringUtils;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Scope("session")
public class HelloController {
    //    @GetMapping("/index")
//    public String SayHello(){
//        return "login";
//    }
    List<Map<String, Object>> mapList;

    @PostConstruct
    public void init() {
        mapList = new ArrayList<Map<String, Object>>();
    }

    @GetMapping("/")//初始页面 也是登陆页面
    public String index() {
        return "login";
    }

    @RequestMapping("/login")//登录页面
    public String login(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (null == username || null == password) {
            return "redirect:/";
        }
        //不正确的用户名密码
        if (!username.equals("admin") || !password.equals("123456")) {
            //登录失败设置标志位null 因为没有登出
            request.getSession().setAttribute("loginName", null);
            return "redirect:/";
        }
        //登陆成功 登录用户名为admin
        request.getSession().setAttribute("loginName", "admin");
        return "redirect:/addrList";//成功转到联系人列表页面
    }

    @RequestMapping("/addrList")//联系人列表页面
    public String addrList(HttpSession session, Model model) {
        String name = (String) session.getAttribute("name");
        String phone = (String) session.getAttribute("phone");
        String email = (String) session.getAttribute("email");
        String address = (String) session.getAttribute("address");
        String qq = (String) session.getAttribute("qq");
        System.out.println("addrList 需要联系人 名字为" + name);
        Map<String, Object> temp = new HashMap<String, Object>() {
            {
                put("name", name);
                put("phone", phone);
                put("email", email);
                put("address", address);
                put("qq", qq);
            }
        };
        if (name == null) {
            temp = new HashMap<String, Object>() {
                {
                    put("name", "default");
                    put("phone", "1333333333");
                    put("email", "chester@gmail.com");
                    put("address", "Beijing");
                    put("qq", "24131");
                }
            };
        }
        mapList.add(temp);
        model.addAttribute("conList", mapList);
        return "addrList";
    }

    @RequestMapping("/add")//添加联系人页面
    public String addContact(HttpServletRequest request) {
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        String qq = request.getParameter("qq");
        if (name != null) {
            request.getSession().setAttribute("name", name);
            request.getSession().setAttribute("phone", phone);
            request.getSession().setAttribute("email", email);
            request.getSession().setAttribute("address", address);
            request.getSession().setAttribute("qq", qq);
            System.out.println("addContact 添加联系人 名字为" + name);
            return "redirect:/addrList";
        }
        return "addContact";
    }

    @RequestMapping("/loginout")
    public String loginOut(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/";
    }
}
