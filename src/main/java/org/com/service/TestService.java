package org.com.service;

import com.alibaba.fastjson.JSONObject;
import org.com.dao.AccountDao;
import org.com.dao.TestDao;
import org.com.entity.Account;
import org.com.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;


import javax.annotation.Resource;
import java.util.List;

@Service
public class TestService {

    @Resource
    private TestDao testDao;
    @Resource
    private AccountDao accountDao;
    @Resource
    private AccountService accountService;

    public int search(){
       return testDao.search();
    }

    public void updateUser(User user){
        testDao.updateUser(user);
    }

    public List<User> userList(){
     return    testDao.userList();
    }

    public int updateUserPass(User user){
     return    testDao.updateUser(user);
    }

    @Transactional(rollbackFor = Exception.class)
    public String updatePassAndNumber(JSONObject object){
        System.out.println(object);
        User user = object.getObject("user", User.class);
        System.out.println(user);
        Account account = object.getObject("account", Account.class);
        System.out.println(account);
        try {
            testDao.updateUserPass(user);
            accountService.updateById(account);
        }catch (Exception ex){
            ex.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return "失败";
        }
        return "成功";
    }
}
