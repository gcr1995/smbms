package cn.smbms.service.impl;

import cn.smbms.dao.ProviderMapper;
import cn.smbms.pojo.Provider;
import cn.smbms.service.ProviderService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ProviderServiceImpl implements ProviderService {
    @Resource
    private ProviderMapper providerMapper;

    @Override
    public List<Provider> selectProviders() {
        return providerMapper.selectList(null);
    }

    @Override
    public List<Provider> selectProviders(String proCode, String proName) {
        EntityWrapper<Provider> wrapper = new EntityWrapper<>();
        if (proCode != null && !"".equals(proCode)) {
            wrapper.like("proCode", proCode);
        }
        if (proName != null && !"".equals(proName)) {
            wrapper.like("proName", proName);
        }
        return providerMapper.selectList(wrapper);
    }

    @Override
    public Provider selectById(Long id) {
        return providerMapper.selectById(id);
    }

    @Override
    public Integer updateById(Provider provider) {
        return providerMapper.updateById(provider);
    }
}
