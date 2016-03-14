/**
 * 宝龙电商
 * PACKAGE_NAME
 * SchedulerTest.java
 * <p/>
 * 2016-02-19
 * 2016宝龙公司-版权所有
 */

import com.plocc.moffice.entity.UserEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.commonj.TimerManagerTaskScheduler;
import org.springframework.scheduling.concurrent.DefaultManagedTaskScheduler;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

/**
 * SchedulerTest
 *
 * @author Zhanggj
 * @version 1.0.0
 * @time 17:46
 * @email zhanggj@powerlong.com
 * @description 职责描述
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class)
public class SchedulerTest extends AbstractJUnit4SpringContextTests {

    @Autowired
    private UserEntity userEntity;
    @Autowired
    private TaskScheduler taskScheduler;

    @Configuration
    @EnableScheduling
    public static class ContextConfiguration {
        @Bean
        public UserEntity userEntity() {
            return new UserEntity();
        }

        @Scheduled(fixedDelay = 1000)
        public void work() {
            System.out.println("work:");
        }
    }

    @Test
    public void test() throws InterruptedException {
        System.out.println("--------:" + taskScheduler);
        Thread.sleep(100000);
    }

}
