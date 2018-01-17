package org.tacademy.homework4;

/**
 * Created by T on 2015-07-15.
 */
public class SMSItem {
    String mobile;
    String receivedDate;
    String contents;
    int imageId;

    public SMSItem() {

    }

    public SMSItem(String mobile, String receivedDate, String contents, int imageId) {
        this.mobile = mobile;
        this.receivedDate = receivedDate;
        this.contents = contents;
        this.imageId = imageId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getReceivedDate() {
        return receivedDate;
    }

    public void setReceivedDate(String receivedDate) {
        this.receivedDate = receivedDate;
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
