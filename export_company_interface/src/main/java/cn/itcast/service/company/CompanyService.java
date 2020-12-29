package cn.itcast.service.company;

import cn.itcast.domain.Company;
import cn.itcast.domain.PageBean;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * 使用dubbo远程调用的方法
 */
public interface CompanyService {

    List<Company> findAll();

    void save(Company company);

    Company findById(String id);

    void update(Company company);

    void deleteById(String id);

    PageBean findAll(int pageNum, int pageSize);

    PageInfo findPage(int pageNum, int pageSize);
}
