package com.zzw.book.entity;

import com.zzw.base.entity.BaseEntity;

import java.math.BigDecimal;

/**
 * @创建者：zzw
 * @创建时间 2018/1/11 0011
 * @描述 图书实体
 */
public class BookEntity extends BaseEntity {


    private Long bookId;
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


}
