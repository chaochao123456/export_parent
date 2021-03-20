package cn.itcast.quartz;

import cn.itcast.domain.Company;
import cn.itcast.service.CompanyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Component
public class GetSchedule {
    private static final Logger logger = LoggerFactory.getLogger(GetSchedule.class);
    @Autowired
     private CompanyService companyService;
    @SuppressWarnings("unused")
    private void setSHGetSchedule() {
/*        Company company = new Company();
        company.setId(UUID.randomUUID().toString().replace("-",""));
        company.setName("companyId");
        company.setExpirationDate(new Date());
        company.setAddress("beijing");
        company.setPhone("110123");
        company.setState(0);
        company.setIndustry("weedrfw");
        try {
            companyService.save(company);
        } catch (Exception e) {
            logger.error("有问题"+e.getMessage());
        }*/
        logger.info("当前执行的是定时任务"+new Date().getTime());
    }
}
