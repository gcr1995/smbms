package cn.smbms.controller;

import cn.smbms.pojo.Role;
import cn.smbms.pojo.User;
import cn.smbms.service.RoleService;
import cn.smbms.service.UserService;
import cn.smbms.util.UserUtil;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/sys")
public class UserController {
    @Resource
    private UserService userService;
    @Resource
    private RoleService roleService;

    @RequestMapping("/pwdmodify")
    public String pwdModify() {
        return "user/pwdmodify";
    }

    @RequestMapping("/checkpwd")
    @ResponseBody
    public Object checkPwd(HttpSession session, String oldpassword) {
        UserUtil userUtil = new UserUtil();
        if (session == null) {
            userUtil.setResult("sessionerror");
        } else if (oldpassword == null || "".equals(oldpassword)) {
            userUtil.setResult("error");
        } else {
            User user = (User) session.getAttribute("user");
            if (user.getUserpassword().equals(oldpassword)) {
                userUtil.setResult("true");
            } else {
                userUtil.setResult("false");
            }
        }
        return userUtil;
    }


    @RequestMapping("/savepwdmodify")
    public String savePwdModify(HttpSession session, String newpassword) {
        User user = (User) session.getAttribute("user");
        user.setUserpassword(newpassword);
        Integer i = userService.pwdModify(user);
        if (i > 0) {
            session.removeAttribute("user");
            return "redirect:/login";
        }
        return "redirect:/sys/pwdmodify";
    }

    @RequestMapping("/user")
    public String user(HttpSession session, Integer pageIndex, @RequestParam(required = false) String queryname, @RequestParam(required = false) Integer queryUserRole) {
        if (pageIndex == null) {
            pageIndex = 1;
        }
        Page<User> page = userService.selectPage(pageIndex, 5, queryname, queryUserRole);
        List<Role> roles = roleService.selectRoles();
        session.setAttribute("queryUserName", queryname);
        session.setAttribute("queryUserRole", queryUserRole);
        session.setAttribute("roleList", roles);
        session.setAttribute("userList", page.getRecords());
        session.setAttribute("totalPage", page.getPages());
        session.setAttribute("totalCount", page.getTotal());
        session.setAttribute("currentPage", page.getCurrent());
        return "user/userlist";
    }

}
