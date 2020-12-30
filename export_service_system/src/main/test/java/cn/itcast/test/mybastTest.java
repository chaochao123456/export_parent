package cn.itcast.test;

import cn.itcast.dao.cargo.FactoryDao;
import cn.itcast.domain.Factory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:spring/applicationContext-*.xml")
public class mybastTest {
    private static Logger logger = LoggerFactory.getLogger(mybastTest.class);
    @Autowired
    private FactoryDao factoryDao;


    @Test
    public void test(){
        List<Factory> factories = factoryDao.selectByExample(null);
        for (Factory factory : factories) {
            System.out.println(factory.toString());
            logger.info(factory.toString());
        }
    }
}
