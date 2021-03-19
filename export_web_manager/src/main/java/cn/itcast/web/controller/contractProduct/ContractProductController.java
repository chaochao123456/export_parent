package cn.itcast.web.controller.contractProduct;
import cn.itcast.domain.Company;
import cn.itcast.domain.FactoryExample;
import cn.itcast.domain.cargo.ContractProduct;
import cn.itcast.domain.cargo.Factory;
import cn.itcast.service.cargo.ContractProductService;
import cn.itcast.service.cargo.FactoryService;
import cn.itcast.utils.UtilFuns;
import cn.itcast.web.controller.BaseController;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.UUID;


@Controller
@RequestMapping(value = "cargo/contractProduct")
public class ContractProductController extends BaseController {
    @Reference
    private ContractProductService contractProductService;
    @Reference
    private FactoryService factoryService;
    @RequestMapping(value = "/list",name = "企业列表查询")
    public String findById(String contractId){
        ContractProduct contractProduct = contractProductService.findById("contractId");
        request.setAttribute("contractProduct", contractProduct);
            //查询生产厂家
            List<Factory> qw = factoryService.findAll(null);
            request.setAttribute("factoryList", qw);
        return "cargo/product/product-list";
    }


    @RequestMapping(value = "/edit",name = "保存货物信息")
    @ResponseBody
    public String edit(ContractProduct contractProduct){
        if(UtilFuns.isEmpty(contractProduct.getId())){
            try {
                contractProduct.setId(UUID.randomUUID().toString().replace("-",""));
                contractProductService.save(contractProduct);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("sasasasasas");
            }
        }else {
            contractProductService.update(contractProduct);
        }
        return "1";
    }

}
