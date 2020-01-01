package cn.smbms.service;

import cn.smbms.pojo.Provider;

import java.util.List;

public interface ProviderService {
    List<Provider> selectProviders();

    List<Provider> selectProviders(String proCode, String proName);

    Provider selectById(Long id);

    Integer updateById(Provider provider);

    Integer deleteById(Long id);

    Integer insert(Provider provider);
}
