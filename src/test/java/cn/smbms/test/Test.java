package cn.smbms.test;

import cn.smbms.pojo.Bill;
import cn.smbms.pojo.Provider;
import cn.smbms.service.BillService;
import cn.smbms.service.ProviderService;
import com.baomidou.mybatisplus.plugins.Page;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class Test {
    @Resource
    private BillService billService;
    @Resource
    private ProviderService providerService;
    @org.junit.Test
    public void test(){
        //Page<Bill> page = billService.selectPage(1,5);
        List<Provider> providers = providerService.selectProviders();
        //System.out.println(page.getRecords());
        System.out.println(providers);
    }
}
