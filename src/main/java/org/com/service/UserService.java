package org.com.service;

import org.com.entity.UserExcel;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserService {
    // 批量增加保存
    public int saveList(List<UserExcel> list) {
        for (UserExcel userExcel : list) {
            System.out.println(userExcel);
        }
        return 1;
    }
}
