package org.com.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class User {
    Integer id;
    String username;

    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    Date createtime;

    String password;

}
