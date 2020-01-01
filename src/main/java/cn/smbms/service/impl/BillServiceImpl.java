package cn.smbms.service.impl;

import cn.smbms.dao.BillMapper;
import cn.smbms.dao.ProviderMapper;
import cn.smbms.pojo.Bill;
import cn.smbms.pojo.Provider;
import cn.smbms.service.BillService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class BillServiceImpl implements BillService {
    @Resource
    private BillMapper billMapper;
    @Resource
    private ProviderMapper providerMapper;

    @Override
    public Page<Bill> selectPage(Integer currentPageNo, Integer pageSize, String productName, Integer providerId, Integer isPayment) {
        Page<Bill> page = new Page<>(currentPageNo, pageSize);
        EntityWrapper<Bill> wrapper = new EntityWrapper<>();
        if (productName != null && !"".equals(productName)) {
            wrapper.like("productName", productName);
        }
        if (providerId != null && providerId != 0) {
            wrapper.eq("providerId", providerId);
        }
        if (isPayment != null && isPayment != 0) {
            wrapper.eq("isPayment", isPayment);
        }
        List<Bill> bills = billMapper.selectPage(page, wrapper);
        for (Bill bill : bills) {
            Provider provider = providerMapper.selectById(bill.getProviderid());
            bill.setProvider(provider);
        }
        page.setRecords(bills);
        return page;
    }

    @Override
    public Bill selectById(Long id) {
        return billMapper.selectById(id);
    }

    @Override
    public Integer updateById(Bill bill) {
        return billMapper.updateById(bill);
    }

    @Override
    public Integer insert(Bill bill) {
        return billMapper.insert(bill);
    }

    @Override
    public Integer deleteById(Long id) {
        return billMapper.deleteById(id);
    }

    @Override
    public List<Bill> selectByProviderId(Long providerId) {
        return billMapper.selectList(new EntityWrapper<Bill>().eq("providerId", providerId));
    }
}
