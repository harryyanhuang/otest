package org.com.dao;

import org.apache.ibatis.annotations.Mapper;
import org.com.entity.User;

import java.util.List;

@Mapper
public interface TestDao {
    int search();
    int updateUser(User user);

    List<User> userList();

    int updateUserPass(User user);
}
