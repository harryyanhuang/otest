package org.com.controller;

//import org.com.service.TestService;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.exception.ExcelDataConvertException;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.com.entity.RespBean;
import org.com.entity.User;
import org.com.entity.UserExcel;
import org.com.listener.ExcelListener;
import org.com.service.TestService;
import org.com.service.UserService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class TestController {
    @Resource
    private TestService testService;
    @Resource
    UserService userService;

    @RequestMapping("/test")
    public int test(){
    return testService.search();
    }
    @RequestMapping("/test1")
    public String test1(){
        return "123456";
    }

    @GetMapping("/list")
    public List<User> userList(){
        return testService.userList();
    }


    @PostMapping("/user")
    public String add(@RequestBody String s){
        System.out.println("准备添加。。。"+s);
        return "添加成功";
    }

    @DeleteMapping("/user/{s}")
    public String delete(@PathVariable("s") String s){
        System.out.println("准备删除"+s);
        return "删除成功";
    }

    @PutMapping("/user")
    public String put(@RequestBody String s){
        System.out.println("准备修改"+s);
        return "修改成功";
    }

    @GetMapping("/user")
    public String get(@RequestParam Integer id,@RequestParam Integer page) throws InterruptedException {
        System.out.println("准备查询:"+id+" "+page);
        Thread.sleep(6000);
        return "查询成功";
    }


    @PostMapping("/addUser")
    public String addUser(@RequestParam Map<String,Object> user){
        System.out.println("准备添加。。。"+user.toString());
        return "添加成功";
    }

    @PostMapping("/upload")
    public RespBean uploadEXCEL(MultipartFile file){

    if(!file.isEmpty()){
        if(file.getSize()>3*1024*1024) return RespBean.result(0,"文件不能大于3M",null);
        System.out.println(file.getOriginalFilename());
        String substring = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
        if(substring.equals("xlsx")){
            try {
                EasyExcel.read(file.getInputStream(), UserExcel.class, new ExcelListener(userService))
                        .sheet()
                        .doRead();
            } catch (IOException e) {
                e.printStackTrace();
                return RespBean.result(0,"文件读取错误，亲联系管理员",null);
            }
        }else{
            return RespBean.result(0,"请上传表格文件",null);
        }
    }else {
        return RespBean.result(0,"请上传文件",null);
    }
    return RespBean.result(200,"表格数据上传成功",null);
    }

    @GetMapping("/down")
    public void downLoad(HttpServletResponse response) throws IOException {
        // 设置响应头=======分支提交测试
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        // 设置防止中文名乱码======分支提交2测试
        String filename = URLEncoder.encode("员工信息", "utf-8");
        // 文件下载方式(附件下载还是在当前浏览器打开)
        response.setHeader("Content-disposition", "attachment;filename=" + filename + ".xlsx");
        List<UserExcel> list=new LinkedList<>();
        for (int i = 0; i < 100; i++) {
            UserExcel userExcel = UserExcel.builder()
                    .userId(i)
                    .salary(Math.random() * 1000)
                    .gender(i % 2 == 0 ? "男" : "女")
                    .hireDate(new Date())
                    .username("用户" + i).build();
            list.add(userExcel);
        }
        EasyExcel.write(response.getOutputStream(),UserExcel.class)
                .sheet("用户信息")
                .doWrite(list);
    }

    @PostMapping("/Transactional")
    public String TransactionalTest(@RequestBody JSONObject object){
       return testService.updatePassAndNumber(object);
    }
}
