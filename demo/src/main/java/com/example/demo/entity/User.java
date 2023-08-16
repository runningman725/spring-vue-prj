package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

@Data
public class User {
    private int id;
    private String no;
    private String name;
    private String password;
    private int age;
    private int sex;
    private int roleId;
    private String phone;
    @TableField("isvalid")
    private String isValid;

}
