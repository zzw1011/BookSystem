package com.zzw.book.service.impl;

import com.github.pagehelper.PageInfo;
import com.zzw.book.dao.BookDao;
import com.zzw.book.entity.BookEntity;
import com.zzw.base.model.PageQuery;
import com.zzw.book.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @创建者：zzw
 * @创建时间 2018/1/11 0011
 * @描述 图书service层实现
 */
@Service("bookServiceImpl")
public class BookServiceImpl implements BookService {

    @Autowired
    private BookDao<BookEntity> bookDao;

    @Override
    public int insert(BookEntity bookEntity) {
        return bookDao.insert(bookEntity);
    }

    @Override
    public int update(BookEntity bookEntity) {
        return bookDao.update(bookEntity);
    }

    @Override
    public int insertSelective(BookEntity bookEntity) {
        return bookDao.save("insertBookSelective",bookEntity);
    }

    @Override
    public int updateSelective(BookEntity bookEntity) {
        return bookDao.update("updateBookSelective",bookEntity);
    }

    @Override
    public int delete(Long id) {
        return bookDao.delete(id);
    }

    @Override
    public int delete(List<Long> ids) {
        return bookDao.delete(ids);
    }

    @Override
    public PageInfo<BookEntity> findPage(BookEntity bookEntity, PageQuery pageQuery) {
        return bookDao.findPage("findPageBook",bookEntity,pageQuery);
    }

    @Override
    public BookEntity getById(Long id) {
        return bookDao.get("getBookById",id);
    }
}
