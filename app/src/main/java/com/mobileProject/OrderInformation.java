package com.mobileProject;

public class OrderInformation {
    private int imgID;
    private String sellerFirstName;
    private String sellerLastName;
    private String bookName;
    private double price;
    private String desc;
    private String delivery;

    private int orderId;
    private int sellerId;
    private int userId;

    public OrderInformation(int imgID, String sellerFirstName, String sellerLastName, String bookName, double price, String desc, String delivery, int orderId, int sellerId, int userId) {
        this.imgID = imgID;
        this.sellerFirstName = sellerFirstName;
        this.sellerLastName = sellerLastName;
        this.bookName = bookName;
        this.price = price;
        this.desc = desc;
        this.delivery = delivery;
        this.orderId = orderId;
        this.sellerId = sellerId;
        this.userId = userId;
    }

    public int getImgID() {
        return imgID;
    }

    public void setImgID(int imgID) {
        this.imgID = imgID;
    }

    public String getSellerFirstName() {
        return sellerFirstName;
    }

    public void setSellerFirstName(String sellerFirstName) {
        this.sellerFirstName = sellerFirstName;
    }

    public String getSellerLastName() {
        return sellerLastName;
    }

    public void setSellerLastName(String sellerLastName) {
        this.sellerLastName = sellerLastName;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDelivery() {
        return delivery;
    }

    public void setDelivery(String delivery) {
        this.delivery = delivery;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getSellerId() {
        return sellerId;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
