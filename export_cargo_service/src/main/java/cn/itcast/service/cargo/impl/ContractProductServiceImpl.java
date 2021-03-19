package cn.itcast.service.cargo.impl;

import cn.itcast.dao.cargo.ContractProductDao;
import cn.itcast.domain.cargo.Contract;
import cn.itcast.domain.cargo.ContractProduct;
import cn.itcast.domain.cargo.ContractProductExample;
import cn.itcast.service.cargo.ContractProductService;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 货物的展示
 */
@Service
public class ContractProductServiceImpl implements ContractProductService {
    @Autowired
    private ContractProductDao contractProductDao;
    @Override
    public void save(ContractProduct contractProduct) {
        contractProductDao.insertSelective(contractProduct);
    }

    @Override
    public void update(ContractProduct contractProduct) {
        contractProductDao.updateByPrimaryKeySelective(contractProduct);
    }

    @Override
    public void delete(String id) {
        contractProductDao.deleteByPrimaryKey(id);
    }

    @Override
    public ContractProduct findById(String id) {
        return contractProductDao.selectByPrimaryKey(id);
    }

    @Override
    public PageInfo findAll(ContractProductExample example, int page, int size) {
        PageHelper.clearPage();
        List<ContractProduct> contractProducts = contractProductDao.selectByExample(example);
        return new PageInfo(contractProducts);
    }
}
