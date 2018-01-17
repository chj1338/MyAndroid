package org.tacademy.myreciever;

/**
 * Created by T on 2015-07-15.
 */
public class SMSItem {
    String mobile;
    String recievedDate;
    String contents;
    int imageId;

    public SMSItem() {

    }

    public SMSItem(String mobile, String recievedDate, String contents, int imageId) {
        this.mobile = mobile;
        this.recievedDate = recievedDate;
        this.contents = contents;
        this.imageId = imageId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getRecievedDate() {
        return recievedDate;
    }

    public void setRecievedDate(String recievedDate) {
        this.recievedDate = recievedDate;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}
