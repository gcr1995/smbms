package cn.smbms.controller;

import cn.smbms.pojo.User;
import cn.smbms.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * @author Administrator
 */
@Controller
public class LoginController {
    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @Resource
    private UserService userService;

    @RequestMapping(value = "/dologin", method = RequestMethod.POST)
    public Object doLogin(HttpSession session, @RequestParam("userCode") String usercode, @RequestParam String password) {
        User user = userService.login(usercode, password);
        if (user != null) {
            session.removeAttribute("error");
            session.setAttribute("loginUser", user);
            return "redirect:/main.html";
        }
        session.setAttribute("error", "用户名或密码错误");
        return "redirect:/login";
    }

    @RequestMapping("/main.html")
    public String frame() {
        return "frame";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
