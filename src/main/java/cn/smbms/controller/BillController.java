package cn.smbms.controller;

import cn.smbms.pojo.Bill;
import cn.smbms.pojo.Provider;
import cn.smbms.service.BillService;
import cn.smbms.service.ProviderService;
import cn.smbms.util.BillUtil;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/sys")
public class BillController {
    @Resource
    private BillService billService;
    @Resource
    private ProviderService providerService;

    @RequestMapping("/bill")
    public String list(HttpSession session, Integer pageIndex, @RequestParam(required = false) String productName, @RequestParam(required = false) Integer providerId, @RequestParam(required = false) Integer isPayment) {
        if (pageIndex == null) {
            pageIndex = 1;
        }
        Page<Bill> page = billService.selectPage(pageIndex, 5, productName, providerId, isPayment);
        List<Provider> providers = providerService.selectProviders();
        session.setAttribute("productName", productName);
        session.setAttribute("providerId", providerId);
        session.setAttribute("isPayment", isPayment);
        session.setAttribute("totalPageCount", page.getPages());
        session.setAttribute("currentPageNo", page.getCurrent());
        session.setAttribute("totalCount", page.getTotal());
        session.setAttribute("blist", page.getRecords());
        session.setAttribute("plist", providers);
        return "bill/billlist";
    }

    @RequestMapping("/billview")
    public String billView(HttpSession session, Long billid) {
        Bill bill = billService.selectById(billid);
        Provider provider = providerService.selectById(bill.getProviderid());
        bill.setProvider(provider);
        session.setAttribute("bill", bill);
        return "bill/billview";
    }

    @RequestMapping("/billmodify")
    public String billModify(HttpSession session, Long billid) {
        Bill bill = billService.selectById(billid);
        Provider provider = providerService.selectById(bill.getProviderid());
        bill.setProvider(provider);
        session.setAttribute("bill", bill);
        return "bill/billmodify";
    }

    @RequestMapping("/billselect")
    @ResponseBody
    public Object billSelect() {
        return providerService.selectProviders();
    }

    @RequestMapping("/dobillmodify")
    public String doBillModify(Bill bill) {
        Integer i = billService.updateById(bill);
        if (i > 0) {
            return "redirect:/sys/bill";
        } else {
            return "redirect:/sys/billmodify";
        }
    }

    @RequestMapping("/billadd")
    public String billAdd() {
        return "/bill/billadd";
    }

    @RequestMapping("/dobilladd")
    public String doBillAdd(Bill bill) {
        Integer i = billService.insert(bill);
        if (i > 0) {
            return "redirect:/sys/bill";
        } else {
            return "redirect:/sys/billadd";
        }
    }

    @RequestMapping("billdel")
    @ResponseBody
    public Object billDel(Long billid) {
        BillUtil billUtil = new BillUtil();
        Bill bill = billService.selectById(billid);
        if (bill == null) {
            billUtil.setDelResult("notexist");
            return billUtil;
        }
        Integer i = billService.deleteById(billid);
        if (i > 0) {
            billUtil.setDelResult("true");
        } else {
            billUtil.setDelResult("false");
        }
        return billUtil;
    }
}
