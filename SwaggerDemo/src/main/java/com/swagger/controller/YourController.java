package com.swagger.controller;

import com.swagger.entity.User;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

//http://localhost:8080/swagger-ui.html

/**
 * API注解参数
 * value            接口备注（UI上不显示）
 * tags             接口名称，默认显示类名 tags = "tags测试"             tags : description（显示位置）
 * description      对api资源的描述       description = "swagger测试接口1"
 * authorizations   高级特性认证时配置 
 * hidden           配置为true 将在文档中隐藏
 */
@Api(description = "Swagger测试接口")
@RestController
@RequestMapping("test")
public class YourController {

    /**
     * @ApiOperation参数
     * value                未展开状态方法备注信息
     * notes                展开状态方法备注信息
     * authorizations       高级特性认证时配置
     * hidden               配置为true 将在文档中隐藏
     * response             返回的对象
     * responseContainer    这些对象是有效的 “List”, “Set” or “Map”.，其他无效
     * httpMethod           “GET”, “HEAD”, “POST”, “PUT”, “DELETE”, “OPTIONS” and “PATCH”
     * code                 http的状态码 默认 200
     * extensions           扩展属性
     */
    @ApiOperation(value = "test0接口",notes = "测试0接口",httpMethod = "POST",response = Map.class)
    @PostMapping(value = "test0")
    public Map<String,Object> test0(@ApiParam(name = "name",value = "用户名",required = true)@RequestParam String name){
        System.out.println("接收到请求，参数25："+name);
        Map hashMap = new HashMap();
        hashMap.put("name",name);
        return hashMap;
    }

    @ApiOperation(value = "test1接口",notes = "提示内容：这个接口需要怎么怎么样",httpMethod = "POST",response = User.class)
    @PostMapping("test1")
    //@ApiIgnore()//UI隐藏本接口
    public User test1(@ApiParam(name = "user",value = "用户信息",required = true) @RequestBody User user){
        System.out.println("接收到请求，参数："+user);
        return user;
    }


    /**
     * name–参数ming
     * value–参数说明
     * dataType–数据类型
     * paramType–参数类型
     * example–举例说明
     */
    @ApiOperation(value = "test2接口",notes = "测试2接口",httpMethod = "POST",response = Map.class)
    @PostMapping("test2")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username",value = "用户名",dataType = "String",paramType = "query",example = "username1"),
            @ApiImplicitParam(name = "password",value = "密码",dataType = "String",paramType = "query",example = "passowrd1")}
    )
    public Map<String,Object> test2(String username,String password){
        HashMap<String, Object> hashmap = new HashMap<>();
        hashmap.put("username",username);
        hashmap.put("password",password);
        return hashmap;
    }


}
