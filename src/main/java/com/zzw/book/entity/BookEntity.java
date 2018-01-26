package com.zzw.book.entity;

import com.zzw.base.entity.BaseEntity;

import java.math.BigDecimal;

/**
 * @创建者：zzw
 * @创建时间 2018/1/11 0011
 * @描述 图书实体
 */
public class BookEntity extends BaseEntity {


    /*
    * 图书编号
    * */
    private String bookId;
    /*
    * 书名
    * */
    private String bookName;

    /*
    * 图书封面
    * */
    private String bookImg;

    /*
    * 作者
    * */
    private String bookAuthor;

    /*
    * 出版社
    * */
    private String bookPublishing;

    /*
    * 单价
    * */
    private BigDecimal bookMoney;

    /*
    * 图书类型
    * */
    private Long bookType;

    /*
    * 图书数量
    * */
    private Integer bookNum;

    /*
    * 图书简介
    * */
    private String bookDesc;

    /*
    * 图书类型，无需持久化对象
    * */
    private String bookTypeText;

    public String getBookId() {
        return bookId;
    }

    public void setBookId(final String bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(final String bookName) {
        this.bookName = bookName;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(final String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public String getBookPublishing() {
        return bookPublishing;
    }

    public void setBookPublishing(final String bookPublishing) {
        this.bookPublishing = bookPublishing;
    }

    public BigDecimal getBookMoney() {
        return bookMoney;
    }

    public void setBookMoney(final BigDecimal bookMoney) {
        this.bookMoney = bookMoney;
    }

    public Long getBookType() {
        return bookType;
    }

    public void setBookType(final Long bookType) {
        this.bookType = bookType;
    }

    public Integer getBookNum() {
        return bookNum;
    }

    public void setBookNum(final Integer bookNum) {
        this.bookNum = bookNum;
    }

    public String getBookDesc() {
        return bookDesc;
    }

    public void setBookDesc(final String bookDesc) {
        this.bookDesc = bookDesc;
    }

    public String getBookImg() {
        return bookImg;
    }

    public void setBookImg(final String bookImg) {
        this.bookImg = bookImg;
    }

    public String getBookTypeText() {
        return bookTypeText;
    }

    public void setBookTypeText(final String bookTypeText) {
        this.bookTypeText = bookTypeText;
    }
}
