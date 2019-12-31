package cn.smbms.service;

import cn.smbms.pojo.Bill;
import com.baomidou.mybatisplus.plugins.Page;

import java.util.List;

public interface BillService {
    Page<Bill> selectPage(Integer currentPageNo, Integer pageSize, String productName, Integer providerId, Integer isPayment);
    Bill selectById(Long id);
    Integer updateById(Bill bill);
    Integer insert(Bill bill);
    Integer deleteById(Long id);
}
