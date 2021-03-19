package cn.itcast.web.controller.cargo;

import cn.itcast.domain.cargo.ContractExample;
import cn.itcast.service.cargo.ContractService;
import cn.itcast.web.controller.BaseController;
import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 购销合同的信息
 */
@Controller
@RequestMapping(value = "cargo/contract")
public class ContractController extends BaseController {
    //模拟登陆用户的企业id和企业名称
    public String companyId;
    public String companyName;
    @Reference
    private ContractService contractService;
    @RequestMapping(value = "/list",name = "合同查询")
    public String findAll(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "8") int pageSize){
        ContractExample contractExample = new ContractExample();
        PageInfo all = contractService.findAll(contractExample, pageNum, pageSize);
        request.setAttribute("page",all);
        System.out.println("111111111111111111");
        return "cargo/contract/contract-list";
    }
}
