package com.swagger.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * value–表示对象名
 * description–描述
 */
@ApiModel(value = "user",description = "用户对象")
public class User {

    /**
     * value–字段说明               （UI不显示）
     * name–重写属性名字            （UI不显示）
     * dataType–重写属性类型        （UI不显示）
     * required–是否必填
     * example–举例说明
     * hidden–隐藏
     */
    @ApiModelProperty(value = "用户id",example = "1")
    private int id;

    @ApiModelProperty(value = "用户名",example = "username1",required = true)
    private String username;

    @ApiModelProperty(value = "用户密码",example = "password1",required = true)
    private String password;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
