package org.com.service;

import org.com.dao.AccountDao;
import org.com.entity.Account;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AccountService {

    @Resource
    private AccountDao accountDao;

    public int updateById(Account account){
        if(account.getId()==2){
            throw new RuntimeException();
        }
        System.out.println("扣减数量："+accountDao.updateById(account));;
        if(account.getId()==3){
            throw new RuntimeException();
        }
        return 1;
    }
}
