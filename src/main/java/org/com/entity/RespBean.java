package org.com.entity;

import lombok.Data;

@Data
public class RespBean {
    private Integer code;
    private String msg;
    private Object data;


     RespBean(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
    public static  RespBean result(Integer code,String msg,Object data){
      return new RespBean(code, msg, data);
    }
}
