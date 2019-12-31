package cn.smbms.service;

import cn.smbms.pojo.User;
import com.baomidou.mybatisplus.plugins.Page;

public interface UserService {
    User login(String usercode, String password);

    Integer pwdModify(User user);

    Page<User> selectPage(Integer pageIndex, Integer pageSize, String username, Integer roleId);
}
