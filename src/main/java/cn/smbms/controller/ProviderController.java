package cn.smbms.controller;

import cn.smbms.pojo.Provider;
import cn.smbms.service.ProviderService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/sys")
public class ProviderController {
    @Resource
    private ProviderService providerService;

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
}
