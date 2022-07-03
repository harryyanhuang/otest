package org.com;

import org.com.entity.User;
import org.com.service.TestService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@SpringBootTest
public class UserTest {


    @Resource
    TestService testService;

    @Test
    public void test1(){
        User user=new User();
        user.setUsername("张三");
        user.setCreatetime(new Date());
        testService.updateUser(user);
    }

    @Test
    public void test2(){
        List<User> users = testService.userList();
        for (User user : users) {
            System.out.println(user);
        }
    }
}
