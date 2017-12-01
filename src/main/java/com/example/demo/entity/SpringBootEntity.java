package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by zhengyilong on 2017/11/30.
 */
@Entity
@Table(name = "boot1")
public class SpringBootEntity {

    @Id
    @GeneratedValue
    Long id;

    String name;

    String password;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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


    public SpringBootEntity(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public SpringBootEntity() {
        super();
    }

    @Override
    public String toString() {
        String re = "{\"id\":\"";
        re += this.id + "\",\"";
        re += "name\":\"" + this.name + "\",\"";
        re += "password\":\"" + this.password + "\"}";
        return re;
    }
}
