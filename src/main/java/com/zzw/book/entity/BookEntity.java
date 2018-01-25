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
    * 图书类型，无需持久化字段
    * */
    private String bookType;

    /*
    * 图书数量
    * */
    private Integer bookNum;

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public String getBookPublishing() {
        return bookPublishing;
    }

    public void setBookPublishing(String bookPublishing) {
        this.bookPublishing = bookPublishing;
    }

    public BigDecimal getBookMoney() {
        return bookMoney;
    }

    public void setBookMoney(BigDecimal bookMoney) {
        this.bookMoney = bookMoney;
    }

    public String getBookType() {
        return bookType;
    }

    public void setBookType(String bookType) {
        this.bookType = bookType;
    }

    public Integer getBookNum() {
        return bookNum;
    }

    public void setBookNum(Integer bookNum) {
        this.bookNum = bookNum;
    }
}
