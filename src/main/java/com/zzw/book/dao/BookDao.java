package com.zzw.book.dao;

import com.zzw.base.dao.BaseDao;

import java.util.List;

/**
 * @创建者：zzw
 * @创建时间 2018/1/11 0011
 * @描述
 */
public interface BookDao<BookEntity> extends BaseDao<BookEntity> {

    int insert(BookEntity bookEntity);

    int update(BookEntity bookEntity);

    int delete(Long id);

    int delete(List<Long> ids);
}
