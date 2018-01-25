package com.zzw.book.service;

import com.github.pagehelper.PageInfo;
import com.zzw.book.entity.BookEntity;
import com.zzw.base.model.PageQuery;

import java.util.List;

/**
 * @创建者：zzw
 * @创建时间 2018/1/12 0011
 * @描述 图书service层接口
 */
public interface BookService {

    int insert(BookEntity bookEntity);

    int update(BookEntity bookEntity);

    int insertSelective(BookEntity bookEntity);

    int updateSelective(BookEntity bookEntity);

    int delete(Long id);

    int delete(List<Long> ids);

    /**
     * @描述 分页查询
     * @param bookEntity
     * @param pageQuery
     * @return PageInfo<BookEntity>
     */
    PageInfo<BookEntity> findPage(BookEntity bookEntity, PageQuery pageQuery);

    /**
     * @描述 根据ID查找图书
     * @param id
     * @return BookEntity
     */
    BookEntity getById(Long id);




}
