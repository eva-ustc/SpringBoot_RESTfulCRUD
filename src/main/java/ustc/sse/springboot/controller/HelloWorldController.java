package ustc.sse.springboot.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.jws.WebResult;
import java.util.Arrays;
import java.util.Map;

/**
 * Hello World Controller类
 */
@RestController
@RequestMapping("/test")
@Api(value = "/test", tags = "测试接口模块")
public class HelloWorldController {

    /**
     * hello world方法
     * @return
     */
/*    @ResponseBody
    @RequestMapping("/hello")
    public String hello(){
        return "Hello World Restful~";
    }*/
    @ApiOperation(value = "展示信息首页",notes = "展示信息首页")
    @GetMapping("/hello")
    public String hello(){
        return "Hello World Restful~";
    }

    /**
     * 测试EL表达式
     * @param map
     * @return
     */
 /*   @RequestMapping("/success")
    public String success(Map<String,Object> map){
        map.put("hello","<h1>你好</h1>");
        map.put("users", Arrays.asList("zhangsan","lisi","eva"));
        return "success";
    }*/
    @PostMapping("/success")
    @ApiImplicitParam(name = "user",value = "User",required = true,dataType = "User")
    @ApiOperation(value = "添加用户信息",notes = "添加用户信息")
    public String success(Map<String,Object> map){
        map.put("hello","<h1>你好</h1>");
        map.put("users", Arrays.asList("zhangsan","lisi","eva"));
        return "success";
    }
}
