package cn.smbms.dao;

import cn.smbms.pojo.Bill;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import cn.smbms.pojo.User;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {
    List<Bill> selectBills();
}