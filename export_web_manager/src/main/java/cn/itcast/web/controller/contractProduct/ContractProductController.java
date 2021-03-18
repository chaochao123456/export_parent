package cn.itcast.web.controller.contractProduct;
import cn.itcast.domain.cargo.ContractProduct;
import cn.itcast.service.cargo.ContractProductService;
import cn.itcast.web.controller.BaseController;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping(value = "cargo/contractProduct")
public class ContractProductController extends BaseController {
    @Reference
    private ContractProductService contractProductService;
    @RequestMapping(value = "/list",name = "企业列表查询")
    public String findById(String contractId){
/*        ContractProduct contractProduct = contractProductService.findById(contractId);
        request.setAttribute("contractProduct",contractProduct);*/
        return "cargo/product/product-list";
    }

}
