package org.com.dao;

import org.apache.ibatis.annotations.Mapper;
import org.com.entity.Account;

@Mapper
public interface AccountDao {
    int updateById(Account account);
}
