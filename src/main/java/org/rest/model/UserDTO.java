package org.rest.model;

import com.sun.org.apache.xpath.internal.operations.Bool;

/**
 * Created by XiuYin.Cui on 2018/5/22.
 */
public class UserDTO {

    /**
     * 姓名
     */
    private String name;

    /**
     * 密码
     */
    private String password;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 是否通过认证
     */
    private Boolean valid;


    public UserDTO() {

    }

    public UserDTO(String name, String password, Integer age, Boolean valid) {
        this.name = name;
        this.password = password;
        this.age = age;
        this.valid = valid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Boolean getValid() {
        return valid;
    }

    public void setValid(Boolean valid) {
        this.valid = valid;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", age=" + age +
                ", valid=" + valid +
                '}';
    }
}
