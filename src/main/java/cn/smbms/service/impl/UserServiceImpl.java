package cn.smbms.service.impl;

import cn.smbms.dao.RoleMapper;
import cn.smbms.dao.UserMapper;
import cn.smbms.pojo.Role;
import cn.smbms.pojo.User;
import cn.smbms.service.UserService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;
    @Resource
    private RoleMapper roleMapper;

    @Override
    public User login(String usercode, String password) {
        User u = new User();
        u.setUsercode(usercode);
        User user = userMapper.selectOne(u);
        if (user != null && user.getUserpassword().equals(password)) {
            return user;
        }
        return null;
    }

    @Override
    public Integer updateById(User user) {
        return userMapper.updateById(user);
    }

    @Override
    public Page<User> selectPage(Integer pageIndex, Integer pageSize, String username, Integer roleId) {
        Page<User> page = new Page<>(pageIndex, pageSize);
        EntityWrapper<User> wrapper = new EntityWrapper<>();
        if (username != null && !"".equals(username)) {
            wrapper.like("username", username);
        }
        if (roleId != null && roleId != 0) {
            wrapper.eq("userrole", roleId);
        }
        List<User> users = userMapper.selectPage(page, wrapper);
        for (User user : users) {
            Role role = roleMapper.selectById(user.getUserrole());
            user.setRole(role);
        }
        page.setRecords(users);
        return page;
    }

    @Override
    public Integer insert(User user) {
        return userMapper.insert(user);
    }

    @Override
    public List<User> selectByUserCode(String userCode) {
        return userMapper.selectList(new EntityWrapper<User>().eq("userCode", userCode));
    }

    @Override
    public User selectById(Long id) {
        return userMapper.selectById(id);
    }

    @Override
    public Integer deleteById(Long id) {
        return userMapper.deleteById(id);
    }
}
