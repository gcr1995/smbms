package cn.smbms.controller;

import cn.smbms.pojo.Bill;
import cn.smbms.pojo.Provider;
import cn.smbms.service.BillService;
import cn.smbms.service.ProviderService;
import cn.smbms.util.BillUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/sys")
public class ProviderController {
    @Resource
    private ProviderService providerService;
    @Resource
    private BillService billService;

    @RequestMapping("/provider")
    public String provider(HttpSession session, @RequestParam(required = false) String queryProCode, @RequestParam(required = false) String queryProName) {
        List<Provider> providers = providerService.selectProviders(queryProCode, queryProName);
        session.setAttribute("queryProCode", queryProCode);
        session.setAttribute("queryProName", queryProName);
        session.setAttribute("proList", providers);
        return "pro/providerlist";
    }

    @RequestMapping("/proview")
    public String providerView(HttpSession session, Long proid) {
        Provider provider = providerService.selectById(proid);
        session.setAttribute("provider", provider);
        return "pro/providerview";
    }

    @RequestMapping("/providermodify")
    public String providerModify(HttpSession session, Long proid) {
        Provider provider = providerService.selectById(proid);
        session.setAttribute("provider", provider);
        return "pro/providermodify";
    }

    @RequestMapping("/providermodifysave")
    public String providerModifySave(Provider provider) {
        Integer i = providerService.updateById(provider);
        if (i > 0) {
            return "redirect:/sys/bill";
        } else {
            return "redirect:/sys/providermodify";
        }
    }

    @RequestMapping("/delprovider")
    @ResponseBody
    public Object delProvider(Long proid) {
        List<Bill> bills = billService.selectByProviderId(proid);
        BillUtil billUtil = new BillUtil();
        if (bills.size() != 0) {
            billUtil.setDelResult(String.valueOf(bills.size()));
        } else {
            Integer i = providerService.deleteById(proid);
            if (i > 0) {
                billUtil.setDelResult("true");
            } else {
                billUtil.setDelResult("false");
            }
        }
        return billUtil;
    }

    @RequestMapping("/provideradd")
    public String addProvider() {
        return "pro/provideradd";
    }

    @RequestMapping("/provideraddsave")
    public String doAddprovider(Provider provider) {
        Integer i = providerService.insert(provider);
        if (i > 0) {
            return "redirect:/sys/provider";
        } else {
            return "redirect:/sys/provideradd";
        }
    }
}
