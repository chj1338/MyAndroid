package com.example.p048293.homework5;

/**
 * Created by T on 2015-07-14.
 */
public class CustomerItem {
    String name;
    int age;
    String tel;
    String addr;

    public CustomerItem() {

    }

    public CustomerItem(String name, int age, String tel, String addr) {
        this.name = name;
        this.age = age;
        this.tel = tel;
        this.addr = addr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

}
