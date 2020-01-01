package cn.smbms.service;

import cn.smbms.pojo.User;
import com.baomidou.mybatisplus.plugins.Page;

import java.util.List;

public interface UserService {
    User login(String usercode, String password);

    Integer updateById(User user);

    Page<User> selectPage(Integer pageIndex, Integer pageSize, String username, Integer roleId);

    Integer insert(User user);

    List<User> selectByUserCode(String userCode);

    User selectById(Long id);

    Integer deleteById(Long id);
}
