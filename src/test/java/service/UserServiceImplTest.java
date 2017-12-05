package service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import bean.user;
/**
 * Created by mac on 16/7/18.
 */

    @RunWith(SpringJUnit4ClassRunner.class)
    @ContextConfiguration("classpath*:/applicationContext.xml")
    public class UserServiceImplTest {
        @Autowired
        private userService userService;

        @Test
        public void getUserTest(){
            user user = userService.getUser("1");
            Assert.assertNotNull(user);
        }
    }

