package org.tacademy.mylist;

/**
 * Created by T on 2015-07-14.
 */
public class SingerItem {
    String name;
    int age;
    String mobile;
    int imageId;

    public SingerItem() {

    }

    public SingerItem(String name, int age, String mobile, int imageId) {
        this.name = name;
        this.age = age;
        this.mobile = mobile;
        this.imageId = imageId;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
